package seedu.address.testutil;

import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_ADDRESS_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_ADDRESS_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_NAME_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_NAME_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PHONE_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PHONE_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PRICE_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PRICE_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_TAG_BIG;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_TAG_SQUARE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PropertyBook;
import seedu.address.model.property.Property;

/**
 * A utility class containing a list of {@code Customer} objects to be used in tests.
 */
public class TypicalProperties {

    public static final Property AQUAVISTA = new PropertyBuilder().withName("Aquavista")
            .withPrice("123456").withAddress("123 Orchid Lane, Singapore 456789")
            .withPhone("94351253")
            .withTags("pink").build();
    public static final Property SKYVISTA = new PropertyBuilder().withName("Skyvista")
            .withPrice("8880000")
            .withAddress("456 Sapphire Avenue, Singapore 987654").withPhone("98765432")
            .withTags("square", "garden").build();
    public static final Property HORIZONVIEW = new PropertyBuilder().withName("Horizonview").withPhone("95352563")
            .withAddress("789 Palm Grove Road, Singapore 321012").withPrice("400000").build();
    public static final Property LUXELOFT = new PropertyBuilder().withName("Luxeloft").withPhone("87652533")
            .withAddress("234 Amber Crescent, Singapore 567890").withPrice("100000").withTags("garage").build();
    public static final Property RIVERIA = new PropertyBuilder().withName("Riveria").withPhone("94821224")
            .withAddress("567 Maple Lane, Singapore 109876").withPrice("4000000").build();
    public static final Property AZURE = new PropertyBuilder().withName("Azure").withPhone("94821427")
            .withAddress("101 Radiant Lane, Singapore 123456").withPrice("874000").build();
    public static final Property TRANQUILIS = new PropertyBuilder().withName("Tranquilis").withPhone("94812442")
            .withAddress("202 Shoreline Street, Singapore 654321").withPrice("321950").build();

    // Manually added
    public static final Property ASCEND = new PropertyBuilder().withName("Ascend").withPhone("84821424")
            .withAddress("909 Skyline Boulevard, Singapore 789123").withPrice("4321000").build();
    public static final Property SPECTRA = new PropertyBuilder().withName("Spectra").withPhone("84821131")
            .withAddress("808 Colorful Street, Singapore 456012").withPrice("500000").build();

    // Manually added - Customer's details found in {@code CommandTestUtil}
    public static final Property AQUAVIEW = new PropertyBuilder().withName(VALID_NAME_AQUAVIEW)
            .withPhone(VALID_PHONE_AQUAVIEW).withAddress(VALID_ADDRESS_AQUAVIEW).withPrice(VALID_PRICE_AQUAVIEW)
            .withTags(VALID_TAG_SQUARE).build();
    public static final Property SKYVIEW = new PropertyBuilder().withName(VALID_NAME_SKYVIEW)
            .withPhone(VALID_PHONE_SKYVIEW).withAddress(VALID_ADDRESS_SKYVIEW).withPrice(VALID_PRICE_SKYVIEW)
            .withTags(VALID_TAG_BIG, VALID_TAG_SQUARE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalProperties() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical customers.
     */
    public static PropertyBook getTypicalPropertyBook() {
        PropertyBook pb = new PropertyBook();
        for (Property property : getTypicalProperties()) {
            pb.addProperty(property);
        }
        return pb;
    }

    public static List<Property> getTypicalProperties() {
        return new ArrayList<>(Arrays.asList(AQUAVISTA, SKYVISTA, HORIZONVIEW, LUXELOFT, RIVERIA, AZURE, TRANQUILIS));
    }
}
