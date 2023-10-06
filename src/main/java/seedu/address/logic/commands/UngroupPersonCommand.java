package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.AddressBook;
import seedu.address.model.person.NameEqualsKeywordPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.Group;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class UngroupPersonCommand extends Command {
    public static final String COMMAND_WORD = "ungroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the person from the group.\n"
            + "Parameters: "
            + "NAME (must be a string)\n"
            + "GROUPNAME (must be a string)\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_NAME + "John "
            + PREFIX_GROUPTAG + "CS2103T ";

    public static final String MESSAGE_UNGROUP_PERSON_SUCCESS = "Ungrouped Person: %1$s";

    private final NameEqualsKeywordPredicate predicate;
//    private final String name;
    private final Group group;

    public UngroupPersonCommand(NameEqualsKeywordPredicate predicate, Group group) {
        requireNonNull(predicate);
        requireNonNull(group);
        this.predicate = predicate;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        List<Person> lastShownList = model.getFilteredPersonList();
        Index index = Index.fromZeroBased(0);
        Person personToUngroup = lastShownList.get(index.getZeroBased());
        group.remove(personToUngroup);
        //insert another line here to person.remove(group);
        return new CommandResult(
                String.format(MESSAGE_UNGROUP_PERSON_SUCCESS, Messages.format(personToUngroup)));
    }


}
