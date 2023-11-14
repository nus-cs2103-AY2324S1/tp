
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;
import seedu.address.model.requirement.Requirement;


/**
 * Edits the details of an existing internship in the internship book.
 */
public class ModifyCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "modify";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the internship identified "
            + "by the index number used in the displayed internship list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY_NAME] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_APPLICATION_STATUS + "APPLICATION_STATUS] "
            + "[" + PREFIX_START_DATE + "START_DATE] "
            + "[" + PREFIX_DURATION + "DURATION] "
            + "[" + PREFIX_REQUIREMENT + "REQUIREMENT]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COMPANY_NAME + "Google "
            + PREFIX_ROLE + "Software Engineer Intern "
            + PREFIX_APPLICATION_STATUS + "Yet to apply "
            + PREFIX_START_DATE + "20/01/2023 "
            + PREFIX_DURATION + "3 "
            + PREFIX_DEADLINE + "25/12/2022 "
            + PREFIX_REQUIREMENT + "C++ "
            + PREFIX_REQUIREMENT + "Python";

    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP =
            "This internship entry with the corresponding company name and role already exists in the application.";
    public static final String MESSAGE_DEADLINE_CONSTRAINTS =
            "Deadline should only contain numbers and slashes. It must follow the form DD/MM/YYYY. It must be earlier"
                    + " than the internship entry's start date.";
    private final Index index;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
     * Constructs an {@code ModifyCommand} to edit the details of an existing internship.
     *
     * @param index The index of the internship in the filtered internship list to edit.
     *              Must not be {@code null}.
     * @param editInternshipDescriptor Details to edit the internship with.
     *                                 Cannot be {@code null} and must not contain duplicate tags.
     */
    public ModifyCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        requireNonNull(index);
        requireNonNull(editInternshipDescriptor);
        this.index = index;
        this.editInternshipDescriptor = new EditInternshipDescriptor(editInternshipDescriptor);
    }


    @Override
    public CommandResult execute(InternshipModel model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());

        boolean editedDeadline = editInternshipDescriptor.getDeadline().isPresent();
        boolean editedStartDate = editInternshipDescriptor.getStartDate().isPresent();
        if (editedDeadline || editedStartDate) {
            String startDate = internshipToEdit.getStartDate().toString();
            String deadline = internshipToEdit.getDeadline().toString();
            if (editedDeadline) {
                deadline = editInternshipDescriptor.getDeadline().get().toString();
            }
            if (editedStartDate) {
                startDate = editInternshipDescriptor.getStartDate().get().toString();
            }
            try {
                editInternshipDescriptor.setDeadline(ParserUtil
                        .parseDeadline(deadline, startDate));
            } catch (ParseException e) {
                throw new CommandException(MESSAGE_DEADLINE_CONSTRAINTS);
            }
        }

        Internship editedInternship = createEditedInternship(internshipToEdit, editInternshipDescriptor);

        if (!internshipToEdit.isSameInternship(editedInternship) && model.hasInternship(editedInternship)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.setInternship(internshipToEdit, editedInternship);
        return new CommandResult(String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, Messages.format(editedInternship)));
    }

    private Internship createEditedInternship(Internship internshipToEdit,
                                              EditInternshipDescriptor editInternshipDescriptor) {
        CompanyName updatedCompanyName = editInternshipDescriptor.getCompanyName()
                .orElse(internshipToEdit.getCompanyName());
        Role updatedRole = editInternshipDescriptor.getRole().orElse(internshipToEdit.getRole());
        ApplicationStatus updatedApplicationStatus = editInternshipDescriptor
                .getApplicationStatus().orElse(internshipToEdit.getApplicationStatus());
        Deadline updatedDeadline = editInternshipDescriptor
                .getDeadline().orElse(internshipToEdit.getDeadline());
        StartDate updatedStartDate = editInternshipDescriptor.getStartDate().orElse(internshipToEdit.getStartDate());
        Duration updatedDuration = editInternshipDescriptor.getDuration().orElse(internshipToEdit.getDuration());
        Set<Requirement> updatedRequirements = editInternshipDescriptor.getRequirements()
                .orElse(internshipToEdit.getRequirements());

        return new Internship(updatedCompanyName, updatedRole, updatedApplicationStatus, updatedDeadline,
                updatedStartDate, updatedDuration, updatedRequirements);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModifyCommand)) {
            return false;
        }

        ModifyCommand otherEditInternshipCommand = (ModifyCommand) other;
        return index.equals(otherEditInternshipCommand.index)
                && editInternshipDescriptor.equals(otherEditInternshipCommand.editInternshipDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editInternshipDescriptor", editInternshipDescriptor)
                .toString();
    }

    /**
     * Represents a descriptor with details to edit an internship with.
     * Each non-null field value will replace the corresponding field value of the internship.
     */
    public static class EditInternshipDescriptor {
        private CompanyName companyName;
        private Role role;
        private ApplicationStatus applicationStatus;
        private Deadline deadline;
        private StartDate startDate;
        private Duration duration;
        private Set<Requirement> requirements;

        public EditInternshipDescriptor() {}


        /**
         * Represents a descriptor with details to edit an internship with.
         * Each non-null field value will replace the corresponding field value of the internship.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setCompanyName(toCopy.companyName);
            setRole(toCopy.role);
            setApplicationStatus(toCopy.applicationStatus);
            setDeadline(toCopy.deadline);
            setStartDate(toCopy.startDate);
            setDuration(toCopy.duration);
            setRequirements(toCopy.requirements);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(companyName, role, applicationStatus, startDate,
                    duration, requirements, deadline);
        }

        public Optional<CompanyName> getCompanyName() {
            return Optional.ofNullable(companyName);
        }
        //
        public void setCompanyName(CompanyName companyName) {
            this.companyName = companyName;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<ApplicationStatus> getApplicationStatus() {
            return Optional.ofNullable(applicationStatus);
        }

        public void setApplicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<StartDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setStartDate(StartDate startDate) {
            this.startDate = startDate;

        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<Set<Requirement>> getRequirements() {
            return (requirements != null) ? Optional.of(Collections.unmodifiableSet(requirements)) : Optional.empty();
        }

        public void setRequirements(Set<Requirement> requirements) {
            this.requirements = (requirements != null) ? new HashSet<>(requirements) : null;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditInternshipDescriptor)) {
                return false;
            }

            EditInternshipDescriptor otherEditInternshipDescriptor = (EditInternshipDescriptor) other;
            return Optional.ofNullable(companyName)
                    .equals(Optional.ofNullable(otherEditInternshipDescriptor.companyName))
                    && Optional.ofNullable(role).equals(Optional.ofNullable(otherEditInternshipDescriptor.role))
                    && Optional.ofNullable(applicationStatus)
                    .equals(Optional.ofNullable(otherEditInternshipDescriptor.applicationStatus))
                    && Optional.ofNullable(deadline).equals(Optional.ofNullable(otherEditInternshipDescriptor.deadline))
                    && Optional.ofNullable(startDate)
                    .equals(Optional.ofNullable(otherEditInternshipDescriptor.startDate))
                    && Optional.ofNullable(duration).equals(Optional.ofNullable(otherEditInternshipDescriptor.duration))
                    && Optional.ofNullable(requirements)
                    .equals(Optional.ofNullable(otherEditInternshipDescriptor.requirements));
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("companyName", companyName)
                    .add("role", role)
                    .add("applicationStatus", applicationStatus)
                    .add("deadline", deadline)
                    .add("startDate", startDate)
                    .add("duration", duration)
                    .add("requirements", requirements)
                    .toString();
        }
    }
}
