package seedu.address.logic.parser;

import static seedu.address.logic.parser.TypeParsingUtil.parseField;

import seedu.address.logic.MacroPredicate;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterLessonCommand;
import seedu.address.logic.commands.FilterPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Day;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Subjects;
import seedu.address.model.person.Tags;
import seedu.address.model.state.State;
/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    private static final String FILTER_NO_FIELDS_ERROR = "You must specify at least one unique field to filter!";
    private final State state;
    /**
     * Creates a FilterCommandParser with the specified state.
     */
    public FilterCommandParser(State state) {
        this.state = state;
    }
    @Override
    public FilterCommand parse(String userInput) throws ParseException {
        if (state == State.STUDENT) {
            try {
                MacroPredicate<Person> predicate = new MacroPredicate<>();
                Name name = parseField("name", userInput, Name::of);
                if (name != null) {
                    predicate.addPredicate(person -> person.getName().contains(name));
                }
                Subjects subjects = parseField("subject", userInput, Subjects::of);
                if (subjects != null) {
                    predicate.addPredicate(person -> person.getSubjects().containAll(subjects));
                }
                Tags tags = parseField("tag", userInput, Tags::of);
                if (tags != null) {
                    predicate.addPredicate(person -> person.getTags().containAll(tags));
                }
                Remark remark = parseField("remark", userInput, Remark::of);
                if (remark != null) {
                    predicate.addPredicate(person -> person.getRemark().contains(remark));
                }
                if (predicate.isEmpty()) {
                    throw new ParseException(FILTER_NO_FIELDS_ERROR);
                }
                return new FilterPersonCommand(predicate);
            } catch (ParseException e) {
                throw new ParseException("Invalid filter format: " + e.getMessage() + ". "
                        + getFilterPersonUsageInfo());
            }
        } else if (state == State.SCHEDULE) {
            try {
                MacroPredicate<Lesson> predicate = new MacroPredicate<>();
                Name name = parseField("name", userInput, Name::of);
                if (name != null) {
                    predicate.addPredicate(lesson -> lesson.getName().contains(name));
                }
                Subjects subjects = parseField("subject", userInput, Subjects::of);

                if (subjects != null) {
                    predicate.addPredicate(lesson -> subjects.contains(lesson.getSubject()));
                }
                Day before = parseField("before", userInput, Day::of);
                if (before != null) {
                    predicate.addPredicate(lesson -> lesson.getDay().compareTo(before) < 0);
                }
                Day on = parseField("on", userInput, Day::of);
                if (on != null) {
                    predicate.addPredicate(lesson -> lesson.getDay().equals(on));
                }
                Day after = parseField("after", userInput, Day::of);
                if (after != null) {
                    predicate.addPredicate(lesson -> lesson.getDay().compareTo(after) > 0);
                }
                if (predicate.isEmpty()) {
                    throw new ParseException(FILTER_NO_FIELDS_ERROR);
                }
                return new FilterLessonCommand(predicate);
            } catch (ParseException e) {
                throw new ParseException("Invalid lesson format: " + e.getMessage() + ". "
                        + getFilterScheduleUsageInfo());
            }
        } else if (state == State.TASK) {
            throw new ParseException("Filter cannot be used in task list!");
        } else {
            throw new ParseException("Filter cannot be used in this state!");
        }
    }
    public String getFilterPersonUsageInfo() {
        return "\nUsage: filter (any number of unique -[name|subject|tag|remark] [value]). "
                + "\nFor example, filter -name John -subject physics,english";
    }
    public String getFilterScheduleUsageInfo() {
        return "\nUsage: filter -(at least one of unique [-name|subject|before|on|after|remark VALUE]). "
                + "\nFor example, filter -before 2023/10/10 -subject physics"
                + "\nNote you should only use one of -before, -on, -after at a time.";
    }
}
