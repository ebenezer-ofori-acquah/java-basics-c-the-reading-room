
// A class representing a library system with members, items, and loans.
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members;
    private List<LendingItem> items;
    private List<Loan> loans;

    public Library() {
        this.members = new ArrayList<>();
        this.items = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void addItem(LendingItem item) {
        items.add(item);
    }

    // borrows an item for a member, checking availability and applying the
    // appropriate loan policy based on item type;
    // throws exceptions for unavailable items or unsupported item types
    public Loan borrowItem(Member member, LendingItem item) {
        if (!item.isAvailable()) {
            throw new IllegalStateException("Item is already on loan.");
        }
        LoanPolicy loanPolicy;

        switch (item.getItemType()) {
            case ItemType.BOOK -> loanPolicy = new BookLoanPolicy();
            case ItemType.AUDIO_KIT -> loanPolicy = new AudioKitLoanPolicy();
            default -> throw new IllegalArgumentException("Unsupported item type.");
        }

        int loanDays = loanPolicy.calculateLoanDays(member);
        Loan loan = new Loan(member.getMemberId(), member, item, loanDays);
        item.assignLoan(loan);
        loans.add(loan);
        return loan;
    }

    // returns an item, marking the loan as returned and releasing the item for
    // future loans;
    // throws an exception if the item is not currently on loan
    public void returnItem(LendingItem item) {
        if (item.isAvailable()) {
            throw new IllegalStateException("Item is not currently on loan.");
        }
        Loan loan = item.getCurrentLoan();
        item.releaseLoan(loan);
        loan.markAsReturned();
    }

    // returns a copy of the list of loans to prevent external modification
    public List<Loan> getLoans() {
        return List.copyOf(this.loans);
    }

}