package seedu.address.logic.parser.assigment;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.assignment.AddAssignmentCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_NAME;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_STATUS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.assignment.AddAssignmentCommand;
import seedu.address.logic.parser.assignment.AddAssignmentParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.NoDate;
import seedu.address.model.fields.Status;
import seedu.address.model.tag.Tag;

public class AddAssignmentParserTest {
    private final AddAssignmentParser parser = new AddAssignmentParser();

    private final String multiplePrefixMsg = "Multiple values specified for the following single-valued field(s): ";

    @Test
    public void parse_allFieldsPresentSingleTag_success() throws ParseException {
        String validInput = " n/task1 s/complete e/2025-11-11 23:59 d/description p/2025-11-11 11:11 t/cs2100";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(true), new Description("description"),
                new IsoDate(LocalDateTime.parse("2025-11-11 11:11",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                Set.of(new Tag("cs2100")));

        assertParseSuccess(parser, validInput, new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_allFieldsPresentMultipleTag_success() throws ParseException {
        String validInput = " n/task1 s/complete e/2025-11-11 23:59 d/description p/2025-11-11 11:11 "
                + "t/cs2100 t/cs2103 t/gg";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(true), new Description("description"),
                new IsoDate(LocalDateTime.parse("2025-11-11 11:11",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                Set.of(new Tag("cs2100"), new Tag("cs2103"), new Tag("gg")));

        assertParseSuccess(parser, validInput, new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_missingStatus_success() throws ParseException {
        String validInput = " n/task1 e/2025-11-11 23:59 d/description p/2025-11-11 11:11 "
                + "t/cs2100 t/cs2103 t/gg";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(false), new Description("description"),
                new IsoDate(LocalDateTime.parse("2025-11-11 11:11",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                Set.of(new Tag("cs2100"), new Tag("cs2103"), new Tag("gg")));

        assertParseSuccess(parser, validInput, new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_missingPlannedDate_success() throws ParseException {
        String validInput = " n/task1 s/complete e/2025-11-11 23:59 d/description t/cs2100 t/cs2103 t/gg";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(true), new Description("description"),
                new NoDate(),
                Set.of(new Tag("cs2100"), new Tag("cs2103"), new Tag("gg")));

        assertParseSuccess(parser, validInput, new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_missingDescription_success() throws ParseException {
        String validInput = " n/task1 s/complete e/2025-11-11 23:59 p/2025-11-11 11:11 "
                + "t/cs2100 t/cs2103 t/gg";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(true), new Description(""),
                new IsoDate(LocalDateTime.parse("2025-11-11 11:11",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                Set.of(new Tag("cs2100"), new Tag("cs2103"), new Tag("gg")));

        assertParseSuccess(parser, validInput, new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_validDateWithoutTime_success() throws ParseException {
        String validInput = " n/task1 s/complete e/2025-11-11 d/description p/2025-11-11 11:11 t/cs2100";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(true), new Description("description"),
                new IsoDate(LocalDateTime.parse("2025-11-11 11:11",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                Set.of(new Tag("cs2100")));

        assertParseSuccess(parser, validInput, new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_invalidDateWithoutTime_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete e/2021-11-11 d/description p/2025-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, MESSAGE_INVALID_DATE);

    }

    @Test
    public void parse_invalidDateWithoutTime2_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete e/2021-11-1 d/description p/2025-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, MESSAGE_INVALID_DATE);

    }

    @Test
    public void parse_multipleName_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete e/2025-11-11 23:59 "
                + "d/description p/2025-11-11 11:11 t/cs2100 n/task2";
        assertParseFailure(parser, invalidInput, multiplePrefixMsg + "n/");
    }

    @Test
    public void parse_multipleStatus_fail() throws ParseException {
        String invalidInput = " n/task1 s/incomplete s/complete e/2025-11-11 23:59 "
                + "d/description p/2025-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, multiplePrefixMsg + "s/");
    }

    @Test
    public void parse_multipleEndDate_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete e/2025-11-11 23:59 e/2025-11-11 11:11 "
                + "d/description p/2025-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, multiplePrefixMsg + "e/");
    }

    @Test
    public void parse_multipleDescription_fail() throws ParseException {
        String invalidInput = " n/task1 s/incomplete e/2025-11-11 23:59 "
                + "d/description d/description1 d/description3 p/2025-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, multiplePrefixMsg + "d/");
    }

    @Test
    public void parse_multiplePlannedDate_fail() throws ParseException {
        String invalidInput = " n/task1 s/incomplete e/2025-11-11 23:59 "
                + "d/description p/2025-11-11 11:11 p/2025-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, multiplePrefixMsg + "p/");
    }

    @Test
    public void parse_invalidName_fail() throws ParseException {
        String invalidInput = " n/ s/complete e/2025-11-11 23:59 d/description p/2025-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, MESSAGE_INVALID_NAME);
    }

    @Test
    public void parse_invalidDate_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete e/1111-11-11 23:59 d/description p/1111-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_invalidStatus_fail() throws ParseException {
        String invalidInput = " n/task1 s/completee e/2025-11-11 23:59 d/description p/2025-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, MESSAGE_INVALID_STATUS);
    }

    @Test
    public void parse_invalidPlannedDate_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete e/2025-11-11 23:59 d/description p/1111-11-1 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_missingName_fail() throws ParseException {
        String invalidInput = " s/complete e/2025-11-11 23:59 d/description p/2025-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_missingEndDate_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete d/description p/2025-11-11 11:11 t/cs2100";
        assertParseFailure(parser, invalidInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }
}
