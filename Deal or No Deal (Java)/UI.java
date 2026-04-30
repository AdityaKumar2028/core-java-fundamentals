import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Arrays;

public class UI {

    static final Color BG = new Color(245, 245, 245);
    static final Color SURFACE = Color.WHITE;
    static final Color BLACK = new Color(15, 15, 15);
    static final Color DIM = new Color(180, 180, 180);
    static final Font BOLD_FONT = new Font("SansSerif", Font.BOLD, 16);

    private Game game;
    private long lastOffer = 0;

    private JFrame window;
    private JButton[] caseButtons = new JButton[26];
    private JLabel[] prizeLabels = new JLabel[26];
    private JLabel statusLabel, offerAmtLbl;
    private JPanel bankerPanel;

    public UI() {
        game = new Game();
        buildWindow();
        refreshUI();
    }

    private void buildWindow() {
        window = new JFrame("Deal or No Deal");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1000, 650);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout(10, 10));
        window.getContentPane().setBackground(BG);

        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        statusLabel.setForeground(BLACK);
        statusLabel.setBorder(new EmptyBorder(15, 0, 5, 0));
        window.add(statusLabel, BorderLayout.NORTH);

        window.add(buildPrizeBoard(), BorderLayout.WEST);
        window.add(buildGamePanel(), BorderLayout.CENTER);
        window.setVisible(true);
    }

    private JPanel buildPrizeBoard() {
        JPanel grid = new JPanel(new GridLayout(26, 1, 2, 2));
        grid.setBackground(BG);
        grid.setBorder(new EmptyBorder(0, 15, 10, 10));

        double[] sorted = Game.ALL_PRIZES.clone();
        Arrays.sort(sorted);

        for (int i = 0; i < 26; i++) {
            prizeLabels[i] = new JLabel(Game.formatMoney(sorted[i]), SwingConstants.CENTER);
            prizeLabels[i].setFont(new Font("SansSerif", Font.BOLD, 12));
            prizeLabels[i].setOpaque(true);
            prizeLabels[i].setBorder(new LineBorder(BLACK, 1));
            prizeLabels[i].setName(String.valueOf(sorted[i]));
            grid.add(prizeLabels[i]);
        }
        return grid;
    }

    private JPanel buildGamePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BG);
        panel.setBorder(new EmptyBorder(0, 0, 15, 15));

        JPanel grid = new JPanel(new GridLayout(5, 6, 8, 8));
        grid.setBackground(BG);

        for (int i = 0; i < 26; i++) {
            final int num = i + 1;
            JButton btn = new JButton(String.valueOf(num));
            btn.setFont(new Font("SansSerif", Font.BOLD, 20));
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.addActionListener(e -> handleCaseClick(num));
            caseButtons[i] = btn;
            grid.add(btn);
        }

        bankerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        bankerPanel.setBackground(BLACK);

        JLabel bankerTitle = new JLabel("BANKER OFFER:");
        bankerTitle.setFont(BOLD_FONT);
        bankerTitle.setForeground(SURFACE);

        offerAmtLbl = new JLabel("$0");
        offerAmtLbl.setFont(new Font("SansSerif", Font.BOLD, 24));
        offerAmtLbl.setForeground(SURFACE);

        JButton dealBtn = new JButton("DEAL");
        JButton noDealBtn = new JButton("NO DEAL");
        dealBtn.addActionListener(e -> handleDecision(true));
        noDealBtn.addActionListener(e -> handleDecision(false));

        bankerPanel.add(bankerTitle);
        bankerPanel.add(offerAmtLbl);
        bankerPanel.add(dealBtn);
        bankerPanel.add(noDealBtn);
        bankerPanel.setVisible(false);

        panel.add(grid, BorderLayout.CENTER);
        panel.add(bankerPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void handleCaseClick(int num) {
        if (game.isGameOver() || bankerPanel.isVisible())
            return;

        JButton btn = caseButtons[num - 1];

        if (!game.isCasePicked()) {
            game.pickMyCase(num);
            btn.setBackground(BLACK);
            btn.setForeground(SURFACE);
            btn.setText("YOURS");
            btn.setEnabled(false);
            statusLabel
                    .setText("Round " + game.getCurrentRound() + " - Open " + game.getCasesLeftThisRound() + " cases.");
            return;
        }

        if (num == game.getMyCase())
            return;

        double prize = game.openCase(num);
        if (prize < 0)
            return;

        btn.setText(Game.formatMoney(prize));
        btn.setBackground(BG);
        btn.setForeground(DIM);
        btn.setBorder(new LineBorder(DIM, 1));
        btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btn.setEnabled(false);

        crossOffPrize(prize);

        if (game.isRoundOver()) {
            if (game.getUnopenedCount() == 0) {
                showEnd(false, 0); // Forced to open last case
            } else {
                lastOffer = Banker.calculateOffer(game);
                offerAmtLbl.setText(Game.formatMoney(lastOffer));
                bankerPanel.setVisible(true);
                statusLabel.setText("The Banker is calling...");
            }
        } else {
            statusLabel.setText(
                    "Round " + game.getCurrentRound() + " - Open " + game.getCasesLeftThisRound() + " more case(s).");
        }
    }

    private void handleDecision(boolean tookDeal) {
        if (tookDeal) {
            showEnd(true, lastOffer);
        } else {
            bankerPanel.setVisible(false);
            game.startNextRound();
            statusLabel
                    .setText("Round " + game.getCurrentRound() + " - Open " + game.getCasesLeftThisRound() + " cases.");
        }
    }

    private void showEnd(boolean tookDeal, long dealAmount) {
        game.setGameOver();
        double contents = game.getMyCasePrize();

        String title = tookDeal ? "DEAL!" : "NO DEAL!";
        String msg = tookDeal
                ? "You accepted " + Game.formatMoney(dealAmount) + "!\nYour original case held "
                        + Game.formatMoney(contents)
                : "You kept your case and won " + Game.formatMoney(contents) + "!";

        JOptionPane.showMessageDialog(window, msg, title, JOptionPane.INFORMATION_MESSAGE);

        game.init();
        refreshUI();
    }

    private void crossOffPrize(double prize) {
        String key = String.valueOf(prize);
        for (JLabel lbl : prizeLabels) {
            if (lbl.getName().equals(key)) {
                lbl.setForeground(DIM);
                lbl.setBackground(BG);
                lbl.setBorder(new LineBorder(DIM, 1));
                break;
            }
        }
    }

    private void refreshUI() {
        for (int i = 0; i < 26; i++) {
            caseButtons[i].setText(String.valueOf(i + 1));
            caseButtons[i].setBackground(SURFACE);
            caseButtons[i].setForeground(BLACK);
            caseButtons[i].setFont(new Font("SansSerif", Font.BOLD, 20));
            caseButtons[i].setBorder(new LineBorder(BLACK, 2));
            caseButtons[i].setEnabled(true);
        }
        for (JLabel lbl : prizeLabels) {
            lbl.setForeground(BLACK);
            lbl.setBackground(SURFACE);
            lbl.setBorder(new LineBorder(BLACK, 1));
        }
        bankerPanel.setVisible(false);
        statusLabel.setText("Select your lucky briefcase!");
    }
}