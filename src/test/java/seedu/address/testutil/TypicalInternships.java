package seedu.address.testutil;

import static seedu.address.commons.util.DateTimeParserUtil.parseStringToDateTime;

import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipInterviewDateTime;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {
    public static final Internship SOFTWARE_ENGINEER_WITH_DATETIME = new InternshipBuilder()
            .withInternshipName("Software Engineering Intern 2024")
            .withDescription("Produce high quality software following good architecture that are scalable")
            .withInterviewDateTime(parseStringToDateTime("20-12-2023 08:00")).build();

    public static final Internship SOFTWARE_ENGINEER_WITHOUT_DATETIME = new InternshipBuilder()
            .withInternshipName("Software Engineering Intern 2024")
            .withDescription("Develop new features/functionalities on existing software products")
            .withoutInterviewDateTime().build();

    public static final Internship DATA_ANALYST_WITH_DATETIME = new InternshipBuilder()
            .withInternshipName("Data Analyst Intern 2024")
            .withDescription("Learn data analysis tools and techniques")
            .withInterviewDateTime(parseStringToDateTime("15-01-2024 10:30"))
            .build();

    public static final Internship MARKETING_INTERN_WITHOUT_DATETIME = new InternshipBuilder()
            .withInternshipName("Marketing Intern 2024")
            .withDescription("Conduct market research and analysis")
            .withoutInterviewDateTime()
            .build();

    public static final Internship FINANCE_INTERN_WITH_DATETIME = new InternshipBuilder()
        .withInternshipName("Finance Intern 2024")
        .withDescription("Assist with financial models and forecasts")
        .withInterviewDateTime(parseStringToDateTime("25-01-2024 09:00")).build();

    public static final Internship HR_INTERN_WITHOUT_DATETIME = new InternshipBuilder()
        .withInternshipName("HR Intern 2024")
        .withDescription("Support HR tasks and operations")
        .withoutInterviewDateTime().build();

    public static final Internship UX_DESIGNER_WITH_DATETIME = new InternshipBuilder()
        .withInternshipName("UX Designer Intern 2024")
        .withDescription("Design user-centric digital interfaces")
        .withInterviewDateTime(parseStringToDateTime("24-01-2024 14:00")).build();

    public static final Internship NO_DATETIME_A = new InternshipBuilder()
        .withInternshipName("No Datetime A")
        .withDescription("No Datetime A")
        .withoutInterviewDateTime().build();

    public static final Internship NO_DATETIME_B = new InternshipBuilder()
        .withInternshipName("No Datetime B")
        .withDescription("No Datetime B")
        .withoutInterviewDateTime().build();

    public static final Internship NO_DATETIME_C = new InternshipBuilder()
        .withInternshipName("No Datetime C")
        .withDescription("No Datetime C")
        .withoutInterviewDateTime().build();

    public static final Internship NO_DATETIME_D = new InternshipBuilder()
        .withInternshipName("No Datetime D")
        .withDescription("No Datetime D")
        .withoutInterviewDateTime().build();

    public static final Internship NO_DATETIME_E = new InternshipBuilder()
        .withInternshipName("No Datetime E")
        .withDescription("No Datetime E")
        .withoutInterviewDateTime().build();

    public static final Internship DATETIME_A = new InternshipBuilder()
        .withInternshipName("Datetime A")
        .withDescription("Datetime A")
        .withInterviewDateTime(parseStringToDateTime("01-01-2024 14:00")).build();

    public static final Internship DATETIME_B = new InternshipBuilder()
        .withInternshipName("Datetime B")
        .withDescription("Datetime B")
        .withInterviewDateTime(parseStringToDateTime("02-01-2024 14:00")).build();

    public static final Internship DATETIME_C = new InternshipBuilder()
        .withInternshipName("Datetime C")
        .withDescription("Datetime C")
        .withInterviewDateTime(parseStringToDateTime("03-01-2024 14:00")).build();

    public static final Internship DATETIME_D = new InternshipBuilder()
        .withInternshipName("Datetime D")
        .withDescription("Datetime D")
        .withInterviewDateTime(parseStringToDateTime("04-01-2024 14:00")).build();

    public static final Internship DATETIME_E = new InternshipBuilder()
        .withInternshipName("Datetime E")
        .withDescription("Datetime E")
        .withInterviewDateTime(parseStringToDateTime("05-01-2024 14:00")).build();

    public static final Internship DATETIME_F = new InternshipBuilder()
        .withInternshipName("Datetime F")
        .withDescription("Datetime F")
        .withInterviewDateTime(parseStringToDateTime("06-01-2024 14:00")).build();

    public static final Internship DATETIME_G = new InternshipBuilder()
        .withInternshipName("Datetime G")
        .withDescription("Datetime G")
        .withInterviewDateTime(parseStringToDateTime("07-01-2024 14:00")).build();

    public static final Internship DATETIME_H = new InternshipBuilder()
        .withInternshipName("Datetime H")
        .withDescription("Datetime H")
        .withInterviewDateTime(parseStringToDateTime("08-01-2024 14:00")).build();

    public static final InternshipInterviewDateTime DATETIME_PRE_A = new InternshipInterviewDateTime(
        parseStringToDateTime("01-01-2024 13:00"));

    public static final InternshipInterviewDateTime DATETIME_PRE_B = new InternshipInterviewDateTime(
        parseStringToDateTime("02-01-2024 13:00"));

    public static final InternshipInterviewDateTime DATETIME_PRE_C = new InternshipInterviewDateTime(
        parseStringToDateTime("03-01-2024 13:00"));

    public static final InternshipInterviewDateTime DATETIME_PRE_D = new InternshipInterviewDateTime(
        parseStringToDateTime("04-01-2024 13:00"));

    public static final InternshipInterviewDateTime DATETIME_PRE_E = new InternshipInterviewDateTime(
        parseStringToDateTime("05-01-2024 13:00"));

    public static final InternshipInterviewDateTime DATETIME_PRE_F = new InternshipInterviewDateTime(
        parseStringToDateTime("06-01-2024 13:00"));

    public static final InternshipInterviewDateTime DATETIME_PRE_G = new InternshipInterviewDateTime(
        parseStringToDateTime("07-01-2024 13:00"));

    public static final InternshipInterviewDateTime DATETIME_PRE_H = new InternshipInterviewDateTime(
        parseStringToDateTime("08-01-2024 13:00"));
}
