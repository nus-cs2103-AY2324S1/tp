package seedu.address.model.calendar;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Optional;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;


public class Calendar {
    private NavigableMap<EventPeriod, Event> eventTree;

    public Calendar() {
        this.eventTree = new TreeMap<EventPeriod, Event>(EventPeriod::compareTo);
    }

    public boolean isEventAddValid(Event event) {
        requireNonNull(event);

        EventPeriod eventPeriod = event.getEventPeriod();

        Event precedingEvent = Optional.<Map.Entry<EventPeriod, Event>>ofNullable(this.eventTree.floorEntry(
                eventPeriod)).map(Map.Entry::getValue).orElse(Event.createNonConflictingEvent());
        Event proceedingEvent = Optional.<Map.Entry<EventPeriod, Event>>ofNullable(this.eventTree.ceilingEntry(
                eventPeriod)).map(Map.Entry::getValue).orElse(Event.createNonConflictingEvent());
        return !(precedingEvent.isConflicting(event) || proceedingEvent.isConflicting(event));
    }

    public void addEvent(Event event) {
        requireNonNull(event);
        assert(isEventAddValid(event));

        this.eventTree.put(event.getEventPeriod(), event);
    }

    private boolean isIndexValid(int index) {
        return index <= this.eventTree.values().toArray().length;
    }

    private Event getEventAtIndex(int index) {
        assert(isIndexValid(index));
        return this.eventTree.values().toArray(new Event[0])[index - 1];
    }

    public void editEventPeriod(int index, EventPeriod eventPeriod) {
        requireNonNull(eventPeriod);

        getEventAtIndex(index).changeEventPeriod(eventPeriod);
    }

    public void removeEvent(int index) {
        assert(!this.eventTree.isEmpty());

        Event toBeRemoved = getEventAtIndex(index);
        assert(this.eventTree.containsValue(toBeRemoved));

        this.eventTree.remove(getEventAtIndex(index));
    }
}
