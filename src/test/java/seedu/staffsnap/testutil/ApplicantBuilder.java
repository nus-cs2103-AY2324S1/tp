package seedu.staffsnap.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Email;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.applicant.Position;
import seedu.staffsnap.model.applicant.Status;
import seedu.staffsnap.model.interview.Interview;
import seedu.staffsnap.model.util.SampleDataUtil;

/**
 * A utility class to help with building Applicant objects.
 */
public class ApplicantBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_POSITION = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STATUS = "UNDECIDED";

    private Name name;
    private Phone phone;
    private Email email;
    private Position position;
    private List<Interview> interviews;
    private Status status;

    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        position = new Position(DEFAULT_POSITION);
        interviews = new ArrayList<>();
        status = Status.findByName(DEFAULT_STATUS);
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code applicantToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
        email = applicantToCopy.getEmail();
        position = applicantToCopy.getPosition();
        interviews = new ArrayList<>(applicantToCopy.getInterviews());
        status = applicantToCopy.getStatus();
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code interviews} into a {@code List<Interview>} and set it to the {@code Applicant} that we are
     * building.
     */
    public ApplicantBuilder withInterviews(String ... interviews) {
        this.interviews = SampleDataUtil.getInterviewList(interviews);
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
     * Sets the {@code Email} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withStatus(String status) {
        this.status = Status.findByName(status);
        return this;
    }

    public Applicant build() {
        return new Applicant(name, phone, email, position, interviews, status);
    }

}
