package networkbook.logic.parser;

import static networkbook.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.CommandTestUtil;
import networkbook.logic.commands.delete.DeleteCourseAction;
import networkbook.logic.commands.delete.DeleteEmailAction;
import networkbook.logic.commands.delete.DeleteFieldCommand;
import networkbook.logic.commands.delete.DeleteGraduationAction;
import networkbook.logic.commands.delete.DeleteLinkAction;
import networkbook.logic.commands.delete.DeletePersonCommand;
import networkbook.logic.commands.delete.DeletePhoneAction;
import networkbook.logic.commands.delete.DeletePriorityAction;
import networkbook.logic.commands.delete.DeleteSpecialisationAction;
import networkbook.logic.commands.delete.DeleteTagAction;
import networkbook.testutil.TypicalIndexes;


/**
 * Test class that contains unit test cases for {@code DeleteCommandParser}.
 */
public class DeleteCommandParserTest {

    private static final DeleteCommandParser parser = new DeleteCommandParser();
    private static final String MESSAGE_INVALID_COMMAND = String.format(
            Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE);

    private static final String NAME = CliSyntax.PREFIX_NAME.getPrefix();
    private static final String PHONE = CliSyntax.PREFIX_PHONE.getPrefix();
    private static final String EMAIL = CliSyntax.PREFIX_EMAIL.getPrefix();
    private static final String LINK = CliSyntax.PREFIX_LINK.getPrefix();
    private static final String GRADUATION = CliSyntax.PREFIX_GRADUATION.getPrefix();
    private static final String COURSE = CliSyntax.PREFIX_COURSE.getPrefix();
    private static final String SPECIALISATION = CliSyntax.PREFIX_SPECIALISATION.getPrefix();
    private static final String PRIORITY = CliSyntax.PREFIX_PRIORITY.getPrefix();
    private static final String TAG = CliSyntax.PREFIX_TAG.getPrefix();
    private static final String INDEX = CliSyntax.PREFIX_INDEX.getPrefix();
    private static final String WHITESPACE = " ";
    private static final String LONG_WHITESPACE = "         ";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String RANDOM = "$'fn2";
    private static final String INVALID_PREFIX = "/invalid";
    private static final Index INDEX_ONE = Index.fromOneBased(1);
    private static final Index INDEX_TWO = Index.fromOneBased(2);
    private static final DeletePhoneAction DELETE_FIRST_PHONE_ACTION = new DeletePhoneAction(INDEX_ONE);
    private static final DeleteEmailAction DELETE_FIRST_EMAIL_ACTION = new DeleteEmailAction(INDEX_ONE);
    private static final DeleteLinkAction DELETE_FIRST_LINK_ACTION = new DeleteLinkAction(INDEX_ONE);
    private static final DeleteCourseAction DELETE_FIRST_COURSE_ACTION = new DeleteCourseAction(INDEX_ONE);
    private static final DeleteSpecialisationAction DELETE_FIRST_SPECIALISATION_ACTION =
            new DeleteSpecialisationAction(INDEX_ONE);
    private static final DeleteTagAction DELETE_FIRST_TAG_ACTION = new DeleteTagAction(INDEX_ONE);
    private static final DeleteGraduationAction DELETE_GRADUATION_ACTION = new DeleteGraduationAction();
    private static final DeletePriorityAction DELETE_PRIORITY_ACTION = new DeletePriorityAction();

    public String generateUserInput(String... parts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            sb.append(parts[i]);
            sb.append(WHITESPACE);
        }
        sb.append(parts[parts.length - 1]);
        return sb.toString();
    }

    @Test
    public void parse_validIndex_returnsDeletePersonCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "1",
                new DeletePersonCommand(TypicalIndexes.INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndexOfContact_failure() {
        assertParseFailure(parser,
                CommandTestUtil.INVALID_INDEX_NEGATIVE + WHITESPACE + PRIORITY,
                MESSAGE_INVALID_COMMAND);
        assertParseFailure(parser,
                CommandTestUtil.INVALID_INDEX_ZERO + WHITESPACE + GRADUATION,
                MESSAGE_INVALID_COMMAND);
        assertParseFailure(parser,
                CommandTestUtil.INVALID_INDEX_OVERFLOW,
                MESSAGE_INVALID_COMMAND);
    }

    @Test
    public void parse_invalidIndexOfEntry_failure() {
        assertParseFailure(parser,
                "1" + WHITESPACE + COURSE + CommandTestUtil.INVALID_INDEX_DESC_NEGATIVE,
                MESSAGE_INVALID_COMMAND);
        assertParseFailure(parser,
                "1" + WHITESPACE + SPECIALISATION + CommandTestUtil.INVALID_INDEX_DESC_ZERO,
                MESSAGE_INVALID_COMMAND);
        assertParseFailure(parser,
                "1" + WHITESPACE + TAG + CommandTestUtil.INVALID_INDEX_DESC_OVERFLOW,
                MESSAGE_INVALID_COMMAND);
    }

    @Test
    public void parse_noPersonIndex_throwsParseException() {
        assertParseFailure(parser, WHITESPACE,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                generateUserInput(NAME),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                generateUserInput(INDEX, ONE),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                generateUserInput(INDEX),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                generateUserInput(PRIORITY),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_deleteName_throwsParseException() {
        assertParseFailure(parser,
                generateUserInput(ONE, NAME),
                DeleteCommandParser.MESSAGE_DELETE_NAME);
        assertParseFailure(parser,
                generateUserInput(ONE, PHONE, NAME, RANDOM),
                DeleteCommandParser.MESSAGE_DELETE_NAME);
        assertParseFailure(parser,
                generateUserInput(ONE, NAME, INDEX, ONE),
                DeleteCommandParser.MESSAGE_DELETE_NAME);
    }

    @Test
    public void parse_deleteInvalidPrefix_throwsParseException() {
        assertParseFailure(parser,
                generateUserInput(ONE, INVALID_PREFIX),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                generateUserInput(ONE, INVALID_PREFIX, INDEX, ONE),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                generateUserInput(ONE, INVALID_PREFIX, GRADUATION),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                generateUserInput(ONE, INVALID_PREFIX, TAG, INDEX, ONE),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                generateUserInput(ONE, INDEX),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                generateUserInput(ONE, INDEX, ONE),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                generateUserInput(ONE, INDEX, ONE, INDEX, TWO),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_deleteMultiplePrefixes_throwsParseException() {
        assertParseFailure(parser,
                generateUserInput(ONE, GRADUATION, PRIORITY),
                Messages.MESSAGE_EXACTLY_ONE_FIELD);
        assertParseFailure(parser,
                generateUserInput(ONE, GRADUATION, TAG),
                Messages.MESSAGE_EXACTLY_ONE_FIELD);
        assertParseFailure(parser,
                generateUserInput(ONE, TAG, GRADUATION, PRIORITY),
                Messages.MESSAGE_EXACTLY_ONE_FIELD);
        assertParseFailure(parser,
                generateUserInput(ONE, GRADUATION, TAG, INDEX, ONE),
                Messages.MESSAGE_EXACTLY_ONE_FIELD);
        assertParseFailure(parser,
                generateUserInput(ONE, GRADUATION, PRIORITY, INDEX, ONE),
                Messages.MESSAGE_EXACTLY_ONE_FIELD);
        assertParseFailure(parser,
                generateUserInput(ONE, GRADUATION, INDEX, ONE, PRIORITY),
                Messages.MESSAGE_EXACTLY_ONE_FIELD);
        assertParseFailure(parser,
                generateUserInput(ONE, GRADUATION, RANDOM, PRIORITY),
                Messages.MESSAGE_EXACTLY_ONE_FIELD);
    }

    @Test
    public void parse_deleteSamePrefixMultipleTimes_throwsParseException() {
        assertParseFailure(parser,
                generateUserInput(ONE, PRIORITY, PRIORITY),
                Messages.MESSAGE_DUPLICATE_PREFIX);
        assertParseFailure(parser,
                generateUserInput(ONE, EMAIL, INDEX, ONE, EMAIL, INDEX, ONE),
                Messages.MESSAGE_DUPLICATE_PREFIX);
        assertParseFailure(parser,
                generateUserInput(ONE, LINK, GRADUATION, LINK),
                Messages.MESSAGE_DUPLICATE_PREFIX);
        assertParseFailure(parser,
                generateUserInput(ONE, COURSE, RANDOM, COURSE, INDEX, PRIORITY),
                Messages.MESSAGE_DUPLICATE_PREFIX);
    }

    @Test
    public void parse_deleteMultiValuedFieldInvalidIndex_throwsParseException() {
        assertParseFailure(parser,
                generateUserInput(ONE, SPECIALISATION, INDEX, ONE, INDEX, TWO),
                Messages.MESSAGE_DUPLICATE_SINGLE_VALUED_FIELDS + CliSyntax.PREFIX_INDEX);
        assertParseFailure(parser,
                generateUserInput(ONE, LINK, INDEX, ONE, INDEX, ONE),
                Messages.MESSAGE_DUPLICATE_SINGLE_VALUED_FIELDS + CliSyntax.PREFIX_INDEX);
        assertParseFailure(parser,
                generateUserInput(ONE, COURSE, INDEX, INDEX),
                Messages.MESSAGE_DUPLICATE_SINGLE_VALUED_FIELDS + CliSyntax.PREFIX_INDEX);
    }

    @Test
    public void parse_deleteSingleValuedFieldUsingIndex_throwsParseException() {
        assertParseFailure(parser,
                generateUserInput(ONE, PRIORITY, INDEX, ONE),
                Messages.MESSAGE_INDEX_CANNOT_BE_PRESENT);
        assertParseFailure(parser,
                generateUserInput(ONE, PRIORITY, INDEX, ONE, INDEX, TWO),
                Messages.MESSAGE_INDEX_CANNOT_BE_PRESENT);
        assertParseFailure(parser,
                generateUserInput(ONE, GRADUATION, INDEX),
                Messages.MESSAGE_INDEX_CANNOT_BE_PRESENT);
        assertParseFailure(parser,
                generateUserInput(ONE, GRADUATION, INDEX, RANDOM),
                Messages.MESSAGE_INDEX_CANNOT_BE_PRESENT);
    }

    @Test
    public void parse_deleteWithRedundantValue_throwsParseException() {
        assertParseFailure(parser,
                generateUserInput(ONE, PRIORITY, RANDOM),
                String.format(Messages.MESSAGE_VALUE_CANNOT_BE_PRESENT, PRIORITY));
        assertParseFailure(parser,
                generateUserInput(ONE, GRADUATION, RANDOM),
                String.format(Messages.MESSAGE_VALUE_CANNOT_BE_PRESENT, GRADUATION));
        assertParseFailure(parser,
                generateUserInput(ONE, TAG, RANDOM, INDEX, ONE),
                String.format(Messages.MESSAGE_VALUE_CANNOT_BE_PRESENT, TAG));
        assertParseFailure(parser,
                generateUserInput(ONE, INDEX, ONE, LINK, RANDOM),
                String.format(Messages.MESSAGE_VALUE_CANNOT_BE_PRESENT, LINK));
    }

    @Test
    public void parse_deleteSingleValuedField_success() {
        DeleteFieldCommand expectedGraduationCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_GRADUATION_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(ONE, GRADUATION),
                expectedGraduationCommand);

        DeleteFieldCommand expectedPriorityCommand = new DeleteFieldCommand(INDEX_TWO, DELETE_PRIORITY_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(TWO, PRIORITY),
                expectedPriorityCommand);
    }

    @Test
    public void parse_deleteMultiValuedField_success() {

        DeleteFieldCommand expectedEmailCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_EMAIL_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(ONE, EMAIL, INDEX, ONE), // "delete 1 /email /index 1"
                expectedEmailCommand);

        DeleteFieldCommand expectedLinkCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_LINK_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(ONE, INDEX, ONE, LINK), // "delete 1 /index 1 /link"
                expectedLinkCommand);

        DeleteFieldCommand expectedPhoneCommand = new DeleteFieldCommand(INDEX_TWO, DELETE_FIRST_PHONE_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(TWO, PHONE, INDEX, ONE), // "delete 2 /phone /index 1"
                expectedPhoneCommand);

        DeleteFieldCommand expectedCourseCommand = new DeleteFieldCommand(INDEX_TWO, DELETE_FIRST_COURSE_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(TWO, INDEX, ONE, COURSE), // "delete 2 /index 1 /course"
                expectedCourseCommand);

        // delete multivalued field without /index prefix will default to 1

        DeleteFieldCommand expectedSpecCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_SPECIALISATION_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(ONE, SPECIALISATION), // "delete 1 /spec"
                expectedSpecCommand);

        DeleteFieldCommand expectedTagCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_TAG_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(ONE, TAG), // "delete 1 /tag"
                expectedTagCommand);
    }

    @Test
    public void parse_deleteValidInputWithWhiteSpaces_success() {
        DeleteFieldCommand expectedEmailCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_EMAIL_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(ONE, LONG_WHITESPACE, EMAIL, INDEX, ONE),
                expectedEmailCommand);

        DeleteFieldCommand expectedLinkCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_LINK_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(ONE, INDEX, LONG_WHITESPACE, ONE, LINK),
                expectedLinkCommand);

        DeleteFieldCommand expectedPhoneCommand = new DeleteFieldCommand(INDEX_TWO, DELETE_FIRST_PHONE_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(TWO, PHONE, INDEX, LONG_WHITESPACE, ONE),
                expectedPhoneCommand);

        DeleteFieldCommand expectedCourseCommand = new DeleteFieldCommand(INDEX_TWO, DELETE_FIRST_COURSE_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(LONG_WHITESPACE, TWO, LONG_WHITESPACE, INDEX, ONE, COURSE),
                expectedCourseCommand);

        DeleteFieldCommand expectedSpecCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_SPECIALISATION_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(ONE, SPECIALISATION, INDEX, LONG_WHITESPACE, ONE, LONG_WHITESPACE),
                expectedSpecCommand);

        DeleteFieldCommand expectedTagCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_TAG_ACTION);
        CommandParserTestUtil.assertParseSuccess(parser,
                generateUserInput(LONG_WHITESPACE, ONE, LONG_WHITESPACE, INDEX,
                        LONG_WHITESPACE, ONE, LONG_WHITESPACE, TAG),
                expectedTagCommand);
    }
}
