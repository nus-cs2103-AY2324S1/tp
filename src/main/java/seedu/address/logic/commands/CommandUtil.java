package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Contains shared utility methods used in the various *Command classes.
 */
public class CommandUtil {
    /**
     * Finds a {@code Person} from {@code List<Person> persons} using the given {@code Name name}
     * and {@code Nric nric}, if able.
     *
     * @param name The {@code Name} identifier field to search with.
     * @param nric The {@code Nric} identifier field to search with.
     * @param persons The collection of {@code Person} to search within.
     * @return A person that was found, wrapped as an {@code Optional<Person>}.
     */
    public static Optional<Person> findPersonByIdentifier(Name name, Nric nric, List<Person> persons) {
        Optional<Person> personByName = persons.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
        Optional<Person> personByNric = persons.stream()
                .filter(p -> p.getNric().equals(nric))
                .findFirst();

        if (personByName.isPresent() && personByNric.isPresent()) {
            return personByName.filter((val) -> personByName.equals(personByNric));
        }

        return personByName.isPresent() ? personByName : personByNric;
    }
}
