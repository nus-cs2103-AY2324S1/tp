package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALIZATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALIZATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YOE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YOE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.dentist.Dentist;

/**
 * A utility class containing a list of {@code Dentist} objects to be used in tests.
 */
public class TypicalDentists {

    public static final Dentist DENTIST_ALICE = new DentistBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("99999999")
            .withSpecialization("ORTHODONTICS")
            .withYoe("5")
            .withTags("Professional")
            .build();
    public static final Dentist DENTIST_BENSON = new DentistBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("88888888")
            .withSpecialization("ENDODONTICS")
            .withYoe("6")
            .withTags("Professional", "Europe")
            .build();
    public static final Dentist DENTIST_CARL = new DentistBuilder().withName("Carl Kurz")
            .withPhone("11111111")
            .withEmail("heinz@example.com")
            .withSpecialization("PAEDIATRIC DENTISTRY")
            .withYoe("7")
            .withAddress("wall street")
            .build();
    public static final Dentist DENTIST_DANIEL = new DentistBuilder().withName("Daniel Meier")
            .withPhone("22222222")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withSpecialization("PERIODONTICS")
            .withYoe("5").withTags("America")
            .build();
    public static final Dentist DENTIST_ELLE = new DentistBuilder().withName("Elle Meyer")
            .withPhone("33333333")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withSpecialization("PROSTHODONTICS")
            .withYoe("5")
            .withTags("London", "France")
            .build();
    public static final Dentist DENTIST_FIONA = new DentistBuilder().withName("Fiona Kunz")
            .withPhone("44444444")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withSpecialization("ORAL AND MAXILLOFACIAL SURGERY")
            .withYoe("5")
            .build();
    public static final Dentist DENTIST_GEORGE = new DentistBuilder().withName("George Best")
            .withPhone("55555555")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withSpecialization("DENTAL PUBLIC HEALTH")
            .withYoe("5")
            .withTags("America")
            .build();

    // Manually added
    public static final Dentist DENTIST_HOON = new DentistBuilder().withName("Hoon Meier")
            .withPhone("66666666")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withSpecialization("ORTHODONTICS")
            .withYoe("2")
            .withTags("NUSDentistry")
            .build();
    public static final Dentist DENTIST_IDA = new DentistBuilder().withName("Ida Mueller")
            .withPhone("77777777")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withSpecialization("PERIODONTICS")
            .withYoe("5")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Dentist AMY = new DentistBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withSpecialization(VALID_SPECIALIZATION_AMY)
            .withYoe(VALID_YOE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Dentist BOB = new DentistBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withSpecialization(VALID_SPECIALIZATION_BOB)
            .withYoe(VALID_YOE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDentists() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical dentists.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Dentist dentist : getTypicalDentists()) {
            ab.addDentist(dentist);
        }
        return ab;
    }

    public static List<Dentist> getTypicalDentists() {
        return new ArrayList<>(Arrays.asList(DENTIST_ALICE, DENTIST_BENSON, DENTIST_CARL,
                DENTIST_DANIEL, DENTIST_ELLE, DENTIST_FIONA, DENTIST_GEORGE));
    }
}
