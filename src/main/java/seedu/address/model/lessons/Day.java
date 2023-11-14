package seedu.address.model.lessons;

import static seedu.address.logic.parser.RegularExpressionUtil.FOUR_DIGITS;
import static seedu.address.logic.parser.RegularExpressionUtil.ONE_TO_TWO_DIGITS;
import static seedu.address.logic.parser.RegularExpressionUtil.TWO_DIGITS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.TypeParsingUtil;
import seedu.address.logic.parser.exceptions.InvalidInputException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListEntryField;

/**
 * Represents a Day in the application.
 * Guarantees: immutable;
 */
public class Day extends ListEntryField {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final Day DEFAULT_DAY = new Day();
    private LocalDate day;
    public Day(LocalDate day) {
        this.day = day;
    }
    /**
     * Constructs a {@code Day} from input of format "dd-MM-yyyy".
     */
    public Day(String str) throws ParseException {
        this.day = parseDate(str);
    }
    private Day() {
    }
    /**
     * Constructs a {@code Day} from input of format "dd-MM-yyyy".
     */
    public static Day of(String str) throws ParseException {
        return new Day(str);
    }
    /**
     * Returns a serialized string of the day.
     */
    public static Day deserialize(String str) {
        return new Day(LocalDate.parse(str, FORMATTER));
    }

    /**
     * Parses the date from the input string, which can be in the following formats:
     * 1. dd, then the date will be the current month and year
     * 2. mm/dd ,then the date will be the current year
     * 3. yyyy/mm/dd ,then the date will be the given year
     * 4. yy/mm/dd ,then the date will be the given year + 2000
     * @param input the input string where the date is to be parsed from
     * @return the date parsed
     * @throws ParseException if the input is not a valid date
     */
    private static LocalDate parseDate(String input) throws ParseException {
        String dayPattern = ONE_TO_TWO_DIGITS;
        String monthDayPattern = ONE_TO_TWO_DIGITS + "/" + ONE_TO_TWO_DIGITS;
        String yearMonthDayPattern2 = TWO_DIGITS + "/" + ONE_TO_TWO_DIGITS + "/" + ONE_TO_TWO_DIGITS;
        String yearMonthDayPattern4 = FOUR_DIGITS + "/" + ONE_TO_TWO_DIGITS + "/" + ONE_TO_TWO_DIGITS;

        Pattern dayP = Pattern.compile(dayPattern);
        Pattern monthDayP = Pattern.compile(monthDayPattern);
        Pattern yearMonthDayP2 = Pattern.compile(yearMonthDayPattern2);
        Pattern yearMonthDayP4 = Pattern.compile(yearMonthDayPattern4);

        Matcher dayM = dayP.matcher(input);
        Matcher monthDayM = monthDayP.matcher(input);
        Matcher yearMonthDayM2 = yearMonthDayP2.matcher(input);
        Matcher yearMonthDayM4 = yearMonthDayP4.matcher(input);

        if (yearMonthDayM2.matches()) {
            int year = TypeParsingUtil.parseNum(yearMonthDayM2.group(1), 0, 99) + 2000;
            int month = TypeParsingUtil.parseNum(yearMonthDayM2.group(2), 1, 12);
            int day = TypeParsingUtil.parseNum(yearMonthDayM2.group(3), 1, findMaxDay(year, month));
            return LocalDate.of(year, month, day);
        } else if (yearMonthDayM4.matches()) {
            int year = TypeParsingUtil.parseNum(yearMonthDayM4.group(1), 0, 9999);
            int month = TypeParsingUtil.parseNum(yearMonthDayM4.group(2), 1, 12);
            int day = TypeParsingUtil.parseNum(yearMonthDayM4.group(3), 1, findMaxDay(year, month));
            return LocalDate.of(year, month, day);
        } else if (monthDayM.matches()) {
            LocalDate now = LocalDate.now();
            int month = TypeParsingUtil.parseNum(monthDayM.group(1), 1, 12);
            int day = TypeParsingUtil.parseNum(monthDayM.group(2), 1, findMaxDay(now.getYear(), month));
            return LocalDate.of(now.getYear(), month, day);
        } else if (dayM.matches()) {
            LocalDate now = LocalDate.now();
            int day = TypeParsingUtil.parseNum(dayM.group(1), 1, findMaxDay(now.getYear(), now.getMonthValue()));
            return LocalDate.of(now.getYear(), now.getMonth(), day);
        } else {
            throw new InvalidInputException(input
                    + " is not a valid date, please use yyyy/mm/dd or mm/dd or dd"
                    + "\nfor example, assume today is 2023/11/3, to add 2023/11/29, could"
                    + " use 29, 11/29, 2023/11/29 or 23/11/29");
        }
    }
    private static Integer findMaxDay(int year, int month) {
        if (month == 2) {
            if (year % 4 == 0) {
                return 29;
            } else {
                return 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }
    @Override
    public String toString() {
        if (this == DEFAULT_DAY) {
            return "To be added";
        }
        return day.format(FORMATTER);
    }
    public LocalDate getDay() {
        return day;
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Day)) {
            return false;
        }
        Day otherDay = (Day) other;
        if (this == DEFAULT_DAY || other == DEFAULT_DAY) {
            return false;
        }
        return this.day.equals(otherDay.day);
    }
    /**
     * Compares this day with another day, return 0 if equal, 1 if after, -1 if before.
     */
    public int compareTo(Day other) {
        if (this == DEFAULT_DAY || other == DEFAULT_DAY) {
            return other == DEFAULT_DAY ? -1 : 1;
        }
        return this.day.compareTo(other.day);
    }
    /**
     * Return a clone of this day.
     */
    @Override
    public Day clone() {
        return this == DEFAULT_DAY ? DEFAULT_DAY : new Day(this.day);
    }
}
