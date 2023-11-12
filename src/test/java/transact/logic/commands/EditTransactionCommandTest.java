package transact.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import transact.logic.commands.EditTransactionCommand.EditTransactionDescriptor;
import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.ModelManager;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionType;


public class EditTransactionCommandTest {

    private static Model model;
    private static Transaction testTransaction;
    private static TransactionType transactionType;
    private static Description description;
    private static Amount amount;
    private static Date date;
    private static EditTransactionCommand editTransactionCommand;

    @BeforeAll
    public static void setUpOnce() {
        transactionType = TransactionType.EXPENSE;
        description = new Description("Test");
        amount = new Amount(100);
        date = new Date("10/10/23");
        model = new ModelManager();
        testTransaction = new Transaction(transactionType, description, amount, date);
        model.addTransaction(testTransaction);
    }

    @Test
    public void execute_transactionEditAmountSuccessful() throws CommandException {
        Amount newAmount = new Amount(200);
        EditTransactionDescriptor editTransactionDescriptor = new EditTransactionDescriptor();
        editTransactionDescriptor.setType(transactionType);
        editTransactionDescriptor.setDescription(description);
        editTransactionDescriptor.setAmount(newAmount);
        editTransactionDescriptor.setDate(date);
        editTransactionCommand = new EditTransactionCommand(testTransaction.getTransactionId().getValue(),
                editTransactionDescriptor);
        CommandResult result = editTransactionCommand.execute(model);
        Transaction editedTransaction = model.getTransaction(testTransaction.getTransactionId());
        assertNotNull(editedTransaction);
        assertEquals(0, editedTransaction.getAmount().getValue().compareTo(BigDecimal.valueOf(200)));
        assertEquals("Edited Transaction: " + testTransaction.getTransactionId() + "; "
                + "TransactionType: Expense; Description: "
                + "Test; Amount: 200.00; Date: 10/10/23; StaffId: None", result.getFeedbackToUser());
    }

    @Test
    public void execute_transactionEditTypeSuccessful() throws CommandException {
        TransactionType newType = TransactionType.REVENUE;

        EditTransactionDescriptor editTransactionDescriptor = new EditTransactionDescriptor();
        editTransactionDescriptor.setType(newType);
        editTransactionDescriptor.setDescription(description);
        editTransactionDescriptor.setAmount(amount);
        editTransactionDescriptor.setDate(date);
        editTransactionCommand = new EditTransactionCommand(testTransaction.getTransactionId().getValue(), editTransactionDescriptor);
        CommandResult result = editTransactionCommand.execute(model);
        Transaction editedTransaction = model.getTransaction(testTransaction.getTransactionId());
        assertNotNull(editedTransaction);
        assertEquals(newType, editedTransaction.getTransactionType());
        assertEquals("Edited Transaction: " + testTransaction.getTransactionId() + "; "
                + "TransactionType: Revenue; Description: Test; Amount: 100.00; "
                + "Date: 10/10/23; StaffId: None", result.getFeedbackToUser());
    }

    @Test
    public void execute_transactionEditDescriptionSuccessful() throws CommandException {
        Description newDescription = new Description("New Description");
        EditTransactionDescriptor editTransactionDescriptor = new EditTransactionDescriptor();
        editTransactionDescriptor.setDescription(newDescription);
        editTransactionDescriptor.setType(transactionType);
        editTransactionDescriptor.setAmount(amount);
        editTransactionDescriptor.setDate(date);
        editTransactionCommand = new EditTransactionCommand(testTransaction.
                getTransactionId().getValue(), editTransactionDescriptor);
        CommandResult result = editTransactionCommand.execute(model);
        Transaction editedTransaction = model.getTransaction(testTransaction.getTransactionId());
        assertNotNull(editedTransaction);
        assertEquals(newDescription, editedTransaction.getDescription());
        assertEquals("Edited Transaction: " + testTransaction.getTransactionId() + "; "
                + "TransactionType: Expense; "
                + "Description: New Description; Amount: 100.00; Date: 10/10/23; "
                + "StaffId: None", result.getFeedbackToUser());
    }

    @Test
    public void execute_transactionEditDateSuccessful() throws CommandException {
        Date newDate = new Date("01/01/24");

        EditTransactionDescriptor editTransactionDescriptor = new EditTransactionDescriptor();
        editTransactionDescriptor.setDate(newDate);
        editTransactionDescriptor.setType(transactionType);
        editTransactionDescriptor.setDescription(description);
        editTransactionDescriptor.setAmount(amount);
        editTransactionCommand = new EditTransactionCommand(testTransaction.getTransactionId().getValue(),
                editTransactionDescriptor);
        CommandResult result = editTransactionCommand.execute(model);
        Transaction editedTransaction = model.getTransaction(testTransaction.getTransactionId());
        assertNotNull(editedTransaction);
        assertEquals(newDate, editedTransaction.getDate());
        assertEquals("Edited Transaction: " + testTransaction.getTransactionId() + "; "
                + "TransactionType: "
                + "Expense; Description: Test; Amount: 100.00; "
                + "Date: 01/01/24; StaffId: None", result.getFeedbackToUser());
    }

    @Test
    public void execute_transactionEditPersonIdUnsuccessful() {
        int personId = 10;
        EditTransactionDescriptor editTransactionDescriptor = new EditTransactionDescriptor();
        editTransactionDescriptor.setDate(date);
        editTransactionDescriptor.setType(transactionType);
        editTransactionDescriptor.setDescription(description);
        editTransactionDescriptor.setAmount(amount);
        editTransactionDescriptor.setStaffId(personId);
        editTransactionCommand = new EditTransactionCommand(testTransaction.getTransactionId().getValue(),
                editTransactionDescriptor);
        Transaction editedTransaction = model.getTransaction(testTransaction.getTransactionId());
        assertNotNull(editedTransaction);
        assertThrows(CommandException.class, () -> editTransactionCommand.execute(model));
    }
}
