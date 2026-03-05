public class thisKeyword {
    int x;

    thisKeyword() {
        System.out.println(this);
        System.out.println(this.x);
    }

    public static void main(String[] args) {
        thisKeyword obj1 = new thisKeyword();
        thisKeyword obj2 = new thisKeyword();
    }
}
