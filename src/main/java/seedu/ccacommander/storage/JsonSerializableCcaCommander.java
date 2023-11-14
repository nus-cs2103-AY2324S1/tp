package seedu.ccacommander.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.ccacommander.commons.exceptions.IllegalValueException;
import seedu.ccacommander.model.CcaCommander;
import seedu.ccacommander.model.ReadOnlyCcaCommander;
import seedu.ccacommander.model.enrolment.Enrolment;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;

/**
 * An Immutable CcaCommander that is serializable to JSON format.
 */
@JsonRootName(value = "ccacommander")
class JsonSerializableCcaCommander {

    public static final String MESSAGE_DUPLICATE_MEMBER = "Members list contains duplicate member(s).";
    public static final String MESSAGE_DUPLICATE_EVENT = "Events list contains duplicate event(s).";
    public static final String MESSAGE_DUPLICATE_ENROLMENT = "Enrolment list contains duplicate enrolment(s).";

    private final List<JsonAdaptedMember> members = new ArrayList<>();
    private final List<JsonAdaptedEvent> events = new ArrayList<>();
    private final List<JsonAdaptedEnrolment> enrolments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCcaCommander} with the given members and events.
     */
    @JsonCreator
    public JsonSerializableCcaCommander(@JsonProperty("members") List<JsonAdaptedMember> members,
                                        @JsonProperty("events") List<JsonAdaptedEvent> events,
                                        @JsonProperty("enrolments") List<JsonAdaptedEnrolment> enrolments) {
        this.members.addAll(members);
        this.events.addAll(events);
        this.enrolments.addAll(enrolments);
    }

    /**
     * Converts a given {@code ReadOnlyCcaCommander} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCcaCommander}.
     */
    public JsonSerializableCcaCommander(ReadOnlyCcaCommander source) {
        members.addAll(source.getMemberList().stream().map(JsonAdaptedMember::new).collect(Collectors.toList()));
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
        enrolments.addAll(source.getEnrolmentList().stream().map(JsonAdaptedEnrolment::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this CcaCommander into the model's {@code CcaCommander} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CcaCommander toModelType() throws IllegalValueException {
        CcaCommander ccaCommander = new CcaCommander();
        for (JsonAdaptedMember jsonAdaptedMember : members) {
            Member member = jsonAdaptedMember.toModelType();
            if (ccaCommander.hasMember(member)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEMBER);
            }
            ccaCommander.createMember(member);
        }
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (ccaCommander.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            ccaCommander.createEvent(event);
        }
        for (JsonAdaptedEnrolment jsonAdaptedEnrolment : enrolments) {
            Enrolment enrolment = jsonAdaptedEnrolment.toModelType();
            if (ccaCommander.hasEnrolment(enrolment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ENROLMENT);
            }
            ccaCommander.createEnrolment(enrolment);
        }
        return ccaCommander;
    }

}
