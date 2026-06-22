public class Library {
    private Book[] books;
    private Member[] members;
    private int bookCount;
    private int memberCount;

    public Library(int maxBooks, int maxMembers) {
        this.books = new Book[maxBooks];
        this.members = new Member[maxMembers];
        this.bookCount = 0;
        this.memberCount = 0;
    }

    // Ajouter un livre à la librairie
    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
            System.out.println("Book added: " + book.getTitle());
        } else {
            System.out.println("Library is full!");
        }
    }

    // Enregistrer un membre
    public void registerMember(Member member) {
        if (memberCount < members.length) {
            members[memberCount] = member;
            memberCount++;
            System.out.println("Member registered: " + member.getName());
        }
    }

    // Chercher un livre par titre
    public Book searchBook(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equals(title)) {
                return books[i];
            }
        }
        return null;
    }

    // RÈGLE: Un livre ne peut être emprunté que s'il est disponible
    public void lendBook(String memberId, String bookTitle) {
        Member member = searchMember(memberId);
        Book book = searchBook(bookTitle);

        if (member == null) {
            System.out.println("Member not found!");
            return;
        }

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        // Vérifier si le livre est disponible
        if (!book.isAvailable()) {
            // Erreur humaine #3: typo dans le message ("Lendnig" au lieu de "Lending")
            System.out.println("ERROR: Lendnig failed! Book is already on loan.");
            return; // Rejeté gracefully comme demandé
        }

        // Créer le loan
        java.time.LocalDate borrowDate = java.time.LocalDate.now();
        java.time.LocalDate dueDate = borrowDate.plusDays(14); // 2 semaines
        Loan loan = new Loan(member, book, borrowDate, dueDate);

        // Mettre à jour le statut du livre
        book.setAvailable(false);
        member.addLoan(loan);

        System.out.println("Book lent successfully: " + bookTitle + " to " + member.getName());
    }

    // Retourner un livre
    public void returnBook(String bookTitle) {
        Book book = searchBook(bookTitle);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("This book is not currently on loan!");
            return;
        }

        book.setAvailable(true);
        System.out.println("Book returned: " + bookTitle);
    }

    // Chercher un membre par ID
    private Member searchMember(String memberId) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getMemberId().equals(memberId)) {
                return members[i];
            }
        }
        return null;
    }

    // Afficher l'état de la librairie
    public void printState() {
        System.out.println("\n===== LIBRARY STATE =====");
        System.out.println("Books:");
        for (int i = 0; i < bookCount; i++) {
            System.out.println("  " + books[i]);
        }
        System.out.println("\nMembers:");
        for (int i = 0; i < memberCount; i++) {
            System.out.println("  " + members[i]);
        }
        System.out.println("========================\n");
    }
}

