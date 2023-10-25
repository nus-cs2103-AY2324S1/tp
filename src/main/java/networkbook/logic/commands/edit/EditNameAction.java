package networkbook.logic.commands.edit;

import static java.util.Objects.requireNonNull;

import networkbook.commons.util.ToStringBuilder;
import networkbook.model.person.Name;

/**
 * Represents an action to edit the name of a person.
 */
public class EditNameAction implements EditAction {
    private final Name name;

    /**
     * Constructs a new action to edit name.
     * @param name The new value of name.
     */
    public EditNameAction(Name name) {
        this.name = name;
    }

    @Override
    public void edit(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);
        editPersonDescriptor.setName(name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EditNameAction)) {
            return false;
        }

        EditNameAction otherAction = (EditNameAction) object;
        return this.name.equals(otherAction.name);
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        toStringBuilder.add("name", this.name);
        return toStringBuilder.toString();
    }
}
