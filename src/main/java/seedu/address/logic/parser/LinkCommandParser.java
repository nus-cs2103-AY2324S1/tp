package seedu.address.logic.parser;

import static seedu.address.logic.parser.TypeParsingUtil.parseField;

import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.state.State;

/**
 * Parses input arguments and creates a new LinkCommand object
 */
public class LinkCommandParser implements Parser<LinkCommand> {
    private final Model model;
    public LinkCommandParser(Model model) {
        this.model = model;
    }
    public LinkCommandParser() {
        this.model = null;
    }
    @Override
    public LinkCommand parse(String arguments) throws ParseException {
        if (model == null) {
            return staticParse(arguments);
        } else {
            return statefulParse(arguments);
        }
    }

    private LinkCommand statefulParse(String arguments) throws ParseException {
        assert model != null;
        State state = model.getState();
        Name studentName;
        Name lessonName;
        switch (state) {
        case STUDENT:
            if (model.getCurrentlyDisplayedPerson() == null) {
                throw new ParseException("No student is shown" + "\n" + getStatefulUsageInfoPerson());
            }
            try {
                studentName = model.getCurrentlyDisplayedPerson().getName();
                lessonName = Name.of(arguments);
                return new LinkCommand(lessonName, studentName);
            } catch (ParseException e) {
                throw new ParseException(e.getMessage() + "\n" + getStatefulUsageInfoPerson());
            }
        case SCHEDULE:
            if (model.getCurrentlyDisplayedLesson() == null) {
                throw new ParseException("No lesson is shown" + "\n" + getStatefulUsageInfoLesson());
            }
            try {
                studentName = Name.of(arguments);
                lessonName = model.getCurrentlyDisplayedLesson().getName();
                return new LinkCommand(lessonName, studentName);
            } catch (ParseException e) {
                throw new ParseException(e.getMessage() + "\n" + getStatefulUsageInfoLesson());
            }
        default:
            throw new ParseException("Link command is not available in this state"
                    + state.toString() + "\n" + getStatefulUsageInfoPerson() + "\n" + getStatefulUsageInfoLesson());
        }
    }
    private LinkCommand staticParse(String args) throws ParseException {
        try {
            Name studentName = parseField("student", args, Name::of, false);
            Name lessonName = parseField("lesson", args, Name::of, false);
            return new LinkCommand(lessonName, studentName);
        } catch (ParseException e) {
            throw new ParseException(e.getMessage() + "\n" + getStaticUsageInfo());
        }

    }
    public String getStaticUsageInfo() {
        return "Link command usage: link "
                + "-student STUDENT_NAME"
                + "-lesson LESSON_NAME"
                + "\nExample: " + LinkCommand.COMMAND_WORD + " "
                + "-student Alice Pauline -lesson CS2103T lab1";
    }
    //todo make it cleat that commands are case insensitive
    public String getStatefulUsageInfoLesson() {
        return "LinkTo command usage: linkTo "
                + "STUDENT_NAME"
                + "\nExample: linkTo "
                + "Alice Pauline"
                + "\nNote: This command is only available when a lesson is shown";
    }
    public String getStatefulUsageInfoPerson() {
        return "LinkTo command usage: linkTo "
                + "LESSON_NAME"
                + "\nExample: linkTo "
                + "CS2103T lab1"
                + "\nNote: This command is only available when a student is shown";
    }
}
