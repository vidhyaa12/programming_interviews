package basic;

public class Box {
    private int len;
    private int breadth;
    private int height;

    public Box(int len, int breadth, int height) {
        this.len = len;
        this.breadth = breadth;
        this.height = height;
    }

    public int getLen() {
        return len;
    }

    public int getBreadth() {
        return breadth;
    }

    public int getHeight() {
        return height;
    }
}
