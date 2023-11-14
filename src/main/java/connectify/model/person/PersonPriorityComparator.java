package connectify.model.person;

import java.util.Comparator;

/**
 * Compares a {@code Person} to another using their {@code Priority}.
 */
public class PersonPriorityComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getPriority().getValue() < o2.getPriority().getValue()) {
            return 1;
        } else if (o1.getPriority().getValue() > o2.getPriority().getValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}
