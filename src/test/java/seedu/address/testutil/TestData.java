package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.nio.file.Paths;
import java.util.logging.Level;

import seedu.address.commons.core.Config;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.model.Contacts;
import seedu.address.model.ReadOnlyContacts;
import seedu.address.model.ReadOnlySettings;
import seedu.address.model.Settings;
import seedu.address.model.contact.Contact;



/**
 * Holds test data used by test cases, such as strings and {@link Contact}s.
 */
public final class TestData {
    // These are used for default values during {@code ContactBuilder} initialisation.
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_NOTE = "I forgot where this contact came from...";

    // These are used for testing commands as well creating of {@code Contact} objects
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_NOTE_AMY = "";
    public static final String VALID_NOTE_BOB = "CS2013 tutorial mate.";
    public static final String INVALID_NAME = "R@chel";
    public static final String INVALID_PHONE = "+651234";
    public static final String INVALID_EMAIL = "example.com";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String NOTE_DESC_AMY = " " + PREFIX_NOTE + VALID_NOTE_AMY;
    public static final String NOTE_DESC_BOB = " " + PREFIX_NOTE + VALID_NOTE_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol

    // These contain {@code Index} objects that are used in test cases
    public static final Index INDEX_FIRST_CONTACT = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_CONTACT = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_CONTACT = Index.fromOneBased(3);

    // These contain {@code Contact} objects that are used in test cases.
    public static final Contact ALICE = new ContactBuilder().withName("Alice Pauline")
            .withNote("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Contact BENSON = new ContactBuilder().withName("Benson Meier")
            .withNote("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Contact CARL = new ContactBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withNote("wall street").build();
    public static final Contact DANIEL = new ContactBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withNote("10th street").withTags("friends").build();
    public static final Contact ELLE = new ContactBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withNote("michegan ave").build();
    public static final Contact FIONA = new ContactBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withNote("little tokyo").build();
    public static final Contact GEORGE = new ContactBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withNote("4th street").build();

    // Manually added
    public static final Contact HOON = new ContactBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withNote("little india").build();
    public static final Contact IDA = new ContactBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withNote("chicago ave").build();

    /**
     * Holds valid test data.
     */
    public static final class Valid {
        /**
         * Holds tag-related data.
         */
        public static final class Tag {
            public static final String ALPHANUMERIC = "Friend1";
            public static final String ALPHANUMERIC_SPACES = "2023 tutorial classmate";

            public static final String FLAG = " " + PREFIX_TAG;
            public static final String FLAG_ALPHANUMERIC = Tag.FLAG + Tag.ALPHANUMERIC;
            public static final String FLAG_ALPHANUMERIC_SPACES = Tag.FLAG + Tag.ALPHANUMERIC_SPACES;
        }

        /**
         * Holds {@link Contact}s.
         */
        public static final class Contact {
            public static final seedu.address.model.contact.Contact AMY =
                    new ContactBuilder()
                            .withName(VALID_NAME_AMY)
                            .withPhone(VALID_PHONE_AMY)
                            .withEmail(VALID_EMAIL_AMY)
                            .withNote(VALID_NOTE_AMY)
                            .withTags(TestData.Valid.Tag.ALPHANUMERIC)
                            .build();
            public static final seedu.address.model.contact.Contact BOB =
                    new ContactBuilder()
                            .withName(VALID_NAME_BOB)
                            .withPhone(VALID_PHONE_BOB)
                            .withEmail(VALID_EMAIL_BOB)
                            .withNote(VALID_NOTE_BOB)
                            .withTags(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                            .build();
        }

        /**
         * Holds {@link EditContactDescriptor}s.
         */
        public static final class EditDescriptor {
            public static final EditContactDescriptor AMY =
                    new EditContactDescriptorBuilder()
                            .withName(VALID_NAME_AMY)
                            .withPhone(VALID_PHONE_AMY)
                            .withEmail(VALID_EMAIL_AMY)
                            .withNote(VALID_NOTE_AMY)
                            .withTags(TestData.Valid.Tag.ALPHANUMERIC)
                            .build();
            public static final EditContactDescriptor BOB =
                    new EditContactDescriptorBuilder()
                            .withName(VALID_NAME_BOB)
                            .withPhone(VALID_PHONE_BOB)
                            .withEmail(VALID_EMAIL_BOB)
                            .withNote(VALID_NOTE_BOB)
                            .withTags(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                            .build();
        }
    }

    /**
     * Holds invalid test data.
     */
    public static final class Invalid {
        /**
         * Holds tag-related data.
         */
        public static final class Tag {
            public static final String HASHTAG = "#WrongKindOfTag";
            public static final String UNDERSCORE_DASH = "kebab-snake_case";

            public static final String FLAG_HASHTAG = TestData.Valid.Tag.FLAG + Tag.HASHTAG;
        }
    }

    public static final String WHITESPACE = "\t  \r  \n";
    public static final String EXTRA_WORDS = "extra words here";

    /**
     * Returns {@link Config} matching typicalConfig.json.
     */
    public static Config getTypicalConfig() {
        Config config = new Config();
        config.setSettingsPath(Paths.get("myFolder", "settings.json"));
        config.setLogLevel(Level.INFO);
        return config;
    }

    /**
     * Returns {@link Settings} matching typicalSettings.json.
     */
    public static Settings getTypicalSettings() {
        Settings settings = new Settings();
        settings.setContactsPath(Paths.get("myDocuments/contacts.json"));
        settings.setGuiSettings(
            new GuiSettings(1000, 500, 300, 100)
        );
        return settings;
    }

    /**
     * Returns {@link Contacts} matching typicalContacts.json.
     */
    public static Contacts getTypicalContacts() {
        Contacts contacts = new Contacts();
        contacts.add(TestData.ALICE);
        contacts.add(TestData.BENSON);
        contacts.add(TestData.CARL);
        contacts.add(TestData.ELLE);
        contacts.add(TestData.FIONA);
        contacts.add(TestData.GEORGE);
        return contacts;
    }

    private TestData() {
        // No instantiation
    }
}
