package networkbook.logic.commands.edit;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Specialisation;

/**
 * Represents an action to edit a specialisation of a person.
 */
public class EditSpecialisationAction implements EditAction {
    private final Specialisation specialisation;
    private final Index index;

    /**
     * Constructs a new action to edit specialisation.
     * @param index The index of specialisation in the specialisation of the person.
     * @param specialisation The new value of specialisation.
     */
    public EditSpecialisationAction(Index index , Specialisation specialisation) {
        this.index = index;
        this.specialisation = specialisation;
    }

    @Override
    public void edit(EditPersonDescriptor editPersonDescriptor, Index indexOfPerson) throws CommandException {
        assert editPersonDescriptor != null : "editPersonDescriptor should not be null";
        editPersonDescriptor.setSpecialisation(index, specialisation, indexOfPerson);
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
