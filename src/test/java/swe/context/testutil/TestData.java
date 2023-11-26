package swe.context.testutil;

import static swe.context.logic.parser.CliSyntax.PREFIX_ALTERNATE;
import static swe.context.logic.parser.CliSyntax.PREFIX_EMAIL;
import static swe.context.logic.parser.CliSyntax.PREFIX_NAME;
import static swe.context.logic.parser.CliSyntax.PREFIX_NOTE;
import static swe.context.logic.parser.CliSyntax.PREFIX_PHONE;
import static swe.context.logic.parser.CliSyntax.PREFIX_TAG;

import java.nio.file.Paths;
import java.util.logging.Level;

import swe.context.commons.core.Config;
import swe.context.commons.core.GuiSettings;
import swe.context.commons.core.index.Index;
import swe.context.logic.commands.EditCommand.EditContactDescriptor;
import swe.context.model.Contacts;
import swe.context.model.Settings;
import swe.context.model.contact.Contact;

/**
 * Holds test data used by test cases, such as strings and {@link Contact}s.
 */
public final class TestData {
    // These are used for default values during ContactBuilder initialisation
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_NOTE = "I forgot where this contact came from...";

    /**
     * Holds {@link Index} objects that are used in the test cases.
     */
    public static final class IndexContact {
        public static final Index FIRST_CONTACT = Index.fromOneBased(1);
        public static final Index SECOND_CONTACT = Index.fromOneBased(2);
        public static final Index THIRD_CONTACT = Index.fromOneBased(3);
    }

    /**
     * Holds valid test data.
     */
    public static final class Valid {
        public static final String NAME_AMY = "Amy Bee";
        public static final String NAME_BOB = "Bob Choo";
        public static final String PHONE_AMY = "11111111";
        public static final String PHONE_BOB = "22222222";
        public static final String EMAIL_AMY = "amy@example.com";
        public static final String EMAIL_BOB = "bob@example.com";
        public static final String NOTE_AMY = "";
        public static final String NOTE_BOB = "CS2103 tutorial mate.";
        public static final String NAME_DESC_AMY = " " + PREFIX_NAME + NAME_AMY;
        public static final String NAME_DESC_BOB = " " + PREFIX_NAME + NAME_BOB;
        public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + PHONE_AMY;
        public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + PHONE_BOB;
        public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + EMAIL_AMY;
        public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + EMAIL_BOB;
        public static final String NOTE_DESC_AMY = " " + PREFIX_NOTE + NOTE_AMY;
        public static final String NOTE_DESC_BOB = " " + PREFIX_NOTE + NOTE_BOB;

        /**
         * Holds {@code Tag}-related data.
         */
        public static final class Tag {
            public static final String ALPHANUMERIC = "Friend1";
            public static final String ALPHANUMERIC_SPACES = "2023 tutorial classmate";

            public static final String FLAG = " " + PREFIX_TAG;
            public static final String FLAG_ALPHANUMERIC = Tag.FLAG + Tag.ALPHANUMERIC;
            public static final String FLAG_ALPHANUMERIC_SPACES = Tag.FLAG + Tag.ALPHANUMERIC_SPACES;
        }

        /**
         * Holds {@code AlternateContact}-related data.
         */
        public static final class AlternateContact {
            public static final String ALPHANUMERIC = "Test1: Example1";
            public static final String ALPHANUMERIC_UNDERSCORE = "Test2: Example_2";
            public static final String FLAG = " " + PREFIX_ALTERNATE;
            public static final String FLAG_ALPHANUMERIC = FLAG + ALPHANUMERIC;
            public static final String FLAG_ALPHANUMERIC_UNDERSCORE = FLAG + ALPHANUMERIC_UNDERSCORE;
        }

        /**
         * Holds {@link Contact}s.
         */
        public static final class Contact {

            public static final swe.context.model.contact.Contact ALICE =
                    new ContactBuilder()
                            .withName("Alice Pauline")
                            .withNote("Best Friend")
                            .withEmail("alice@example.com")
                            .withPhone("94351253")
                            .withTags("friends")
                            .withAlternateContacts("Example: Alice")
                            .build();
            public static final swe.context.model.contact.Contact BENSON =
                    new ContactBuilder()
                            .withName("Benson Meier")
                            .withNote("Address: 311, Clementi Ave 2, #02-25")
                            .withEmail("johnd@example.com")
                            .withPhone("98765432")
                            .withTags("owesMoney", "friends")
                            .withAlternateContacts("Example: Benson", "Example: Benson.Meier")
                            .build();

            public static final swe.context.model.contact.Contact CARL =
                    new ContactBuilder()
                            .withName("Carl Kurz")
                            .withNote("wall street")
                            .withPhone("95352563")
                            .withEmail("heinz@example.com")
                            .build();
            public static final swe.context.model.contact.Contact DANIEL =
                    new ContactBuilder()
                            .withName("Daniel Meier")
                            .withNote("Close Friend")
                            .withPhone("87652533")
                            .withEmail("cornelia@example.com")
                            .withTags("friends")
                            .withAlternateContacts("Example: Daniel")
                            .build();
            public static final swe.context.model.contact.Contact ELLE =
                    new ContactBuilder()
                            .withName("Elle Meyer")
                            .withNote("Groupmate")
                            .withPhone("9482224")
                            .withEmail("werner@example.com")
                            .build();
            public static final swe.context.model.contact.Contact FIONA =
                    new ContactBuilder()
                            .withName("Fiona Kunz")
                            .withNote("Classmate")
                            .withPhone("9482427")
                            .withEmail("lydia@example.com")
                            .build();
            public static final swe.context.model.contact.Contact GEORGE =
                    new ContactBuilder()
                            .withName("George Best")
                            .withNote("Tutor")
                            .withPhone("9482442")
                            .withEmail("anna@example.com")
                            .build();
            public static final swe.context.model.contact.Contact AMY =
                    new ContactBuilder()
                            .withName(NAME_AMY)
                            .withPhone(PHONE_AMY)
                            .withEmail(EMAIL_AMY)
                            .withNote(NOTE_AMY)
                            .withTags(Tag.ALPHANUMERIC)
                            .withAlternateContacts(AlternateContact.ALPHANUMERIC)
                            .build();
            public static final swe.context.model.contact.Contact BOB =
                    new ContactBuilder()
                            .withName(NAME_BOB)
                            .withPhone(PHONE_BOB)
                            .withEmail(EMAIL_BOB)
                            .withNote(NOTE_BOB)
                            .withTags(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                            .withAlternateContacts(
                                    AlternateContact.ALPHANUMERIC, AlternateContact.ALPHANUMERIC_UNDERSCORE)
                            .build();

            /**
             * Returns {@link Contacts} matching typicalContacts.json.
             */
            public static Contacts getTypicalContacts() {
                Contacts contacts = new Contacts();
                contacts.add(ALICE);
                contacts.add(BENSON);
                contacts.add(CARL);
                contacts.add(DANIEL);
                contacts.add(ELLE);
                contacts.add(FIONA);
                contacts.add(GEORGE);
                return contacts;
            }
        }

        /**
         * Holds {@link EditContactDescriptor}s.
         */
        public static final class EditDescriptor {
            public static final EditContactDescriptor AMY =
                    new EditContactDescriptorBuilder()
                            .withName(NAME_AMY)
                            .withPhone(PHONE_AMY)
                            .withEmail(EMAIL_AMY)
                            .withNote(NOTE_AMY)
                            .withTags(Tag.ALPHANUMERIC)
                            .withAlternateContacts(AlternateContact.ALPHANUMERIC)
                            .build();
            public static final EditContactDescriptor BOB =
                    new EditContactDescriptorBuilder()
                            .withName(NAME_BOB)
                            .withPhone(PHONE_BOB)
                            .withEmail(EMAIL_BOB)
                            .withNote(NOTE_BOB)
                            .withTags(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                            .withAlternateContacts(AlternateContact.ALPHANUMERIC,
                                    AlternateContact.ALPHANUMERIC_UNDERSCORE)
                            .build();
        }
    }

    /**
     * Holds invalid test data.
     */
    public static final class Invalid {
        public static final String NAME = "R@chel";
        public static final String NAME_DESC = " " + PREFIX_NAME + "James&";
        public static final String PHONE = "+651234";
        public static final String PHONE_DESC = " " + PREFIX_PHONE + "99 (need 3+ digits)";
        public static final String EMAIL = "example.com";
        public static final String EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo";
        public static final String ALTERNATE_CONTACT = "ContactNoColonSpaceAfter";

        /**
         * Holds {@code Tag}-related data.
         */
        public static final class Tag {
            public static final String HASHTAG = "#WrongKindOfTag";
            public static final String UNDERSCORE_DASH = "kebab-snake_case";

            public static final String FLAG_HASHTAG = TestData.Valid.Tag.FLAG + Tag.HASHTAG;
        }

        /**
         * Holds {@code AlternateContact}-related data.
         */
        public static final class AlternateContact {
            public static final String MISSING_SYMBOL = "Example Name";
            public static final String NO_WHITESPACE = "Example:Name";
            public static final String WHITESPACE_IN_NAME = "Example: N ame";
            public static final String FLAG_MISSING_SYMBOL = Valid.AlternateContact.FLAG + MISSING_SYMBOL;
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

    private TestData() {
        // No instantiation
    }
}
