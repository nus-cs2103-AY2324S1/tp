package seedu.lovebook.logic.commands;

import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Objects;
import java.util.Optional;

import seedu.lovebook.commons.util.CollectionUtil;
import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Income;
import seedu.lovebook.model.person.Name;

public class EditPrefCommand extends Command {

    public static final String COMMAND_WORD = "editP";

    /**
     * editP only for Age, Gender, Height, Income
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits date preference. "
            + "Parameters: "
            + PREFIX_AGE + "AGE "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_HEIGHT + "HEIGHT "
            + PREFIX_INCOME + "INCOME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_AGE + "21 "
            + PREFIX_GENDER + "M "
            + PREFIX_HEIGHT + "23124 "
            + PREFIX_INCOME + "3000 ";

    public static final String MESSAGE_EDIT_PREF_SUCCESS = "Updated preferences: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This date already exists in the LoveBook.";

    public EditPrefCommand(EditPreferenceDescriptor editPreferenceDescriptor) {

    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EDIT_PREF_SUCCESS, true, false);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

    /**
     * Stores the details to edit the preferences with. Each non-empty field value will replace the
     * corresponding field value of the preference.
     */
    public static class EditPreferenceDescriptor {
        private Age age;
        private Gender gender;
        private Height height;
        private Income income;

        public EditPreferenceDescriptor() {}

        /**
         * Copy Constructor
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPreferenceDescriptor(EditPreferenceDescriptor toCopy) {
            setAge(toCopy.age);
            setGender(toCopy.gender);
            setHeight(toCopy.height);
            setIncome(toCopy.income);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(age, gender, height, income);
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
            if (!(other instanceof EditPrefCommand.EditPreferenceDescriptor)) {
                return false;
            }

            EditPrefCommand.EditPreferenceDescriptor otherEditPreferenceDescriptor = (EditPrefCommand.EditPreferenceDescriptor) other;
            return Objects.equals(age, otherEditPreferenceDescriptor.age)
                    && Objects.equals(gender, otherEditPreferenceDescriptor.gender)
                    && Objects.equals(height, otherEditPreferenceDescriptor.height)
                    && Objects.equals(income, otherEditPreferenceDescriptor.income);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("age", age)
                    .add("gender", gender)
                    .add("height", height)
                    .add("income", income)
                    .toString();
        }
    }
}
