package networkbook.logic.commands.edit;

import static java.util.Objects.requireNonNull;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Phone;

public class EditPhoneAction implements EditAction {
    private final Phone phone;
    private final Index index;

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
