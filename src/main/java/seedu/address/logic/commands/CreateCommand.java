package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.tutorial.Tutorial;

/**
 * Creates a new empty group.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new empty group.\n"
            + "Parameters: "
            + PREFIX_TUTORIAL + "TUTORIAL "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TUTORIAL + "02";

    public static final String MESSAGE_SUCCESS = "Group created successfully! Group number is %1$s";

    private final Tutorial tutorial;

    public CreateCommand(Tutorial tutorial) {
        this.tutorial = tutorial;
    }

    @Override
    public CommandResult execute(Model model) {
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        int number = generateGroupNumber(model);
        Group createdGroup = new Group(number, tutorial);
        model.addGroup(createdGroup);

        return new CommandResult(String.format(MESSAGE_SUCCESS, createdGroup.getNumber()),
                false, false, true, false);
    }

    /**
     * Generates the next group number to be used when creating a new group.
     *
     * @param model
     */
    public int generateGroupNumber(Model model) {
        int number = 1;
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        ObservableList<Group> groups = addressBook.getGroupList();

        if (!groups.isEmpty()) {
            List<Integer> groupNumbers = groups.stream()
                    .map(Group::getNumber).collect(Collectors.toList());
            while (groupNumbers.contains(number)) {
                number++;
            }
        }

        return number;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateCommand)) {
            return false;
        }

        CreateCommand otherCreateCommand = (CreateCommand) other;
        return tutorial.equals(otherCreateCommand.tutorial);
    }
}
