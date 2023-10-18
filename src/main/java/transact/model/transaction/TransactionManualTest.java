package transact.model.transaction;

import java.util.Collections;
import java.util.Scanner;

import transact.model.person.Address;
import transact.model.person.Email;
import transact.model.person.Name;
import transact.model.person.Person;
import transact.model.person.Phone;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;
import transact.model.transaction.info.TransactionType;

public class TransactionManualTest {
    public static void main(String[] args) {
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
        Person person = new Person(new Name(personNameInput),
                new Phone(phoneNumberInput),
                new Email(emailInput),
                new Address(addressInput),
                Collections.emptySet());

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
        System.out.println("Transaction Type: " + transaction.getTransactionType());
        System.out.println("Description: " + transaction.getDescription());
        System.out.println("Amount: " + transaction.getAmount());
        System.out.println("Date: " + transaction.getDate());
        System.out.println("Person: " + transaction.getPerson());

        scanner.close();
    }
}
