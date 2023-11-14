package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BIG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SQUARE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.customer.Customer;

/**
 * A utility class containing a list of {@code Customer} objects to be used in tests.
 */
public class TypicalCustomers {

    public static final Customer ALICE = new CustomerBuilder().withName("Alice Pauline")
            .withBudget("123456").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("pink").build();
    public static final Customer BENSON = new CustomerBuilder().withName("Benson Meier")
            .withBudget("8880000")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("square", "garden").build();
    public static final Customer CARL = new CustomerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withBudget("40000").build();
    public static final Customer DANIEL = new CustomerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withBudget("10000").withTags("garage").build();
    public static final Customer ELLE = new CustomerBuilder().withName("Elle Meyer").withPhone("94821224")
            .withEmail("werner@example.com").withBudget("4000000").build();
    public static final Customer FIONA = new CustomerBuilder().withName("Fiona Kunz").withPhone("94821427")
            .withEmail("lydia@example.com").withBudget("874000").build();
    public static final Customer GEORGE = new CustomerBuilder().withName("George Best").withPhone("94821442")
            .withEmail("anna@example.com").withBudget("321950").build();

    // Manually added
    public static final Customer HOON = new CustomerBuilder().withName("Hoon Meier").withPhone("84821424")
            .withEmail("stefan@example.com").withBudget("4321000").build();
    public static final Customer IDA = new CustomerBuilder().withName("Ida Mueller").withPhone("84821131")
            .withEmail("hans@example.com").withBudget("500000").build();

    // Manually added - Customer's details found in {@code CommandTestUtil}
    public static final Customer AMY = new CustomerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withBudget(VALID_BUDGET_AMY).withTags(VALID_TAG_SQUARE).build();
    public static final Customer BOB = new CustomerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withBudget(VALID_BUDGET_BOB).withTags(VALID_TAG_BIG, VALID_TAG_SQUARE)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCustomers() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical customers.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Customer customer : getTypicalCustomers()) {
            ab.addCustomer(customer);
        }
        return ab;
    }

    public static List<Customer> getTypicalCustomers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
