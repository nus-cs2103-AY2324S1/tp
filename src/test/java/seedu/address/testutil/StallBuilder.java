package seedu.address.testutil;

import static seedu.address.testutil.TypicalItems.CHICKEN_RICE;
import static seedu.address.testutil.TypicalItems.NASI_LEMAK;
import static seedu.address.testutil.TypicalStallReviews.STALL_REVIEW_1;

import seedu.address.model.item.Item;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Menu;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;
import seedu.address.model.stall.review.StallReview;


/**
 * A utility class to help with building Stall objects.
 */
public class StallBuilder {

    public static final String DEFAULT_NAME = "Chicken Rice";
    public static final String DEFAULT_LOCATION = "Deck";
    public static final StallReview VALID_STALL_REVIEW_1 = STALL_REVIEW_1;
    private static final Item VALID_ITEM_1 = CHICKEN_RICE;
    private static final Item VALID_ITEM_2 = NASI_LEMAK;
    private static final Menu DEFAULT_MENU = null;
    public static final Menu VALID_MENU_1 = new MenuBuilder()
            .withItem(VALID_ITEM_1).withItem(VALID_ITEM_2).build();
    private static final StallReview DEFAULT_STALL_REVIEW = null;
    private Name name;
    private Location location;
    private Menu menu;
    private StallReview stallReview;


    /**
     * Creates a {@code StallBuilder} with the default details.
     */
    public StallBuilder() {
        name = new Name(DEFAULT_NAME);
        location = new Location(DEFAULT_LOCATION);
        menu = DEFAULT_MENU;
        stallReview = DEFAULT_STALL_REVIEW;
    }

    /**
     * Initializes the StallBuilder with the data of {@code stallToCopy}.
     */
    public StallBuilder(Stall stallToCopy) {
        name = stallToCopy.getName();
        location = stallToCopy.getLocation();
        menu = stallToCopy.getMenu();
        stallReview = stallToCopy.getStallReview();
    }

    /**
     * Sets the {@code Name} of the {@code Stall} that we are building.
     */
    public StallBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Stall} that we are building.
     */
    public StallBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code Menu} of the {@code Stall} that we are building.
     */
    public StallBuilder withMenu(Menu menu) {
        this.menu = menu;
        return this;
    }

    /**
     * Sets the {@code StallReview} of the {@code Stall} that we are building.
     */
    public StallBuilder withStallReview(StallReview stallReview) {
        this.stallReview = stallReview;
        return this;
    }

    /**
     * Builds a stall with the given parameters.
     */
    public Stall build() {
        if (stallReview != null && menu != null) {
            return new Stall(name, location, menu, stallReview);
        } else if (stallReview != null) {
            return new Stall(name, location, stallReview);
        } else if (menu != null) {
            return new Stall(name, location, menu);
        }
        return new Stall(name, location);
    }
}
