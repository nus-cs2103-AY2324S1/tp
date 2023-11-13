package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_TIME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_addModule() throws Exception {
        Module module = new ModuleBuilder().build();
        String inputCmd = AddModuleCommand.COMMAND_WORD + " m/" + module;
        AddModuleCommand command = (AddModuleCommand) parser.parseCommand(inputCmd);
        assertEquals(new AddModuleCommand(module), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteModule() throws Exception {
        String inputCmd = DeleteModuleCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased();
        DeleteModuleCommand command = (DeleteModuleCommand) parser.parseCommand(inputCmd);

        assertEquals(new DeleteModuleCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_addTutorial() throws Exception {
        String testModuleString = "CS1000";
        Module testModule = new Module("CS1000");
        String testName = "test name";
        String testTime = "Mon 2PM";
        AddTutorialCommand command = (AddTutorialCommand) parser.parseCommand(
                AddTutorialCommand.COMMAND_WORD + " " + PREFIX_MODULE + testModuleString
                        + " " + PREFIX_TUTORIAL_NAME + testName + " " + PREFIX_TUTORIAL_TIME + testTime);
        assertEquals(new AddTutorialCommand(new Tutorial(testModule, "test name", "Mon 2PM")), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_search() throws Exception {
        String testModuleString = "CS1000";
        String testName = "test name";
        String testTutorialString = "TG01";
        Tutorial testTutorial = new Tutorial(new Module(testModuleString), testTutorialString, "Mon 6PM");
        SearchCommand command = (SearchCommand) parser.parseCommand(SearchCommand.COMMAND_WORD + " "
                + PREFIX_NAME + testName + " "
                + PREFIX_MODULE + testModuleString + " "
                + PREFIX_TUTORIAL_NAME + testTutorialString);

        List<Name> nameList = new ArrayList<>(Arrays.asList(new Name(testName)));
        List<Module> moduleList = new ArrayList<>(Arrays.asList(new Module(testModuleString)));
        List<Tutorial> tutorialList = new ArrayList<>(Arrays.asList(testTutorial));
        List<Tag> emptyTagList = new ArrayList<>();
        assertEquals(new SearchCommand(nameList, moduleList, tutorialList, emptyTagList), command);
    }
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
