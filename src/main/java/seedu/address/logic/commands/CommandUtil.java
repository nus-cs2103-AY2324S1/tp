package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Contains shared utility methods used in the various *Command classes.
 */
public class CommandUtil {
    public static final String MESSAGES_ALL_FIELDS_NULL = "At least one identifying field should be provided.";

    private CommandUtil() {}

    /**
     * Finds a {@code Person} from {@code List<Person> persons} using the given {@code Name name}
     * and {@code Id id}, if able.<br>
     * This method expects {@code id} and {@code name} to not be {@code null} simultaneously.
     *
     * @param name The {@code Name} identifier field to search with.
     * @param id The {@code Id} identifier field to search with.
     * @param persons The collection of {@code Person} to search within.
     * @return A person that was found, wrapped as an {@code Optional<Person>}.
     */
    public static Optional<Person> findPersonByIdentifier(Name name, Id id, List<Person> persons)
            throws CommandException {
        requireNonNull(persons);
        if (name == null && id == null) {
            throw new CommandException(MESSAGES_ALL_FIELDS_NULL);
        }

        Optional<Person> fetchPerson = persons.stream()
                .filter(p -> name == null || p.getName().equals(name))
                .filter(p -> id == null || p.getId().equals(id))
                .findFirst();

        return fetchPerson;
    }
}
