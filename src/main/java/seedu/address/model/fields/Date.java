package seedu.address.model.fields;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a generic date
 */
public abstract class Date implements Comparable<Date> {
    public abstract String toSaveData();

    public abstract Optional<LocalDateTime> getDate();

    public abstract int compareTo(Date o);
}
