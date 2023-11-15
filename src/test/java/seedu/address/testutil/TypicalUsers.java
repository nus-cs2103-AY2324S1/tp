package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalDatedEvents.NORMAL_DATEDEVENTS;
import static seedu.address.testutil.TypicalSchedule.FILLED_SCHEDULE;
import static seedu.address.testutil.TypicalSchedule.FULL_SCHEDULE;
import static seedu.address.testutil.TypicalSchedule.NORMAL_SCHEDULE;

import seedu.address.model.user.User;

/**
 * A utility class containing a list of {@code User} objects to be used in tests.
 */
public class TypicalUsers {

    public static final User JOHN = new UserBuilder().withName("John Doe")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("john@example.com")
            .withPhone("98765432").withBirthday("1998-01-01").withSchedule().withDatedEvents()
            .withTags("friends").build();

    public static final User JANE = new UserBuilder().withName("Jane Doe")
            .withAddress("321, Clementi Ave 4, #05-20").withEmail("jane@example.com")
            .withPhone("92345678").withBirthday("2000-01-01").withSchedule(NORMAL_SCHEDULE)
            .withTags("friends").build();

    public static final User JAMES = new UserBuilder().withName("James Doe")
            .withAddress("123, Clementi Ave 6, #05-22").withEmail("james@example.com")
            .withPhone("92345679").withBirthday("2000-01-01").withSchedule(FULL_SCHEDULE)
            .withTags("friends").build();

    public static final User JOSH = new UserBuilder().withName("Josh Doe")
            .withAddress("123, Clementi Ave 5, #05-22").withEmail("josh@example.com")
            .withPhone("92345612").withBirthday("2000-01-01").withSchedule(FILLED_SCHEDULE).build();

    // Manually added - User's details found in {@code CommandTestUtil}
    public static final User AMY = new UserBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withBirthday(VALID_BIRTHDAY_AMY)
            .withSchedule().withTags(VALID_TAG_FRIEND).build();
    public static final User BOB = new UserBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withBirthday(VALID_BIRTHDAY_BOB)
            .withSchedule(NORMAL_SCHEDULE).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withDatedEvents(NORMAL_DATEDEVENTS).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalUsers() {} // prevents instantiation

}

