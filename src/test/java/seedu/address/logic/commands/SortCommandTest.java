package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertInternshipCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternshipBook;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.InternshipComparators;

public class SortCommandTest {
    private InternshipModel model = new InternshipModelManager(getTypicalInternshipBook(), new InternshipUserPrefs());

    @Test
    public void execute_sortByCompanyNameAsc_success() {
        SortCommand sortCommand = new SortCommand(PREFIX_COMPANY_NAME, SortCommand.Order.ASC);
        String expectedMessage = String.format(sortCommand.messageSuccess);
        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());
        expectedModel.sortInternships(InternshipComparators.BY_COMPANY_NAME);
        assertInternshipCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_sortByCompanyNameDesc_success() {
        SortCommand sortCommand = new SortCommand(PREFIX_COMPANY_NAME, SortCommand.Order.DESC);
        String expectedMessage = String.format(sortCommand.messageSuccess);
        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());
        expectedModel.sortInternships(InternshipComparators.BY_COMPANY_NAME.reversed());
        assertInternshipCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_sortByRoleAsc_success() {
        SortCommand sortCommand = new SortCommand(PREFIX_ROLE, SortCommand.Order.ASC);
        String expectedMessage = String.format(sortCommand.messageSuccess);
        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());
        expectedModel.sortInternships(InternshipComparators.BY_ROLE);
        assertInternshipCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_sortByDeadlineAsc_success() {
        SortCommand sortCommand = new SortCommand(PREFIX_DEADLINE, SortCommand.Order.ASC);
        String expectedMessage = String.format(sortCommand.messageSuccess);
        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());
        expectedModel.sortInternships(InternshipComparators.BY_DEADLINE);
        assertInternshipCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_sortByDurationAsc_success() {
        SortCommand sortCommand = new SortCommand(PREFIX_DURATION, SortCommand.Order.ASC);
        String expectedMessage = String.format(sortCommand.messageSuccess);
        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());
        expectedModel.sortInternships(InternshipComparators.BY_DURATION);
        assertInternshipCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_sortByStartDateAsc_success() {
        SortCommand sortCommand = new SortCommand(PREFIX_START_DATE, SortCommand.Order.ASC);
        String expectedMessage = String.format(sortCommand.messageSuccess);
        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());
        expectedModel.sortInternships(InternshipComparators.BY_START_DATE);
        assertInternshipCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_sortByApplicationStatusAsc_success() {
        SortCommand sortCommand = new SortCommand(PREFIX_APPLICATION_STATUS, SortCommand.Order.ASC);
        String expectedMessage = String.format(sortCommand.messageSuccess);
        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());
        expectedModel.sortInternships(InternshipComparators.BY_APPLICATION_STATUS);
        assertInternshipCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_defaultConstructor_sortByDefaultValues() {
        // This tests the default sorting behavior
        SortCommand sortCommand = new SortCommand();
        String expectedMessage = String.format(sortCommand.messageSuccess);
        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());
        expectedModel.sortInternships(InternshipComparators.BY_COMPANY_NAME); // assuming default is company name ASC
        assertInternshipCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void testEquals() {
        SortCommand sortCommand1 = new SortCommand(PREFIX_COMPANY_NAME, SortCommand.Order.ASC);
        SortCommand sortCommand2 = new SortCommand(PREFIX_COMPANY_NAME, SortCommand.Order.ASC);
        assertTrue(sortCommand1.equals(sortCommand2));
        assertFalse(sortCommand1.equals(null));
        assertTrue(sortCommand1.equals(sortCommand1));
    }

    @Test
    public void testToString() {
        SortCommand sortCommand = new SortCommand(PREFIX_COMPANY_NAME, SortCommand.Order.ASC);
        String expectedString = SortCommand.class.getCanonicalName() + "{category=" + PREFIX_COMPANY_NAME
                + ", sortedOrder=" + SortCommand.Order.ASC + "}";
        assertEquals(expectedString, sortCommand.toString());
    }


}
