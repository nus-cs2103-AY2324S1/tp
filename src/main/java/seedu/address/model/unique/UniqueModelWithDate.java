package seedu.address.model.unique;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Is unique and has a date field
 * @param <T> a type that extends Unique and has a date field
 */
public interface UniqueModelWithDate<T> extends Unique<T> {
    void hashDateToNameWith(HashMap<LocalDate, LinkedList<String>> map);
}
