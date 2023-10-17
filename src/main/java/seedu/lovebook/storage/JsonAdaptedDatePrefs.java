package seedu.lovebook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.lovebook.commons.exceptions.IllegalValueException;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Income;
import seedu.lovebook.model.person.horoscope.Horoscope;

/**
 * Jackson-friendly version of {@link DatePrefs}.
 */
public class JsonAdaptedDatePrefs {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Date's %s field is missing!";
    private String age;
    private String gender;
    private String height;
    private String income;
    private String horoscope;

    /**
     * Constructs a {@code JsonAdaptedDate} with the given date details.
     */
    @JsonCreator
    public JsonAdaptedDatePrefs(@JsonProperty("age") String age, @JsonProperty("gender") String gender,
                                @JsonProperty("height") String height, @JsonProperty("income") String income,
                                @JsonProperty("horoscope") String horoscope) {
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.income = income;
        this.horoscope = horoscope;
    }

    /**
     * Converts a given {@code Date} into this class for Jackson use.
     */
    public JsonAdaptedDatePrefs(DatePrefs source) {
        age = source.getAge().value;
        gender = source.getGender().value;
        height = source.getHeight().value;
        income = source.getIncome().value;
        horoscope = source.getHoroscope().value;
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's {@code DatePrefs} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date.
     */
    public DatePrefs toModelType() throws IllegalValueException {
        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (height == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }
        if (!Height.isValidHeight(height)) {
            throw new IllegalValueException(Height.MESSAGE_CONSTRAINTS);
        }
        final Height modelHeight = new Height(height);

        if (income == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Income.class.getSimpleName()));
        }
        if (!Income.isValidIncome(income)) {
            throw new IllegalValueException(Income.MESSAGE_CONSTRAINTS);
        }
        final Income modelIncome = new Income(income);

        if (horoscope == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Horoscope.class.getSimpleName()));
        }
        if (!Horoscope.isValidHoroscope(horoscope)) {
            throw new IllegalValueException(Horoscope.MESSAGE_CONSTRAINTS);
        }
        final Horoscope modelHoroscope = new Horoscope(horoscope);

        return new DatePrefs(modelAge, modelGender, modelHeight, modelIncome, modelHoroscope);
    }

}
