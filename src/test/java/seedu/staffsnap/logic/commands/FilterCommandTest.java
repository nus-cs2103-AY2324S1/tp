package seedu.staffsnap.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
import seedu.staffsnap.model.UserPrefs;
import seedu.staffsnap.model.applicant.CustomFilterPredicate;
import seedu.staffsnap.model.applicant.Email;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.applicant.Position;
import seedu.staffsnap.model.interview.Interview;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.staffsnap.logic.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.staffsnap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.staffsnap.testutil.TypicalApplicants.ALICE;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;
import static seedu.staffsnap.testutil.TypicalApplicants.CARL;
import static seedu.staffsnap.testutil.TypicalApplicants.DANIEL;
import static seedu.staffsnap.testutil.TypicalApplicants.ELLE;
import static seedu.staffsnap.testutil.TypicalApplicants.FIONA;
import static seedu.staffsnap.testutil.TypicalApplicants.getTypicalApplicantBook;

class FilterCommandTest {

    private Model model = new ModelManager(getTypicalApplicantBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalApplicantBook(), new UserPrefs());

    @Test
    public void equals() {
        Name name1 = BENSON.getName();
        Phone phone1 = BENSON.getPhone();
        Email email1 = BENSON.getEmail();
        Position position1 = BENSON.getPosition();
        Set<Interview> interviewList1 = BENSON.getInterviews();

        Name name2 = CARL.getName();
        Phone phone2 = CARL.getPhone();
        Email email2 = CARL.getEmail();
        Position position2 = CARL.getPosition();
        Set<Interview> interviewList2 = CARL.getInterviews();

        CustomFilterPredicate firstPredicate = new CustomFilterPredicate(name1, phone1, email1, position1, interviewList1);
        CustomFilterPredicate secondPredicate = new CustomFilterPredicate(name2, phone2, email2, position2, interviewList2);

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 7);
        CustomFilterPredicate predicate = new CustomFilterPredicate(null, null, null, null, null);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredApplicantList(), model.getFilteredApplicantList());
    }

    @Test
    public void execute_partialName_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 4);
        CustomFilterPredicate predicate = new CustomFilterPredicate(new Name("a"), null, null, null, null);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, DANIEL, FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleKeywords_singleApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 1);
        CustomFilterPredicate predicate = new CustomFilterPredicate(FIONA.getName(), FIONA.getPhone(), null, null, null);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleKeywords_zeroApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        CustomFilterPredicate predicate = new CustomFilterPredicate(
                ALICE.getName(), BENSON.getPhone(), CARL.getEmail(), DANIEL.getPosition(), ELLE.getInterviews());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        System.out.println(model.getFilteredApplicantList());
        assertEquals(Arrays.asList(), model.getFilteredApplicantList());
    }


    @Test
    public void toStringMethod() {
        CustomFilterPredicate predicate = new CustomFilterPredicate(FIONA.getName(), null, null, null, null);
        FilterCommand findCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

}