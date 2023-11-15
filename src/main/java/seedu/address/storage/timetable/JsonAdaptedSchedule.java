package seedu.address.storage.timetable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.Schedule;

/**
 * Jackson-friendly version of {@link Schedule}.
 */
public class JsonAdaptedSchedule {
    private List<JsonAdaptedDatedEvent> datedEvents = new ArrayList<>();
    private List<JsonAdaptedModule> modules = new ArrayList<>();
    private List<JsonAdaptedCca> ccas = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given {@code DatedEvent}.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("datedEvents") List<JsonAdaptedDatedEvent> datedEvents,
                               @JsonProperty("modules") List<JsonAdaptedModule> modules,
                               @JsonProperty("cca") List<JsonAdaptedCca> cca) {
        this.datedEvents = datedEvents;
        this.modules = modules;
        this.ccas = cca;
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        List<DatedEvent> datedEventList = source.getDatedEventsList();
        List<Module> modulesList = source.getModulesList();
        List<Cca> ccaList = source.getCcasList();

        for (DatedEvent datedEvent : datedEventList) {
            datedEvents.add(new JsonAdaptedDatedEvent(datedEvent));
        }

        for (Module module : modulesList) {
            modules.add(new JsonAdaptedModule(module));
        }

        for (Cca cca : ccaList) {
            ccas.add(new JsonAdaptedCca(cca));
        }
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted schedule.
     */
    public List<DatedEvent> toModelTypeDatedEventList() throws IllegalValueException {
        List<DatedEvent> datedEventList = new ArrayList<>();
        for (JsonAdaptedDatedEvent datedEvent : datedEvents) {
            datedEventList.add(datedEvent.toModelType());
        }
        return datedEventList;
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted schedule.
     */
    public List<Module> toModelTypeModuleList() throws IllegalValueException {
        List<Module> modulesList = new ArrayList<>();
        for (JsonAdaptedModule module : modules) {
            modulesList.add(module.toModelType());
        }
        return modulesList;
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted schedule.
     */
    public List<Cca> toModelTypeCcaList() throws IllegalValueException {
        List<Cca> ccaList = new ArrayList<>();
        for (JsonAdaptedCca cca : ccas) {
            ccaList.add(cca.toModelType());
        }
        return ccaList;
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted schedule.
     */
    public Schedule toModelType() throws IllegalValueException {
        List<DatedEvent> datedEventList = toModelTypeDatedEventList();
        List<Module> modulesList = toModelTypeModuleList();
        List<Cca> ccaList = toModelTypeCcaList();

        return new Schedule(modulesList, ccaList, datedEventList);
    }
}
