package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Member;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalMembers {

    public static final Member ALICE = new MemberBuilder().withName("Alice Pauline")
            .withPhone("94351253")
            .withEmail("alicep@example.com")
            .withTelegram("@alicepauline")
            .withTags("friends").build();
    public static final Member BENSON = new MemberBuilder().withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withTelegram("@bensonmeier")
            .withTags("owesMoney", "friends").build();
    public static final Member CARL = new MemberBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withTelegram("@carlkurz").build();
    public static final Member DANIEL = new MemberBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withTelegram("@danielmeier")
            .withTags("friends").build();
    public static final Member ELLE = new MemberBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withTelegram("@ellemeyer").build();
    public static final Member FIONA = new MemberBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withTelegram("@fionakunz").build();
    public static final Member GEORGE = new MemberBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withTelegram("@georgebest").build();

    // Manually added
    public static final Member HOON = new MemberBuilder().withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withTelegram("@hoonmeier").build();
    public static final Member IDA = new MemberBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withTelegram("@idamueller").build();

    // Manually added - Member's details found in {@code CommandTestUtil}
    public static final Member AMY_MEMBER = new MemberBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_BOB).withTags(VALID_TAG_FRIEND).build();
    public static final Member BOB_MEMBER = new MemberBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalMembers() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical members.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Member member : getTypicalMembers()) {
            ab.addMember(member);
        }
        return ab;
    }

    public static List<Member> getTypicalMembers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
