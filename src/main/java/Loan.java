public class Loan {
    private int loanId;
    private Member member;
    private LendingItem item;
    private int loandays;
    private LoanStatus status;

    public Loan(int loanId, Member member, LendingItem item, int loandays){
        this.loanId = loanId;
        this.member = member;
        this.item = item;
        this.loandays = loandays;
        this.status = LoanStatus.ACTIVE;
    }

    public int getLoanId() {
        return loanId;
    }

    public Member getMember() {
        return member;
    }

    public LendingItem getItem() {
        return item;
    }

    public int getLoandays() {
        return loandays;
    }

    public LoanStatus getStatus() {
        return status;
    }

    

}
