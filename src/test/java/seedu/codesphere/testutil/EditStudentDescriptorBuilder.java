package seedu.codesphere.testutil;

import seedu.codesphere.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.codesphere.model.student.Email;
import seedu.codesphere.model.student.Name;
import seedu.codesphere.model.student.PendingQuestion;
import seedu.codesphere.model.student.Remark;
import seedu.codesphere.model.student.Student;
import seedu.codesphere.model.tag.StudentRank;
import seedu.codesphere.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setEmail(student.getEmail());
        descriptor.setRemark(student.getRemark());
        descriptor.setPendingQuestion(student.getPendingQuestion());
        descriptor.setTag(student.getTag());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    /**
     * Sets the {@code Pending Question} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPendingQuestion(String pendingQuestion) {
        descriptor.setPendingQuestion(new PendingQuestion(pendingQuestion));
        return this;
    }


    /**
     * Parses the {@code tag} into a {@code Tag} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withTag(StudentRank studentRank) {
        Tag tag = new Tag(studentRank);
        descriptor.setTag(tag);
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
