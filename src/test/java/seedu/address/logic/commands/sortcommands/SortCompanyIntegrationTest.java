package seedu.address.logic.commands.sortcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.index.Index.fromZeroBased;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBookForSort;
import static seedu.address.testutil.TypicalInternships.DATETIME_PRE_B;
import static seedu.address.testutil.TypicalInternships.DATETIME_PRE_D;
import static seedu.address.testutil.TypicalInternships.DATETIME_PRE_G;
import static seedu.address.testutil.TypicalInternships.DATETIME_PRE_H;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.logic.commands.deletecommands.DeleteCompanyCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;

public class SortCompanyIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookForSort(), new UserPrefs());
    }

    // Test for cases where there are no companies in the specified time range.
    @Test
    public void execute_sortCompanyNoCompanyInTimeRange_success() throws Exception {
        SortCompanyCommand sortCompanyCommand = new SortCompanyCommand(
            Optional.of(DATETIME_PRE_G),
            Optional.of(DATETIME_PRE_H)
        );
        // Assert that after the command is executed, there is no company in the filtered list.
        assertEquals(new RegularCommandResult(SortCompanyCommand.MESSAGE_SUCCESS), sortCompanyCommand.execute(model));
        assertEquals(0, model.getFilteredCompanyList().size());

    }

    // Test for cases where two companies have the interview dates in the specified time range.
    @Test
    public void execute_sortCompanyStartEnd_success() throws Exception {
        SortCompanyCommand sortCompanyCommand = new SortCompanyCommand(
            Optional.of(DATETIME_PRE_B),
            Optional.of(DATETIME_PRE_D)
        );
        assertEquals(new RegularCommandResult(SortCompanyCommand.MESSAGE_SUCCESS), sortCompanyCommand.execute(model));
        assertEquals(4, model.getFilteredCompanyList().size());
        List<String> expectedCompaniesGroup1 = Arrays.asList("Internship AB", "Internship BC");
        List<String> expectedCompaniesGroup2 = Arrays.asList("Internship AC", "Internship CD");
        List<String> actualCompanyNames = new ArrayList<>();

        for (Company company : model.getFilteredCompanyList()) {
            actualCompanyNames.add(company.getCompanyName().toString());
        }

        assertTrue(actualCompanyNames.subList(0, 2).containsAll(expectedCompaniesGroup1));
        assertTrue(actualCompanyNames.subList(2, 4).containsAll(expectedCompaniesGroup2));
    }

    // Test for cases where there is no time range specified.
    @Test
    public void execute_sortCompanyNoTimeRange_success() throws Exception {
        SortCompanyCommand sortCompanyCommand = new SortCompanyCommand(
            Optional.empty(),
            Optional.empty()
        );
        assertEquals(new RegularCommandResult(SortCompanyCommand.MESSAGE_SUCCESS), sortCompanyCommand.execute(model));
        assertEquals(5, model.getFilteredCompanyList().size());
    }

    // Test for cases where only the start date is specified.
    @Test
    public void execute_sortCompanyStart_success() throws Exception {
        SortCompanyCommand sortCompanyCommandNext = new SortCompanyCommand(
            Optional.of(DATETIME_PRE_B),
            Optional.empty()
        );
        assertEquals(new RegularCommandResult(SortCompanyCommand.MESSAGE_SUCCESS),
            sortCompanyCommandNext.execute(model));
        assertEquals(5, model.getFilteredCompanyList().size());
        List<String> expectedCompaniesGroup1 = Arrays.asList("Internship AB", "Internship BC");
        List<String> expectedCompaniesGroup2 = Arrays.asList("Internship AC", "Internship CD");
        List<String> actualCompanyNames = new ArrayList<>();

        for (Company company : model.getFilteredCompanyList()) {
            actualCompanyNames.add(company.getCompanyName().toString());
        }

        assertTrue(actualCompanyNames.subList(0, 2).containsAll(expectedCompaniesGroup1));
        assertTrue(actualCompanyNames.subList(2, 4).containsAll(expectedCompaniesGroup2));
        actualCompanyNames.get(4).equals("Internship DE");
    }
    // Test for cases where two sort commands are executed in a row.
    @Test
    public void execute_sortCompanyTwo_success() throws Exception {
        SortCompanyCommand sortCompanyCommand = new SortCompanyCommand(
            Optional.of(DATETIME_PRE_B),
            Optional.of(DATETIME_PRE_D)
        );
        assertEquals(new RegularCommandResult(SortCompanyCommand.MESSAGE_SUCCESS),
            sortCompanyCommand.execute(model));
        SortCompanyCommand sortCompanyCommandNext = new SortCompanyCommand(
            Optional.of(DATETIME_PRE_B),
            Optional.empty()
        );
        assertEquals(new RegularCommandResult(SortCompanyCommand.MESSAGE_SUCCESS),
            sortCompanyCommandNext.execute(model));
        assertEquals(5, model.getFilteredCompanyList().size());
        List<String> expectedCompaniesGroup1 = Arrays.asList("Internship AB", "Internship BC");
        List<String> expectedCompaniesGroup2 = Arrays.asList("Internship AC", "Internship CD");
        List<String> actualCompanyNames = new ArrayList<>();

        for (Company company : model.getFilteredCompanyList()) {
            actualCompanyNames.add(company.getCompanyName().toString());
        }

        assertTrue(actualCompanyNames.subList(0, 2).containsAll(expectedCompaniesGroup1));
        assertTrue(actualCompanyNames.subList(2, 4).containsAll(expectedCompaniesGroup2));
        actualCompanyNames.get(4).equals("Internship DE");
    }

    // Test for cases where sort is followed by delete company command.
    @Test
    public void execute_sortCompanyDelete_success() throws Exception {
        SortCompanyCommand sortCompanyCommand = new SortCompanyCommand(
            Optional.of(DATETIME_PRE_B),
            Optional.empty()
        );
        assertEquals(new RegularCommandResult(SortCompanyCommand.MESSAGE_SUCCESS), sortCompanyCommand.execute(model));
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(fromZeroBased(4));
        deleteCompanyCommand.execute(model);
        assertEquals(4, model.getFilteredCompanyList().size());
        List<String> expectedCompaniesGroup1 = Arrays.asList("Internship AB", "Internship BC");
        List<String> expectedCompaniesGroup2 = Arrays.asList("Internship AC", "Internship CD");
        List<String> actualCompanyNames = new ArrayList<>();

        for (Company company : model.getFilteredCompanyList()) {
            actualCompanyNames.add(company.getCompanyName().toString());
        }

        assertTrue(actualCompanyNames.subList(0, 2).containsAll(expectedCompaniesGroup1));
        assertTrue(actualCompanyNames.subList(2, 4).containsAll(expectedCompaniesGroup2));
    }
}
