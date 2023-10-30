package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditRoomDescriptor;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingPeriod;
import seedu.address.model.booking.Remark;
import seedu.address.model.booking.Room;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditCommand.EditRoomDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditCommand.EditRoomDescriptor();
    }

    public EditPersonDescriptorBuilder(EditCommand.EditRoomDescriptor descriptor) {
        this.descriptor = new EditRoomDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Booking booking) {
        descriptor = new EditCommand.EditRoomDescriptor();
        descriptor.setRoom(booking.getRoom());
        descriptor.setName(booking.getName());
        descriptor.setPhone(booking.getPhone());
        descriptor.setEmail(booking.getEmail());
        descriptor.setBookingPeriod(booking.getBookingPeriod());
        descriptor.setRemark(booking.getRemark());
    }


    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRoom(String room) {
        descriptor.setRoom(new Room(room));
        return this;
    }

    /**
     * Sets the {@code BookingPeriod} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withBookingPeriod(String address) {
        descriptor.setBookingPeriod(new BookingPeriod(address));
        return this;
    }
    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    public EditCommand.EditRoomDescriptor build() {
        return descriptor;
    }
}
