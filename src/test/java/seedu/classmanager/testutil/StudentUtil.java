package seedu.classmanager.testutil;

import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_CLASS_NUMBER;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.classmanager.logic.commands.AddCommand;
import seedu.classmanager.logic.commands.DeleteCommand;
import seedu.classmanager.logic.commands.EditCommand;
import seedu.classmanager.model.student.Student;
import seedu.classmanager.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns a delete command string for adding the {@code student}.
     */
    public static String getDeleteCommand(Student student) {
        return DeleteCommand.COMMAND_WORD + " " + getStudentNumber(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_STUDENT_NUMBER + student.getStudentNumber().value + " ");
        sb.append(PREFIX_CLASS_NUMBER + student.getClassDetails().classNumber + " ");
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentNumber(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_STUDENT_NUMBER + student.getStudentNumber().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditCommand.EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getStudentNumber().ifPresent(studentNumber -> sb.append(PREFIX_STUDENT_NUMBER)
                        .append(studentNumber.value).append(" "));
        descriptor.getClassNumber().ifPresent(classNumber -> sb.append(PREFIX_CLASS_NUMBER)
                .append(classNumber).append(" "));
        return sb.toString();
    }
    /**
     * Returns the tag command string for the given {@code student}'s details.
     */
    public static String getTagDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        Set<Tag> tags = student.getTags();
        if (tags.isEmpty()) {
            sb.append(PREFIX_TAG);
        } else {
            tags.forEach(s -> sb.append(PREFIX_TAG).append(" ").append(s.tagName).append(" "));
        }

        return sb.toString();
    }
}
