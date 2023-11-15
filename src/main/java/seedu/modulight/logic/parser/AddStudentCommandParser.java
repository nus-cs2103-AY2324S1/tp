package seedu.modulight.logic.parser;

import static seedu.modulight.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.modulight.logic.commands.AddStudentCommand;
import seedu.modulight.logic.parser.exceptions.ParseException;
import seedu.modulight.model.student.Student;
import seedu.modulight.model.student.StudentEmail;
import seedu.modulight.model.student.StudentId;
import seedu.modulight.model.student.StudentName;
import seedu.modulight.model.student.TutorialGroup;
import seedu.modulight.model.studentscore.StudentScore;
import seedu.modulight.model.tag.Tag;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddStudentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException, IllegalArgumentException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_NAME,
                PREFIX_EMAIL, PREFIX_TUTORIAL_GROUP, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_NAME, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT_ID, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TUTORIAL_GROUP);
        Student student = null;
        try {
            StudentId sid = new StudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
            StudentName name = new StudentName(argMultimap.getValue(PREFIX_NAME).get());
            StudentEmail email = new StudentEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            List<StudentScore> scores = new ArrayList<>();
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            TutorialGroup tg = new TutorialGroup("T00");
            if (arePrefixesPresent(argMultimap, PREFIX_TUTORIAL_GROUP)) {
                tg = new TutorialGroup(argMultimap.getValue(PREFIX_TUTORIAL_GROUP).get());
            }
            student = new Student(sid, name, email, tg, scores, tagList);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
        return new AddStudentCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }



}
