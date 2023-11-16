package seedu.address.model.developer;

import java.util.function.Predicate;

/**
 * A combined predicate for filtering developers based on multiple criteria.
 * This class combines individual predicates for filtering by name, role, address, date joined, email, phone,
 * projects, salary, rating, and GitHub ID.
 * It tests whether a developer satisfies all the specified criteria.
 */
public class CombinedDeveloperPredicate implements Predicate<Developer> {
    private final Predicate<Developer> namePredicate;
    private final Predicate<Developer> rolePredicate;
    private final Predicate<Developer> addressPredicate;
    private final Predicate<Developer> dateJoinedPredicate;
    private final Predicate<Developer> emailPredicate;
    private final Predicate<Developer> phonePredicate;
    private final Predicate<Developer> projectPredicate;
    private final Predicate<Developer> salaryPredicate;
    private final Predicate<Developer> ratingPredicate;
    private final Predicate<Developer> githubIdPredicate;

    /**
     * Constructs a CombinedDeveloperPredicate with individual predicates for each filtering criterion.
     *
     * @param namePredicate      Predicate for filtering by name.
     * @param rolePredicate      Predicate for filtering by role.
     * @param addressPredicate   Predicate for filtering by address.
     * @param dateJoinedPredicate Predicate for filtering by date joined.
     * @param emailPredicate     Predicate for filtering by email.
     * @param phonePredicate     Predicate for filtering by phone.
     * @param projectPredicate   Predicate for filtering by projects.
     * @param salaryPredicate    Predicate for filtering by salary.
     * @param ratingPredicate    Predicate for filtering by rating.
     * @param githubIdPredicate  Predicate for filtering by GitHub ID.
     */
    public CombinedDeveloperPredicate(Predicate<Developer> namePredicate,
                                      Predicate<Developer> rolePredicate,
                                      Predicate<Developer> addressPredicate,
                                      Predicate<Developer> dateJoinedPredicate,
                                      Predicate<Developer> emailPredicate,
                                      Predicate<Developer> phonePredicate,
                                      Predicate<Developer> projectPredicate,
                                      Predicate<Developer> salaryPredicate,
                                      Predicate<Developer> ratingPredicate,
                                      Predicate<Developer> githubIdPredicate) {
        this.namePredicate = namePredicate;
        this.rolePredicate = rolePredicate;
        this.addressPredicate = addressPredicate;
        this.dateJoinedPredicate = dateJoinedPredicate;
        this.emailPredicate = emailPredicate;
        this.phonePredicate = phonePredicate;
        this.projectPredicate = projectPredicate;
        this.salaryPredicate = salaryPredicate;
        this.ratingPredicate = ratingPredicate;
        this.githubIdPredicate = githubIdPredicate;
    }

    /**
     * Tests whether a developer satisfies all the specified criteria.
     *
     * @param developer The developer to be tested.
     * @return True if the developer satisfies all the specified criteria, false otherwise.
     */
    @Override
    public boolean test(Developer developer) {
        return namePredicate.test(developer)
                && rolePredicate.test(developer)
                && addressPredicate.test(developer)
                && dateJoinedPredicate.test(developer)
                && emailPredicate.test(developer)
                && phonePredicate.test(developer)
                && projectPredicate.test(developer)
                && salaryPredicate.test(developer)
                && ratingPredicate.test(developer)
                && githubIdPredicate.test(developer);
    }
}
