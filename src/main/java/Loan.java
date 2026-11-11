/**
 * Records a member borrowing an item, including the loan ID, duration, and status.
 * The constructor starts each loan as active; getters expose its details.
 */
public class Loan {
    private int loanId;
    private Member member;
    private LendingItem item;
    private int loanDays;
    private LoanStatus status;

    public Loan(int loanId, Member member, LendingItem item, int loanDays){
        this.loanId = loanId;
        this.member = member;
        this.item = item;
        this.loanDays = loanDays;
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

    public int getLoanDays() {
        return loanDays;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void markAsReturned(){
        this.status = LoanStatus.RETURNED;
    }

}
