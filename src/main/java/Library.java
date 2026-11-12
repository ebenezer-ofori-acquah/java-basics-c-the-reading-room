import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Coordinates the borrowing and returning of items, manages members, and applies loan policies based on item types.
public class Library {
    private List<Member> members = new ArrayList<>();
    private List<LendingItem> items = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();
    private final Map<ItemType, LoanPolicy> loanPolicies = new HashMap<>();
    private int nextLoanId = 1;

    // Initializes the library with default loan policies for books and audio kits.
    public Library() {
        registerLoanPolicy(ItemType.BOOK, new BookLoanPolicy());
        registerLoanPolicy(ItemType.AUDIO_KIT, new AudioKitLoanPolicy());
    }

    public void registerMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null.");
        }
        if (members.contains(member)){
            throw new IllegalArgumentException(member.getName() + " is already registered.");
        }
        members.add(member);
    }

    public void addItem(LendingItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (items.contains(item)) {
            throw new IllegalArgumentException(itemDescription(item) + " is already in the inventory.");
        }
        items.add(item);
    }

    public void registerLoanPolicy(ItemType itemType, LoanPolicy loanPolicy) {
        if (itemType == null || loanPolicy == null) {
            throw new IllegalArgumentException("Item type and loan policy must not be null.");
        }
        loanPolicies.put(itemType, loanPolicy);
    }

    // borrows an item for a member, checking availability and applying the appropriate loan policy based on item type;
    // throws exceptions for unavailable items or unsupported item types
    public Loan borrowItem(Member member, LendingItem item) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null.");
        }
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (!item.isAvailable()) {
            throw new IllegalStateException(itemDescription(item) + " is already on loan.");
        }
        LoanPolicy loanPolicy = loanPolicies.get(item.getItemType());

        if (loanPolicy == null) {
            throw new IllegalArgumentException("Unsupported item type.");
        }
        int loanDays = loanPolicy.calculateLoanDays(member);
        Loan loan = new Loan(nextLoanId++, member, item, loanDays);
        item.assignLoan(loan);
        loans.add(loan);   
        return loan;
    }

    // returns an item, marking the loan as returned and releasing the item for future loans. Throws an exception if the item is not currently on loan.
    public void returnItem(LendingItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (item.isAvailable()) {
            throw new IllegalStateException(itemDescription(item) + " is not currently on loan.");
        }
        Loan loan = item.getCurrentLoan();

        if (loan != null) {
            loan.markAsReturned();
        } 
    
        item.releaseLoan();
    }

    // returns a copy of the list of loans to prevent external modification
    public List<Loan> getLoans() {
        return List.copyOf(this.loans);
    }

    private String itemDescription(LendingItem item) {
        return "Item " + item.getItemId() + " ('" + item.getTitle() + "')";
    }
};