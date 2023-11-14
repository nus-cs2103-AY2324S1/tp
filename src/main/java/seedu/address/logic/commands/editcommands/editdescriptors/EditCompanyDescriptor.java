package seedu.address.logic.commands.editcommands.editdescriptors;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.company.CompanyDescription;
import seedu.address.model.company.CompanyEmail;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.CompanyPhone;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the company with. Each non-empty field value will replace the
 * corresponding field value of the company.
 */
public class EditCompanyDescriptor {
    private CompanyName companyName;
    private CompanyPhone companyPhone;
    private CompanyEmail companyEmail;
    private CompanyDescription companyDescription;
    private Set<Tag> tags;

    public EditCompanyDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditCompanyDescriptor(EditCompanyDescriptor toCopy) {
        setCompanyName(toCopy.companyName);
        setCompanyPhone(toCopy.companyPhone);
        setCompanyEmail(toCopy.companyEmail);
        setDescription(toCopy.companyDescription);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(
                companyName, companyPhone, companyEmail, companyDescription, tags);
    }

    public void setCompanyName(CompanyName companyName) {
        this.companyName = companyName;
    }

    public Optional<CompanyName> getCompanyName() {
        return Optional.ofNullable(companyName);
    }

    public void setCompanyPhone(CompanyPhone companyPhone) {
        this.companyPhone = companyPhone;
    }

    public Optional<CompanyPhone> getCompanyPhone() {
        return Optional.ofNullable(companyPhone);
    }

    public void setCompanyEmail(CompanyEmail companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Optional<CompanyEmail> getCompanyEmail() {
        return Optional.ofNullable(companyEmail);
    }

    public void setDescription(CompanyDescription companyDescription) {
        this.companyDescription = companyDescription;
    }

    public Optional<CompanyDescription> getDescription() {
        return Optional.ofNullable(companyDescription);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCompanyDescriptor)) {
            return false;
        }

        EditCompanyDescriptor otherEditCompanyDescriptor = (EditCompanyDescriptor) other;
        return Objects.equals(companyName, otherEditCompanyDescriptor.companyName)
                && Objects.equals(companyPhone, otherEditCompanyDescriptor.companyPhone)
                && Objects.equals(companyEmail, otherEditCompanyDescriptor.companyEmail)
                && Objects.equals(companyDescription, otherEditCompanyDescriptor.companyDescription)
                && Objects.equals(tags, otherEditCompanyDescriptor.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", companyName)
                .add("phone", companyPhone)
                .add("email", companyEmail)
                .add("companyDescription", companyDescription)
                .add("tags", tags)
                .toString();
    }
}
