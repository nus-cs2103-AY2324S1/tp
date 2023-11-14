package seedu.lovebook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.lovebook.commons.exceptions.IllegalValueException;
import seedu.lovebook.model.date.Age;
import seedu.lovebook.model.date.Avatar;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.Gender;
import seedu.lovebook.model.date.Height;
import seedu.lovebook.model.date.Income;
import seedu.lovebook.model.date.Name;
import seedu.lovebook.model.date.Star;
import seedu.lovebook.model.date.horoscope.Horoscope;

/**
 * Jackson-friendly version of {@link Date}.
 */
class JsonAdaptedDate {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Date's %s field is missing!";
    public static final String INVALID_AVATAR = "Date's avatar is invalid!";

    private final String name;
    private final String age;
    private final String gender;
    private final String height;
    private final String income;
    private final String horoscope;
    private final String star;
    private final String avatar;

    /**
     * Constructs a {@code JsonAdaptedDate} with the given date details.
     */
    @JsonCreator
    public JsonAdaptedDate(@JsonProperty("name") String name, @JsonProperty("age") String age,
            @JsonProperty("gender") String gender, @JsonProperty("height") String height,
            @JsonProperty("income") String income, @JsonProperty("horoscope") String horoscope,
            @JsonProperty("avatar") String avatar) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.income = income;
        this.horoscope = horoscope;
        this.star = "false";
        this.avatar = avatar;
    }

    /**
     * Converts a given {@code Date} into this class for Jackson use.
     */
    public JsonAdaptedDate(Date source) {
        name = source.getName().fullName;
        age = source.getAge().value;
        gender = source.getGender().value;
        height = source.getHeight().value;
        income = source.getIncome().value;
        horoscope = source.getHoroscope().value;
        star = source.getStar().isStarred;
        avatar = source.getAvatar().value;
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's {@code Date} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date.
     */
    public Date toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Horoscope.class.getSimpleName()));
        }
        if (!Horoscope.isValidHoroscope(horoscope)) {
            throw new IllegalValueException(Horoscope.MESSAGE_CONSTRAINTS);
        }
        final Horoscope modelHoroscope = new Horoscope(horoscope);

        if (avatar == null) {
            throw new IllegalValueException(INVALID_AVATAR);
        }
        if (!Avatar.isValidAvatar(avatar)) {
            throw new IllegalValueException(Avatar.MESSAGE_CONSTRAINTS);
        }
        final Avatar modelAvatar = new Avatar(avatar);
        final Star modelStar = new Star(star);

        return new Date(modelName, modelAge, modelGender, modelHeight,
                modelIncome, modelHoroscope, modelStar, modelAvatar);
    }

}
