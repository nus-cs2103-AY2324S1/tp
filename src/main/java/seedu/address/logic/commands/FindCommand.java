package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PATIENT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALHISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.SPECIALIST_TAG;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonType;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final String MESSAGE_USAGE_GENERAL = "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]... ";

    private static final String PERSON_EXAMPLE =
            PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney ";

    public static final String MESSAGE_USAGE_PATIENT = COMMAND_WORD + " "
            + PATIENT_TAG
            + ": Finds all Patients whose attributes contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers. \n"
            + MESSAGE_USAGE_GENERAL
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_MEDICALHISTORY + "MEDICAL HISTORY]... \n"
            + "Example: " + COMMAND_WORD + " "
            + PATIENT_TAG + " "
            + PERSON_EXAMPLE
            + PREFIX_AGE + "30 "
            + PREFIX_MEDICALHISTORY + "Osteoporosis";

    public static final String MESSAGE_USAGE_SPECIALIST = COMMAND_WORD + " "
            + SPECIALIST_TAG
            + ": Finds all Specialists whose attributes contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers. \n"
            + MESSAGE_USAGE_GENERAL
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_SPECIALTY + "SPECIALTY] \n"
            + "Example: " + COMMAND_WORD + " "
            + SPECIALIST_TAG + " "
            + PERSON_EXAMPLE
            + PREFIX_LOCATION + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SPECIALTY + "Physiotherapist ";


    private final FindPredicateMap findPredicateMap;
    private final PersonType personType;

    /**
     * @param findPredicateMap The predicate map that encapsulates the attributes being searched for
     * @param personType The type of person being searched for i.e. patient or specialist
     */
    public FindCommand(FindPredicateMap findPredicateMap, PersonType personType) {
        this.findPredicateMap = findPredicateMap;
        this.personType = personType;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> predicate = person -> findPredicateMap.getAllPredicates().stream()
                .map(pred -> pred.test(person))
                .reduce(true, (x, y) -> x && y);
        model.updateFilteredPersonList(predicate.and(personType.getSearchPredicate()));
        model.commit();
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
        return findPredicateMap.equals(otherFindCommand.findPredicateMap)
                && personType.equals(otherFindCommand.personType);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", findPredicateMap)
                .add("personType", personType)
                .toString();
    }

    public PersonType getPersonType() {
        return personType;
    }

    public FindPredicateMap getPredicate() {
        return findPredicateMap;
    }
}
