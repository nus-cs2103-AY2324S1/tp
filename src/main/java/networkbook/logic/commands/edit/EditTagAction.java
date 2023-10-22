package networkbook.logic.commands.edit;

import static java.util.Objects.requireNonNull;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.tag.Tag;

public class EditTagAction implements EditAction {
    private final Tag tag;
    private final Index index;

    public EditTagAction(Index index, Tag tag) {
        this.index = index;
        this.tag = tag;
    }

    @Override
    public void edit(EditPersonDescriptor editPersonDescriptor) throws CommandException {
        requireNonNull(editPersonDescriptor);
        editPersonDescriptor.setTag(index, tag);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EditTagAction)) {
            return false;
        }

        EditTagAction otherAction = (EditTagAction) object;
        return this.tag.equals(otherAction.tag)
                && this.index.equals(otherAction.index);
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        toStringBuilder.add("tag", this.tag);
        toStringBuilder.add("index", this.index.getOneBased());
        return toStringBuilder.toString();
    }
}
