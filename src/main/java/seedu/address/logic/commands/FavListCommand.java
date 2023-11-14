package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all favourite persons in the address book to the user.
 */
public class FavListCommand extends Command {
    /**
     * Message displayed upon successful execution of the FavListCommand if there are no favorite profiles.
     */
    public static final String MESSAGE_SUCCESS = "You have 0 favourited persons in your list.\n";

    /**
     * Keyword to trigger the 'favorite list' command.
     */
    public static final String COMMAND_WORD = "favlist";

    /**
     * Usage instructions for the 'favlist' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display all the users stored\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Executes the command to list all favorite persons in the address book.
     *
     * @param model The current model.
     * @return The CommandResult containing the list of favourited persons or a message indicating no favorites.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Predicate<Person> favoritedPredicate = person -> person.getFavourite().getFavourite();

        model.updateFilteredPersonList(favoritedPredicate);

        List<Person> favouritePersons = model.getFilteredPersonList();

        int numbFavouritePeople = favouritePersons.size();
        String s = numbFavouritePeople == 1 ? "" : "s";

        String peopleList = favouritePersons.stream()
                .map(this::formatPersonDetails)
                .collect(Collectors.joining("\n"));

        return new CommandResult("You have " + numbFavouritePeople
                + " favourited person" + s + " in your list.\n" + peopleList);
    }

    /**
     * Format the details of a person to a string representation.
     *
     * @param person The person whose details need to be formatted.
     * @return A string with the formatted details of the person.
     */
    private String formatPersonDetails(Person person) {
        return "Name: " + person.getName() + "\n"
                + "Roles: " + person.getRoles() + "\n"
                + "Contacts: " + person.getContacts() + "\n"
                + "Courses: " + person.getCourses() + "\n"
                + "Tutorials: " + person.getTutorials() + "\n";
    }

}


