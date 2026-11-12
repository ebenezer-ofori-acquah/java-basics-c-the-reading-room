/**
 * Represents an item available for lending, with an ID, title, type, and
 * optional current loan.
 * Provides an availability check and getters for the item's identifying
 * details.
 */
class LendingItem {
    private int itemId;
    private String title;
    private ItemType itemType;
    private Loan currentLoan;

    public LendingItem(int itemId, String title, ItemType itemType) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Item title cannot be null or empty.");
        }
        if (itemType == null) {
            throw new IllegalArgumentException("Item type cannot be null.");
        }

        this.itemId = itemId;
        this.title = title;
        this.itemType = itemType;
    }

    // returns true if the item is not currently on loan, false if it is on loan
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

    public Loan getCurrentLoan() {
        return currentLoan;
    }

    // checks if the item is available and assigns a loan to it; throws an exception if the item is already on loan
    public void assignLoan(Loan loan) {
        if (loan == null) {
            throw new IllegalArgumentException("Loan cannot be null.");
        }
        if (!isAvailable()) {
            throw new IllegalStateException(
                    "Item " + itemId + " ('" + title + "') is already on loan.");
        }
        currentLoan = loan;
    }

    public void releaseLoan() {
        if (currentLoan == null) {
            throw new IllegalStateException("Item '" + title + "' (ID: " + itemId + ") is not currently on loan.");
        }
        currentLoan.markAsReturned();
        this.currentLoan = null;
    }

}
