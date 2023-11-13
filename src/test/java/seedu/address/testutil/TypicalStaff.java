package seedu.address.testutil;


import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Staff;

/**
 * A utility class containing a list of {@code Staff} objects to be used in tests.
 */
public class TypicalStaff {

    public static final Staff ALICE = new StaffBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withAffiliations("Benson Meier")
            .withAffiliationHistory("Thomas Mink", "Benson Meier").build();
    public static final Staff BENSON = new StaffBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withAffiliationHistory("Alice Menti", "Bonas Kurz").build();
    public static final Staff CARL = new StaffBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withShiftDays(new HashSet<>(Set.of(3))).build();
    public static final Staff DANIEL = new StaffBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").build();
    public static final Staff ELLE = new StaffBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withShiftDays(new HashSet<>(Set.of(3))).build();
    public static final Staff FIONA = new StaffBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withShiftDays(new HashSet<>(Set.of(3))).build();
    public static final Staff GEORGE = new StaffBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Staff HOON = new StaffBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Staff IDA = new StaffBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Staff's details found in {@code CommandTestUtil}
    public static final Staff AMY = new StaffBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAffiliations(VALID_AFFILIATION_BOB)
            .withAffiliationHistory(VALID_AFFILIATION_BOB).build();
    public static final Staff BOB = new StaffBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAffiliations(VALID_AFFILIATION_AMY, VALID_AFFILIATION_BOB)
            .withAffiliationHistory(VALID_AFFILIATION_AMY, VALID_AFFILIATION_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStaff() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical staff.
     */
    public static AddressBook getTypicalStaffAddressBook() {
        AddressBook ab = new AddressBook();
        for (Staff staff : getTypicalStaff()) {
            ab.addPerson(staff);
        }
        return ab;
    }

    public static List<Staff> getTypicalStaff() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
