package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddTagPersonCommand;
import seedu.address.logic.commands.AddTagTaskCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.DeleteAllDoneCommand;
import seedu.address.logic.commands.DeleteAllPersonCommand;
import seedu.address.logic.commands.DeleteAllTaskCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.DeleteTagPersonCommand;
import seedu.address.logic.commands.DeleteTagTaskCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAllTagCommand;
import seedu.address.logic.commands.FindDoneCommand;
import seedu.address.logic.commands.FindNotDoneCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.ListTagCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.commands.MarkTaskCommand;
import seedu.address.logic.commands.UnmarkTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsAllTagsPredicate;
import seedu.address.model.person.PersonContainsTagsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskContainsAllTagsPredicate;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.model.task.TaskContainsTagsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addPerson() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddPersonCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_shortened_addPerson() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser
                .parseCommand(PersonUtil.getShortenedAddPersonCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_deleteAllPerson() throws Exception {
        assertTrue(parser.parseCommand(DeleteAllPersonCommand.COMMAND_WORD) instanceof DeleteAllPersonCommand);
        assertTrue(parser.parseCommand(DeleteAllPersonCommand.COMMAND_WORD + " 3") instanceof DeleteAllPersonCommand);
    }

    @Test
    public void parseCommand_shortened_deleteAllPerson() throws Exception {
        assertTrue(
                parser.parseCommand(DeleteAllPersonCommand.SHORTENED_COMMAND_WORD) instanceof DeleteAllPersonCommand);
        assertTrue(
                parser.parseCommand(
                        DeleteAllPersonCommand.SHORTENED_COMMAND_WORD + " 3") instanceof DeleteAllPersonCommand);
    }

    @Test
    public void parseCommand_deletePerson() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_shortened_deletePerson() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.SHORTENED_COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editPerson() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_shortened_editPerson() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser
                .parseCommand(EditPersonCommand.SHORTENED_COMMAND_WORD + " "
                        + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_addTagPerson() throws Exception {
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        AddTagPersonCommand command = (AddTagPersonCommand) parser.parseCommand(AddTagPersonCommand.COMMAND_WORD
                + " " + INDEX_FIRST.getOneBased() + " t/caterer");
        assertEquals(new AddTagPersonCommand(INDEX_FIRST, tags), command);
    }

    @Test
    public void parseCommand_shortened_addTagPerson() throws Exception {
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        AddTagPersonCommand command = (AddTagPersonCommand) parser.parseCommand(
                AddTagPersonCommand.SHORTENED_COMMAND_WORD + " " + INDEX_FIRST.getOneBased() + " t/caterer");
        assertEquals(new AddTagPersonCommand(INDEX_FIRST, tags), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_shortened_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.SHORTENED_COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.SHORTENED_COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findPerson() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
                FindPersonCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindPersonCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_shortened_findPerson() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
                FindPersonCommand.SHORTENED_COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindPersonCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_shortened_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.SHORTENED_COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.SHORTENED_COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listPerson() throws Exception {
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD) instanceof ListPersonCommand);
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD + " 3") instanceof ListPersonCommand);
    }

    @Test
    public void parseCommand_shortened_listPerson() throws Exception {
        assertTrue(parser.parseCommand(ListPersonCommand.SHORTENED_COMMAND_WORD) instanceof ListPersonCommand);
        assertTrue(parser.parseCommand(ListPersonCommand.SHORTENED_COMMAND_WORD + " 3") instanceof ListPersonCommand);
    }

    @Test
    public void parseCommand_listAll() throws Exception {
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD) instanceof ListAllCommand);
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD + " 3") instanceof ListAllCommand);
    }

    @Test
    public void parseCommand_shortened_listAll() throws Exception {
        assertTrue(parser.parseCommand(ListAllCommand.SHORTENED_COMMAND_WORD) instanceof ListAllCommand);
        assertTrue(parser.parseCommand(ListAllCommand.SHORTENED_COMMAND_WORD + " 3") instanceof ListAllCommand);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(TaskUtil.getAddTaskCommand(task));
        assertEquals(new AddTaskCommand(task), command);
    }

    @Test
    public void parseCommand_shortened_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(TaskUtil.getShortenedAddTaskCommand(task));
        assertEquals(new AddTaskCommand(task), command);
    }

    @Test
    public void parseCommand_listTask() throws Exception {
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD) instanceof ListTaskCommand);
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD + " 3") instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_shortened_listTask() throws Exception {
        assertTrue(parser.parseCommand(ListTaskCommand.SHORTENED_COMMAND_WORD) instanceof ListTaskCommand);
        assertTrue(parser.parseCommand(ListTaskCommand.SHORTENED_COMMAND_WORD + " 3") instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_findTask() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
                FindTaskCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindTaskCommand(new TaskContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_shortened_findTask() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
                FindTaskCommand.SHORTENED_COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindTaskCommand(new TaskContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findTag() throws Exception {
        List<String> tagKeywords = Arrays.asList("tag1", "tag2", "tag3");
        FindTagCommand command = (FindTagCommand) parser.parseCommand(
                FindTagCommand.COMMAND_WORD + " " + String.join(" ", tagKeywords));
        assertEquals(new FindTagCommand(
                new PersonContainsTagsPredicate(tagKeywords),
                new TaskContainsTagsPredicate(tagKeywords)),
                command);
    }

    @Test
    public void parseCommand_shortened_findTag() throws Exception {
        List<String> tagKeywords = Arrays.asList("tag1", "tag2", "tag3");
        FindTagCommand command = (FindTagCommand) parser.parseCommand(
                FindTagCommand.SHORTENED_COMMAND_WORD + " " + String.join(" ", tagKeywords));
        assertEquals(new FindTagCommand(
                new PersonContainsTagsPredicate(tagKeywords),
                new TaskContainsTagsPredicate(tagKeywords)),
                command);
    }

    @Test
    public void parseCommand_findAllTag() throws Exception {
        List<String> tagKeywords = Arrays.asList("tag1", "tag2", "tag3");
        FindAllTagCommand command = (FindAllTagCommand) parser.parseCommand(
                FindAllTagCommand.COMMAND_WORD + " " + String.join(" ", tagKeywords));
        assertEquals(new FindAllTagCommand(
                         new PersonContainsAllTagsPredicate(tagKeywords), new TaskContainsAllTagsPredicate(tagKeywords)
                ),
                command);
    }

    @Test
    public void parseCommand_shortened_findAllTag() throws Exception {
        List<String> tagKeywords = Arrays.asList("tag1", "tag2", "tag3");
        FindAllTagCommand command = (FindAllTagCommand) parser.parseCommand(
                FindAllTagCommand.SHORTENED_COMMAND_WORD + " " + String.join(" ", tagKeywords));
        assertEquals(new FindAllTagCommand(
                        new PersonContainsAllTagsPredicate(tagKeywords),
                        new TaskContainsAllTagsPredicate(tagKeywords)),
                command);
    }

    @Test
    public void parseCommand_findDone() throws Exception {
        assertTrue(parser.parseCommand(FindDoneCommand.COMMAND_WORD) instanceof FindDoneCommand);
        assertTrue(parser.parseCommand(FindDoneCommand.COMMAND_WORD + " 3") instanceof FindDoneCommand);
    }

    @Test
    public void parseCommand_shortened_findDone() throws Exception {
        assertTrue(parser.parseCommand(FindDoneCommand.SHORTENED_COMMAND_WORD) instanceof FindDoneCommand);
        assertTrue(parser.parseCommand(FindDoneCommand.SHORTENED_COMMAND_WORD + " 3") instanceof FindDoneCommand);
    }

    @Test
    public void parseCommand_findNotDone() throws Exception {
        assertTrue(parser.parseCommand(FindNotDoneCommand.COMMAND_WORD) instanceof FindNotDoneCommand);
        assertTrue(parser.parseCommand(FindNotDoneCommand.COMMAND_WORD + " 3") instanceof FindNotDoneCommand);
    }

    @Test
    public void parseCommand_shortened_findNotDone() throws Exception {
        assertTrue(parser.parseCommand(FindNotDoneCommand.SHORTENED_COMMAND_WORD) instanceof FindNotDoneCommand);
        assertTrue(parser.parseCommand(FindNotDoneCommand.SHORTENED_COMMAND_WORD + " 3") instanceof FindNotDoneCommand);
    }

    @Test
    public void parseCommand_markTask() throws Exception {
        MarkTaskCommand command = (MarkTaskCommand) parser.parseCommand(
                MarkTaskCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new MarkTaskCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_shortened_markTask() throws Exception {
        MarkTaskCommand command = (MarkTaskCommand) parser.parseCommand(
                MarkTaskCommand.SHORTENED_COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new MarkTaskCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_unmarkTask() throws Exception {
        UnmarkTaskCommand command = (UnmarkTaskCommand) parser.parseCommand(
                UnmarkTaskCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new UnmarkTaskCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_shortened_unmarkTask() throws Exception {
        UnmarkTaskCommand command = (UnmarkTaskCommand) parser.parseCommand(
                UnmarkTaskCommand.SHORTENED_COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new UnmarkTaskCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editTask() throws Exception {
        Task task = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(EditTaskCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + TaskUtil.getEditTaskDescriptorDetails(descriptor));
        assertEquals(new EditTaskCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_shortened_editTask() throws Exception {
        Task task = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(EditTaskCommand.SHORTENED_COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + TaskUtil.getEditTaskDescriptorDetails(descriptor));
        assertEquals(new EditTaskCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_addTagTask() throws Exception {
        Tag tag = new Tag("class");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        AddTagTaskCommand command = (AddTagTaskCommand) parser.parseCommand(AddTagTaskCommand.COMMAND_WORD
                + " " + INDEX_FIRST.getOneBased() + " " + " t/class");
        assertEquals(new AddTagTaskCommand(INDEX_FIRST, tags), command);
    }

    @Test
    public void parseCommand_shortened_addTagTask() throws Exception {
        Tag tag = new Tag("class");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        AddTagTaskCommand command = (AddTagTaskCommand) parser.parseCommand(AddTagTaskCommand.SHORTENED_COMMAND_WORD
                + " " + INDEX_FIRST.getOneBased() + " " + " t/class");
        assertEquals(new AddTagTaskCommand(INDEX_FIRST, tags), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_shortened_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.SHORTENED_COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteAllTask() throws Exception {
        assertTrue(parser.parseCommand(DeleteAllTaskCommand.COMMAND_WORD) instanceof DeleteAllTaskCommand);
        assertTrue(parser.parseCommand(DeleteAllTaskCommand.COMMAND_WORD + " 3") instanceof DeleteAllTaskCommand);
    }

    @Test
    public void parseCommand_shortened_deleteAllTask() throws Exception {
        assertTrue(parser.parseCommand(DeleteAllTaskCommand.SHORTENED_COMMAND_WORD) instanceof DeleteAllTaskCommand);
        assertTrue(
                parser.parseCommand(
                        DeleteAllTaskCommand.SHORTENED_COMMAND_WORD + " 3") instanceof DeleteAllTaskCommand);
    }

    @Test
    public void parseCommand_deleteAllDone() throws Exception {
        assertTrue(parser.parseCommand(DeleteAllDoneCommand.COMMAND_WORD) instanceof DeleteAllDoneCommand);
        assertTrue(
                parser.parseCommand(
                        DeleteAllDoneCommand.COMMAND_WORD + " 3") instanceof DeleteAllDoneCommand);
    }

    @Test
    public void parseCommand_shortened_deleteAllDone() throws Exception {
        assertTrue(parser.parseCommand(DeleteAllDoneCommand.SHORTENED_COMMAND_WORD) instanceof DeleteAllDoneCommand);
        assertTrue(
                parser.parseCommand(
                        DeleteAllDoneCommand.SHORTENED_COMMAND_WORD + " 3") instanceof DeleteAllDoneCommand);
    }

    @Test
    public void parseCommand_listTag() throws Exception {
        assertTrue(parser.parseCommand(ListTagCommand.COMMAND_WORD) instanceof ListTagCommand);
        assertTrue(parser.parseCommand(ListTagCommand.COMMAND_WORD + " 3") instanceof ListTagCommand);
    }

    @Test
    public void parseCommand_shortened_listTag() throws Exception {
        assertTrue(parser.parseCommand(ListTagCommand.SHORTENED_COMMAND_WORD) instanceof ListTagCommand);
        assertTrue(parser.parseCommand(ListTagCommand.SHORTENED_COMMAND_WORD + " 3") instanceof ListTagCommand);
    }

    @Test
    public void parseCommand_deleteTagPerson() throws Exception {
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        DeleteTagPersonCommand command = (DeleteTagPersonCommand) parser.parseCommand(
                DeleteTagPersonCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased() + " t/caterer");
        assertEquals(new DeleteTagPersonCommand(INDEX_FIRST, tags), command);
    }

    @Test
    public void parseCommand_shortened_deleteTagPerson() throws Exception {
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        DeleteTagPersonCommand command = (DeleteTagPersonCommand) parser.parseCommand(
                DeleteTagPersonCommand.SHORTENED_COMMAND_WORD + " " + INDEX_FIRST.getOneBased()
                        + " t/caterer");
        assertEquals(new DeleteTagPersonCommand(INDEX_FIRST, tags), command);
    }

    @Test
    public void parseCommand_deleteTagTask() throws Exception {
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        DeleteTagTaskCommand command = (DeleteTagTaskCommand) parser.parseCommand(
                DeleteTagTaskCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased() + " t/caterer");
        assertEquals(new DeleteTagTaskCommand(INDEX_FIRST, tags), command);
    }

    @Test
    public void parseCommand_shortened_deleteTagTask() throws Exception {
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        DeleteTagTaskCommand command = (DeleteTagTaskCommand) parser.parseCommand(
                DeleteTagTaskCommand.SHORTENED_COMMAND_WORD + " " + INDEX_FIRST.getOneBased()
                        + " t/caterer");
        assertEquals(new DeleteTagTaskCommand(INDEX_FIRST, tags), command);
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
