package seedu.address.logic.parser;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.GoalCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HintCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.PractiseCommand;
import seedu.address.logic.commands.SetDifficultyCommand;
import seedu.address.logic.commands.SolveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.testutil.CardBuilder;
import seedu.address.testutil.CardUtil;



public class DeckParserTest {

    private static final String EXAMPLE_QUESTION = "What is this?";
    private static final String EXAMPLE_ANSWER = "Example Answer";
    private final DeckParser parser = new DeckParser();




    @Test
    public void parseCommand_addCommand_success() throws ParseException {
        Card testCard = new CardBuilder().build();
        AddCommand addCommand = (AddCommand) parser.parseCommand(CardUtil.getAddCommand(testCard));
        assertEquals(new AddCommand(testCard), addCommand);
    }


    @Test
    public void parseCommand_deleteCommand_success() throws Exception {
        Index firstIndex = Index.fromOneBased(1);
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + firstIndex.getOneBased());
        assertEquals(new DeleteCommand(firstIndex), command);
    }

    @Test
    public void parseCommand_editCommandQuestion_success() throws Exception {
        Index firstIndex = Index.fromOneBased(1);
        String editTestCommand = EditCommand.COMMAND_WORD + " " + firstIndex.getOneBased() + " "
                + CliSyntax.PREFIX_QUESTION + EXAMPLE_QUESTION;
        EditCommand command = (EditCommand) parser.parseCommand(editTestCommand);
        EditCommand.EditCardDescriptor testEditCardDescriptor = new EditCommand.EditCardDescriptor();
        testEditCardDescriptor.setQuestion(new Question(EXAMPLE_QUESTION));
        assertEquals(new EditCommand(firstIndex, testEditCardDescriptor), command);
    }

    @Test
    public void parseCommand_editCommandAnswer_success() throws Exception {
        Index firstIndex = Index.fromOneBased(1);

        String editTestCommand = EditCommand.COMMAND_WORD + " " + firstIndex.getOneBased() + " "
                + CliSyntax.PREFIX_ANSWER + " " + EXAMPLE_ANSWER;
        EditCommand command = (EditCommand) parser.parseCommand(editTestCommand);
        EditCommand.EditCardDescriptor testEditCardDescriptor = new EditCommand.EditCardDescriptor();
        testEditCardDescriptor.setAnswer(new Answer(EXAMPLE_ANSWER));
        assertEquals(new EditCommand(firstIndex, testEditCardDescriptor), command);
    }

    @Test
    public void parseCommand_editCommandAnswer_failure() {
        Index firstIndex = Index.fromOneBased(1);
        String editTestCommand = EditCommand.COMMAND_WORD + " " + firstIndex.getOneBased() + " "
                + CliSyntax.PREFIX_INVALID + " " + EXAMPLE_ANSWER;

        assertThrows(
                ParseException.class,
                String.format(
                        Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        EditCommand.MESSAGE_USAGE), () -> parser.parseCommand(editTestCommand));
    }

    @Test
    public void parseCommand_solveCommand_success() throws ParseException {
        Index firstIndex = Index.fromOneBased(1);
        String solveTestCommand = SolveCommand.COMMAND_WORD + " " + firstIndex.getOneBased();
        SolveCommand testCommand = (SolveCommand) parser.parseCommand(solveTestCommand);

        assertEquals(new SolveCommand(firstIndex), testCommand);
    }

    @Test
    public void parseCommand_solveCommand_failure() {
        String solveTestCommand = SolveCommand.COMMAND_WORD + " " + "-1";

        assertThrows(
                ParseException.class,
                String.format(
                        Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        SolveCommand.MESSAGE_USAGE), () -> parser.parseCommand(solveTestCommand));
    }

    @Test
    public void parseCommand_practiseCommand_success() throws ParseException {
        Index firstIndex = Index.fromOneBased(1);
        String practiseTestCommand = PractiseCommand.COMMAND_WORD + " " + firstIndex.getOneBased();
        PractiseCommand testCommand = (PractiseCommand) parser.parseCommand(practiseTestCommand);

        assertEquals(new PractiseCommand(firstIndex), testCommand);
    }

    @Test
    public void parseCommand_practiseCommand_failure() {
        String practiseTestCommand = PractiseCommand.COMMAND_WORD + " " + "-1";

        assertThrows(
                ParseException.class,
                String.format(
                        Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        PractiseCommand.MESSAGE_USAGE), () -> parser.parseCommand(practiseTestCommand));
    }

    @Test
    public void parseCommand_goalCommand_success() throws ParseException {
        Index testIndex = Index.fromOneBased(8);
        String goalTestCommand = GoalCommand.COMMAND_WORD + " " + testIndex.getOneBased();
        GoalCommand testCommand = (GoalCommand) parser.parseCommand(goalTestCommand);

        assertEquals(new GoalCommand(testIndex.getOneBased()), testCommand);
    }


    @Test
    public void parseCommand_setDifficultyCommand_success() throws ParseException {
        String testEasyDifficulty = "easy";
        String testMediumDifficulty = "medium";
        String testHardDifficulty = "hard";

        Index firstIndex = Index.fromOneBased(1);
        String setTestCommandEasy = SetDifficultyCommand.COMMAND_WORD + " " + firstIndex.getOneBased() + " "
                + CliSyntax.PREFIX_DIFFICULTY + " " + testEasyDifficulty;
        String setTestCommandMedium = SetDifficultyCommand.COMMAND_WORD + " " + firstIndex.getOneBased() + " "
                + CliSyntax.PREFIX_DIFFICULTY + " " + testMediumDifficulty;
        String setTestCommandHard = SetDifficultyCommand.COMMAND_WORD + " " + firstIndex.getOneBased() + " "
                + CliSyntax.PREFIX_DIFFICULTY + " " + testHardDifficulty;

        SetDifficultyCommand testCommandEasy = (SetDifficultyCommand) parser.parseCommand(setTestCommandEasy);
        SetDifficultyCommand testCommandMedium = (SetDifficultyCommand) parser.parseCommand(setTestCommandMedium);
        SetDifficultyCommand testCommandHard = (SetDifficultyCommand) parser.parseCommand(setTestCommandHard);


        assertEquals(new SetDifficultyCommand(firstIndex, testEasyDifficulty), testCommandEasy);
        assertEquals(new SetDifficultyCommand(firstIndex, testMediumDifficulty), testCommandMedium);
        assertEquals(new SetDifficultyCommand(firstIndex, testHardDifficulty), testCommandHard);

    }

    @Test
    public void parseCommand_hintCommand_success() throws ParseException {
        Index testIndex = Index.fromOneBased(8);
        String hintTestCommand = HintCommand.COMMAND_WORD + " " + testIndex.getOneBased();
        HintCommand testCommand = (HintCommand) parser.parseCommand(hintTestCommand);

        assertEquals(new HintCommand(testIndex), testCommand);

    }



    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(
                ParseException.class,
                String.format(
                        Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                Messages.MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
