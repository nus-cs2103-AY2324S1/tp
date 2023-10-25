package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * The predicate class that brings together of all the other predicate class for Person.
 */
public class GeneralPersonPredicate implements Predicate<Person> {
    private final NameContainsKeywordsPredicate namePredicate;
    private final PhoneContainsPredicate phonePredicate;
    private final EmailContainsKeywordsPredicate emailPredicate;
    private final StatusContainsPredicate statusPredicate;
    private final PersonTagContainsKeywordsPredicate tagPredicate;

    /**
     * Constructs a predicate class that fulfills all the argument predicates
     * @param namePredicate A predicate that test a person's name.
     * @param phonePredicate A predicate that test a person's phone.
     * @param emailPredicate A predicate that test a person's email.
     * @param statusPredicate A predicate that test a person's status.
     * @param tagPredicate A predicate that test a person's tags.
     */
    public GeneralPersonPredicate(NameContainsKeywordsPredicate namePredicate,
                                   PhoneContainsPredicate phonePredicate,
                                   EmailContainsKeywordsPredicate emailPredicate,
                                   StatusContainsPredicate statusPredicate,
                                   PersonTagContainsKeywordsPredicate tagPredicate) {
        this.namePredicate = namePredicate;
        this.phonePredicate = phonePredicate;
        this.emailPredicate = emailPredicate;
        this.statusPredicate = statusPredicate;
        this.tagPredicate = tagPredicate;
    }

    @Override
    public boolean test(Person person) {
        return namePredicate.test(person)
                && phonePredicate.test(person)
                && emailPredicate.test(person)
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
                && statusPredicate.equals(otherGeneralPersonPredicate.statusPredicate)
                && tagPredicate.equals(otherGeneralPersonPredicate.tagPredicate);
    }
}
