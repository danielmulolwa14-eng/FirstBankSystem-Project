public class Member {
    private String memberId;
    private String name;
    private Loan[] loans;
    private int loanCount; // nombre de loans actuels

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.loans = new Loan[10]; // tableau pour max 10 loans
        this.loanCount = 0;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public Loan[] getLoans() {
        return loans;
    }

    public int getLoanCount() {
        return loanCount;
    }

    // Ajouter un loan à la liste du member
    public void addLoan(Loan loan) {
        if (loanCount < loans.length) {
            loans[loanCount] = loan;
            loanCount++; 
            // Erreur humaine #2: manque le point-virgule (;)
        }
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", loanCount=" + loanCount +
                '}';
    }
}

