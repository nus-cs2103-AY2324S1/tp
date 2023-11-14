package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.assignment.DeleteAssignmentCommand;
import seedu.address.logic.commands.assignment.EditAssignmentCommand;
import seedu.address.logic.commands.assignment.FindAssignmentCommand;
import seedu.address.logic.commands.assignment.ListAssignmentCommand;
import seedu.address.logic.commands.assignment.MarkAssignmentCommand;
import seedu.address.logic.commands.assignment.UnMarkAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.View;

public class AddressBookParserTest {

    private static final String MESSAGE_WRONG_VIEW_FIRST_HALF = "Please switch to ";

    private static final String MESSAGE_WRONG_VIEW_SECOND_HALF = " before performing this operation "
            + "using its respective list command";

    private static final Function<View, Boolean> verifierTrue = (v) -> true;

    private static final Function<View, Boolean> verifierFalse = (v) -> false;

    private final AddressBookParser parser = new AddressBookParser();
    @Test
    public void parseCommandCorrectViewExit_success() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, verifierTrue) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", verifierTrue) instanceof ExitCommand);
    }

    @Test
    public void parseCommandWrongViewExit_success() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, verifierFalse) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", verifierFalse) instanceof ExitCommand);
    }

    @Test
    public void parseCommandCorrectViewHelp_success() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, verifierTrue) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", verifierTrue) instanceof HelpCommand);
    }

    @Test
    public void parseCommandWrongViewHelp_success() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, verifierFalse) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", verifierFalse) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", verifierTrue));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser
                .parseCommand("unknownCommand", verifierTrue));
    }

    //========= Assignments =================================================================================

    @Test
    public void parseCommandCorrectViewListA_success() throws Exception {
        assertTrue(parser.parseCommand(ListAssignmentCommand.COMMAND_WORD, verifierTrue)
                instanceof ListAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewListA_success() throws Exception {
        assertTrue(parser.parseCommand(ListAssignmentCommand.COMMAND_WORD, verifierFalse)
                instanceof ListAssignmentCommand);
    }

    @Test
    public void parseCommandCorrectViewFindA_success() throws Exception {
        assertTrue(parser.parseCommand(FindAssignmentCommand.COMMAND_WORD + " 1", verifierTrue)
                instanceof FindAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewFindA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(FindAssignmentCommand.COMMAND_WORD, verifierFalse));
    }

    @Test
    public void parseCommandCorrectViewDeleteA_success() throws Exception {
        assertTrue(parser.parseCommand(DeleteAssignmentCommand.COMMAND_WORD + " 1", verifierTrue)
                instanceof DeleteAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewDeleteA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(DeleteAssignmentCommand.COMMAND_WORD, verifierFalse));
    }

    @Test
    public void parseCommandCorrectViewMarkA_success() throws Exception {
        assertTrue(parser.parseCommand(MarkAssignmentCommand.COMMAND_WORD + " 1", verifierTrue)
                instanceof MarkAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewMarkA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(MarkAssignmentCommand.COMMAND_WORD + " 1", verifierFalse));
    }

    @Test
    public void parseCommandCorrectViewUnmarkA_success() throws Exception {
        assertTrue(parser.parseCommand(UnMarkAssignmentCommand.COMMAND_WORD + " 1", verifierTrue)
                instanceof UnMarkAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewUnmarkA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(UnMarkAssignmentCommand.COMMAND_WORD + " 1", verifierFalse));
    }

    @Test
    public void parseCommandWrongViewEditA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(EditAssignmentCommand.COMMAND_WORD, verifierFalse));
    }

}
