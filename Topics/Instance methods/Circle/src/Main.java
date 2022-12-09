class Circle {

    double radius;

    // write methods here
    public double getLength() {
        return 2 * Math.PI * radius;
    }

    public double getArea() {
        return Math.pow(radius, 2) * Math.PI;
    }
}