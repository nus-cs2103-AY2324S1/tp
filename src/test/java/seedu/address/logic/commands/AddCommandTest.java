package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.META;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.stubs.ModelStub;
import seedu.address.logic.commands.stubs.ModelStubAcceptingCompanyAdded;
import seedu.address.logic.commands.stubs.ModelStubWithCompanyInFilteredCompanyList;
import seedu.address.logic.commands.stubs.ModelStubWithCompanyNotInFilteredCompanyList;
import seedu.address.model.company.Company;
import seedu.address.testutil.CompanyBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_companyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCompanyAdded modelStub = new ModelStubAcceptingCompanyAdded();
        Company validCompany = new CompanyBuilder().build();

        CommandResult commandResult = new AddCommand(validCompany).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(validCompany)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCompany), modelStub.companiesAdded);
    }

    //Duplicate tests, considered duplicate if same company name, same role and deadline
    @Test
    public void execute_fullyDuplicatedCompany_throwsCommandException() {
        Company validCompany = new CompanyBuilder().build();
        AddCommand addCommand = new AddCommand(validCompany);
        ModelStub modelStub = new ModelStubWithCompanyNotInFilteredCompanyList(validCompany);

        assertThrows(CommandException.DuplicateException.class,
                new CommandException.DuplicateException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(validCompany,
                        modelStub.getDuplicateIndexFromOriginalAddressbook(validCompany),
                        validCompany.listAllChangedFields(
                                modelStub.getDuplicateCompany(validCompany)),
                                false)).getMessage(), (
                ) -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_successDifferentCompanyName_doesNotThrowCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithDifferentNames = companyBuilder.withName("Google").build();
        ModelStubWithCompanyNotInFilteredCompanyList modelStub =
                new ModelStubWithCompanyNotInFilteredCompanyList(validCompany);

        assertFalse(validCompany.isSameCompany(duplicateCompanyWithDifferentNames));

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithDifferentNames).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithDifferentNames)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithDifferentNames));
    }

    @Test
    public void execute_successOnlyDifferentRoles_addsCompany() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithDifferentRole = companyBuilder.withRole("Chief Designer").build();
        ModelStubWithCompanyNotInFilteredCompanyList modelStub =
                new ModelStubWithCompanyNotInFilteredCompanyList(validCompany);

        assertFalse(validCompany.isSameCompany(duplicateCompanyWithDifferentRole));

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithDifferentRole).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                duplicateCompanyWithDifferentRole)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithDifferentRole));
    }

    @Test
    public void execute_onlySameCompanyAndSameRole_throwsCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompany = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withPriority("HIGH")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .build();

        assert duplicateCompany.isSameCompany(validCompany);
        AddCommand addCommand = new AddCommand(duplicateCompany);
        ModelStub modelStub = new ModelStubWithCompanyNotInFilteredCompanyList(validCompany);

        //assumes the caller of listAllChangedFields() is correct
        assertThrows(CommandException.DuplicateException.class,
                new CommandException.DuplicateException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(validCompany,
                                modelStub.getDuplicateIndexFromOriginalAddressbook(validCompany),
                                duplicateCompany.listAllChangedFields(
                                        modelStub.getDuplicateCompany(duplicateCompany)),
                                false)).getMessage(), (
                ) -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_successOnlySameRole() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithSameRole = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withDeadline("12-10-2015")
                .withPriority("LOW")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .withName("Google")
                .build();

        ModelStubWithCompanyNotInFilteredCompanyList modelStub =
                new ModelStubWithCompanyNotInFilteredCompanyList(validCompany);

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithSameRole).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithSameRole)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithSameRole));
    }

    @Test
    public void execute_successOnlySameCompany() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithSameCompany = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withDeadline("12-10-2015")
                .withPriority("LOW")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .withRole("UI UX Designer")
                .build();

        ModelStubWithCompanyNotInFilteredCompanyList modelStub =
                new ModelStubWithCompanyNotInFilteredCompanyList(validCompany);

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithSameCompany).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithSameCompany)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithSameCompany));
    }

    //More than one company present in the address book
    @Test
    public void execute_successOneFullyDuplicatedCompanyInList_throwsCommandException() {
        Company validCompany = new CompanyBuilder().build();
        AddCommand addCommand = new AddCommand(validCompany);
        ModelStub modelStub = new ModelStubWithCompanyNotInFilteredCompanyList(validCompany, 1);

        assertThrows(CommandException.DuplicateException.class,
                new CommandException.DuplicateException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(validCompany,
                                modelStub.getDuplicateIndexFromOriginalAddressbook(validCompany),
                                validCompany.listAllChangedFields(
                                        modelStub.getDuplicateCompany(validCompany)),
                                false)).getMessage(), (
                ) -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_successTwoDifferentCompanyName_doesNotThrowCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithDifferentNames = companyBuilder.withName("Google").build();
        ModelStubWithCompanyNotInFilteredCompanyList modelStub =
                new ModelStubWithCompanyNotInFilteredCompanyList(validCompany, 2);

        assertFalse(validCompany.isSameCompany(duplicateCompanyWithDifferentNames));

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithDifferentNames).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithDifferentNames)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithDifferentNames));
    }

    @Test
    public void execute_successOnlyDifferentRolesWithThreeCompanies_addsCompany() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithDifferentRole = companyBuilder.withRole("Chief Designer").build();
        ModelStubWithCompanyNotInFilteredCompanyList modelStub =
                new ModelStubWithCompanyNotInFilteredCompanyList(validCompany, 3);

        assertFalse(validCompany.isSameCompany(duplicateCompanyWithDifferentRole));

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithDifferentRole).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithDifferentRole)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithDifferentRole));
    }

    @Test
    public void execute_onlySameCompanyAndSameRoleWithOneCompany_throwsCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompany = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withPriority("HIGH")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .build();

        assert duplicateCompany.isSameCompany(validCompany);
        AddCommand addCommand = new AddCommand(duplicateCompany);
        ModelStub modelStub = new ModelStubWithCompanyNotInFilteredCompanyList(validCompany, 1);

        //assumes the caller of listAllChangedFields() is correct
        assertThrows(CommandException.DuplicateException.class,
                new CommandException.DuplicateException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(validCompany,
                                modelStub.getDuplicateIndexFromOriginalAddressbook(validCompany),
                                duplicateCompany.listAllChangedFields(
                                        modelStub.getDuplicateCompany(duplicateCompany)),
                                false)).getMessage(), (
                ) -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_successOnlySameRoleWithTwoCompanies() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithSameRole = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withDeadline("12-10-2015")
                .withPriority("LOW")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .withName("Google")
                .build();

        ModelStubWithCompanyNotInFilteredCompanyList modelStub =
                new ModelStubWithCompanyNotInFilteredCompanyList(validCompany, 2);

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithSameRole).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithSameRole)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithSameRole));
    }

    @Test
    public void execute_successOnlySameCompanyWithThreeCompanies() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithSameCompany = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withDeadline("12-10-2015")
                .withPriority("LOW")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .withRole("UI UX Designer")
                .build();

        ModelStubWithCompanyNotInFilteredCompanyList modelStub =
                new ModelStubWithCompanyNotInFilteredCompanyList(validCompany, 3);

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithSameCompany).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithSameCompany)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithSameCompany));
    }

    //Duplicate tests - with duplicate in filtered company view
    @Test
    public void execute_fullyDuplicatedCompanyFiltered_throwsCommandException() {
        Company validCompany = new CompanyBuilder().build();
        AddCommand addCommand = new AddCommand(validCompany);
        ModelStub modelStub = new ModelStubWithCompanyInFilteredCompanyList(validCompany);

        assertThrows(CommandException.DuplicateException.class,
                new CommandException.DuplicateException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(validCompany,
                                modelStub.getDuplicateIndexFromOriginalAddressbook(validCompany),
                                validCompany.listAllChangedFields(
                                        modelStub.getDuplicateCompany(validCompany)),
                                true)).getMessage(), (
                ) -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_successDifferentCompanyNameFiltered_doesNotThrowCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithDifferentNames = companyBuilder.withName("Google").build();
        ModelStubWithCompanyInFilteredCompanyList modelStub =
                new ModelStubWithCompanyInFilteredCompanyList(validCompany);

        assertFalse(validCompany.isSameCompany(duplicateCompanyWithDifferentNames));

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithDifferentNames).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithDifferentNames)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithDifferentNames));
    }

    @Test
    public void execute_successOnlyDifferentRolesFiltered_addsCompany() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithDifferentRole = companyBuilder.withRole("Chief Designer").build();
        ModelStubWithCompanyInFilteredCompanyList modelStub =
                new ModelStubWithCompanyInFilteredCompanyList(validCompany);

        assertFalse(validCompany.isSameCompany(duplicateCompanyWithDifferentRole));

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithDifferentRole).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithDifferentRole)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithDifferentRole));
    }

    @Test
    public void execute_onlySameCompanyAndSameRoleFiltered_throwsCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompany = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withPriority("HIGH")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .build();

        assert duplicateCompany.isSameCompany(validCompany);
        AddCommand addCommand = new AddCommand(duplicateCompany);
        ModelStub modelStub = new ModelStubWithCompanyInFilteredCompanyList(validCompany);

        assertThrows(CommandException.DuplicateException.class,
                new CommandException.DuplicateException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(validCompany,
                                modelStub.getDuplicateIndexFromOriginalAddressbook(validCompany),
                                duplicateCompany.listAllChangedFields(
                                        modelStub.getDuplicateCompany(duplicateCompany)),
                                true)).getMessage(), (
                ) -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_successOnlySameRoleFiltered() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithSameRole = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withDeadline("12-10-2015")
                .withPriority("LOW")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .withName("Google")
                .build();

        ModelStubWithCompanyInFilteredCompanyList modelStub =
                new ModelStubWithCompanyInFilteredCompanyList(validCompany);

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithSameRole).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithSameRole)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithSameRole));
    }

    @Test
    public void execute_successOnlySameCompanyFiltered() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompanyWithSameCompany = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withDeadline("12-10-2015")
                .withPriority("LOW")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .withRole("UI UX Designer")
                .build();

        ModelStubWithCompanyInFilteredCompanyList modelStub =
                new ModelStubWithCompanyInFilteredCompanyList(validCompany);

        CommandResult commandResult = null;
        try {
            commandResult = new AddCommand(duplicateCompanyWithSameCompany).execute(modelStub);
        } catch (CommandException e) {
            fail("Execution of command should not throw CommandException.");
        }

        assertNotNull(commandResult);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(
                        duplicateCompanyWithSameCompany)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLastViewedCompany().equals(duplicateCompanyWithSameCompany));
    }


    @Test
    public void equals() {
        Company meta = new CompanyBuilder().withName("Meta").build();
        Company tiktok = new CompanyBuilder().withName("Tiktok").build();
        AddCommand addMetaCommand = new AddCommand(meta);
        AddCommand addTiktokCommand = new AddCommand(tiktok);

        // same object -> returns true
        assertTrue(addMetaCommand.equals(addMetaCommand));

        // same values -> returns true
        AddCommand addMetaCommandCopy = new AddCommand(meta);
        assertTrue(addMetaCommand.equals(addMetaCommandCopy));

        // different types -> returns false
        assertFalse(addMetaCommand.equals(1));

        // null -> returns false
        assertFalse(addMetaCommand.equals(null));

        // different company -> returns false
        assertFalse(addMetaCommand.equals(addTiktokCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(META);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + META + "}";
        assertEquals(expected, addCommand.toString());
    }
}
