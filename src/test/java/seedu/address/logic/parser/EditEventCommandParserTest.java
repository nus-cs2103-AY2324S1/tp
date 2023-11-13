package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNASSIGN_GROUPS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNASSIGN_PERSONS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

public class EditEventCommandParserTest {

    private final EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParts_failure() {


        // no index specified
        assertParseFailure(parser, VALID_EVENT_NAME, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));

        // no field specified
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_EVENT_NAME, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0" + VALID_EVENT_NAME, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/string", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " ", EventName.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " "
                        + PREFIX_DATE + " ",
                EventDate.MESSAGE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(
                parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_START_TIME + "1",
                EventTime.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " "
                + PREFIX_START_TIME + "1400" + PREFIX_END_TIME + " ", EventTime.MESSAGE_CONSTRAINTS);

        // invalid group
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_GROUP
                + " ", Group.MESSAGE_CONSTRAINTS);

        // invalid unassign group
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " "
                + PREFIX_UNASSIGN_GROUPS + " ", Group.MESSAGE_CONSTRAINTS);

        // invalid unassign person
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " "
                + PREFIX_UNASSIGN_PERSONS + " ", Name.MESSAGE_CONSTRAINTS);

        // invalid person
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_NAME
                + " ", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() throws Exception {
        String userInput = "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_DATE + " "
                + "2021-12-12" + " " + PREFIX_START_TIME + " " + "1400" + " " + PREFIX_END_TIME + " " + "1500" + " "
                + PREFIX_GROUP + " " + "friends" + " " + PREFIX_UNASSIGN_GROUPS + " " + "groups" + " "
                + PREFIX_NAME + " " + "Alice" + " " + PREFIX_UNASSIGN_PERSONS + " " + "Ben";
        EditEventCommand.EditEventDescriptor descriptor = new EditEventCommand.EditEventDescriptor();
        descriptor.setName(new EventName(VALID_EVENT_NAME));
        descriptor.setDate(new EventDate("2021-12-12"));
        descriptor.setStartTime(EventTime.of("1400"));
        descriptor.setEndTime(EventTime.of("1500"));
        descriptor.setGroups(new HashSet<>(Set.of(new Group("friends"))));
        descriptor.setUnassignGroups(new HashSet<>(Set.of(new Group("groups"))));
        descriptor.setPersonNames(new HashSet<>(Set.of(new Name("Alice"))));
        descriptor.setUnassignPersons(new HashSet<>(Set.of(new Name("Ben"))));
        EditEventCommand expectedCommand = new EditEventCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
