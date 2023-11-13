package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.module.Module;

/**
 * Represents a Tutorial in the address book.
 */
public class Tutorial {
    /* Fields */
    public static final String TIME_FORMAT_REGEX = "\\b([1-9]|1[0-2])[APap][Mm]";
    private Module module;
    private String tutName;
    private String time;

    /* Constructors */

    /**
     * Constructs a {@code Tutorial}.
     * This constructor only create a tutorial model
     * to find the actual tutorial in tutorial list.
     * @param module Module tutorial belongs to.
     * @param tutName Name of tutorial.
     */
    public Tutorial(Module module, String tutName) {
        requireNonNull(module);
        requireNonNull(tutName);
        this.module = module;
        this.tutName = tutName;
    }

    /**
     * Constructs a {@code Tutorial}.
     * @param module Module tutorial belongs to.
     * @param tutName Name of tutorial.
     * @param time Time of tutorial.
     */
    public Tutorial(Module module, String tutName, String time) {
        requireNonNull(module);
        requireNonNull(tutName);
        requireNonNull(time);
        this.module = module;
        this.tutName = tutName;
        this.time = time;
    }

    /* Methods */

    public String getModuleCode() {
        return module.getModuleCode();
    }

    public String getTutName() {
        return tutName;
    }

    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tutorial tutorial = (Tutorial) o;

        return Objects.equals(module, tutorial.module) && Objects.equals(tutName, tutorial.tutName);
    }

    /**
     * the equals function of Tutorial
     * @param o the object to compare
     * @throws NullPointerException when Tutorial is created using the constructor with 2 arguments.
     */
    public boolean isSameTutorial(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tutorial tutorial = (Tutorial) o;

        requireNonNull(this.time);
        requireNonNull(tutorial.time);

        return Objects.equals(module, tutorial.module) && Objects.equals(tutName, tutorial.tutName)
                && Objects.equals(time, tutorial.time);
    }

    @Override
    public String toString() {
        requireNonNull(time);

        StringBuffer res = new StringBuffer("");
        res.append(module.getModuleCode() + " ");
        res.append(this.getTutName() + " ");
        res.append(this.getTime() + " ");

        return res.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(module, tutName, time);
    }

    /**
     * reformat time to expected format
     * if time is in correct time format.
     * @param time the time to be reformatted.
     * @return the time in expected format
     * @throws IllegalArgumentException if time is not in correct format
     */
    public static String reformat(String time) {
        String[] dateTimeParts = time.split(" ");
        if (dateTimeParts.length != 2) {
            throw new IllegalArgumentException("incorrect format");
        }

        String dayPart = dateTimeParts[0];
        String timePart = dateTimeParts[1];

        // checks if time is in correct format
        if (!DayOfWeek.contains(dayPart) || !timePart.matches(Tutorial.TIME_FORMAT_REGEX)) {
            throw new IllegalArgumentException("incorrect format");
        }

        StringBuffer res = new StringBuffer();
        res.append(dayPart.substring(0, 1).toUpperCase());
        res.append(dayPart.substring(1, 3).toLowerCase() + " ");
        res.append(timePart.toUpperCase());

        return res.toString();
    }

    private enum DayOfWeek {
        Mon("Monday"),
        Tue("Tuesday"),
        Wed("Wednesday"),
        Thu("Thursday"),
        Fri("Friday"),
        Sat("Saturday"),
        Sun("Sunday");

        public final String fullName;
        DayOfWeek(String fullName) {
            this.fullName = fullName;
        }

        public static boolean contains(String value) {
            for (DayOfWeek day : values()) {
                if (day.name().equalsIgnoreCase(value)) {
                    return true;
                } else if (day.fullName.equalsIgnoreCase(value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
