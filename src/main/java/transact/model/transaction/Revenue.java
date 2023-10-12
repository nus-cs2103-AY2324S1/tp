package transact.model.transaction;

import transact.model.person.Person;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;

public class Revenue extends Transaction {

    /**
     * Creates a new Revenue transaction.
     *
     * @param transactionId The unique transaction ID.
     * @param person        The person associated with the revenue.
     * @param description   The description of the revenue.
     * @param amount        The amount of the revenue.
     */
    public Revenue(TransactionId transactionId, Person person, Description description, Amount amount) {
        super(transactionId, person, description, amount);
    }
}


