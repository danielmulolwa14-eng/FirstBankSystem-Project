public class Circle extends Shape {
    private double radius;

    public Circle(double radius) throws InvalidShapeException {
        super();
        if (radius <= 0) {
            throw new InvalidShapeException("Radius must be positive!");
        }
        this.radius = radius;
    }

    public Circle(String color, boolean filled, double radius) throws InvalidShapeException {
        super(color, filled);
        if (radius <= 0) {
            throw new InvalidShapeException("Radius must be positive!");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void resize(double factor) throws InvalidShapeException {
        if (factor <= 0) {
            throw new InvalidShapeException("Resize factor must be positive!");
        }
        this.radius = radius * factor;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", color='" + color + '\'' +
                ", filled=" + filled +
                ", area=" + String.format("%.2f", getArea()) +
                '}';
    }
}

