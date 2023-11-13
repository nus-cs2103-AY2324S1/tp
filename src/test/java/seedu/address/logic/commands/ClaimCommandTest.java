package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ClaimCommand.ALLOCATE_SUCCESS;
import static seedu.address.logic.commands.ClaimCommand.CLAIM_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Money;
import seedu.address.model.person.Person;

public class ClaimCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final boolean isSubtract = true;
    private Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
    private long originalBudget = Long.parseLong(targetPerson.getClaimBudget().amount);

    @Test
    public void execute_validPersonIndexUnfilteredList_success() {
        Person personToClaim = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        long prevClaimBudget = Integer.parseInt(personToClaim.getClaimBudget().amount);
        Money claimBudget = new Money(Long.toString(prevClaimBudget - 1));
        Person expectedPersonAfterClaim = new Person(personToClaim.getName(), personToClaim.getPhone(),
                personToClaim.getEmail(), personToClaim.getAddress(), personToClaim.getSalary(),
                    claimBudget, personToClaim.getDepartment(), personToClaim.getDob(), personToClaim.getLeave());
        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, true, 1);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToClaim, expectedPersonAfterClaim);
        String expectedMessage = String.format("%sRemaining claim budget %s has: %s",
                CLAIM_SUCCESS, expectedPersonAfterClaim.getName(), claimBudget);
        assertCommandSuccess(claimCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validPersonIndexUnfilteredListAllocation_success() {
        Person personToClaim = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        long prevClaimBudget = Integer.parseInt(personToClaim.getClaimBudget().amount);
        Money claimBudget = new Money(Long.toString(prevClaimBudget + 1));
        Person expectedPersonAfterClaim = new Person(personToClaim.getName(), personToClaim.getPhone(),
                personToClaim.getEmail(), personToClaim.getAddress(), personToClaim.getSalary(),
                claimBudget, personToClaim.getDepartment(), personToClaim.getDob(), personToClaim.getLeave());
        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, false, 1);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToClaim, expectedPersonAfterClaim);
        String expectedMessage = String.format("%sRemaining claim budget %s has: %s",
                ALLOCATE_SUCCESS, expectedPersonAfterClaim.getName(), claimBudget);
        assertCommandSuccess(claimCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_calculationNewBudget_success() throws CommandException {
        long claimAmount = originalBudget - 1;
        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, isSubtract, claimAmount);
        Money newMoney = claimCommand.calculateNewClaimBudget(originalBudget);
        long expectedBudgetAmount = originalBudget - claimAmount;
        Money expectedMoney = new Money(String.valueOf(expectedBudgetAmount));
        assertEquals(newMoney, expectedMoney);
    }

    @Test
    public void execute_calculationNewBudget_addsuccess() throws CommandException {
        long claimAmount = originalBudget + 1;
        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, false, claimAmount);
        Money newMoney = claimCommand.calculateNewClaimBudget(originalBudget);
        long expectedBudgetAmount = originalBudget + claimAmount;
        Money expectedMoney = new Money(String.valueOf(expectedBudgetAmount));
        assertEquals(newMoney, expectedMoney);
    }

    @Test
    public void execute_calculationExcessBudget_failure() {
        long claimAmount = originalBudget + 1;
        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, isSubtract, claimAmount);
        assertThrows(CommandException.class, () -> {
            claimCommand.calculateNewClaimBudget(originalBudget);
        }, Messages.MESSAGE_OVER_CLAIM);
    }

    @Test
    public void execute_calculationExcessLongBudget_failure() {
        long claimAmount = (long) (originalBudget + Math.pow(10, 12));
        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, !isSubtract, claimAmount);
        assertThrows(CommandException.class, () -> {
            claimCommand.calculateNewClaimBudget(originalBudget);
        }, Messages.TOO_LARGE_A_NUMBER);
    }

    @Test
    public void execute_generateNewPerson_success() {
        Money newClaimBudget = new Money(String.valueOf(originalBudget + 1));
        Person expectedPerson = new Person(targetPerson.getName(), targetPerson.getPhone(), targetPerson.getEmail(),
                targetPerson.getAddress(), targetPerson.getSalary(), newClaimBudget, targetPerson.getDepartment(),
                    targetPerson.getDob(), targetPerson.getLeave());
        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, true, 0);
        Person newPerson = claimCommand.postClaimPerson(targetPerson, newClaimBudget);
        assertEquals(newPerson.toString(), expectedPerson.toString());
    }

    @Test
    public void execute_generateNewPerson_failure() {
        Money newClaimBudget = new Money(String.valueOf(originalBudget + 1));
        Person expectedPerson = new Person(targetPerson.getName(), targetPerson.getPhone(), targetPerson.getEmail(),
                targetPerson.getAddress(), targetPerson.getSalary(), targetPerson.getClaimBudget(),
                    targetPerson.getDepartment(), targetPerson.getDob());
        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, true, 0);
        Person newPerson = claimCommand.postClaimPerson(targetPerson, newClaimBudget);
        assertNotEquals(newPerson.toString(), expectedPerson.toString());
    }

    @Test
    public void equals() {
        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, true, 1);
        ClaimCommand commandWithSameValues = new ClaimCommand(INDEX_FIRST_PERSON, true, 1);
        ClaimCommand commandWithDifferentValues = new ClaimCommand(INDEX_FIRST_PERSON, false, 1);
        String notACommand = "This is a string, not a command";
        assertTrue(claimCommand.equals(claimCommand));
        assertTrue(claimCommand.equals(commandWithSameValues));
        assertFalse(claimCommand.equals(commandWithDifferentValues));
        assertFalse(claimCommand == null);
        assertFalse(claimCommand.equals(notACommand));
    }
}
