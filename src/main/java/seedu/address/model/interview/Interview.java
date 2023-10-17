package seedu.address.model.interview;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.applicant.Applicant;

/**
 * Represents an Interview in the address book.
 */
public class Interview {
    /**
     * Static counter of how many Interview objects have been globally created.
     * Used as the unique ID (until int limit 2,147,483,647) for Interview objects.
     * Limit is presumed to not feasibly be reached ever since the application is constrained to
     * 'no remote server, single user'
     */
    private static int globalInterviewId = 0;

    private int interviewId;
    /** TODO Change from 'String' to 'Applicant' once Applicant is on master*/
    private Applicant applicant;
    private String jobRole;
    /** TODO Change from 'String' to proper 'Date/Time' once natural DT is implemented*/
    private String interviewTiming;
    private boolean isDone = false;

    /**
     * Default constructor for Interview object.
     * Increments the `globalInterviewId` and uses it as the unique `interviewId` for this instance
     */
    public Interview(Applicant app, String role, String timing) {
        requireAllNonNull(app, timing);
        incrementGlobalInterviewId();
        interviewId = getGlobalInterviewId();
        applicant = app;
        jobRole = role;
        interviewTiming = timing;
        app.setInterview();
    }

    /**
     * Returns true if both Interviews have the same Applicant & Timing or if both Interviews are the same object
     * Adapted from AB3's Person.isSamePerson() method
     */
    public boolean isValidInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }

        return otherInterview != null
                && otherInterview.getInterviewTiming() == getInterviewTiming()
                && otherInterview.getInterviewApplicant().equals(getInterviewApplicant());
    }

    public int getInterviewId() {
        return interviewId;
    }

    /* TODO Update return type from String to Applicant */
    public Applicant getInterviewApplicant() {
        return applicant;
    }

    public String getJobRole() {
        return jobRole;
    }


    public String getInterviewTiming() {
        return interviewTiming;
    }

    public boolean isDone() {
        return isDone;
    }

    public static int getGlobalInterviewId() {
        return globalInterviewId;
    }

    /**
     * Increments the interviewId counter by 1.
     */
    public static void incrementGlobalInterviewId() {
        globalInterviewId += 1;
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }
}
