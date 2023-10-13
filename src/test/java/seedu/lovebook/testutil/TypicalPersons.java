package seedu.lovebook.testutil;

import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_HEIGHT_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.person.Date;

/**
 * A utility class containing a list of {@code Date} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Date ALICE = new PersonBuilder().withName("Alice Pauline")
            .withHeight("123").withGender("F")
            .withAge("31")
            .withTags("friends").build();
    public static final Date BENSON = new PersonBuilder().withName("Benson Meier")
            .withHeight("23124")
            .withGender("M").withAge("22")
            .withTags("owesMoney", "friends").build();
    public static final Date CARL = new PersonBuilder().withName("Carl Kurz").withAge("23")
            .withGender("M").withHeight("54321").build();
    public static final Date DANIEL = new PersonBuilder().withName("Daniel Meier").withAge("25")
            .withGender("M").withHeight("10").withTags("friends").build();
    public static final Date ELLE = new PersonBuilder().withName("Elle Meyer").withAge("34")
            .withGender("F").withHeight("1023").build();
    public static final Date FIONA = new PersonBuilder().withName("Fiona Kunz").withAge("24")
            .withGender("F").withHeight("3444").build();
    public static final Date GEORGE = new PersonBuilder().withName("George Best").withAge("94")
            .withGender("M").withHeight("23245").build();

    // Manually added
    public static final Date HOON = new PersonBuilder().withName("Hoon Meier").withAge("76")
            .withGender("M").withHeight("232").build();
    public static final Date IDA = new PersonBuilder().withName("Ida Mueller").withAge("38")
            .withGender("F").withHeight("35545").build();

    // Manually added - Date's details found in {@code CommandTestUtil}
    public static final Date AMY = new PersonBuilder().withName(VALID_NAME_AMY).withAge(VALID_AGE_AMY)
            .withGender(VALID_GENDER_AMY).withHeight(VALID_HEIGHT_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Date BOB = new PersonBuilder().withName(VALID_NAME_BOB).withAge(VALID_AGE_BOB)
            .withGender(VALID_GENDER_BOB).withHeight(VALID_HEIGHT_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code LoveBook} with all the typical dates.
     */
    public static LoveBook getTypicalLoveBook() {
        LoveBook ab = new LoveBook();
        for (Date date : getTypicalPersons()) {
            ab.addPerson(date);
        }
        return ab;
    }

    public static List<Date> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
