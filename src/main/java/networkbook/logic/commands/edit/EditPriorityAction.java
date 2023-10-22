package networkbook.logic.commands.edit;

import static java.util.Objects.requireNonNull;

import networkbook.commons.util.ToStringBuilder;
import networkbook.model.person.Priority;

public class EditPriorityAction implements EditAction {
    private final Priority priority;

    public EditPriorityAction(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void edit(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);
        editPersonDescriptor.setPriority(priority);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EditPriorityAction)) {
            return false;
        }

        EditPriorityAction otherAction = (EditPriorityAction) object;
        return this.priority.equals(otherAction.priority);
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        toStringBuilder.add("priority", this.priority);
        return toStringBuilder.toString();
    }
}
