package seedu.application.model.job.interview;

import java.util.Objects;

import seedu.application.commons.util.CollectionUtil;
import seedu.application.commons.util.ToStringBuilder;

/**
 * Represents an Interview in the application book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {

    public static final Interview DEFAULT_INTERVIEW = new Interview(InterviewType.DEFAULT_INTERVIEW_TYPE,
            InterviewDateTime.DEFAULT_INTERVIEW_DATE_TIME, InterviewAddress.DEFAULT_INTERVIEW_ADDRESS);
    public final InterviewType interviewType;
    public final InterviewDateTime interviewDateTime;
    public final InterviewAddress interviewAddress;

    /**
     * Constructs a {@code Interview}.
     *
     * @param interviewType A valid interview type.
     * @param interviewDateTime A valid interview date and time.
     * @param interviewAddress A valid interview address.
     */
    public Interview(InterviewType interviewType, InterviewDateTime interviewDateTime,
                     InterviewAddress interviewAddress) {
        CollectionUtil.requireAllNonNull(interviewType, interviewDateTime, interviewAddress);
        this.interviewType = interviewType;
        this.interviewDateTime = interviewDateTime;
        this.interviewAddress = interviewAddress;
    }

    public InterviewType getInterviewType() {
        return interviewType;
    }

    public InterviewDateTime getInterviewDateTime() {
        return interviewDateTime;
    }

    public InterviewAddress getInterviewAddress() {
        return interviewAddress;
    }

    /**
     * Returns true if both interview have the same interview type, date time and address.
     * This defines a weaker notion of equality between two interview.
     */
    public boolean isSameInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }

        return otherInterview != null
                && otherInterview.getInterviewType().equals(getInterviewType())
                && otherInterview.getInterviewDateTime().equals(getInterviewDateTime())
                && otherInterview.getInterviewAddress().equals(getInterviewAddress());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interview)) {
            return false;
        }

        Interview otherInterview = (Interview) other;
        return interviewType.equals(otherInterview.interviewType)
            && interviewDateTime.equals(otherInterview.interviewDateTime)
            && interviewAddress.equals(otherInterview.interviewAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewType, interviewDateTime, interviewAddress);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("interviewType", interviewType)
            .add("interviewDateTime", interviewDateTime)
            .add("interviewAddress", interviewAddress)
            .toString();
    }
}

