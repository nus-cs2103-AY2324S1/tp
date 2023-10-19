package seedu.lovebook.testutil;

import seedu.lovebook.logic.commands.SetPrefCommand;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Income;
import seedu.lovebook.model.person.horoscope.Horoscope;

/**
 * A utility class to help with building preference objects.
 */
public class PreferenceBuilder {
    public static final String DEFAULT_AGE = "49";
    public static final String DEFAULT_GENDER = "M";
    public static final String DEFAULT_HEIGHT = "176";
    public static final String DEFAULT_INCOME = "3000";
    public static final String DEFAULT_HOROSCOPE = "TAURUS";

    private Age age;
    private Gender gender;
    private Height height;
    private Income income;
    private Horoscope horoscope;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PreferenceBuilder() {
        age = new Age(DEFAULT_AGE);
        gender = new Gender(DEFAULT_GENDER);
        height = new Height(DEFAULT_HEIGHT);
        income = new Income(DEFAULT_INCOME);
        horoscope = new Horoscope(DEFAULT_HOROSCOPE);
    }

    public SetPrefCommand.SetPreferenceDescriptor build() {
        return new SetPrefCommand.SetPreferenceDescriptor(age, gender, height, income, horoscope);
    }
}
