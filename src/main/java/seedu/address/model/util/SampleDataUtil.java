package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Price;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Menu;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Stall[] getSampleStalls() {
        Menu japaneseMenu = new Menu();
        Item[] japaneseItems = new Item[] {
            new Item(new ItemName("Chicken Katsu Curry"), new Price("5.60")),
            new Item(new ItemName("Chicken and Fish Don"), new Price("5.60")),
            new Item(new ItemName("Chicken and Ebi Don"), new Price("5.60")),
            new Item(new ItemName("Chicken Don"), new Price("4.60")),
            new Item(new ItemName("Ebi Don"), new Price("4.60")),
        };

        for (Item item : japaneseItems) {
            japaneseMenu.addItem(item);
        }
        return new Stall[] {
            new Stall(new Name("Japanese"), new Location("Deck"), japaneseMenu),
            new Stall(new Name("Noodles"), new Location("Deck")),
            new Stall(new Name("Western"), new Location("Techno Edge")),
            new Stall(new Name("Korean"), new Location("Frontier")),
            new Stall(new Name("Economical Rice"), new Location("Techno Edge")),
            new Stall(new Name("Thai"), new Location("Deck"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Stall sampleStall : getSampleStalls()) {
            sampleAb.addStall(sampleStall);
        }
        return sampleAb;
    }

}
