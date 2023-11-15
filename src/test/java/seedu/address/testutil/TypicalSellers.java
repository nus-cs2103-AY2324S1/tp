package seedu.address.testutil;


import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.displayable.seller.Seller;

/**
 * A set of typical sellers.
 */
public class TypicalSellers {
    public static final Seller SALICE = new SellerBuilder().withName("SAlice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withSellingAddress("Selling address example")
            .withInfo("Has Good Views").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").withSellingAddress("Selling address example")
            .withInfo("Has Good Views").build();
    public static final Seller SBENSON = new SellerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withSellingAddress("Selling address example")
            .withInfo("Has Good Views").build();
    public static final Seller SCARL = new SellerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withSellingAddress("Selling address example")
            .withInfo("Has Good Views").build();
    public static final Seller SDANIEL = new SellerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withSellingAddress("Selling address example").withInfo("Has Good Views").build();
    public static final Seller SELLE = new SellerBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withSellingAddress("Selling address example")
            .withInfo("Has Good Views").build();
    public static final Seller SFIONA = new SellerBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withSellingAddress("Selling address example")
            .withInfo("Has Good Views").build();
    public static final Seller SGEORGE = new SellerBuilder().withName("SGeorge Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withSellingAddress("Selling address example")
            .withInfo("Has Good Views").build();

    // Manually added
    public static final Seller SHOON = new SellerBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withSellingAddress("Selling address example")
            .withInfo("Has Good Views").build();
    public static final Seller SIDA = new SellerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withSellingAddress("Selling address example")
            .withInfo("Has Good Views").build();

    // Manually added - Seller's details found in {@code CommandTestUtil}
    public static final Seller SAMY = new SellerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withSellingAddress("Selling address example").withInfo("Has Good Views").build();
    public static final Seller SBOB = new SellerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withSellingAddress("Selling address example").withInfo("Has Good Views").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalSellers() {} // prevents instantiation


    public static List<Seller> getTypicalSellers() {
        return new ArrayList<>(Arrays.asList(SALICE, SBENSON, SCARL, SDANIEL, SELLE, SFIONA, SGEORGE));
    }
}
