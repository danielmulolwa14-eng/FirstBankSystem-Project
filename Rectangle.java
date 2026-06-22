public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) throws InvalidShapeException {
        super();
        if (width <= 0 || height <= 0) {
            throw new InvalidShapeException("Width and height must be positive!");
        }
        this.width = width;
        this.height = height;
    }

    public Rectangle(String color, boolean filled, double width, double height) throws InvalidShapeException {
        super(color, filled);
        if (width <= 0 || height <= 0) {
            throw new InvalidShapeException("Width and height must be positive!");
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void resize(double factor) throws InvalidShapeException {
        if (factor <= 0) {
            throw new InvalidShapeException("Resize factor must be positive!");
        }
        this.width = width * factor;
        this.height = height * factor;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", color='" + color + '\'' +
                ", filled=" + filled +
                ", area=" + String.format("%.2f", getArea()) +
                '}';
    }
}

