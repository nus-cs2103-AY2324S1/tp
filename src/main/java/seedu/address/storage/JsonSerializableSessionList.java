package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlySessionList;
import seedu.address.model.SessionListBook;
import seedu.address.model.session.Session;


/**
 * An Immutable SessionList that is serializable to JSON format.
 */
@JsonRootName(value = "sessionlist")
public class JsonSerializableSessionList {
    public static final String MESSAGE_DUPLICATE_SESSION = "Session list contains duplicate session(s).";

    private final List<JsonAdaptedSession> sessions = new ArrayList<>();

    @JsonCreator
    public JsonSerializableSessionList(@JsonProperty("sessions") List<JsonAdaptedSession> sessions) {
        this.sessions.addAll(sessions);
    }

    /**
     * Converts a given {@code ReadOnlySessionList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSessionList}.
     */
    public JsonSerializableSessionList(ReadOnlySessionList source) {
        sessions.addAll(source.getSessionList().stream().map(JsonAdaptedSession::new).collect(Collectors.toList()));
    }

    /**
     * Converts this session list into the model's {@code SessionListBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SessionListBook toModelType() throws IllegalValueException {
        SessionListBook sessionList = new SessionListBook();
        for (JsonAdaptedSession jsonAdaptedSession : sessions) {
            Session session = jsonAdaptedSession.toModelType();
            if (sessionList.hasSession(session)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SESSION);
            }
            sessionList.addSession(session);
        }
        return sessionList;
    }
}
