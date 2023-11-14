package seedu.application.model.util;

import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.job.Company;
import seedu.application.model.job.Deadline;
import seedu.application.model.job.Industry;
import seedu.application.model.job.Job;
import seedu.application.model.job.JobType;
import seedu.application.model.job.Role;
import seedu.application.model.job.Status;

/**
 * Contains utility methods for populating {@code ApplicationBook} with sample data.
 */
public class SampleDataUtil {

    public static Job[] getSampleJobs() {
        return new Job[]{
            new Job(new Role("Recruitment Coordinator"), new Company("Shopee"),
                new Deadline("Jan 31 2024 2359"), new Status("PENDING"),
                new JobType("Contract"), new Industry("TECHNOLOGY, INFORMATION AND INTERNET")),
            new Job(new Role("Junior IT Analyst"), new Company("Flex"),
                new Deadline("Dec 31 2023 2359"), new Status("REJECTED"),
                new JobType("Full_Time"), new Industry("ELECTRONICS MANUFACTURING")),
            new Job(new Role("Junior Business Analyst"), new Company("eToro"),
                new Deadline("Dec 31 2023 2359"), new Status("PENDING"),
                new JobType("Full_Time"), Industry.EMPTY_INDUSTRY),
            new Job(new Role("Apprenticeship Program"), new Company("JPMorgan"),
                new Deadline("Feb 25 2024 2359"), new Status("APPROVED"),
                new JobType("Full_Time"), new Industry("FINANCIAL SERVICES")),
            new Job(new Role("Global Trainee Program"), new Company("Lalamove"),
                new Deadline("Dec 15 2023 2359"), new Status("PENDING"),
                new JobType("Internship"), new Industry("SOFTWARE DEVELOPMENT")),
            new Job(new Role("Project Admin"), new Company("Deloitte"),
                new Deadline("Jan 31 2024 2359"), new Status("PENDING"),
                new JobType("Internship"), new Industry("BUSINESS CONSULTING AND SERVICES")),
            new Job(new Role("Model Labelling Project Specialist"), new Company("TikTok"),
                new Deadline("Dec 15 2023 2359"), Status.EMPTY_STATUS,
                new JobType("Full_Time"), new Industry("ENTERTAINMENT PROVIDERS"))
        };
    }

    public static ReadOnlyApplicationBook getSampleApplicationBook() {
        ApplicationBook sampleAb = new ApplicationBook();
        for (Job sampleJob : getSampleJobs()) {
            sampleAb.addJob(sampleJob);
        }
        return sampleAb;
    }
}
