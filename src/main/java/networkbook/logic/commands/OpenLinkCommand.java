package networkbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.util.List;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.Model;
import networkbook.model.person.Link;
import networkbook.model.person.Person;

public class OpenLinkCommand extends Command {
    public static final String COMMAND_WORD = "open";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": yo";
    public static final String MESSAGE_SUCCESS = "Successfully opened %s";
    public static final String MESSAGE_CANNOT_OPEN_LINK = "Oops, your computer does not allow me to open a link.";

    private final Index personIndex;
    private final Index linkIndex;

    /**
     * Creates a new {@code OpenLinkCommand}.
     * @param personIndex The index of the person to open the link.
     * @param linkIndex The index of the link in the link list of the person.
     */
    public OpenLinkCommand(Index personIndex, Index linkIndex) {
        requireAllNonNull(personIndex, linkIndex);
        this.personIndex = personIndex;
        this.linkIndex = linkIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (this.personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        try {
            Link openedLink = model.openLink(personIndex, linkIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS, openedLink.getValue()));
        } catch (IOException e) {
            throw new CommandException(MESSAGE_CANNOT_OPEN_LINK);
        }
    }
}
