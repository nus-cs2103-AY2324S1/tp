package networkbook.logic.commands.edit;

import static java.util.Objects.requireNonNull;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Phone;

/**
 * Represents an action to edit a phone number of person.
 */
public class EditPhoneAction implements EditAction {
    private final Phone phone;
    private final Index index;

    /**
     * Constructs a new action to edit phone number.
     * @param index The index of the phone number in the phone number list of the person.
     * @param phone The new value of phone number.
     */
    public EditPhoneAction(Index index, Phone phone) {
        this.index = index;
        this.phone = phone;
    }

    @Override
    public void edit(EditPersonDescriptor editPersonDescriptor) throws CommandException {
        requireNonNull(editPersonDescriptor);
        editPersonDescriptor.setPhone(index, phone);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EditPhoneAction)) {
            return false;
        }

        EditPhoneAction otherAction = (EditPhoneAction) object;
        return this.phone.equals(otherAction.phone)
                && this.index.equals(otherAction.index);
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        toStringBuilder.add("phone", this.phone);
        toStringBuilder.add("index", this.index.getOneBased());
        return toStringBuilder.toString();
    }
}
