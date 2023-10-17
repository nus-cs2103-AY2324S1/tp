package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.MonthDay;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.ModelManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

public class EventFactoryTest {

    @Test
    void createEvents_hasBirthdays_success() {
        ModelManager modelManager = new ModelManager();
        modelManager.addPerson(new Person(
                new Name("Some Name"),
                new Phone("1235"),
                new Email("email@email.com"),
                new Address("Some address"),
                Optional.of(new Birthday(MonthDay.of(1, 20))),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                new HashSet<>(),
                Optional.empty()
        ));

        assertTrue(EventFactory.createEvents(modelManager).size() > 0);
    }

}
