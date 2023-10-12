package seedu.address.model.event;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventFactory {
    public static List<Event> createEvents(Model model) {
        ArrayList<Event> events = new ArrayList<>();

        events.addAll(createBirthdayEvents(model));

        // Reset model filter
        model.updateFilteredPersonList(p -> true);

        return events;
    }

    private static List<Event> createBirthdayEvents(Model model) {
        ArrayList<Event> events = new ArrayList<>();

        model.updateFilteredPersonList(p -> p.getBirthday().isPresent());
        for (Person person: model.getFilteredPersonList()) {
            Event event;
            LocalDate birthdayThisYear =
                    person.getBirthday().orElseThrow().birthday.atYear(LocalDate.now().getYear());
            LocalDate birthdayNextYear =
                    person.getBirthday().orElseThrow().birthday.atYear(
                            LocalDate.now().plusYears(1).getYear()
                    );

            // Birthday has not passed this year
            if (birthdayThisYear.isAfter(LocalDate.now())) {
                event = new Event(
                        String.format("%s's Birthday", person.getName()), "",
                        birthdayThisYear.atTime(0, 0)
                );
            } else {
                event = new Event(
                        String.format("%s's Birthday", person.getName()), "",
                        birthdayNextYear.atTime(0, 0)
                );
            }

            event.addReminder(Duration.ofDays(1)); // Birthday reminder defaults to 1 day in advance
            event.addMember(person);
            events.add(event);
        }

        return events;
    }
}
