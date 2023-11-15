package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_POTENTIAL_DUPLICATE_BUYER;
import static seedu.address.logic.Messages.MESSAGE_SIMILAR_SELLER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLING_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_SELLERS;

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
import seedu.address.model.displayable.seller.Seller;
import seedu.address.model.tag.Tag;

//Solution below adapted by https://github.com/nus-cs2103-AY2324S1/tp
/**
 * Edits the details of an existing seller in the address book.
 */
public class EditSellerCommand extends Command {

    public static final String COMMAND_WORD = "sedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the seller identified "
            + "by the index number used in the displayed seller list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_SELLING_ADDRESS + "SELLING_ADDRESS] "
            + "[" + PREFIX_HOUSE_INFO + "SELL_HOUSE_INFO] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_PRIORITY + "PRIORITY] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_SELLER_SUCCESS = "Got it. I've edited a seller contact:\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided!";
    public static final String MESSAGE_DUPLICATE_SELLER = "This seller already exists in the address book!";
    private final Index index;
    private final EditSellerDescriptor editSellerDescriptor;
    private final CommandWarnings commandWarnings;

    /**
     * @param index of the seller in the filtered seller list to edit
     * @param editSellerDescriptor details to edit the seller with
     */
    public EditSellerCommand(Index index, EditSellerDescriptor editSellerDescriptor, CommandWarnings commandWarnings) {
        requireNonNull(index);
        requireNonNull(editSellerDescriptor);
        this.index = index;
        this.editSellerDescriptor = new EditSellerDescriptor(editSellerDescriptor);
        this.commandWarnings = commandWarnings;
    }

    /**
     * @param index of the seller in the filtered seller list to edit
     * @param editSellerDescriptor details to edit the seller with
     */
    public EditSellerCommand(Index index, EditSellerDescriptor editSellerDescriptor) {
        requireNonNull(index);
        requireNonNull(editSellerDescriptor);
        this.index = index;
        this.editSellerDescriptor = new EditSellerDescriptor(editSellerDescriptor);
        this.commandWarnings = new CommandWarnings();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Seller> lastShownList = model.getFilteredSellerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
        }

        Seller sellerToEdit = lastShownList.get(index.getZeroBased());
        Seller editedSeller = createEditedSeller(sellerToEdit, editSellerDescriptor);

        if (!sellerToEdit.isSamePerson(editedSeller) && model.hasSeller(editedSeller)) {
            throw new CommandException(MESSAGE_DUPLICATE_SELLER);
        }

        if (model.sellerHasSameBuyerName(sellerToEdit)) {
            commandWarnings.addWarning(MESSAGE_POTENTIAL_DUPLICATE_BUYER);
        }

        if (!sellerToEdit.isSimilarDisplayable(editedSeller) && model.hasSimilarSeller(editedSeller)) {
            commandWarnings.addWarning(MESSAGE_SIMILAR_SELLER);
        }

        model.setSeller(sellerToEdit, editedSeller);
        model.commitAddressBook();
        model.updateFilteredSellerList(PREDICATE_SHOW_SELLERS);

        if (commandWarnings.containsWarnings()) {
            return new CommandResult(commandWarnings.getWarningMessage());
        }
        return new CommandResult(String.format(MESSAGE_EDIT_SELLER_SUCCESS, Messages.format(editedSeller)));
    }

    /**
     * Creates and returns a {@code Seller} with the details of {@code sellerToEdit}
     * edited with {@code editSellerDescriptor}.
     */
    private static Seller createEditedSeller(Seller sellerToEdit, EditSellerDescriptor editSellerDescriptor) {
        assert sellerToEdit != null;

        Name updatedName = editSellerDescriptor.getName().orElse(sellerToEdit.getName());
        Phone updatedPhone = editSellerDescriptor.getPhone().orElse(sellerToEdit.getPhone());
        Email updatedEmail = editSellerDescriptor.getEmail().orElse(sellerToEdit.getEmail());
        Address updatedAddress = editSellerDescriptor.getAddress().orElse(sellerToEdit.getAddress());
        Address updatedSellingAddress = editSellerDescriptor.getSellingAddress()
                .orElse(sellerToEdit.getSellingAddress());
        HouseInfo updatedHouseInfo = editSellerDescriptor.getHouseInfo().orElse(sellerToEdit.getHouseInfo());
        Set<Tag> updatedTags = editSellerDescriptor.getTags().orElse(sellerToEdit.getTags());
        Priority updatedPriority = editSellerDescriptor.getPriority().orElse(sellerToEdit.getPriority());

        return new Seller(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedSellingAddress,
                updatedHouseInfo, updatedTags, updatedPriority);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSellerCommand)) {
            return false;
        }

        EditSellerCommand otherEditCommand = (EditSellerCommand) other;
        return index.equals(otherEditCommand.index)
                && editSellerDescriptor.equals(otherEditCommand.editSellerDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editSellerDescriptor", editSellerDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the seller with. Each non-empty field value will replace the
     * corresponding field value of the seller.
     */
    public static class EditSellerDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Address sellingAddress;
        private HouseInfo houseInfo;
        private Set<Tag> tags;
        private Priority priority;

        public EditSellerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditSellerDescriptor(EditSellerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setSellingAddress(toCopy.sellingAddress);
            setHouseInfo(toCopy.houseInfo);
            setTags(toCopy.tags);
            setPriority(toCopy.priority);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, sellingAddress,
                    houseInfo, tags, priority);
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
        public void setSellingAddress(Address sellingAddress) {
            this.sellingAddress = sellingAddress;
        }

        public Optional<Address> getSellingAddress() {
            return Optional.ofNullable(sellingAddress);
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
            if (!(other instanceof EditSellerDescriptor)) {
                return false;
            }

            EditSellerDescriptor otherEditSellerDescriptor = (EditSellerDescriptor) other;
            return Objects.equals(name, otherEditSellerDescriptor.name)
                    && Objects.equals(phone, otherEditSellerDescriptor.phone)
                    && Objects.equals(email, otherEditSellerDescriptor.email)
                    && Objects.equals(address, otherEditSellerDescriptor.address)
                    && Objects.equals(sellingAddress, otherEditSellerDescriptor.sellingAddress)
                    && Objects.equals(houseInfo, otherEditSellerDescriptor.houseInfo)
                    && Objects.equals(tags, otherEditSellerDescriptor.tags)
                    && Objects.equals(priority, otherEditSellerDescriptor.priority);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("sellingAddress", sellingAddress)
                    .add("houseInfo", houseInfo)
                    .add("tags", tags)
                    .add("priority", priority)
                    .toString();
        }
    }
}
