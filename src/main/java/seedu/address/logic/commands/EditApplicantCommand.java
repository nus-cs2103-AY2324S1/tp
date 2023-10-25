package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;

/**
 * Edits the details of an existing applicant in the address book.
 */
public class EditApplicantCommand extends Command {

    public static final String COMMAND_WORD = "editApplicant";
    public static final String COMMAND_ALIAS = "edita";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the applicant identified "
            + "by the index number used in the displayed applicant list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + " {applicantName} "
            + PREFIX_PHONE + " {phoneNumber} \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + " Johnny Doe "
            + PREFIX_PHONE + " 91234567";

    public static final String MESSAGE_EDIT_APPLICANT_SUCCESS = "Edited applicant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in the address book.";

    private final Index index;
    private final EditApplicantDescriptor editApplicantDescriptor;

    /**
     * @param index                   of the applicant in the filtered applicant list to edit
     * @param editApplicantDescriptor details to edit the applicant with
     */
    public EditApplicantCommand(Index index, EditApplicantDescriptor editApplicantDescriptor) {
        requireNonNull(index);
        requireNonNull(editApplicantDescriptor);

        this.index = index;
        this.editApplicantDescriptor = new EditApplicantDescriptor(editApplicantDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToEdit = lastShownList.get(index.getZeroBased());
        Applicant editedApplicant = createEditedApplicant(applicantToEdit, editApplicantDescriptor);

        if (!applicantToEdit.isSamePerson(editedApplicant) && model.hasApplicant(editedApplicant)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.setApplicant(applicantToEdit, editedApplicant);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, Messages.format(editedApplicant)));
    }

    /**
     * Creates and returns a {@code Applicant} with the details of {@code applicantToEdit}
     * edited with {@code editApplicantDescriptor}.
     */
    private static Applicant createEditedApplicant(Applicant applicantToEdit,
            EditApplicantDescriptor editApplicantDescriptor) {
        assert applicantToEdit != null;

        Name updatedName = editApplicantDescriptor.getName().orElse(applicantToEdit.getName());
        Phone updatedPhone = editApplicantDescriptor.getPhone().orElse(applicantToEdit.getPhone());

        return new Applicant(updatedName, updatedPhone);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditApplicantCommand)) {
            return false;
        }

        EditApplicantCommand otherEditApplicantCommand = (EditApplicantCommand) other;
        return index.equals(otherEditApplicantCommand.index)
                && editApplicantDescriptor.equals(otherEditApplicantCommand.editApplicantDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editApplicantDescriptor", editApplicantDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the applicant with. Each non-empty field value will replace the
     * corresponding field value of the applicant.
     */
    public static class EditApplicantDescriptor {
        private Name name;
        private Phone phone;

        public EditApplicantDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditApplicantDescriptor(EditApplicantDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone);
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

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditApplicantDescriptor)) {
                return false;
            }

            EditApplicantDescriptor otherEditApplicantDescriptor = (EditApplicantDescriptor) other;
            return Objects.equals(name, otherEditApplicantDescriptor.name)
                    && Objects.equals(phone, otherEditApplicantDescriptor.phone);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .toString();
        }
    }
}
