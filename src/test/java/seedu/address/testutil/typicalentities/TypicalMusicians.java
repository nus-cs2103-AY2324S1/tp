package seedu.address.testutil.typicalentities;


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

import seedu.address.model.musician.Musician;
import seedu.address.testutil.MusicianBuilder;

/**
 * A utility class containing a list of {@code Musician} objects to be used in tests.
 */
public class TypicalMusicians {

    public static final Musician ALICE = new MusicianBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withInstruments("piano")
            .withGenres("rock").build();
    public static final Musician BENSON = new MusicianBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withInstruments("guitar").withGenres("pop").build();
    public static final Musician CARL = new MusicianBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Musician DANIEL = new MusicianBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Musician ELLE = new MusicianBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Musician FIONA = new MusicianBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").build();
    public static final Musician GEORGE = new MusicianBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").build();
    // ARIANA has the same tags, instruments, genres as ALICE
    public static final Musician ARIANA = new MusicianBuilder().withName("Ariana Pauline")
            .withEmail("ariana@example.com")
            .withPhone("94351254")
            .withTags("friends")
            .withInstruments("piano")
            .withGenres("rock").build();

    // Manually added
    public static final Musician HOON = new MusicianBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Musician IDA = new MusicianBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Musician's details found in {@code CommandTestUtil}
    public static final Musician AMY = new MusicianBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Musician BOB = new MusicianBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalMusicians() {} // prevents instantiation

    public static List<Musician> getTypicalMusicians() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
