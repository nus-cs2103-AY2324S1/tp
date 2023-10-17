package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.lovebook.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.lovebook.commons.core.index.Index;
import seedu.lovebook.commons.util.CollectionUtil;
import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Income;
import seedu.lovebook.model.person.Name;

/**
 * Edits the details of an existing date in the lovebook book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the date identified "
            + "by the index number used in the displayed date list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_HEIGHT + "HEIGHT] "
            + "[" + PREFIX_INCOME + "INCOME] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_AGE + "91234567 "
            + PREFIX_GENDER + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Date: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This date already exists in the lovebook book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the date in the filtered date list to edit
     * @param editPersonDescriptor details to edit the date with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Date> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Date dateToEdit = lastShownList.get(index.getZeroBased());
        Date editedDate = createEditedPerson(dateToEdit, editPersonDescriptor);

        if (!dateToEdit.isSamePerson(editedDate) && model.hasPerson(editedDate)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(dateToEdit, editedDate);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedDate)));
    }

    /**
     * Creates and returns a {@code Date} with the details of {@code dateToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Date createEditedPerson(Date dateToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert dateToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(dateToEdit.getName());
        Age updatedAge = editPersonDescriptor.getAge().orElse(dateToEdit.getAge());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(dateToEdit.getGender());
        Height updatedHeight = editPersonDescriptor.getHeight().orElse(dateToEdit.getHeight());
        Income updatedIncome = editPersonDescriptor.getIncome().orElse(dateToEdit.getIncome());

        return new Date(updatedName, updatedAge, updatedGender, updatedHeight, updatedIncome);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the date with. Each non-empty field value will replace the
     * corresponding field value of the date.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Age age;
        private Gender gender;
        private Height height;
        private Income income;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setAge(toCopy.age);
            setGender(toCopy.gender);
            setHeight(toCopy.height);
            setIncome(toCopy.income);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, age, gender, height);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setHeight(Height height) {
            this.height = height;
        }

        public void setIncome(Income income) {
            this.income = income;
        }

        public Optional<Height> getHeight() {
            return Optional.ofNullable(height);
        }

        public Optional<Income> getIncome() {
            return Optional.ofNullable(income);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(age, otherEditPersonDescriptor.age)
                    && Objects.equals(gender, otherEditPersonDescriptor.gender)
                    && Objects.equals(height, otherEditPersonDescriptor.height)
                    && Objects.equals(income, otherEditPersonDescriptor.income);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("age", age)
                    .add("gender", gender)
                    .add("height", height)
                    .add("income", income)
                    .toString();
        }
    }
}
