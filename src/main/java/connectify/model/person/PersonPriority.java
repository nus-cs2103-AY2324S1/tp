package connectify.model.person;

import connectify.model.Priority;

/**
 * A person's priority.
 */
public class PersonPriority extends Priority {

    public PersonPriority(Integer value) {
        super(value);
    }

    public PersonPriority(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
