package seedu.address.model.util;

import static seedu.address.model.task.Task.FORMATTER;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ConsultationListBook;
import seedu.address.model.GradedTestListBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyConsultationList;
import seedu.address.model.ReadOnlyGradedTestList;
import seedu.address.model.ReadOnlySessionList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.SessionListBook;
import seedu.address.model.TaskListBook;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.gradedtest.Finals;
import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.gradedtest.MidTerms;
import seedu.address.model.gradedtest.PracticalExam;
import seedu.address.model.gradedtest.ReadingAssessment1;
import seedu.address.model.gradedtest.ReadingAssessment2;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentSet;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionNumber;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskPriority;



/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final String VALID_GRADED_TEST_1 =
            "RA1:- | RA2:- | MidTerms:3 | Finals:4 | PE:5";
    public static final String VALID_GRADED_TEST_2 =
            "RA1:100 | RA2:100 | MidTerms:100 | Finals:100 | PE:100";
    public static final Person PERSON_ALEX = new Person(new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh@u.nus.edu"), new TelegramHandle("alexYeohh"),
            getTagSet("friends"), getGradedTestSet(VALID_GRADED_TEST_1));
    public static final Person PERSON_BERNICE = new Person(new Name("Bernice Yu"), new Phone("99272758"),
            new Email("berniceyu@u.nus.edu"), new TelegramHandle("berrynice123"),
            getTagSet("colleagues", "friends"), getGradedTestSet(VALID_GRADED_TEST_1));
    public static final Person PERSON_CHARLOTTE = new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
            new Email("charlotte@u.nus.edu"), new TelegramHandle("charl0tt0"),
            getTagSet("neighbours"), getGradedTestSet(VALID_GRADED_TEST_1));
    public static final Person PERSON_DAVID = new Person(new Name("David Li"), new Phone("91031282"),
            new Email("lidavid@u.nus.edu"), new TelegramHandle("goliathMyBestie"),
            getTagSet("family"), getGradedTestSet(VALID_GRADED_TEST_1));
    public static final Person PERSON_IRFAN = new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
            new Email("irfan@u.nus.edu"), new TelegramHandle("nafri00"),
            getTagSet("classmates"), getGradedTestSet(VALID_GRADED_TEST_2));
    public static final Person PERSON_ROY = new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
            new Email("royb@u.nus.edu"), new TelegramHandle("RoytheBoy"),
            getTagSet("colleagues"), getGradedTestSet(VALID_GRADED_TEST_2));


    public static Person[] getSamplePersons() {
        return new Person[] { PERSON_ALEX, PERSON_BERNICE, PERSON_CHARLOTTE, PERSON_DAVID, PERSON_IRFAN, PERSON_ROY };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskName("Do 2103T"), new TaskDescription("Homework assignment"),
                    TaskPriority.HIGH, LocalDate.parse("22/10/2023", FORMATTER)),
            new Task(new TaskName("Do cs2101"), new TaskDescription("Practice script"),
                    TaskPriority.HIGH, LocalDate.parse("22/10/2023", FORMATTER)),
            new Task(new TaskName("Do cs2100"), new TaskDescription("Remember mips"),
                    TaskPriority.HIGH, LocalDate.parse("22/10/2023", FORMATTER))
        };
    }

    public static Consultation[] getSampleConsultations() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return new Consultation[] { new Consultation(
                LocalDate.parse("11/11/2023", dateFormatter),
                LocalTime.parse("11:11", timeFormatter),
                SampleDataUtil.getStudentSet(PERSON_ALEX, PERSON_BERNICE)
        ), new Consultation(
                LocalDate.parse("01/11/2023", dateFormatter),
                LocalTime.parse("10:00", timeFormatter),
                SampleDataUtil.getStudentSet(PERSON_ALEX)
        ), new Consultation(
                LocalDate.parse("11/12/2023", dateFormatter),
                LocalTime.parse("15:00", timeFormatter),
                SampleDataUtil.getStudentSet(PERSON_DAVID, PERSON_IRFAN, PERSON_ROY)
        ),
        };
    }

    public static Session[] getSampleSessions() {
        return new Session[] {
            new Session(new SessionNumber("1"), new StudentSet(getSamplePersons())),
            new Session(new SessionNumber("2"), new StudentSet(getSamplePersons()))
        };
    }

    public static GradedTest[] getSampleGradedTest() {
        return new GradedTest[] {
            new GradedTest(new ReadingAssessment1("1"), new ReadingAssessment2("2"),
                    new MidTerms("3"), new Finals("4"), new PracticalExam("5"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        TaskListBook sampleTl = new TaskListBook();
        for (Task sampleTask : getSampleTasks()) {
            sampleTl.addTask(sampleTask);
        }
        return sampleTl;
    }

    public static ReadOnlyConsultationList getSampleConsultationList() {
        ConsultationListBook sampleClb = new ConsultationListBook();
        for (Consultation sampleConsultation : getSampleConsultations()) {
            sampleClb.addConsultation(sampleConsultation);
        }
        return sampleClb;
    }

    public static ReadOnlySessionList getSampleSessionList() {
        SessionListBook sampleSl = new SessionListBook();
        for (Session sampleSession : getSampleSessions()) {
            sampleSl.addSession(sampleSession);
        }
        return sampleSl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a StudentSet object containing the list of person given.
     */
    public static StudentSet getStudentSet(Person ... students) {
        Set<Person> setOfStudents = Arrays.stream(students)
                .collect(Collectors.toSet());
        return new StudentSet(setOfStudents);
    }

    /**
     * Returns a names set containing the list of string names given.
     */
    public static Set<Name> getNamesSet(String ... names) {
        return Arrays.stream(names)
                .map(Name::new)
                .collect(Collectors.toSet());
    }

    public static ReadOnlyGradedTestList getSampleGradedTestList() {
        GradedTestListBook sampleTl = new GradedTestListBook();
        for (GradedTest sampleGradedTest : getSampleGradedTest()) {
            sampleTl.addGradedTest(sampleGradedTest);
        }
        return sampleTl;
    }

    /**
     * Returns a gradedTest set containing the list of strings given.
     */
    public static Set<GradedTest> getGradedTestSet(String... strings) {
        return Arrays.stream(strings)
                .map(GradedTest::new)
                .collect(Collectors.toSet());
    }

}
