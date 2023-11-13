package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.availability.TimeInterval;
import seedu.address.model.course.Course;
import seedu.address.model.course.UniqueCourseList;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AvailableTimePredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagPredicate;
import seedu.address.model.person.predicates.TeachingCoursePredicate;
import seedu.address.model.tag.Tag;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(new NameContainsKeywordsPredicate(List.of("Alice")));

        Course course = UniqueCourseList.findByCourseCode("CS2103T");
        predicates.add(new TeachingCoursePredicate(List.of(course)));

        predicates.add(new TagPredicate(List.of(new Tag("friends"))));

        TimeInterval interval = new TimeInterval(LocalTime.parse("10:00"), LocalTime.parse("12:00"));
        predicates.add(new AvailableTimePredicate(DayOfWeek.MONDAY.getValue(), interval));

        FindCommand expectedFindCommand = new FindCommand(predicates);
        assertParseSuccess(parser, " n/Alice c/cs2103t t/friends d/1 from/10:00 to/12:00", expectedFindCommand);

    }

    @Test
    public void parse_whitespaceArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(new NameContainsKeywordsPredicate(List.of("Alice", "Bob")));

        FindCommand expectedFindCommand = new FindCommand(predicates);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidDay_throwsParseException() {
        assertParseFailure(parser, " d/0 from/10:00 to/12:00", Messages.MESSAGE_INVALID_DAY);
        assertParseFailure(parser, " d/6 from/10:00 to/12:00", Messages.MESSAGE_INVALID_DAY);
    }

    @Test
    public void parse_noPrefix_throwsParseException() {
        assertParseFailure(parser, " Alice", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInterval_throwsNumberFormatException() {
        assertParseFailure(parser, " d/aa from/10:00 to/12:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
