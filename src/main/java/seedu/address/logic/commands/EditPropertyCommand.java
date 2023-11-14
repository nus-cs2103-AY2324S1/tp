package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Price;
import seedu.address.model.property.PropAddress;
import seedu.address.model.property.PropName;
import seedu.address.model.property.PropPhone;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing property in the Price book.
 */
public class EditPropertyCommand extends Command {

    public static final String COMMAND_WORD = "editprop";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the property identified "
            + "by the index number used in the displayed property list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25";

    public static final String MESSAGE_EDIT_PROPERTY_SUCCESS = "Edited Property: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property has a duplicate address.";

    private final Index index;
    private final EditPropertyDescriptor editPropertyDescriptor;

    /**
     * @param index of the property in the filtered property list to edit
     * @param editPropertyDescriptor details to edit the property with
     */
    public EditPropertyCommand(Index index, EditPropertyDescriptor editPropertyDescriptor) {
        requireNonNull(index);
        requireNonNull(editPropertyDescriptor);

        this.index = index;
        this.editPropertyDescriptor = new EditPropertyDescriptor(editPropertyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Property> lastShownList = model.getFilteredPropertyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToEdit = lastShownList.get(index.getZeroBased());
        Property editedproperty = createEditedproperty(propertyToEdit, editPropertyDescriptor);

        if (!propertyToEdit.isSameProperty(editedproperty) && model.hasProperty(editedproperty)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        model.setProperty(propertyToEdit, editedproperty);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        return new CommandResult(String.format(MESSAGE_EDIT_PROPERTY_SUCCESS, Messages.format(editedproperty)));
    }

    /**
     * Creates and returns a {@code property} with the details of {@code propertyToEdit}
     * edited with {@code editpropertyDescriptor}.
     */
    private static Property createEditedproperty(Property propertyToEdit,
                                                 EditPropertyDescriptor editpropertyDescriptor) {
        assert propertyToEdit != null;

        PropName updatedName = editpropertyDescriptor.getName().orElse(propertyToEdit.getName());
        PropPhone updatedPhone = editpropertyDescriptor.getPhone().orElse(propertyToEdit.getPhone());
        PropAddress updatedAddress = editpropertyDescriptor.getAddress().orElse(propertyToEdit.getAddress());
        Price updatedPrice = editpropertyDescriptor.getPrice().orElse(propertyToEdit.getPrice());
        Set<Tag> updatedTags = editpropertyDescriptor.getTags().orElse(propertyToEdit.getTags());

        return new Property(updatedName, updatedAddress, updatedPhone, updatedPrice, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPropertyCommand)) {
            return false;
        }

        EditPropertyCommand otherEditCommand = (EditPropertyCommand) other;
        return index.equals(otherEditCommand.index)
                && editPropertyDescriptor.equals(otherEditCommand.editPropertyDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPropertyDescriptor", editPropertyDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the property with. Each non-empty field value will replace the
     * corresponding field value of the property.
     */
    public static class EditPropertyDescriptor {
        private PropName name;
        private PropPhone phone;
        private PropAddress address;
        private Price price;
        private Set<Tag> tags;

        public EditPropertyDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPropertyDescriptor(EditPropertyDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setAddress(toCopy.address);
            setPrice(toCopy.price);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, address, price, tags);
        }

        public void setName(PropName name) {
            this.name = name;
        }

        public Optional<PropName> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(PropPhone phone) {
            this.phone = phone;
        }

        public Optional<PropPhone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setAddress(PropAddress address) {
            this.address = address;
        }

        public Optional<PropAddress> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPropertyDescriptor)) {
                return false;
            }

            EditPropertyDescriptor otherEditpropertyDescriptor = (EditPropertyDescriptor) other;
            return Objects.equals(name, otherEditpropertyDescriptor.name)
                    && Objects.equals(phone, otherEditpropertyDescriptor.phone)
                    && Objects.equals(address, otherEditpropertyDescriptor.address)
                    && Objects.equals(price, otherEditpropertyDescriptor.price)
                    && Objects.equals(tags, otherEditpropertyDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("address", address)
                    .add("price", price)
                    .add("tags", tags)
                    .toString();
        }
    }
}
