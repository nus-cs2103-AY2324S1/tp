package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_POTENTIAL_DUPLICATE_SELLER;
import static seedu.address.logic.Messages.MESSAGE_SIMILAR_BUYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_BUYERS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.Priority;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.tag.Tag;

//Solution below adapted by https://github.com/nus-cs2103-AY2324S1/tp
/**
 * Edits the details of an existing buyer in the address book.
 */
public class EditBuyerCommand extends Command {

    public static final String COMMAND_WORD = "bedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the buyer identified "
            + "by the index number used in the displayed buyer list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_HOUSE_INFO + "BUY_HOUSE_INFO] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_PRIORITY + "PRIORITY] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";
    public static final String MESSAGE_EDIT_BUYER_SUCCESS = "Got it. I've edited a buyer contact:\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided!";
    public static final String MESSAGE_DUPLICATE_BUYER = "This buyer already exists in the address book!";


    private final Index index;
    private final EditBuyerDescriptor editBuyerDescriptor;
    private final CommandWarnings commandWarnings;

    /**
     * @param index of the buyer in the filtered buyer list to edit
     * @param editBuyerDescriptor details to edit the buyer with
     */
    public EditBuyerCommand(Index index, EditBuyerDescriptor editBuyerDescriptor, CommandWarnings commandWarnings) {
        requireNonNull(index);
        requireNonNull(editBuyerDescriptor);
        this.index = index;
        this.editBuyerDescriptor = new EditBuyerDescriptor(editBuyerDescriptor);
        this.commandWarnings = commandWarnings;
    }
    /**
     * @param index of the buyer in the filtered buyer list to edit
     * @param editBuyerDescriptor details to edit the buyer with
     */
    public EditBuyerCommand(Index index, EditBuyerDescriptor editBuyerDescriptor) {
        requireNonNull(index);
        requireNonNull(editBuyerDescriptor);
        this.index = index;
        this.editBuyerDescriptor = new EditBuyerDescriptor(editBuyerDescriptor);
        this.commandWarnings = new CommandWarnings();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredBuyerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
        }

        Buyer buyerToEdit = lastShownList.get(index.getZeroBased());
        Buyer editedBuyer = createEditedBuyer(buyerToEdit, editBuyerDescriptor);

        if (!buyerToEdit.isSamePerson(editedBuyer) && model.hasBuyer(editedBuyer)) {
            throw new CommandException(MESSAGE_DUPLICATE_BUYER);
        }

        if (model.buyerHasSameSellerName(editedBuyer)) {
            commandWarnings.addWarning(MESSAGE_POTENTIAL_DUPLICATE_SELLER);
        }

        if (!buyerToEdit.isSimilarDisplayable(editedBuyer) && model.hasSimilarBuyer(editedBuyer)) {
            commandWarnings.addWarning(MESSAGE_SIMILAR_BUYER);
        }

        model.setBuyer(buyerToEdit, editedBuyer);
        model.commitAddressBook();
        model.updateFilteredBuyerList(PREDICATE_SHOW_BUYERS);

        if (commandWarnings.containsWarnings()) {
            return new CommandResult(commandWarnings.getWarningMessage());
        }
        return new CommandResult(String.format(MESSAGE_EDIT_BUYER_SUCCESS, Messages.format(editedBuyer)));
    }

    /**
     * Creates and returns a {@code Buyer} with the details of {@code buyerToEdit}
     * edited with {@code editBuyerDescriptor}.
     */
    private static Buyer createEditedBuyer(Buyer buyerToEdit, EditBuyerDescriptor editBuyerDescriptor) {
        assert buyerToEdit != null;

        Name updatedName = editBuyerDescriptor.getName().orElse(buyerToEdit.getName());
        Phone updatedPhone = editBuyerDescriptor.getPhone().orElse(buyerToEdit.getPhone());
        Email updatedEmail = editBuyerDescriptor.getEmail().orElse(buyerToEdit.getEmail());
        Address updatedAddress = editBuyerDescriptor.getAddress().orElse(buyerToEdit.getAddress());
        HouseInfo updatedHouseInfo = editBuyerDescriptor.getHouseInfo().orElse(buyerToEdit.getHouseInfo());
        Set<Tag> updatedTags = editBuyerDescriptor.getTags().orElse(buyerToEdit.getTags());
        Priority updatedPriority = editBuyerDescriptor.getPriority().orElse(buyerToEdit.getPriority());

        return new Buyer(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedHouseInfo,
                updatedTags, updatedPriority);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBuyerCommand)) {
            return false;
        }

        EditBuyerCommand otherEditCommand = (EditBuyerCommand) other;
        return index.equals(otherEditCommand.index)
                && editBuyerDescriptor.equals(otherEditCommand.editBuyerDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editBuyerDescriptor", editBuyerDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the buyer with. Each non-empty field value will replace the
     * corresponding field value of the buyer.
     */
    public static class EditBuyerDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private HouseInfo houseInfo;
        private Set<Tag> tags;
        private Priority priority;

        public EditBuyerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBuyerDescriptor(EditBuyerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setHouseInfo(toCopy.houseInfo);
            setTags(toCopy.tags);
            setPriority(toCopy.priority);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, houseInfo, tags, priority);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }
        public void setHouseInfo(HouseInfo houseInfo) {
            this.houseInfo = houseInfo;
        }

        public Optional<HouseInfo> getHouseInfo() {
            return Optional.ofNullable(houseInfo);
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

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBuyerDescriptor)) {
                return false;
            }

            EditBuyerDescriptor otherEditBuyerDescriptor = (EditBuyerDescriptor) other;
            return Objects.equals(name, otherEditBuyerDescriptor.name)
                    && Objects.equals(phone, otherEditBuyerDescriptor.phone)
                    && Objects.equals(email, otherEditBuyerDescriptor.email)
                    && Objects.equals(address, otherEditBuyerDescriptor.address)
                    && Objects.equals(houseInfo, otherEditBuyerDescriptor.houseInfo)
                    && Objects.equals(tags, otherEditBuyerDescriptor.tags)
                    && Objects.equals(priority, otherEditBuyerDescriptor.priority);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("houseInfo", houseInfo)
                    .add("tags", tags)
                    .add("priority", priority)
                    .toString();
        }
    }
}
