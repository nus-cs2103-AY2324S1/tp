package seedu.staffsnap.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Department;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.applicant.Position;
import seedu.staffsnap.model.interview.Interview;
import seedu.staffsnap.model.util.SampleDataUtil;

/**
 * A utility class to help with building Applicant objects.
 */
public class ApplicantBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_DEPARTMENT = "amy@gmail.com";
    public static final String DEFAULT_POSITION = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Department department;
    private Position position;
    private Set<Interview> interviews;

    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        department = new Department(DEFAULT_DEPARTMENT);
        position = new Position(DEFAULT_POSITION);
        interviews = new HashSet<>();
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code applicantToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
        department = applicantToCopy.getDepartment();
        position = applicantToCopy.getPosition();
        interviews = new HashSet<>(applicantToCopy.getInterviews());
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code interviews} into a {@code Set<Interview>} and set it to the {@code Applicant} that we are
     * building.
     */
    public ApplicantBuilder withInterviews(String ... interviews) {
        this.interviews = SampleDataUtil.getInterviewSet(interviews);
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Department} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withDepartment(String department) {
        this.department = new Department(department);
        return this;
    }

    public Applicant build() {
        return new Applicant(name, phone, department, position, interviews);
    }

}
