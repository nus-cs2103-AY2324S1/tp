package seedu.address.logic.commands.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertProjectCommandSuccess;
import static seedu.address.testutil.TypicalProjects.PROJECT_A;
import static seedu.address.testutil.TypicalProjects.PROJECT_A_NO_SPACING;
import static seedu.address.testutil.TypicalProjects.PROJECT_B;
import static seedu.address.testutil.TypicalProjects.PROJECT_C;
import static seedu.address.testutil.TypicalProjects.getTypicalAddressBook;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commons.Date;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Priority;

public class FindDeadlineCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_zeroKeywords_noDeadlineFound() throws ParseException {
        //String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        String expectedMessage = "There are no deadlines with matching information.";
        Predicate<Deadline> finalPredicate = deadline -> true;
        String dateKeywords = "10-10-2000";
        Date input = ParserUtil.parseDateDeadline(dateKeywords);
        finalPredicate =
                finalPredicate.and(d -> !d.getDate().value.after(input.value));
        FindDeadlineCommand command = new FindDeadlineCommand(finalPredicate);
        model.updateFilteredProjectDeadlineList(finalPredicate);
        assertProjectCommandSuccess(command, model, expectedMessage, model);
    }

    @Test
    public void execute_multipleKeywords_onePersonsFound() {
        //String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        String expectedMessage = "This is the 1 deadline with matching information.";
        Predicate<Deadline> finalPredicate = deadline -> true;
        finalPredicate = finalPredicate.and(d -> d.getPriority().equals(Priority.HIGH));
        FindDeadlineCommand command = new FindDeadlineCommand(finalPredicate);
        model.updateFilteredProjectDeadlineList(finalPredicate);
        assertProjectCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(PROJECT_A, PROJECT_B, PROJECT_C, PROJECT_A_NO_SPACING),
                model.getFilteredProjectList());
    }

}
