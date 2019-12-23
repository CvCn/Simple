public class Point {
    public float x;

    public float y;

    public float z;

    public float f;

    public int result;

    public Point(float x, float y, float z, float f) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.f = f;
        this.result = 4 * x + 1 * y + 2.3 * z + 110 >= 0 ? 1 : -1;
    }
}
