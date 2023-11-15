package seedu.address.model.stall;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.item.Item;
import seedu.address.model.review.Rating;
import seedu.address.model.stall.review.StallReview;

/**
 * Represents a Stall in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Stall {

    // Identity fields
    private final Name name;
    private final Location location;
    private final Menu menu;
    private StallReview stallReview;

    /**
     * Every field must be present and not null.
     */
    public Stall(Name name, Location location) {
        requireAllNonNull(name, location);
        this.name = name;
        this.location = location;
        this.menu = new Menu();
        this.stallReview = null;
    }

    /**
     * Constructor with menu and no review
     */
    public Stall(Name name, Location location, Menu menu) {
        requireAllNonNull(name, location, menu);
        this.name = name;
        this.location = location;
        this.menu = menu;
        this.stallReview = null;
    }

    /**
     * Constructor with review and no menu
     */
    public Stall(Name name, Location location, StallReview stallReview) {
        requireAllNonNull(name, location, stallReview);
        this.name = name;
        this.location = location;
        this.menu = new Menu();
        this.stallReview = stallReview;
    }

    /**
     * Constructor with menu and review
     */
    public Stall(Name name, Location location, Menu menu, StallReview stallReview) {
        requireAllNonNull(name, location, menu, stallReview);
        this.name = name;
        this.location = location;
        this.menu = menu;
        this.stallReview = stallReview;
    }

    public AveragePrice getAveragePrice() {
        return menu.getAveragePrice();
    }

    public Rating getRating() {
        if (stallReview == null) {
            return null;
        }
        return stallReview.getRating();
    }

    public String getAveragePriceString() {
        if (getAveragePrice() == null) {
            return "No items in menu yet.";
        } else {
            return getAveragePrice().toString();
        }
    }
    public Name getName() {
        return name;
    }

    public String getStallString() {
        return "Stall Name: " + name.toString();
    }

    public Location getLocation() {
        return location;
    }

    public String getStallStarRating() {
        if (stallReview == null) {
            return "No ratings yet.";
        } else {
            return stallReview.getStarRating();
        }
    }

    public String getStallDescription() {
        return stallReview.getDescriptionString();
    }

    public String getLocationString() {
        return "Location: " + location.toString();
    }

    public Menu getMenu() {
        return menu;
    }

    public ObservableList<Item> getMenuList() {
        return menu.getItemList();
    }

    public boolean hasStallReview() {
        return stallReview != null;
    }

    public StallReview getStallReview() {
        return stallReview;
    }

    public void setStallReview(StallReview stallReview) {
        requireAllNonNull(stallReview);
        this.stallReview = stallReview;
    }

    public String getMenuString() {
        String str = "Menu Items:" + '\n';
        for (int i = 0; i < this.getMenu().numOfItem(); i++) {
            Index index = Index.fromZeroBased(i);
            str = str + (i + 1) + ". " + this.getMenu().getItem(index).getNameString() + '\n';
        }
        return str;
    }

    /**
     * Returns true if both stalls have the same name and location.
     * This defines a weaker notion of equality between two stalls.
     */
    public boolean isSameStall(Stall otherStall) {
        if (otherStall == this) {
            return true;
        }

        return otherStall != null
                && otherStall.getName().equals(getName())
                && otherStall.getLocation().equals(getLocation());
    }

    /**
     * Returns true if both stalls have the same identity and data fields.
     * This defines a stronger notion of equality between two stalls.
     * @param item item to be checked
     * @return true if an item with the same identity as {@code item} exists in the menu.
     */
    public boolean hasItem(Item item) {
        return menu.hasItem(item);
    }

    /**
     * Adds an item to the menu.
     * The item must not already exist in the menu.
     * @param item item to be added
     */
    public void addItem(Item item) {
        menu.addItem(item);
    }

    /**
     * Deletes an item from the menu.
     * The item must already exist in the menu.
     * @param itemIndex index of item to delete
     */
    public void deleteItem(Index itemIndex) {
        menu.removeItem(itemIndex);

    }

    /**
     * Gets an item from the menu.
     *
     * @param itemIndex index of item to get
     * @return item at index
     */
    public Item getItem(Index itemIndex) {
        return menu.getItem(itemIndex);
    }

    public void deleteReview() {
        this.stallReview = null;
    }

    public String getStallReviewString() {
        if (this.stallReview != null) {
            return stallReview.getRating().starRating
                + stallReview.getDescription().description;
        } else {
            return "No review added.";
        }
    }


    /**
     * Returns true if both stalls have the same identity and data fields.
     * This defines a stronger notion of equality between two stalls.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Stall)) {
            return false;
        }

        Stall otherStall = (Stall) other;
        return name.equals(otherStall.name)
                && location.equals(otherStall.location)
                && menu.equals(otherStall.menu);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, location);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("location", location)
                .add("menu", menu)
                .add("stallReview", stallReview)
                .toString();
    }
}
