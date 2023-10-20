package seedu.address.logic.parser;

import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Subject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class AddLessonCommandParser implements Parser<AddLessonCommand> {

    // let be consistent with this, a model's optional field could be null, but during the rendering we check for that
    @Override
    public AddLessonCommand parse(String args) throws ParseException {
        LocalTime startTime = TypeParsingUtil.parseTime("start", args);
        LocalTime endTime = TypeParsingUtil.parseTime("end", args);
        if(startTime.isAfter(endTime)) {
            throw new ParseException("Start time must be before end time");
        }
        Subject subject = TypeParsingUtil.parseSubject("subject", args, true);
        String studentName = TypeParsingUtil.parseStr("name", args);
        LocalDate date = TypeParsingUtil.parseDate("date", args, true);
        if(date == null) {
            date = LocalDate.now();
        }
        LocalDateTime start = LocalDateTime.of(date, startTime);
        LocalDateTime end = LocalDateTime.of(date, endTime);
        if(subject == null) {
            return new AddLessonCommand(new Lesson(start, end, studentName));
        }
        return new AddLessonCommand(new Lesson(start, end, subject, studentName));
    }
}
