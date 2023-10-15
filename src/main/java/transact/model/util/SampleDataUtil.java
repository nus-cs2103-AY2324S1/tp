package transact.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import transact.model.AddressBook;
import transact.model.ReadOnlyAddressBook;
import transact.model.ReadOnlyTransactionBook;
import transact.model.TransactionBook;
import transact.model.person.Address;
import transact.model.person.Email;
import transact.model.person.Name;
import transact.model.person.Person;
import transact.model.person.Phone;
import transact.model.tag.Tag;
import transact.model.transaction.Expense;
import transact.model.transaction.Revenue;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static Transaction[] getSampleTransactions() {
        // TODO Add more fields
        return new Transaction[] {
            new Expense(new TransactionId("00000001"), new Description("Expense 1"), new Amount(100)),
            new Expense(new TransactionId("00000002"), new Description("Expense 2"), new Amount(200)),
            new Expense(new TransactionId("00000003"), new Description("Expense 3"), new Amount(300)),
            new Revenue(new TransactionId("00000004"), new Description("Revenue 1"), new Amount(1000)),
            new Revenue(new TransactionId("00000005"), new Description("Revenue 2"), new Amount(500)),
        };
    }

    public static ReadOnlyTransactionBook getSampleTransactionBook() {
        TransactionBook sampleTb = new TransactionBook();
        for (Transaction sampleTransaction : getSampleTransactions()) {
            sampleTb.addTransaction(sampleTransaction);
        }
        return sampleTb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
