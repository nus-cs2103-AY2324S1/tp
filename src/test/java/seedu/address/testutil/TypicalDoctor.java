package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Doctor;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalDoctor {

    public static final Doctor ALICE = new DoctorBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRemark("She wants to become a Surgeon.").withGender("F").withIc("S9631267K")
            .withTags("friends").build();
    public static final Doctor BOYD = new DoctorBuilder().withName("Boyd Anders")
            .withAddress("311, Clementi Ave 2, #02-25").withRemark("His weakness is being a Perfectionist")
            .withEmail("boyda@example.com").withPhone("98765432").withGender("M").withIc("S9331268K")
            .withTags("owesMoney", "friends").build();
    public static final Doctor CARLOS = new DoctorBuilder().withName("Carlos Sainz").withPhone("95352563")
            .withEmail("smoothoperator@example.com").withAddress("wall street").withGender("M")
            .withIc("S9831269K").build();
    public static final Doctor DAVID = new DoctorBuilder().withName("David Beckham").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withGender("M").withIc("S7531260K").build();
    public static final Doctor EDITH = new DoctorBuilder().withName("Edith Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withGender("F").withIc("S7231261K").build();
    public static final Doctor FELICIA = new DoctorBuilder().withName("Felicia Kunz").withPhone("9482427")
            .withEmail("felbel@example.com").withAddress("little tokyo").withGender("F").withIc("S9431262K").build();
    public static final Doctor GREG = new DoctorBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withGender("M").withIc("S9531263K").build();

    // Manually added
    public static final Doctor ALLEN = new DoctorBuilder().withName("Barry Allen").withPhone("8482424")
            .withEmail("flash@example.com").withAddress("Central City").withGender("M")
            .withIc("S9831263J").build();
    public static final Doctor WAYNE = new DoctorBuilder().withName("Bruce Wayne").withPhone("8482131")
            .withEmail("bats@example.com").withAddress("Gotham City").withGender("F").withIc("S8931263P")
            .build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Doctor CHERYL = new DoctorBuilder().withName(VALID_NAME_CHERYL).withPhone(VALID_PHONE_CHERYL)
            .withEmail(VALID_EMAIL_CHERYL)
            .withAddress(VALID_ADDRESS_CHERYL).withIc(VALID_NRIC_CHERYL).withGender(VALID_GENDER_FEMALE).build();
    public static final Doctor DEREK = new DoctorBuilder().withName(VALID_NAME_DEREK).withPhone(VALID_PHONE_DEREK)
            .withEmail(VALID_EMAIL_DEREK)
            .withAddress(VALID_ADDRESS_DEREK).withIc(VALID_NRIC_DEREK).withGender(VALID_GENDER_MALE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDoctor() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical patients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Doctor doctor : getTypicalDoctors()) {
            ab.addDoctor(doctor);
        }
        return ab;
    }

    public static List<Doctor> getTypicalDoctors() {
        return new ArrayList<>(Arrays.asList(ALICE, BOYD, CARLOS, DAVID, EDITH, FELICIA, GREG, ALLEN, WAYNE));
    }
}
