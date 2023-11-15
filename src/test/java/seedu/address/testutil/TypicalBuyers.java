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

import seedu.address.model.displayable.buyer.Buyer;

/**
 * A utility class containing a list of {@code Buyer} objects to be used in tests.
 */
public class TypicalBuyers {

    public static final Buyer ALICE = new BuyerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withInfo("Loves views")
            .withTags("friends").build();
    public static final Buyer BENSON = new BuyerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withInfo("Loves views")
            .withTags("owesMoney", "friends").build();
    public static final Buyer CARL = new BuyerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withInfo("Loves views").build();
    public static final Buyer DANIEL = new BuyerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withInfo("Loves views")
            .withTags("friends").build();
    public static final Buyer ELLE = new BuyerBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withInfo("Loves views")
            .build();
    public static final Buyer FIONA = new BuyerBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withInfo("Loves views").build();
    public static final Buyer GEORGE = new BuyerBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withInfo("Loves views").build();

    // Manually added
    public static final Buyer HOON = new BuyerBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withInfo("Loves views").build();
    public static final Buyer IDA = new BuyerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withInfo("Loves views").build();

    // Manually added - Buyer's details found in {@code CommandTestUtil}
    public static final Buyer AMY = new BuyerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withInfo("Loves views")
            .withTags(VALID_TAG_FRIEND).build();
    public static final Buyer BOB = new BuyerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withInfo("Loves views")
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBuyers() {} // prevents instantiation

    public static List<Buyer> getTypicalBuyers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
