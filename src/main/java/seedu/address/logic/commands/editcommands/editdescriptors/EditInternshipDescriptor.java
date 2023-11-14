package seedu.address.logic.commands.editcommands.editdescriptors;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.company.internship.InternshipDescription;
import seedu.address.model.company.internship.InternshipInterviewDateTime;
import seedu.address.model.company.internship.InternshipName;

/**
 * Stores the details to edit the internship with. Each non-empty field value will replace the
 * corresponding field value of the internship.
 */
public class EditInternshipDescriptor {
    private InternshipName name;
    private InternshipDescription description;
    private InternshipInterviewDateTime interviewDateTime;

    public EditInternshipDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code interviewDateTime} is used internally.
     */
    public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
        setInternshipName(toCopy.name);
        setInternshipDescription(toCopy.description);
        setInternshipDateTime(toCopy.interviewDateTime);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, description, interviewDateTime);
    }

    public void setInternshipName(InternshipName name) {
        this.name = name;
    }

    public Optional<InternshipName> getInternshipName() {
        return Optional.ofNullable(name);
    }

    public void setInternshipDescription(InternshipDescription description) {
        this.description = description;
    }

    public Optional<InternshipDescription> getInternshipDescription() {
        return Optional.ofNullable(description);
    }

    public void setInternshipDateTime(InternshipInterviewDateTime interviewDateTime) {
        this.interviewDateTime = interviewDateTime;
    }

    public Optional<InternshipInterviewDateTime> getInternshipDateTime() {
        return Optional.ofNullable(interviewDateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInternshipDescriptor)) {
            return false;
        }

        EditInternshipDescriptor otherEditInternshipDescriptor = (EditInternshipDescriptor) other;
        return Objects.equals(name, otherEditInternshipDescriptor.name)
                && Objects.equals(description, otherEditInternshipDescriptor.description)
                && Objects.equals(interviewDateTime, otherEditInternshipDescriptor.interviewDateTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("description", description)
                .add("interviewDateTime", interviewDateTime)
                .toString();
    }
}
