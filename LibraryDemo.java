public class LibraryDemo {
    public static void main(String[] args) {
        // Créer la librairie
        Library library = new Library(10, 10);

        // Ajouter des livres
        Book book1 = new Book("978-0134685991", "Effective Java");
        Book book2 = new Book("978-0596007928", "Head First Design Patterns", "Freeman & Freeman");
        Book book3 = new Book("978-0135957059", "Clean Code", "Robert C. Martin");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Enregistrer des membres
        Member member1 = new Member("M001", "Alice Johnson");
        Member member2 = new Member("M002", "Bob Smith");

        library.registerMember(member1);
        library.registerMember(member2);

        // État initial
        System.out.println("\n--- INITIAL STATE ---");
        library.printState();

        // Test 1: Emprunter un livre (OK)
        System.out.println("--- TEST 1: Lending book1 to Alice ---");
        library.lendBook("M001", "Effective Java");

        // Test 2: Emprunter le même livre à quelqu'un d'autre (DOIT ÉCHOUER)
        System.out.println("\n--- TEST 2: Attempting to lend same book to Bob (should fail) ---");
        library.lendBook("M002", "Effective Java");

        // Test 3: Emprunter un autre livre (OK)
        System.out.println("\n--- TEST 3: Lending book2 to Bob ---");
        library.lendBook("M002", "Head First Design Patterns");

        // État après les emprunts
        System.out.println("\n--- STATE AFTER LENDING ---");
        library.printState();

        // Test 4: Retourner un livre
        System.out.println("\n--- TEST 4: Bob returns book2 ---");
        library.returnBook("Head First Design Patterns");

        // État final
        System.out.println("\n--- FINAL STATE ---");
        library.printState();
    }
}

