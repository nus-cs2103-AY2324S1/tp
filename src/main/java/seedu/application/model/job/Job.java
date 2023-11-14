package seedu.application.model.job;

import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.CollectionUtil;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.model.job.interview.Interview;

/**
 * Represents a Job in the application book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Job {

    // Identity fields
    private final Role role;
    private final Company company;
    private final Deadline deadline;
    private final Status status;
    private final JobType jobType;
    private final Industry industry;

    private List<Interview> interviews = new ArrayList<>();

    /**
     * Not all fields are compulsory
     */
    public Job(Role role, Company company, Deadline deadline, Status status, JobType jobType,
               Industry industry) {
        CollectionUtil.requireAllNonNull(role, company);
        this.role = role;
        this.company = company;
        this.deadline = deadline;
        this.status = status;
        this.jobType = jobType;
        this.industry = industry;
    }

    /**
     * Not all fields are compulsory
     */
    public Job(Role role, Company company, Deadline deadline, Status status, JobType jobType,
               Industry industry, List<Interview> interviews) {
        CollectionUtil.requireAllNonNull(role, company);
        this.role = role;
        this.company = company;
        this.deadline = deadline;
        this.status = status;
        this.jobType = jobType;
        this.industry = industry;
        this.interviews = interviews;
    }

    public Role getRole() {
        return role;
    }

    public Company getCompany() {
        return company;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    public JobType getJobType() {
        return jobType;
    }

    public Industry getIndustry() {
        return industry;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }
    public Interview getInterview(Index index) {
        return interviews.get(index.getZeroBased());
    }

    /**
     * Adds an interview to the list of interviews for a job.
     * The interview must not already exist for the job.
     */
    public void addInterview(Interview interview) {
        interviews.add(interview);
    }

    /**
     * Replaces the interview {@code target} in the list with {@code editedInterview}.
     * {@code target} must exist in the list of interviews.
     * The interview identity of {@code editedInterview} must not be the same as another existing interview in the list.
     */
    public void setInterview(Interview target, Interview editedInterview) {
        requireAllNonNull(target, editedInterview);

        int index = interviews.indexOf(target);
        interviews.set(index, editedInterview);
    }

    /**
     * Deletes an interview in the list of interviews for a job.
     * The interview must exist for the job.
     */
    public void deleteInterview(Interview interview) {
        interviews.remove(interview);
    }

    /**
     * Returns number of interviews for a job.
     */
    public int interviewLength() {
        return interviews.size();
    }

    /**
     * Returns true if an interview with the same identity as {@code interview} exists for the job.
     */
    public boolean hasInterview(Interview interview) {
        for (Interview i : interviews) {
            if (interview.equals(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both jobs have the same role and company.
     * This defines a weaker notion of equality between two jobs.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
            && otherJob.getRole().equals(getRole())
            && otherJob.getCompany().equals(getCompany());
    }

    /**
     * Returns true if both jobs have the same role, company and data fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return role.equals(otherJob.role)
            && company.equals(otherJob.company)
            && deadline.equals(otherJob.deadline)
            && status.equals(otherJob.status)
            && jobType.equals(otherJob.jobType)
            && industry.equals(otherJob.industry);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(role, company, deadline, status, jobType, industry);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("role", role)
            .add("company", company)
            .add("deadline", deadline)
            .add("status", status)
            .add("jobType", jobType)
            .add("industry", industry)
            .toString();
    }
}
