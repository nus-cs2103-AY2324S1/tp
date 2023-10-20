package seedu.address.logic.parser.FlagParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtil {
    private static final Pattern FLAG_PATTERN = Pattern.compile("-([\\w-]+)\\s*([\\w-]+)");
    // parsers responsible for parsing the input string into the correct argument type
    private static final HashMap<String, Function<String,?>> typeParsers = new HashMap<>() {
        {
            put("num", Integer::parseInt);
            put("str", Function.identity());
            put("time", ParserUtil::parseTime);
            put("day", ParserUtil::parseDay);
            put("month-day", ParserUtil::parseMonthDay);
            put("year-month-day",ParserUtil::parseYearMonthDay);
            put("dayOfWeek",ParserUtil::parseDayOfWeek );
        }
    };
    // validators responsible for validating the input string against the correct argument type
    private static final HashMap<String, Function<String,Boolean>> typeValidators = new HashMap<>() {
        {
            put("num", validatorBuilder("\\d+"));
            put("str", validatorBuilder("\\w+"));
            put("time", validatorBuilder("\\d{1,2}:\\d{2}"));
            put("day", validatorBuilder("\\d{1,2}"));
            put("month-day", validatorBuilder("\\d{1,2}-\\d{1,2}"));
            put("year-month-day", validatorBuilder("\\d{4}-\\d{1,2}-\\d{1,2}"));
            put("dayOfWeek", validatorBuilder("monday|tuesday|wednesday|thursday|friday|saturday|sunday"));
        }
    };
    // sample usages for each argument type
    private static final HashMap<String,String> typeSampleUsages = new HashMap<>() {
        {
            put("num", "1");
            put("str", "hello");
            put("time", "12:30");
            put("day", "1");
            put("month-day", "1-1");
            put("year-month-day", "2020-1-1");
            put("dayOfWeek", "monday");
        }
    };

    private static Function<String, Boolean> validatorBuilder(String pattern) {
        Pattern p = Pattern.compile(pattern);
        return (String input) -> {
            Matcher m = p.matcher(input.toLowerCase());
            return m.matches();
        };
    };

    private static LocalTime parseTime(String input) {
        String[] parts = input.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return java.time.LocalTime.of(hour, minute);
    };
    private static java.time.DayOfWeek parseDayOfWeek(String input) {
        return java.time.DayOfWeek.valueOf(input.toUpperCase());
    };

    private static java.time.LocalDate parseYearMonthDay(String input) {
        String[] parts = input.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        return java.time.LocalDate.of(year, month, day);
    };

    private static java.time.LocalDate parseMonthDay (String input) {
        String[] parts = input.split("-");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        LocalDate date = LocalDate.now();
        return java.time.LocalDate.of(date.getYear(), month, day);
    };

    private static java.time.LocalDate parseDay (String input) {
        int day = Integer.parseInt(input);
        LocalDate date = LocalDate.now();
        return java.time.LocalDate.of(date.getYear(), date.getMonth(), day);
    };

    private static boolean validate(String type, String input) {
        return typeValidators.get(type).apply(input);
    }

    private static Object parse(String type, String input) {
        return typeParsers.get(type).apply(input);
    }

    private static String sampleUsagesFor(String... type) {
        StringBuilder sb = new StringBuilder();
        for (String t : type) {
            sb.append(typeSampleUsages.get(t));
            sb.append(" or ");
        }
        return sb.substring(0, sb.length() - 4);
    }

    public static Object parseFlag(String textString, String flagName, String type) throws Exception {
        Matcher matcher = Pattern.compile("-" + flagName + "\\s*([\\w-]+)").matcher(textString);
        if (!matcher.find()) {
            return null;
        } else {
            String[] types = type.split("\\|");
            for (String t : types) {
                if (validate(t, matcher.group(1))) {
                    return parse(t, matcher.group(1));
                }
            }
            throw new Exception("Invalid format for flag: " + flagName + ". Expected: " + sampleUsagesFor(types));
        }
    }
    public static HashMap<String, Object> parseFlags(String textString, String[] flagNames, String[] types) throws Exception {
        if (flagNames.length != types.length) {
            throw new Exception("flagNames and types must have the same length");
        }
        HashMap<String,Object> arguments = new HashMap<>();
        for (int i = 0; i < flagNames.length; i++) {
            Object value = parseFlag(textString, flagNames[i], types[i]);
            if (value != null) {
                arguments.put(flagNames[i], value);
            }
        }
        return arguments;
    }

    public static boolean containsAllFlags(String textString, String... flags) {
        for (String flag : flags) {
            Matcher matcher = Pattern.compile("-" + flag + "\\s*([\\w-]+)").matcher(textString);
            if (!matcher.find()) {
                return false;
            }
        }
        return true;
    }

}
