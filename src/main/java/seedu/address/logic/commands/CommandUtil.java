package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

public class CommandUtil {
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
