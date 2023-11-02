package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.COMMANDWORD_DESC_VALID;
import static seedu.address.logic.commands.CommandTestUtil.COMMAND_WORD_1;
import static seedu.address.logic.commands.CommandTestUtil.SHORTCUT_ALIAS_1;
import static seedu.address.logic.commands.CommandTestUtil.SHORTCUT_DESC_VALID;
import static seedu.address.logic.parser.CliSyntax.PATIENT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.SPECIALIST_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.address.logic.commands.EditCommand.EditSpecialistDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindPredicateMap;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Patient;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.Specialist;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.EditSpecialistDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PatientUtil;
import seedu.address.testutil.SpecialistBuilder;
import seedu.address.testutil.SpecialistUtil;

public class AddressBookParserTest {
    private final Model model = new ModelManager();
    private final AddressBookParser parser = new AddressBookParser(model);

    @Test
    public void parseCommand_addShortcut() throws Exception {
        AddShortcutCommand command = (AddShortcutCommand) parser.parseCommand(AddShortcutCommand.COMMAND_WORD
                + SHORTCUT_DESC_VALID + COMMANDWORD_DESC_VALID);
        assertEquals(new AddShortcutCommand(SHORTCUT_ALIAS_1, COMMAND_WORD_1), command);
    }

    @Test
    public void parseCommand_deleteShortcut() throws Exception {
        DeleteShortcutCommand command = (DeleteShortcutCommand) parser.parseCommand(DeleteShortcutCommand.COMMAND_WORD
                + SHORTCUT_DESC_VALID);
        assertEquals(new DeleteShortcutCommand(new ArrayList<>() {
            {
                add(SHORTCUT_ALIAS_1);
            }
        }), command);
    }

    @Test
    public void parseCommand_add_patient() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PatientUtil.getAddCommand(patient));
        assertEquals(new AddCommand(patient), command);
    }

    @Test
    public void parseCommand_add_specialist() throws Exception {
        Specialist specialist = new SpecialistBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(SpecialistUtil.getAddCommand(specialist));
        assertEquals(new AddCommand(specialist), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        List<Index> indexList =
                Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(2), Index.fromOneBased(4));
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + "1 2 4");
        assertEquals(new DeleteCommand(indexList), command);
    }

    @Test
    public void parseCommand_edit_patient() throws Exception {
        Patient patient = (Patient) new PatientBuilder()
                .withMedicalHistory("MedHistory1")
                .withTags("Tag1", "Tag2")
                .build();
        model.addPerson(patient);
        model.updateSelectedPerson(patient);
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        String input = EditCommand.COMMAND_WORD + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor);
        EditCommand command = (EditCommand) parser.parseCommand(input);
        assertEquals(new EditCommand(descriptor), command);
        model.deletePerson(patient);
    }

    @Test
    public void parseCommand_edit_specialist() throws Exception {
        Specialist specialist = (Specialist) new SpecialistBuilder()
                .withSpecialty("TestSpecialty")
                .withLocation("TestLocation")
                .withTags("Tag1", "Tag2")
                .build();
        model.addPerson(specialist);
        model.updateSelectedPerson(specialist);
        EditSpecialistDescriptor descriptor = new EditSpecialistDescriptorBuilder(specialist).build();
        String input = EditCommand.COMMAND_WORD
                + " " + SpecialistUtil.getEditSpecialistDescriptorDetails(descriptor);
        EditCommand command = (EditCommand) parser.parseCommand(input);
        assertEquals(new EditCommand(descriptor), command);
        model.deletePerson(specialist);
    }

    @Test
    public void parseCommand_complexEdit_failure() {
        Patient patient = (Patient) new PatientBuilder()
                .withMedicalHistory("MedHistory1")
                .withTags("Tag1", "Tag2")
                .build();
        Specialist specialist = (Specialist) new SpecialistBuilder()
                .withSpecialty("TestSpecialty")
                .withLocation("TestLocation")
                .withTags("Tag1", "Tag2")
                .build();
        String userInput1 = EditCommand.COMMAND_WORD + " " + PATIENT_TAG;
        String userInput2 = EditCommand.COMMAND_WORD + " " + SPECIALIST_TAG;
        model.addPerson(patient);
        model.updateSelectedPerson(patient);
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE_PATIENT), () -> parser.parseCommand(userInput1));
        model.deletePerson(patient);
        model.addPerson(specialist);
        model.updateSelectedPerson(specialist);
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE_SPECIALIST), () -> parser.parseCommand(userInput2));
        model.deletePerson(specialist);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find_patientByName() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + CliSyntax.PATIENT_TAG + " "
                        + PREFIX_NAME
                        + keywords.stream().collect(Collectors.joining(" ")));
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_NAME, new NameContainsKeywordsPredicate(keywords));
        assertEquals(findPredicateMap, command.getPredicate());
        assertEquals(PersonType.PATIENT, command.getPersonType());
    }

    @Test
    public void parseCommand_find_specialistByNameAndTags() throws Exception {
        List<String> nameKeywords = Arrays.asList("foo", "bar", "baz");
        List<String> tagKeywords = Arrays.asList("tag1", "tag2");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + CliSyntax.SPECIALIST_TAG + " "
                        + PREFIX_NAME
                        + nameKeywords.stream().collect(Collectors.joining(" ")) + " "
                        + PREFIX_TAG
                        + tagKeywords.stream().collect(Collectors.joining(" ")));

        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_NAME, new NameContainsKeywordsPredicate(nameKeywords));
        findPredicateMap.put(PREFIX_TAG, new TagsContainsKeywordsPredicate(tagKeywords));
        assertEquals(findPredicateMap, command.getPredicate());
        assertEquals(PersonType.SPECIALIST, command.getPersonType());
    }

    @Test
    public void parseCommand_view_patient() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ViewCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(
                ListCommand.COMMAND_WORD + " " + CliSyntax.PATIENT_TAG) instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_basicCommandAsComplexCommand_throwsParseException() {

        String deleteInput = DeleteCommand.COMMAND_WORD + " " + PATIENT_TAG;
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), ()
                        -> parser.parseCommand(deleteInput));

        String undoInput = UndoCommand.COMMAND_WORD + " " + SPECIALIST_TAG;
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE), ()
                        -> parser.parseCommand(undoInput));

        String redoInput = RedoCommand.COMMAND_WORD + " " + PATIENT_TAG;
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedoCommand.MESSAGE_USAGE), ()
                        -> parser.parseCommand(redoInput));

        String addShortcutInput = AddShortcutCommand.COMMAND_WORD + " " + SPECIALIST_TAG;
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE), ()
                        -> parser.parseCommand(addShortcutInput));

        String delShortcutInput = DeleteShortcutCommand.COMMAND_WORD + " " + PATIENT_TAG;
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteShortcutCommand.MESSAGE_USAGE), ()
                        -> parser.parseCommand(delShortcutInput));

        String viewInput = ViewCommand.COMMAND_WORD + " " + SPECIALIST_TAG;
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), ()
                        -> parser.parseCommand(viewInput));

        String themeInput = ThemeCommand.COMMAND_WORD + " " + PATIENT_TAG;
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE), ()
                        -> parser.parseCommand(themeInput));
    }
}
