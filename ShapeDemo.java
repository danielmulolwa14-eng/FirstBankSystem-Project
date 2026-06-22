public class ShapeDemo {
    
    /**
     * Méthode pour afficher les aires (démontre le polymorphisme)
     * @param shapes Tableau de références Shape
     */
    public static void printAreas(Shape[] shapes) {
        System.out.println("\n=== AREAS OF ALL SHAPES ===");
        for (Shape shape : shapes) {
            // Vérification du null avant d'appeler des méthodes
            if (shape != null) {
                // Liaison dynamique (Dynamic binding) : Java appelle la bonne méthode getArea()
                System.out.println(shape.getClass().getSimpleName() + " area: " + 
                                 String.format("%.2f", shape.getArea()));
            }
        }
    }

    /**
     * Trouve la forme avec la plus grande aire dans le tableau
     */
    public static Shape largest(Shape[] shapes) {
        if (shapes == null || shapes.length == 0) return null;

        Shape largestShape = null;
        for (Shape s : shapes) {
            if (s != null) {
                if (largestShape == null || s.getArea() > largestShape.getArea()) {
                    largestShape = s;
                }
            }
        }
        return largestShape;
    }

    public static void main(String[] args) {
        // Le tableau est initialisé avec des valeurs null par défaut
        Shape[] shapes = new Shape[9];

        try {
            // CORRECTION 1 : Affecter chaque forme à un indice du tableau
            shapes[0] = new Circle("red", true, 5);
            shapes[1] = new Rectangle("blue", false, 4, 6);
            shapes[2] = new Triangle("green", true, 3, 4, 5);

            // Affichage de l'état initial des formes
            System.out.println("=== VALID SHAPES ===");
            for (Shape s : shapes) {
                if (s != null) System.out.println(s);
            }

            // Appel polymorphique
            printAreas(shapes);

            // Recherche de la plus grande forme
            Shape biggest = largest(shapes);
            System.out.println("\n=== LARGEST SHAPE ===");
            if (biggest != null) {
                System.out.println("The largest shape is: " + biggest);
            }

            // Test de redimensionnement (resize)
            System.out.println("\n=== RESIZING CIRCLE BY FACTOR 2 ===");
            // CORRECTION 3 : Appeler resize() sur l'élément [0], pas sur le tableau
            if (shapes[0] != null) {
                shapes[0].resize(2);
                System.out.println("After resize: " + shapes[0]);
            }

        } catch (InvalidShapeException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // Test de gestion des exceptions (Exception Handling)
        System.out.println("\n=== ATTEMPTING TO CREATE INVALID TRIANGLE ===");
        try {
            // Objet anonyme : déclenche l'exception sans créer de variable inutile
            new Triangle(1, 2, 10);
        } catch (InvalidShapeException e) {
            // Capture et affichage du message de l'exception personnalisée
            System.out.println("Caught exception: " + e.getMessage());
            System.out.println("Triangle creation was correctly rejected!");
        }
    }
}
