package transact.model.transaction;

import java.text.ParseException;
import java.util.Scanner;

import transact.model.person.Person;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;
import transact.model.transaction.info.TransactionType;

public class TransactionManualTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Manual Transaction Test");

        System.out.print("Enter Person Name: ");
        String personNameInput = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumberInput = scanner.nextLine();

        System.out.print("Enter Email: ");
        String emailInput = scanner.nextLine();

        System.out.print("Enter Address: ");
        String addressInput = scanner.nextLine();

        System.out.print("Enter TransactionType: ");
        String typeInput = scanner.nextLine();

        System.out.print("Enter Description: ");
        String descriptionInput = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amountInput = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Date (dd/mm/yyyy): ");
        String dateInput = scanner.nextLine();

        // Create a Transaction ID
        TransactionId transactionId = new TransactionId();

        // Create a Person object
        Person person = new Person(new transact.model.person.Name(personNameInput),
                new transact.model.person.Phone(phoneNumberInput),
                new transact.model.person.Email(emailInput),
                new transact.model.person.Address(addressInput),
                new java.util.HashSet<>());

        // Create a Description object
        Description description = new Description(descriptionInput);

        // Create an Amount object
        Amount amount = new Amount(amountInput);

        // Create a Date object
        Date date = new Date(dateInput);

        // Create a TransactionType object
        TransactionType transactionType = TransactionType.getType(typeInput);

        // Create a Transaction object
        Transaction transaction = new Transaction(transactionId, transactionType, description, amount, date, person);


        // Print Transaction information for verification
        System.out.println("\nTransaction Information:");
        System.out.println("Transaction ID: " + transaction.getTransactionId());
        System.out.println("Person: " + transaction.getPerson());
        System.out.println("Description: " + transaction.getDescription());
        System.out.println("Amount: " + transaction.getAmount());
        System.out.println("Date: " + transaction.getDate());

        scanner.close();
    }
}
