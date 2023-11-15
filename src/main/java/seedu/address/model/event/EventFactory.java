package seedu.address.model.event;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * EventFactory generates events from model state.
 */
public class EventFactory {
    /**
     * Creates events from current model state.
     * @param model {@code Model} representing current state of program.
     * @return
     */
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
            if (birthdayThisYear.isAfter(LocalDate.now()) || birthdayThisYear.equals(LocalDate.now())) {
                event = new Event(
                        String.format("%s's Birthday", person.getName()), "",
                        birthdayThisYear.atTime(23, 59)
                );
            } else {
                event = new Event(
                        String.format("%s's Birthday", person.getName()), "",
                        birthdayNextYear.atTime(23, 59)
                );
            }

            event.addReminder(Duration.ofDays(2)); // Birthday reminder within 1 day of the birthday
            event.addMember(person);
            events.add(event);
        }

        return events;
    }
}
