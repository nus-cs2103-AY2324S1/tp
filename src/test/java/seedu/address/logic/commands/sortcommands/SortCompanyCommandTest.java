package seedu.address.logic.commands.sortcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.internship.InternshipInterviewDateTime;

public class SortCompanyCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }
    @Test
    public void execute_sortCompany_success() throws Exception {
        SortCompanyCommand sortCompanyCommand = new SortCompanyCommand(
            Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2023, 12, 1, 0, 0))),
            Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2024, 1, 20, 0, 0)))
        );

        assertEquals(new RegularCommandResult(SortCompanyCommand.MESSAGE_SUCCESS), sortCompanyCommand.execute(model));

        // In v1.4 we might want to add assertions to check that the companies in the model are sorted correctly.
    }

    @Test
    public void equals() {
        SortCompanyCommand sortFirstCommand = new SortCompanyCommand(
            Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2023, 12, 1, 0, 0))),
            Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2024, 1, 20, 0, 0)))
        );

        SortCompanyCommand sortSecondCommand = new SortCompanyCommand(
            Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2023, 11, 1, 0, 0))),
            Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2024, 1, 10, 0, 0)))
        );

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCompanyCommand sortFirstCommandCopy = new SortCompanyCommand(
            Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2023, 12, 1, 0, 0))),
            Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2024, 1, 20, 0, 0)))
        );
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different company -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }
}
