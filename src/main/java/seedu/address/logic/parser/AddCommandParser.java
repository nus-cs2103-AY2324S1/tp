package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GPA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREVIOUS_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attachment.Attachment;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gpa;
import seedu.address.model.person.InterviewScore;
import seedu.address.model.person.IsBookmarked;
import seedu.address.model.person.IsHidden;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PreviousGrade;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_STUDENT_NUMBER, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_GPA, PREFIX_PREVIOUS_GRADE, PREFIX_INTERVIEW_SCORE,
                PREFIX_COMMENT, PREFIX_TAG);

        if (!arePrefixesPresent(
                argMultimap,
                PREFIX_STUDENT_NUMBER,
                PREFIX_NAME,
                PREFIX_GPA,
                PREFIX_PREVIOUS_GRADE,
                PREFIX_PHONE,
                PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_STUDENT_NUMBER, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_GPA, PREFIX_COMMENT, PREFIX_PREVIOUS_GRADE,
                PREFIX_INTERVIEW_SCORE);

        StudentNumber studentNo = ParserUtil.parseStudentNumber(argMultimap.getValue(PREFIX_STUDENT_NUMBER).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Gpa gpa = ParserUtil.parseGpa(argMultimap.getValue(PREFIX_GPA).get());
        PreviousGrade previousGrade = ParserUtil.parsePreviousGrade(argMultimap.getValue(PREFIX_PREVIOUS_GRADE).get());
        Optional<String> maybeInterviewScore = argMultimap.getValue(PREFIX_INTERVIEW_SCORE);
        Optional<InterviewScore> interviewScore = maybeInterviewScore.isPresent()
                ? Optional.of(ParserUtil.parseInterviewScore(maybeInterviewScore.get()))
                : Optional.empty();
        Optional<String> maybeComment = argMultimap.getValue(PREFIX_COMMENT);
        Optional<Comment> comment = maybeComment.isPresent()
                ? Optional.of(ParserUtil.parseComment(maybeComment.get()))
                : Optional.empty();
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        IsHidden isHidden = new IsHidden(false);
        List<Attachment> attachments = List.of();
        IsBookmarked isBookmarked = new IsBookmarked(false);

        Person person = new Person(
                studentNo,
                name,
                phone,
                email,
                gpa,
                previousGrade,
                interviewScore,
                comment,
                tagList,
                attachments,
                isHidden,
                isBookmarked);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
