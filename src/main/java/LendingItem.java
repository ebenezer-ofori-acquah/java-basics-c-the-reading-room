/**
 * Represents an item available for lending, with an ID, title, type, and optional current loan.
 * Provides an availability check and getters for the item's identifying details.
 */
public class LendingItem {
    private int itemId;
    private String title;
    private ItemType itemType;
    private Loan currentLoan;

    public LendingItem(int itemId, String title, ItemType itemType){
        this.itemId = itemId;
        this.title = title;
        this.itemType = itemType;
    }

    //returns true if the item is not currently on loan, false if it is on loan
    public boolean isAvailable() {
        return currentLoan == null;
    }

    public int getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public ItemType getItemType() {
        return itemType;
    }

    //checks if the item is available and assigns a loan to it; throws an exception if the item is already on loan
    public void assignLoan(Loan loan){
        if (!isAvailable()) {
            throw new IllegalStateException("Item is already on loan.");
        }
        currentLoan = loan;
    }

    //checks if the item is currently on loan and releases it; throws an exception if the item is not on loan or if the provided loan does not match the current loan
    public void releaseLoan(Loan loan){
        if (currentLoan != loan){
            throw new IllegalStateException("This is not the item's current loan.");
        }
        currentLoan = null;
    }
    
}
