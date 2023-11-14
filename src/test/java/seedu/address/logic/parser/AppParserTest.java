package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddOrganizationCommand;
import seedu.address.logic.commands.ApplyCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.contact.Organization;
import seedu.address.testutil.ContactUtil;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.OrganizationBuilder;

public class AppParserTest {

    private final AppParser parser = new AppParser();

    @Test
    public void parseCommand_addOrganization() throws Exception {
        Organization organization = new OrganizationBuilder().build();
        var parsedAddCommand = (AddOrganizationCommand) parser.parseCommand(ContactUtil.getAddOrgCommand(organization));
        AddOrganizationCommand addCommand = new AddOrganizationCommand(
                organization.getName(),
                organization.getId(),
                organization.getPhone().orElse(null),
                organization.getEmail().orElse(null),
                organization.getUrl().orElse(null),
                organization.getAddress().orElse(null),
                organization.getTags()
        );
        assertEquals(addCommand, parsedAddCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Organization organization = new OrganizationBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(organization).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CONTACT.getOneBased() + " " + ContactUtil.getEditContactDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_CONTACT, descriptor), command);
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
    public void parseCommand_apply() throws Exception {
        assertTrue(parser.parseCommand(ApplyCommand.COMMAND_WORD + " id_1 --title SWE") instanceof ApplyCommand);
        assertTrue(parser.parseCommand(ApplyCommand.COMMAND_WORD + " 3 --title SWE") instanceof ApplyCommand);
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

    @Test
    public void parseCompletionGenerator_knownSubsequence_canGenerateCorrectSuggestions() {
        // Add example
        String userInput = "add --org -o";
        assertEquals(
                List.of(
                        "add --org --phone"
                ),
                parser.parseCompletionGenerator(userInput)
                        .generateCompletions(userInput)
                        .collect(Collectors.toList())
        );

        // Edit example
        userInput = "edit 1 --phone 12345678 --nm";
        assertEquals(
                List.of(
                        "edit 1 --phone 12345678 --name"
                ),
                parser.parseCompletionGenerator(userInput)
                        .generateCompletions(userInput)
                        .collect(Collectors.toList())
        );

        // List example
        userInput = "list -o";
        assertEquals(
                List.of("list --org", "list --toapply"),
                parser.parseCompletionGenerator(userInput)
                        .generateCompletions(userInput)
                        .collect(Collectors.toList())
        );

        // Delete example
        userInput = "delete 0 --re";
        assertEquals(
                List.of("delete 0 --recursive"),
                parser.parseCompletionGenerator(userInput)
                        .generateCompletions(userInput)
                        .collect(Collectors.toList())
        );

        // Extra middle contents example
        userInput = "edit 1 --phone 12345678 --nm";
        assertEquals(
                List.of(
                        "edit 1 --phone 12345678 --name"
                ),
                parser.parseCompletionGenerator(userInput)
                        .generateCompletions(userInput)
                        .collect(Collectors.toList())
        );

        // Command words example
        userInput = "e";
        assertEquals(
                List.of(
                        "edit",
                        "exit",
                        "delete",
                        "remind",
                        "help",
                        "clear"
                ),
                parser.parseCompletionGenerator(userInput)
                        .generateCompletions(userInput)
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void parseCompletionGenerator_unknownSubsequence_willGenerateNoResults() {
        String userInput = "add -asdf";
        assertEquals(
                List.of(),
                parser.parseCompletionGenerator(userInput)
                        .generateCompletions(userInput)
                        .collect(Collectors.toList())
        );

        userInput = "edit 1 -xxx";
        assertEquals(
                List.of(),
                parser.parseCompletionGenerator(userInput)
                        .generateCompletions(userInput)
                        .collect(Collectors.toList())
        );
    }
}
