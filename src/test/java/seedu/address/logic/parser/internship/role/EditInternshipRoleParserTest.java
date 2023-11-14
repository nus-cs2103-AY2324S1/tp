package seedu.address.logic.parser.internship.role;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.internship.role.EditInternshipRoleCommand;
import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.fields.Cycle;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Pay;


public class EditInternshipRoleParserTest {
    private final EditInternshipRoleParser parser = new EditInternshipRoleParser();

    private final String multiplePrefixMsg = "Multiple values specified for the following single-valued field(s): ";

    private final String invalidPayMsg = "Pay must be a non negative number, e.g. 0, 1600.50";

    @Test
    public void parse_allValidFieldsPresent_success() {
        String validInput = " i/1 c/cycle d/desc p/2000 o/accepted l/location";
        EditInternshipRoleCommand editCmd = new EditInternshipRoleCommand(Index.fromOneBased(1),
                new Cycle("cycle"), new Description("desc"), new Pay(new BigDecimal("2000")),
                new ApplicationOutcome(Outcome.ACCEPTED), new Location("location"));
        assertParseSuccess(parser, validInput, editCmd);
    }

    @Test
    public void parse_oneValidFieldPresent_success() {
        String validInput = " i/1 c/cycle";
        EditInternshipRoleCommand editCmd = new EditInternshipRoleCommand(Index.fromOneBased(1),
                new Cycle("cycle"), null, null, null, null);
        assertParseSuccess(parser, validInput, editCmd);
    }


    @Test
    public void parse_emptyFields_throwParseException() {
        String invalidInput = " i/1";
        assertParseFailure(parser, invalidInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditInternshipRoleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicatePrefix_throwsParseException() {
        assertParseFailure(parser, " i/1 i/1 d/desc", multiplePrefixMsg + "i/");
        assertParseFailure(parser, " i/1 d/desc d/desc", multiplePrefixMsg + "d/");
        assertParseFailure(parser, " i/1 d/desc c/cycle c/cycle", multiplePrefixMsg + "c/");
        assertParseFailure(parser, " i/1 d/desc p/123 p/123", multiplePrefixMsg + "p/");
        assertParseFailure(parser, " i/1 d/desc l/loc l/loc", multiplePrefixMsg + "l/");
        assertParseFailure(parser, " i/1 o/follow-up o/awaiting", multiplePrefixMsg + "o/");
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        assertParseFailure(parser, " i/0 d/desc", "Index is not a non-zero unsigned integer.");
        assertParseFailure(parser, " i/1 o/invalid_cycle",
                "Enter a valid outcome: o/follow-up, ghosted, rejected, offered, accepted, awaiting");
        assertParseFailure(parser, " i/1 c/", "Cycle cannot be empty");
        assertParseFailure(parser, " i/1 l/", "Location cannot be empty");

        assertParseFailure(parser, " i/1 p/-0", invalidPayMsg);
        assertParseFailure(parser, " i/1 p/.50", invalidPayMsg);
    }

    @Test
    public void parse_indexNotPresent_throwsParseException() {
        String invalidInput = " c/cycle d/desc p/2000 o/accepted l/location";
        assertParseFailure(parser, invalidInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditInternshipRoleCommand.MESSAGE_USAGE));
    }

}
