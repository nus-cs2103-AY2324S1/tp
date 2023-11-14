package seedu.address.commons.util;

import static java.util.Objects.isNull;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Parses String objects into LocalDateTime, and vice versa.
 */
public class DateTimeParserUtil {
    // Do NOT change DATE_FORMAT and TIME_FORMAT for now, this WILL mess up the parsing.
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String TIME_FORMAT = "HH:mm";

    public static final String DATE_TIME_CONSTRAINTS =
            "Date and time must follow the format of " + DATE_FORMAT + " " + TIME_FORMAT;

    private static final Logger logger = Logger.getLogger(DateTimeParserUtil.class.getName());

    /**
     * Parses a given string of the format stated in DATE_FORMAT and TIME_FORMAT into the LocalDateTime format.
     * If the string provided is empty or invalid, this method returns null.
     *
     * @param dateTimeString The String object to be converted.
     * @return The dateTime as a LocalDateTime object.
     */
    public static LocalDateTime parseStringToDateTime(String dateTimeString) throws DateTimeParseException {
        String toParse = dateTimeString.strip();

        if (Objects.equals(toParse, "")) {
            logger.info("The String provided for parsing is empty");
            return null;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
            return LocalDateTime.parse(toParse, formatter);
        } catch (DateTimeParseException e) {
            logger.info("ERROR PARSING String TO LocalDateTime : " + e.getMessage());
            return null;
        }
    }

    /**
     * Parses a given string of the format stated in DATE_FORMAT and TIME_FORMAT into the LocalDateTime format.
     * If the string provided is empty or invalid, this method throws a DateTimeParseException.
     *
     * @param dateTimeString The String object to be converted.
     * @return The dateTime as a LocalDateTime object.
     * @throws DateTimeParseException if the string provided is empty or invalid.
     */
    public static LocalDateTime parseStringToDateTimeThrow(String dateTimeString) throws DateTimeParseException {
        String toParse = dateTimeString.strip();

        if (Objects.equals(toParse, "")) {
            logger.info("The String provided for parsing is empty");
            throw new DateTimeParseException("The String provided for parsing is empty", toParse, 0);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
            return LocalDateTime.parse(toParse, formatter);
        } catch (DateTimeParseException e) {
            logger.info("ERROR PARSING String TO LocalDateTime : " + e.getMessage());
            throw new DateTimeParseException("ERROR PARSING String TO LocalDateTime : " + e.getMessage(), toParse, 0);
        }

    }

    /**
     * Converts the interview date and time to a formatted string and returns it.
     * If the LocalDateTime object provided is empty or invalid, this method returns an empty String.
     *
     * @param dateTimeLdt The LocalDateTime object to be converted.
     * @return The dateTime as a String object.
     */
    public static String parseDateTimeToString(LocalDateTime dateTimeLdt) {
        if (isNull(dateTimeLdt)) {
            logger.info("The LocalDateTime object provided for parsing is empty");
            return "";
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
            return dateTimeLdt.format(formatter);
        } catch (DateTimeException e) {
            logger.info("ERROR PARSING LocalDateTime to String : " + e.getMessage());
            return "";
        }

    }

    /**
     * Checks if a given dateTime String is in the correct format to be parsed, based on DATE_FORMAT and TIME_FORMAT.
     *
     * @param input The String to be checked.
     * @return true if the string is in the correct format, and false if not.
     */
    public static boolean isValidDateTimeString(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Checks if a given dateTime LocalDateTime object is in the correct format to be parsed,
     * based on DATE_FORMAT and TIME_FORMAT.
     *
     * @param input The LocalDateTime object to be checked.
     * @return true if the LocalDateTime object is in the correct format, and false if not.
     */
    public static boolean isValidLocalDateTime(LocalDateTime input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
            input.format(formatter);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Checks if a given LocalDateTime is happening within a week from the current date.
     *
     * @param dateTime The LocalDateTime to check.
     * @return true if the dateTime is within a week, false otherwise.
     */
    public static boolean isWithinAWeek(LocalDateTime dateTime) {
        if (dateTime == null) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, dateTime);
        long days = duration.toDays();

        return days >= 0 && days <= 7;
    }

    /**
     * Returns true if a given date and time is after the current date and time.
     *
     * @param dateTime The given date and time.
     * @return true if it is after.
     */
    public static boolean isAfterNow(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        return dateTime.isAfter(now);
    }

}
