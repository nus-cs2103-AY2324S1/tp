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
 * A utility class containing a list of {@code Member} objects to be used in tests.
 */
public class TypicalMembers {

    public static final Member ALAN_MEMBER = new MemberBuilder().withName("Alan Pauline")
            .withPhone("94351253")
            .withEmail("alicep@example.com")
            .withTelegram("@alanpauline")
            .withTags("friends").build();
    public static final Member BETTY_MEMBER = new MemberBuilder().withName("Betty Meier")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withTelegram("@bettymeier")
            .withTags("owesMoney", "friends").build();
    public static final Member CHARLES_MEMBER = new MemberBuilder().withName("Charles Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withTelegram("@charleskurz").build();
    public static final Member DENISE_MEMBER = new MemberBuilder().withName("Denise Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withTelegram("@denisemeier")
            .withTags("friends").build();
    public static final Member ETHAN_MEMBER = new MemberBuilder().withName("Ethan Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withTelegram("@ethanmeyer").build();
    public static final Member FRED_MEMBER = new MemberBuilder().withName("Fred Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withTelegram("@fredkunz").build();
    public static final Member GISELLE_MEMBER = new MemberBuilder().withName("Giselle Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withTelegram("@gisellebest").build();

    // Manually added
    public static final Member HUGO_MEMBER = new MemberBuilder().withName("Hugo Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withTelegram("@hugomeier").build();

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
    public static AddressBook getTypicalAddressBookWithMembers() {
        AddressBook ab = new AddressBook();
        for (Member member : getTypicalMembers()) {
            ab.addMember(member);
        }
        return ab;
    }

    public static List<Member> getTypicalMembers() {
        return new ArrayList<>(Arrays.asList(ALAN_MEMBER, BETTY_MEMBER, CHARLES_MEMBER, DENISE_MEMBER,
                ETHAN_MEMBER, FRED_MEMBER, GISELLE_MEMBER));
    }
}
