package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.HashMap;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static HashMap<String, Person> getSamplePersons() {
        HashMap<String, Person> persons = new HashMap<>();
        persons.put("Alex Yeoh", new Person(new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh@example.com")));
        persons.put("Bernice Yu", new Person(new Name("Bernice Yu"), new Phone("99272758"),
            new Email("berniceyu@example.com")));
        persons.put("Charlotte Oliveiro", new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
            new Email("charlotte@example.com")));
        persons.put("David Li", new Person(new Name("David Li"), new Phone("91031282"),
            new Email("lidavid@example.com")));
        persons.put("Irfan Ibrahim", new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
            new Email("irfan@example.com")));
        persons.put("Roy Balakrishnan", new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
            new Email("royb@example.com")));
        return persons;
    }

    /**
     * Method to create an array of schedules, given the persons.
     * @param persons to create schedule for.
     * @return an array of schedules.
     */
    public static Schedule[] generateSchedules(HashMap<String, Person> persons) {
        Schedule[] schedules = new Schedule[persons.size()];
        int i = 0;
        for (Person person : persons.values()) {
            schedules[i] = new Schedule(person,
                new StartTime(LocalDateTime.of(2023, 1, 1 + i, 8 + (i * 2), 0, 0)),
                new EndTime(LocalDateTime.of(2023, 1, 1 + i, 10 + (i * 2), 0, 0)));
            i++;
        }
        return schedules;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        HashMap<String, Person> samplePersons = getSamplePersons();
        Schedule[] sampleSchedules = generateSchedules(samplePersons);

        for (Person samplePerson : samplePersons.values()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Schedule sampleSchedule : sampleSchedules) {
            sampleAb.addSchedule(sampleSchedule);
        }
        return sampleAb;
    }

}
