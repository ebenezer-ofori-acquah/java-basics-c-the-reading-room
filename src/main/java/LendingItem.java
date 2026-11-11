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

    
}
