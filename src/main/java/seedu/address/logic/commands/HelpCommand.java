package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    /**
     * Keyword to trigger the help command.
     */
    public static final String COMMAND_WORD = "help";

    /**
     * Usage instructions for the 'help' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String ADD_PERSON = "Adding a person: add --name NAME [--role ROLE1, ...]  "
            + "[--contact CONTACT1, ...] [--course COURSECODE1/CLASS1, ...]";
    public static final String LIST = "Listing all persons: list";
    public static final String DELETE = "Deleting a person: delete INDEX";
    public static final String SEARCH_NAME = "Search by name: search NAME";
    public static final String SEARCH_ROLE = "Search by role: searchrole ROLE";
    public static final String SEARCH_COURSE = "Search by course: searchcourse COURSECODE";
    public static final String SEARCH_TUTORIAL = "Search by tutorial class: searchtutorial TUTORIAL";
    public static final String FAV = "Adding persons to favourites: fav INDEX";
    public static final String UNFAV = "Removing persons from favourites: unfav INDEX";
    public static final String FAVLIST = "Display all favourites: favlist";
    public static final String CLEAR = "Clear all data: clear";
    public static final String EXIT = "Exit the application: exit";


    public static final String SHOWING_HELP_MESSAGE = "Quick Guide: \n"
            + ADD_PERSON + "\n" + LIST + "\n" + DELETE + "\n" + SEARCH_NAME + "\n" + SEARCH_ROLE + "\n"
            + SEARCH_COURSE + "\n" + SEARCH_TUTORIAL + "\n" + FAV + "\n" + UNFAV + "\n"
            + FAVLIST + "\n" + CLEAR + "\n" + EXIT + "\n"
            + "Refer to the User Guide for the detailed implementation.";

    /**
     * Executes the HelpCommand to display program usage instructions.
     *
     * @param model The model this command should operate on.
     * @return The CommandResult displaying the program usage instructions.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
