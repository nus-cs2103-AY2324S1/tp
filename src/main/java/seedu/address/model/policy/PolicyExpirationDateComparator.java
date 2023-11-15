package seedu.address.model.policy;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * A comparator used to rank persons in the database for sort
 */
public class PolicyExpirationDateComparator implements Comparator<Person> {

    /**
     * Designates the person with the earlier policy expiration date as lesser
     * Policies with default policy values are kept at the end
     * @param person1 the first object to be compared.
     * @param person2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(Person person1, Person person2) {
        PolicyDate expirationDate1 = person1.getPolicy().getPolicyExpiryDate();
        PolicyDate expirationDate2 = person2.getPolicy().getPolicyExpiryDate();

        if (expirationDate1.date.toString().equals(PolicyDate.DEFAULT_COMPARISON_VALUE)) {
            return 1;
        } else if (expirationDate2.date.toString().equals(PolicyDate.DEFAULT_COMPARISON_VALUE)) {
            return -1;
        }
        return expirationDate1.compareTo(expirationDate2);
    }
}

