package seedu.address.model;

import seedu.address.model.person.Name;

/**
 * Represents a ListEntry that is in the left list of the application and could be shown in the right
 */
public abstract class ListEntry<T> {
    protected Name name = Name.DEFAULT_NAME;
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
    public void setNameIfNotDefault(Name name) {
        if (name != null && !name.equals(Name.DEFAULT_NAME)) {
            setName(name);
        }
    }
    public abstract T clone();
}
