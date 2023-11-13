package seedu.address.model.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * Manages the SingleDayEventList objects for every day.
 */
public class AllDaysEventListManager {
    private final NavigableMap<String, SingleDayEventList> dayToEventListMap;

    /**
     * Creates a AllDaysEventListManager to manage the daily events.
     */
    public AllDaysEventListManager() {
        this.dayToEventListMap = new TreeMap<String, SingleDayEventList>();
    }

    /**
     * Check if the event is conflicting with any events stored in the manager.
     *
     * @param event event to be added.
     * @return true if the event can be added, false otherwise.
     */
    public boolean canAddEvent(Event event) {
        List<LocalDate> eventDays = event.getEventDays();
        return eventDays.stream().filter(x -> dayToEventListMap.containsKey(x.toString()))
                .allMatch(x -> dayToEventListMap.get(x.toString()).isEventAddValid(event));
    }

    /**
     * Stores the event to the appropriate SingleDayEventList objects corresponding to the event's dates.
     *
     * @param event event to be added.
     */
    public void addEvent(Event event) {
        List<LocalDate> eventDays = event.getEventDays();
        for (LocalDate date : eventDays) {
            if (!(dayToEventListMap.containsKey(date.toString()))) {
                dayToEventListMap.put(date.toString(), new SingleDayEventList(date));
            }
            dayToEventListMap.get(date.toString()).addEvent(event.boundEventByDate(date));
        }
    }

    /**
     * Forcibly add the event to the appropriate SingleDayEventList objects corresponding to the event's dates.
     *
     * @param event event to be added.
     */
    public void forceAddEvent(Event event) {
        List<LocalDate> eventDays = event.getEventDays();
        for (LocalDate date : eventDays) {
            if (!(dayToEventListMap.containsKey(date.toString()))) {
                dayToEventListMap.put(date.toString(), new SingleDayEventList(date));
            }
            dayToEventListMap.get(date.toString()).forceAddEvent(event.boundEventByDate(date));
        }
    }

    /**
     * Clears the manager.
     */
    public void clear() {
        this.dayToEventListMap.clear();
    }

    /**
     * Check if the event is stored in this manager.
     *
     * @param event event to be checked.
     * @return true if it is stored, false otherwise.
     */
    public boolean contains(Event event) {
        for (LocalDate date : event.getEventDays()) {
            if (!(this.dayToEventListMap.getOrDefault(date.toString(), new SingleDayEventList(date))
                    .containsEvent(event))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if this manager has an equivalent SingleDayEventList object stored in its dayToEventListMap.
     *
     * @param day SingleDayEventList object to be checked.
     * @return true if there is a equivalent SingleDayEventList object stored in this manager, false otherwise.
     */
    private boolean contains(SingleDayEventList day) {
        for (SingleDayEventList thisDay : this.dayToEventListMap.values()) {
            if (thisDay.equals(day)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is an event at the specified time.
     *
     * @param dateTime the specified time.
     * @return an optional containing the event at the specified time if there is an event, an empty optional otherwise.
     */
    public Optional<Event> eventAt(LocalDateTime dateTime) {
        String key = dateTime.toLocalDate().toString();
        if (dayToEventListMap.containsKey((key))) {
            return dayToEventListMap.get(key).eventAtTime(dateTime);
        }
        return Optional.empty();
    }

    /**
     * Looks for an event at specified time and deletes it if found.
     *
     * @param dateTime the specified time.
     * @throws EventNotFoundException if no event is found.
     */
    public void deleteEventAt(LocalDateTime dateTime) throws EventNotFoundException {
        Optional<Event> optionalToDelete = eventAt(dateTime);
        try {
            Event toDelete = optionalToDelete.orElseThrow();
            List<LocalDate> days = toDelete.getEventDays();
            days.stream().map(LocalDate::toString)
                    .forEach(x -> dayToEventListMap.get(x).remove(toDelete));
        } catch (NoSuchElementException e) {
            throw new EventNotFoundException();
        }
    }

    /**
     * Looks for events within a specified time and returns the list of events.
     *
     * @param range the {@code EventPeriod} describing the time range to check.
     * @return A list of events or an empty list if no events are within the range.
     */
    public List<Event> eventsInRange(EventPeriod range) {
        List<LocalDate> days = range.getDates();
        return days.stream().map(LocalDate::toString)
                .flatMap(x -> dayToEventListMap.containsKey(x)
                        ? dayToEventListMap.get(x).eventsInRange(range).stream()
                        : Stream.<Event>empty())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Checks if the manager is empty.
     *
     * @return true if the manager has no events stored, false otherwise.
     */
    public boolean isEmpty() {
        return this.dayToEventListMap.isEmpty();
    }

    /**
     * Returns the event list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Event> asUnmodifiableObservableList() {
        List<Event> list = dayToEventListMap.values().stream()
                .flatMap(singleDayEventList -> singleDayEventList.getDayEventList().stream())
                .map(Event::getParentEvent).distinct()
                .collect(Collectors.toList());

        return FXCollections.observableList(list);
    }

    /**
     * Returns the event list storing events that occur between the start and end dates as an unmodifiable
     * ObservableList.
     *
     * @param startDate start date.
     * @param endDate end date.
     * @return the event list storing events that occur between the start and end dates as an unmodifiable
     *     ObservableList.
     */
    public ObservableList<Event> asUnmodifiableObservableList(LocalDate startDate, LocalDate endDate) {
        List<Event> list = dayToEventListMap.values().stream()
                .flatMap(singleDayEventList -> singleDayEventList.getDayEventList().stream())
                .filter(event -> event.occursBetweenDates(startDate, endDate))
                .collect(Collectors.toList());

        return FXCollections.observableList(list);
    }

    /**
     * Checks if there are any events at all in the manager.
     *
     * @return true if there are any events in the manager, false otherwise.
     */
    public boolean hasEvents() {
        if (!this.isEmpty()) {
            return dayToEventListMap.values().stream().map(SingleDayEventList::isEmpty).anyMatch(x -> x.equals(false));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AllDaysEventListManager)) {
            return false;
        }

        AllDaysEventListManager otherManager = (AllDaysEventListManager) other;

        if (!(this.dayToEventListMap.size() == otherManager.dayToEventListMap.size())) {
            return false;
        }

        for (SingleDayEventList day : this.dayToEventListMap.values()) {
            if (!otherManager.contains(day)) {
                return false;
            }
        }
        return true;
    }
}
