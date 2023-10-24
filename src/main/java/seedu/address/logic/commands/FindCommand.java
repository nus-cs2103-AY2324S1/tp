package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.PredicateUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.CompanyContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.LicenceContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.PolicyExpiryContainsKeywordsPredicate;
import seedu.address.model.person.PolicyIssueContainsKeywordsPredicate;
import seedu.address.model.person.PolicyNumberContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified fields (case-insensitive for values) and displays them as a list with index numbers.\n"
            + "Parameters: [n/NAME] [l/LICENCE PLATE] ...\n"
            + "Example: " + COMMAND_WORD + " n/Alice Rodriguez";

    private final NameContainsKeywordsPredicate namePredicate;
    private final PhoneContainsKeywordsPredicate phonePredicate;
    private final EmailContainsKeywordsPredicate emailPredicate;
    private final TagContainsKeywordsPredicate tagPredicate;
    private final NricContainsKeywordsPredicate nricPredicate;
    private final LicenceContainsKeywordsPredicate licencePredicate;
    private final CompanyContainsKeywordsPredicate companyPredicate;
    private final PolicyNumberContainsKeywordsPredicate policyNumberPredicate;
    private final PolicyIssueContainsKeywordsPredicate policyIssuePredicate;
    private final PolicyExpiryContainsKeywordsPredicate policyExpiryPredicate;

    /**
     * Constructor for FindCommand
     *
     * @param namePredicate the predicate to be used for finding by name
     * @param licencePredicate the predicate to be used for finding by licence plate number
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate,
                       LicenceContainsKeywordsPredicate licencePredicate,
                       NricContainsKeywordsPredicate nricPredicate,
                       PhoneContainsKeywordsPredicate phonePredicate,
                       PolicyNumberContainsKeywordsPredicate policyNumberPredicate,
                       TagContainsKeywordsPredicate tagPredicate,
                       PolicyExpiryContainsKeywordsPredicate policyExpiryPredicate,
                       EmailContainsKeywordsPredicate emailPredicate,
                       PolicyIssueContainsKeywordsPredicate policyIssuePredicate,
                       CompanyContainsKeywordsPredicate companyPredicate) {
        this.namePredicate = namePredicate;
        this.licencePredicate = licencePredicate;
        this.nricPredicate = nricPredicate;
        this.phonePredicate = phonePredicate;
        this.policyNumberPredicate = policyNumberPredicate;
        this.tagPredicate = tagPredicate;
        this.policyExpiryPredicate = policyExpiryPredicate;
        this.emailPredicate = emailPredicate;
        this.policyIssuePredicate = policyIssuePredicate;
        this.companyPredicate = companyPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Predicate<Person>> predicates = addAllToPredicatesList();
        model.updateFilteredPersonList(PredicateUtil.combinePredicates(predicates));

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return namePredicate.equals(otherFindCommand.namePredicate)
                && phonePredicate.equals(otherFindCommand.phonePredicate)
                && emailPredicate.equals(otherFindCommand.emailPredicate)
                && tagPredicate.equals(otherFindCommand.tagPredicate)
                && nricPredicate.equals(otherFindCommand.nricPredicate)
                && licencePredicate.equals(otherFindCommand.licencePredicate)
                && companyPredicate.equals(otherFindCommand.companyPredicate)
                && policyNumberPredicate.equals(otherFindCommand.policyNumberPredicate)
                && policyExpiryPredicate.equals(otherFindCommand.policyExpiryPredicate)
                && policyIssuePredicate.equals(otherFindCommand.policyIssuePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name predicate", namePredicate)
                .add("licence predicate", licencePredicate)
                .add("nric predicate", nricPredicate)
                .add("phone predicate", phonePredicate)
                .add("policy number predicate", policyNumberPredicate)
                .add("tag predicate", tagPredicate)
                .add("policy expiry predicate", policyExpiryPredicate)
                .add("email predicate", emailPredicate)
                .add("policy issue predicate", policyIssuePredicate)
                .add("company predicate", companyPredicate)
                .toString();
    }

    private List<Predicate<Person>> addAllToPredicatesList() {
        List<Predicate<Person>> predicates = new ArrayList<>();

        if (!namePredicate.isEmpty()) {
            predicates.add(namePredicate);
        }
        if (!licencePredicate.isEmpty()) {
            predicates.add(licencePredicate);
        }
        if (!nricPredicate.isEmpty()) {
            predicates.add(nricPredicate);
        }
        if (!phonePredicate.isEmpty()) {
            predicates.add(phonePredicate);
        }
        if (!policyNumberPredicate.isEmpty()) {
            predicates.add(policyNumberPredicate);
        }
        if (!tagPredicate.isEmpty()) {
            predicates.add(tagPredicate);
        }
        if (!policyExpiryPredicate.isEmpty()) {
            predicates.add(policyExpiryPredicate);
        }
        if (!emailPredicate.isEmpty()) {
            predicates.add(emailPredicate);
        }
        if (!policyIssuePredicate.isEmpty()) {
            predicates.add(policyIssuePredicate);
        }
        if (!companyPredicate.isEmpty()) {
            predicates.add(companyPredicate);
        }
        return predicates;
    }
}
