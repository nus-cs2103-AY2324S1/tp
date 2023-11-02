package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * The command handler for {@code list contact} command
 */
public class ListPersonCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS = "Listed contacts";
    public static final String SECONDARY_COMMAND_WORD = "contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + SECONDARY_COMMAND_WORD
            + ": Lists contacts in the address book."
            + "Parameters: "
            + PREFIX_TAG + " TAGNAME...\n"
            + "Tag argument may be empty."
            + "Example: \n"
            + "- " + COMMAND_WORD + " " + SECONDARY_COMMAND_WORD + "\n"
            + "- " + COMMAND_WORD + " " + SECONDARY_COMMAND_WORD + " " + PREFIX_TAG + " recruiter";

    private final Set<Tag> tags;

    /**
     * Creates a ListPersonCommand to list contacts based on tags (may be empty) {@code Person}
     */
    public ListPersonCommand(Set<Tag> tags) {
        requireNonNull(tags);
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (tags.isEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        } else {
            model.updateFilteredPersonList(person -> {
                for (Tag tag : tags) {
                    if (person.containsTag(tag)) {
                        return true;
                    }
                }
                return false;
            });
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
