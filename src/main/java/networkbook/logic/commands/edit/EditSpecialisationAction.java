package networkbook.logic.commands.edit;

import static java.util.Objects.requireNonNull;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Specialisation;

public class EditSpecialisationAction implements EditAction {
    private final Specialisation specialisation;
    private final Index index;

    public EditSpecialisationAction(Index index , Specialisation specialisation) {
        this.index = index;
        this.specialisation = specialisation;
    }

    @Override
    public void edit(EditPersonDescriptor editPersonDescriptor) throws CommandException {
        requireNonNull(editPersonDescriptor);
        editPersonDescriptor.setSpecialisation(index, specialisation);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EditSpecialisationAction)) {
            return false;
        }

        EditSpecialisationAction otherAction = (EditSpecialisationAction) object;
        return this.specialisation.equals(otherAction.specialisation)
                && this.index.equals(otherAction.index);
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        toStringBuilder.add("specialisation", this.specialisation);
        toStringBuilder.add("index", this.index.getOneBased());
        return toStringBuilder.toString();
    }
}
