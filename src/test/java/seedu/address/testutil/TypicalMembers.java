package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.member.Member;

/**
 * A utility class containing a list of {@code Member} objects to be used in tests.
 */
public class TypicalMembers {

    public static final Member ALICE = new MemberBuilder().withName("Alice Pauline")
            .withGender("Female")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Member BENSON = new MemberBuilder().withName("Benson Meier")
            .withGender("Male")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Member CARL = new MemberBuilder().withName("Carl Kurz")
            .withGender("Male")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street").build();
    public static final Member DANIEL = new MemberBuilder().withName("Daniel Meier")
            .withGender("Male")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withTags("friends").build();
    public static final Member ELLE = new MemberBuilder().withName("Elle Meyer")
            .withGender("Female")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave").build();
    public static final Member FIONA = new MemberBuilder().withName("Fiona Kunz")
            .withGender("Female")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo").build();
    public static final Member GEORGE = new MemberBuilder().withName("George Best")
            .withGender("Male")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street").build();

    // Manually added
    public static final Member HOON = new MemberBuilder().withName("Hoon Meier")
            .withGender("Female")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india").build();
    public static final Member IDA = new MemberBuilder().withName("Ida Mueller")
            .withGender("Female")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave").build();

    // Manually added - Member's details found in {@code CommandTestUtil}
    public static final Member AMY = new MemberBuilder().withName(VALID_NAME_AMY)
            .withGender(VALID_GENDER_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Member BOB = new MemberBuilder().withName(VALID_NAME_BOB)
            .withGender(VALID_GENDER_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalMembers() {} // prevents instantiation

    public static List<Member> getTypicalMembers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
