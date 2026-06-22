public class Triangle extends Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) throws InvalidShapeException {
        super();
        if (!isValidTriangle(sideA, sideB, sideC)) {
            throw new InvalidShapeException("Invalid triangle: sides must be positive and satisfy triangle inequality!");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public Triangle(String color, boolean filled, double sideA, double sideB, double sideC) throws InvalidShapeException {
        super(color, filled);
        if (!isValidTriangle(sideA, sideB, sideC)) {
            throw new InvalidShapeException("Invalid triangle: sides must be positive and satisfy triangle inequality!");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    // Vérifier l'inégalité triangulaire
    private boolean isValidTriangle(double a, double b, double c) {
        return (a > 0 && b > 0 && c > 0) &&
               (a + b > c) && (b + c > a) && (a + c > b);
    }

    @Override
    public double getArea() {
        // Formule de Heron
        double s = (sideA + sideB + sideC) / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public void resize(double factor) throws InvalidShapeException {
        if (factor <= 0) {
            throw new InvalidShapeException("Resize factor must be positive!");
        }
        this.sideA = sideA * factor;
        this.sideB = sideB * factor;
        this.sideC = sideC * factor;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "sideA=" + sideA +
                ", sideB=" + sideB +
                ", sideC=" + sideC +
                ", color='" + color + '\'' +
                ", filled=" + filled +
                ", area=" + String.format("%.2f", getArea()) +
                '}';
    }
}

