package networkbook.logic.commands.edit;

import static java.util.Objects.requireNonNull;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Link;

/**
 * Represents an action to edit a link of a person.
 */
public class EditLinkAction implements EditAction {
    private final Link link;
    private final Index index;

    /**
     * Constructs a new action to edit link.
     * @param index The index of the link in the link list of the person.
     * @param link The new value of the link.
     */
    public EditLinkAction(Index index, Link link) {
        this.index = index;
        this.link = link;
    }

    @Override
    public void edit(EditPersonDescriptor editPersonDescriptor) throws CommandException {
        requireNonNull(editPersonDescriptor);
        editPersonDescriptor.setLink(index, link);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EditLinkAction)) {
            return false;
        }

        EditLinkAction otherAction = (EditLinkAction) object;
        return this.link.equals(otherAction.link)
                && this.index.equals(otherAction.index);
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        toStringBuilder.add("link", this.link);
        toStringBuilder.add("index", this.index.getOneBased());
        return toStringBuilder.toString();
    }
}
