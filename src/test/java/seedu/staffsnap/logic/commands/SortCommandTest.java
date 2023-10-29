package seedu.staffsnap.logic.commands;

import static seedu.staffsnap.testutil.TypicalApplicants.getApplicantsSortedByEmail;
import static seedu.staffsnap.testutil.TypicalApplicants.getApplicantsSortedByName;
import static seedu.staffsnap.testutil.TypicalApplicants.getApplicantsSortedByPhone;
import static seedu.staffsnap.testutil.TypicalApplicants.getApplicantsSortedByPosition;
import static seedu.staffsnap.testutil.TypicalApplicants.getApplicantsSortedByScore;
import static seedu.staffsnap.testutil.TypicalApplicants.getApplicantsSortedByStatus;
import static seedu.staffsnap.testutil.TypicalApplicants.getUnsortedApplicantBook;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
import seedu.staffsnap.model.UserPrefs;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Descriptor;

class SortCommandTest {

    private Model model;

    @Test
    void execute_sortName_ascending() {
        model = new ModelManager(getUnsortedApplicantBook(), new UserPrefs());
        try {
            new SortCommand(Descriptor.NAME, false).execute(model);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
        assert(model.getFilteredApplicantList().equals(getApplicantsSortedByName()));
    }

    @Test
    void execute_sortPhone_ascending() {
        model = new ModelManager(getUnsortedApplicantBook(), new UserPrefs());
        try {
            new SortCommand(Descriptor.PHONE, false).execute(model);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
        assert(model.getFilteredApplicantList().equals(getApplicantsSortedByPhone()));
    }
    @Test
    void execute_sortScore_ascending() {
        model = new ModelManager(getUnsortedApplicantBook(), new UserPrefs());
        try {
            new SortCommand(Descriptor.SCORE, false).execute(model);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
        assert(model.getFilteredApplicantList().equals(getApplicantsSortedByScore()));
    }

    @Test
    void execute_sortStatus_ascending() {
        model = new ModelManager(getUnsortedApplicantBook(), new UserPrefs());
        try {
            new SortCommand(Descriptor.STATUS, false).execute(model);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
        assert(model.getFilteredApplicantList().equals(getApplicantsSortedByStatus()));
    }

    @Test
    void execute_sortEmail_ascending() {
        model = new ModelManager(getUnsortedApplicantBook(), new UserPrefs());
        try {
            new SortCommand(Descriptor.EMAIL, false).execute(model);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
        assert(model.getFilteredApplicantList().equals(getApplicantsSortedByEmail()));
    }

    @Test
    void execute_sortPosition_ascending() {
        model = new ModelManager(getUnsortedApplicantBook(), new UserPrefs());
        try {
            new SortCommand(Descriptor.POSITION, false).execute(model);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
        assert(model.getFilteredApplicantList().equals(getApplicantsSortedByPosition()));
    }

    @Test
    void execute_sortName_descending() {
        model = new ModelManager(getUnsortedApplicantBook(), new UserPrefs());
        try {
            new SortCommand(Descriptor.NAME, true).execute(model);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
        List<Applicant> applicantList = getApplicantsSortedByName();
        Collections.reverse(applicantList);
        assert(model.getFilteredApplicantList().equals(applicantList));
    }
}

