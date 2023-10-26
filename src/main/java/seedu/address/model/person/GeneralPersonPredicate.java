package seedu.address.model.person;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * The predicate class that brings together of all the other predicate class for Person.
 */
public class GeneralPersonPredicate implements Predicate<Person> {
    private final NameContainsKeywordsPredicate namePredicate;
    private final PhoneContainsPredicate phonePredicate;
    private final EmailContainsKeywordsPredicate emailPredicate;
    private final LastContactTimeContainsPredicate lastContactTimePredicate;
    private final StatusContainsKeywordsPredicate statusPredicate;
    private final PersonTagContainsKeywordsPredicate tagPredicate;

    /**
     * Constructs a predicate class that fulfills all the argument predicates
     * @param namePredicate A predicate that test a person's name.
     * @param phonePredicate A predicate that test a person's phone.
     * @param emailPredicate A predicate that test a person's email.
     * @param lastContactTimePredicate A predicate that test a person's last contacted time.
     * @param statusPredicate A predicate that test a person's status.
     * @param tagPredicate A predicate that test a person's tags.
     */
    public GeneralPersonPredicate(NameContainsKeywordsPredicate namePredicate,
                                   PhoneContainsPredicate phonePredicate,
                                   EmailContainsKeywordsPredicate emailPredicate,
                                   LastContactTimeContainsPredicate lastContactTimePredicate,
                                   StatusContainsKeywordsPredicate statusPredicate,
                                   PersonTagContainsKeywordsPredicate tagPredicate) {
        this.namePredicate = namePredicate;
        this.phonePredicate = phonePredicate;
        this.emailPredicate = emailPredicate;
        this.lastContactTimePredicate = lastContactTimePredicate;
        this.statusPredicate = statusPredicate;
        this.tagPredicate = tagPredicate;
    }

    /**
     * Constructs a predicate class that fulfills all the argument predicates
     * @param nameKeyWords String array that will be used to construct NameContainsKeywordsPredicate
     * @param phoneValues String array that will be used to construct PhoneContainsPredicate
     * @param emailKeyWords String array that will be used to construct EmailContainsKeywordsPredicate
     * @param lastContacted A predicate that wil be used to construct LastContactTimeContainsPredicate.
     * @param statusKeyWords String array that will be used to construct StatusContainsKeywordsPredicate
     * @param tagKeyWords String array that will be used to construct PersonTagContainsKeywordsPredicate
     */
    public GeneralPersonPredicate(String[] nameKeyWords, String[] phoneValues, String[] emailKeyWords,
                                  LocalDateTime lastContacted, String[] statusKeyWords, String[] tagKeyWords) {
        this.namePredicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeyWords));
        this.phonePredicate = new PhoneContainsPredicate(Arrays.asList(phoneValues));
        this.emailPredicate = new EmailContainsKeywordsPredicate(Arrays.asList(emailKeyWords));
        this.lastContactTimePredicate = new LastContactTimeContainsPredicate(lastContacted);
        this.statusPredicate = new StatusContainsKeywordsPredicate(Arrays.asList(statusKeyWords));
        this.tagPredicate = new PersonTagContainsKeywordsPredicate(Arrays.asList(tagKeyWords));
    }

    @Override
    public boolean test(Person person) {
        return namePredicate.test(person)
                && phonePredicate.test(person)
                && emailPredicate.test(person)
                && lastContactTimePredicate.test(person)
                && statusPredicate.test(person)
                && tagPredicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GeneralPersonPredicate)) {
            return false;
        }

        GeneralPersonPredicate otherGeneralPersonPredicate = (GeneralPersonPredicate) other;
        return namePredicate.equals(otherGeneralPersonPredicate.namePredicate)
                && phonePredicate.equals(otherGeneralPersonPredicate.phonePredicate)
                && emailPredicate.equals(otherGeneralPersonPredicate.emailPredicate)
                && lastContactTimePredicate.equals(otherGeneralPersonPredicate.lastContactTimePredicate)
                && statusPredicate.equals(otherGeneralPersonPredicate.statusPredicate)
                && tagPredicate.equals(otherGeneralPersonPredicate.tagPredicate);
    }
}
