package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_ST2334;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.GroupPersonCommand;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.PersonBuilder;

public class GroupPersonCommandParserTest {

    private final GroupPersonCommandParser parser = new GroupPersonCommandParser();

    public GroupPersonCommandParser getParser() {
        return parser;
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();
        Group expectedGroup = new GroupBuilder().withName(VALID_GROUP_ST2334).build();

        assertParseSuccess(parser,
            NAME_DESC_BOB + GROUP_DESC_BOB,
            new GroupPersonCommand(VALID_NAME_BOB, VALID_GROUP_ST2334));
        // whitespace only preamble
        assertParseSuccess(parser,
            PREAMBLE_WHITESPACE + NAME_DESC_BOB + GROUP_DESC_BOB,
            new GroupPersonCommand(VALID_NAME_BOB, VALID_GROUP_ST2334));


    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB;

        // multiple contacts
        assertParseFailure(parser,
            NAME_DESC_BOB + NAME_DESC_BOB + GROUP_DESC_BOB,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));


        // multiple groups
        assertParseFailure(parser,
            NAME_DESC_BOB + GROUP_DESC_BOB + GROUP_DESC_BOB,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GROUPTAG));


    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupPersonCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + GROUP_DESC_BOB, expectedMessage);

        // missing group prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_GROUP_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_GROUP_BOB, expectedMessage);
    }

}


