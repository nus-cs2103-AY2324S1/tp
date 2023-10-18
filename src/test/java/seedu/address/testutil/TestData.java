package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.model.AddressBook;
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

    /**
     * Holds (@code Index} objects that are used in the test cases.
     */
    public static final class IndexContact {
        public static final Index FIRST_CONTACT = Index.fromOneBased(1);
        public static final Index SECOND_CONTACT = Index.fromOneBased(2);
        public static final Index THIRD_CONTACT = Index.fromOneBased(3);

    }

    /**
     * Holds JsonUtilTest test data.
     */
    public static final class Json {
        public static final String JSON_STRING_REPRESENTATION = String.format("{%n"
                + "  \"name\" : \"This is a test class\",%n"
                + "  \"listOfLocalDateTimes\" : "
                + "[ \"-999999999-01-01T00:00:00\", \"+999999999-12-31T23:59:59.999999999\", "
                + "\"0001-01-01T01:01:00\" ],%n"
                + "  \"mapOfIntegerToString\" : {%n"
                + "    \"1\" : \"One\",%n"
                + "    \"2\" : \"Two\",%n"
                + "    \"3\" : \"Three\"%n"
                + "  }%n"
                + "}");

        public static final String NAME_TEST_VALUE = "This is a test class";
    }


    //TODO Refine the various test data above this line
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
        public static final String NOTE_BOB = "CS2013 tutorial mate.";
        public static final String NAME_DESC_AMY = " " + PREFIX_NAME + NAME_AMY;
        public static final String NAME_DESC_BOB = " " + PREFIX_NAME + NAME_BOB;
        public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + PHONE_AMY;
        public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + PHONE_BOB;
        public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + EMAIL_AMY;
        public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + EMAIL_BOB;
        public static final String NOTE_DESC_AMY = " " + PREFIX_NOTE + NOTE_AMY;
        public static final String NOTE_DESC_BOB = " " + PREFIX_NOTE + NOTE_BOB;
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

            public static final seedu.address.model.contact.Contact ALICE =
                    new ContactBuilder()
                            .withName("Alice Pauline")
                            .withNote("Best Friend")
                            .withEmail("alice@example.com")
                            .withPhone("94351253")
                            .withTags("friends")
                            .build();
            public static final seedu.address.model.contact.Contact BENSON =
                    new ContactBuilder()
                            .withName("Benson Meier")
                            .withNote("Address: 311, Clementi Ave 2, #02-25")
                            .withEmail("johnd@example.com")
                            .withPhone("98765432")
                            .withTags("owesMoney", "friends")
                            .build();

            public static final seedu.address.model.contact.Contact CARL =
                    new ContactBuilder()
                            .withName("Carl Kurz")
                            .withNote("wall street")
                            .withPhone("95352563")
                            .withEmail("heinz@example.com")
                            .build();
            public static final seedu.address.model.contact.Contact DANIEL =
                    new ContactBuilder()
                            .withName("Daniel Meier")
                            .withNote("Close Friend")
                            .withPhone("87652533")
                            .withEmail("cornelia@example.com")
                            .withTags("friends")
                            .build();
            public static final seedu.address.model.contact.Contact ELLE =
                    new ContactBuilder()
                            .withName("Elle Meyer")
                            .withNote("Groupmate")
                            .withPhone("9482224")
                            .withEmail("werner@example.com")
                            .build();
            public static final seedu.address.model.contact.Contact FIONA =
                    new ContactBuilder()
                            .withName("Fiona Kunz")
                            .withNote("Classmate")
                            .withPhone("9482427")
                            .withEmail("lydia@example.com")
                            .build();
            public static final seedu.address.model.contact.Contact GEORGE =
                    new ContactBuilder()
                            .withName("George Best")
                            .withNote("Tutor")
                            .withPhone("9482442")
                            .withEmail("anna@example.com")
                            .build();
            public static final seedu.address.model.contact.Contact AMY =
                    new ContactBuilder()
                            .withName(NAME_AMY)
                            .withPhone(PHONE_AMY)
                            .withEmail(EMAIL_AMY)
                            .withNote(NOTE_AMY)
                            .withTags(TestData.Valid.Tag.ALPHANUMERIC)
                            .build();
            public static final seedu.address.model.contact.Contact BOB =
                    new ContactBuilder()
                            .withName(NAME_BOB)
                            .withPhone(PHONE_BOB)
                            .withEmail(EMAIL_BOB)
                            .withNote(NOTE_BOB)
                            .withTags(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                            .build();

            /**
             * Returns an {@code AddressBook} with all the typical contacts.
             */
            public static AddressBook getTypicalAddressBook() {
                AddressBook ab = new AddressBook();
                for (seedu.address.model.contact.Contact contact : getTypicalContacts()) {
                    ab.addContact(contact);
                }
                return ab;
            }

            public static List<seedu.address.model.contact.Contact> getTypicalContacts() {
                return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
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
                        .withTags(TestData.Valid.Tag.ALPHANUMERIC)
                        .build();
            public static final EditContactDescriptor BOB =
                    new EditContactDescriptorBuilder()
                            .withName(NAME_BOB)
                            .withPhone(PHONE_BOB)
                            .withEmail(EMAIL_BOB)
                            .withNote(NOTE_BOB)
                            .withTags(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                            .build();
        }
    }

    /**
     * Holds invalid test data.
     */
    public static final class Invalid {
        public static final String NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
        public static final String PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
        public static final String EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
        public static final String NAME = "R@chel";
        public static final String PHONE = "+651234";
        public static final String EMAIL = "example.com";


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

    private TestData() {
        // No instantiation
    }
}
