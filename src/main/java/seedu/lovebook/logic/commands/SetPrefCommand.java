package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HOROSCOPE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;

import java.util.Objects;
import java.util.Optional;

import seedu.lovebook.commons.util.CollectionUtil;
import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Income;
import seedu.lovebook.model.person.horoscope.Horoscope;

/**
 * Sets the Preference of the user in the LoveBook.
 */
public class SetPrefCommand extends Command {

    public static final String COMMAND_WORD = "setP";

    /**
     * editP only for Age, Gender, Height, Income
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets date preference. "
            + "Parameters: "
            + PREFIX_AGE + "AGE "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_HEIGHT + "HEIGHT "
            + PREFIX_INCOME + "INCOME "
            + PREFIX_HOROSCOPE + "HOROSCOPE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_AGE + "21 "
            + PREFIX_GENDER + "M "
            + PREFIX_HEIGHT + "23124 "
            + PREFIX_INCOME + "3000 "
            + PREFIX_HOROSCOPE + "Scorpio ";

    public static final String MESSAGE_EDIT_PREF_SUCCESS = "Updated preferences: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n"
            + "Please try the following command format:\n"
            + MESSAGE_USAGE;

    private final SetPreferenceDescriptor setPreferenceDescriptor;

    /**
     * @param setPreferenceDescriptor details to edit the preferences with
     */
    public SetPrefCommand(SetPreferenceDescriptor setPreferenceDescriptor) {
        requireNonNull(setPreferenceDescriptor);
        if (!setPreferenceDescriptor.isAnyFieldEdited()) {
            throw new RuntimeException("SetPreferenceDescriptor cannot have empty fields");
        }
        this.setPreferenceDescriptor = new SetPreferenceDescriptor(setPreferenceDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // OK to type cast this because I know this is ReadOnlyDatePrefs
        DatePrefs datePrefsToEdit = (DatePrefs) model.getDatePrefs();
        DatePrefs editedDatePreference = createEditedPreference(datePrefsToEdit, setPreferenceDescriptor);
        model.setDatePrefs(editedDatePreference);
        return new CommandResult(String.format(MESSAGE_EDIT_PREF_SUCCESS, Messages.format(editedDatePreference)));
    }

    /**
     * Creates and returns a {@code DatePrefs} with the details of {@code datePrefsToEdit}
     * edited with {@code setPreferenceDescriptor}.
     */
    private static DatePrefs createEditedPreference(
            DatePrefs datePrefsToEdit, SetPreferenceDescriptor setPreferenceDescriptor) {
        assert datePrefsToEdit != null;

        Age updatedAge = setPreferenceDescriptor.getAge().orElse(datePrefsToEdit.getAge());
        Gender updatedGender = setPreferenceDescriptor.getGender().orElse(datePrefsToEdit.getGender());
        Height updatedHeight = setPreferenceDescriptor.getHeight().orElse(datePrefsToEdit.getHeight());
        Income updatedIncome = setPreferenceDescriptor.getIncome().orElse(datePrefsToEdit.getIncome());
        Horoscope updatedHoroscope = setPreferenceDescriptor.getHoroscope().orElse(datePrefsToEdit.getHoroscope());

        return new DatePrefs(updatedAge, updatedGender, updatedHeight, updatedIncome, updatedHoroscope);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("setPreferenceDescriptor", setPreferenceDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the preferences with. Each non-empty field value will replace the
     * corresponding field value of the preference.
     */
    public static class SetPreferenceDescriptor {
        private Age age;
        private Gender gender;
        private Height height;
        private Income income;
        private Horoscope horoscope;

        public SetPreferenceDescriptor() {}

        /**
         * Constructor that accepts default values for each metric
         */
        public SetPreferenceDescriptor(Age age, Gender gender, Height height, Income income, Horoscope horoscope) {
            setAge(age);
            setGender(gender);
            setHeight(height);
            setIncome(income);
            setHoroscope(horoscope);
        }

        /**
         * Copy Constructor
         * A defensive copy of {@code tags} is used internally.
         */
        public SetPreferenceDescriptor(SetPreferenceDescriptor toCopy) {
            setAge(toCopy.age);
            setGender(toCopy.gender);
            setHeight(toCopy.height);
            setIncome(toCopy.income);
            setHoroscope(toCopy.horoscope);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(age, gender, height, income, horoscope);
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

        public Optional<Height> getHeight() {
            return Optional.ofNullable(height);
        }

        public void setIncome(Income income) {
            this.income = income;
        }

        public Optional<Income> getIncome() {
            return Optional.ofNullable(income);
        }

        public void setHoroscope(Horoscope horoscope) {
            this.horoscope = horoscope;
        }

        public Optional<Horoscope> getHoroscope() {
            return Optional.ofNullable(horoscope);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SetPreferenceDescriptor)) {
                return false;
            }

            SetPreferenceDescriptor otherEditPreferenceDescriptor = (SetPreferenceDescriptor) other;
            return Objects.equals(age, otherEditPreferenceDescriptor.age)
                    && Objects.equals(gender, otherEditPreferenceDescriptor.gender)
                    && Objects.equals(height, otherEditPreferenceDescriptor.height)
                    && Objects.equals(income, otherEditPreferenceDescriptor.income)
                    && Objects.equals(horoscope, otherEditPreferenceDescriptor.horoscope);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("age", age)
                    .add("gender", gender)
                    .add("height", height)
                    .add("income", income)
                    .add("horoscope", horoscope)
                    .toString();
        }
    }
}
