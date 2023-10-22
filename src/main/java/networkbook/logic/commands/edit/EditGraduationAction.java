package networkbook.logic.commands.edit;

import static java.util.Objects.requireNonNull;

import networkbook.commons.util.ToStringBuilder;
import networkbook.model.person.Graduation;

public class EditGraduationAction implements EditAction {
    private final Graduation graduation;

    public EditGraduationAction(Graduation graduation) {
        this.graduation = graduation;
    }

    @Override
    public void edit(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);
        editPersonDescriptor.setGraduation(graduation);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EditGraduationAction)) {
            return false;
        }

        EditGraduationAction otherAction = (EditGraduationAction) object;
        return this.graduation.equals(otherAction.graduation);
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        toStringBuilder.add("graduation", graduation);
        return toStringBuilder.toString();
    }
}
