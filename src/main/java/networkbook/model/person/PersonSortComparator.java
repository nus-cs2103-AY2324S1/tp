package networkbook.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Optional;

import networkbook.commons.util.ToStringBuilder;
/**
 * Provides an ordering for {@code Person} instances based on the values of a specific field.
 */
public class PersonSortComparator implements Comparator<Person> {

    /** Comparator that does nothing. */
    public static final Comparator<Person> EMPTY_COMPARATOR = (Person o1, Person o2) -> 0;

    /** Comparator that puts empty optionals at the end of the list. */
    public static final Comparator<Optional<?>> OPTIONAL_COMPARATOR = (Optional<?> o1, Optional<?> o2) -> {
        if (o1.isPresent() && o2.isEmpty()) {
            return -1;
        } else if (o1.isEmpty() && o2.isPresent()) {
            return 1;
        } else {
            return 0;
        } 
    };


    private final Comparator<Person> comparator;

    public PersonSortComparator(SortField field, SortOrder order) {
        requireNonNull(field);
        requireNonNull(order);
        this.comparator = generateComparator(field, order);
    }

    /**
     * Generates comparator based on specified field and order.
     * Person instances without the specified field are always placed at the end of the sort.
     * @param field Field to sort by.
     * @param order Order to sort by.
     * @return Comparator.
     */
    public static Comparator<Person> generateComparator(SortField field, SortOrder order) {
        requireNonNull(field);
        requireNonNull(order);
        assert isValidSortField(field);
        assert isValidSortOrder(order);

        boolean isAsc = order == SortOrder.ASCENDING;
        switch (field) {
        case NAME:
            return generateNameComparator(isAsc);
        case GRAD:
            return generateGradComparator(isAsc);
        case PRIORITY:
            return generatePriorityComparator(isAsc);
        case NONE: // Fallthrough
        default:
            return EMPTY_COMPARATOR;
        }
    }

    /**
     * Generates comparator based on name and specified order.
     * @param isAsc True if order is ascending, false otherwise.
     * @return Comparator.
     */
    public static Comparator<Person> generateNameComparator(boolean isAsc) {
        return (Person o1, Person o2) -> isAsc ?
                o1.getName().compareTo(o2.getName()) : o2.getName().compareTo(o1.getName());
    }

    
    /**
     * Generates comparator based on graduation date and specified order.
     * @param isAsc True if order is ascending, false otherwise.
     * @return Comparator.
     */
    public static Comparator<Person> generateGradComparator(boolean isAsc) {
        return (Person o1, Person o2) -> {
            Optional<GraduatingYear> g1 = o1.getGraduatingYear();
            Optional<GraduatingYear> g2 = o2.getGraduatingYear();
            if (g1.isPresent() && g2.isPresent()) {
                return 0;
                // TODO implement once graduation feature is merged
                // return isAsc ? g1.get().compareTo(g2.get()) : g2.get().compareTo(g1.get());
            } else {
                return OPTIONAL_COMPARATOR.compare(g1, g2);
            }
        };
    }

    /**
     * Generates comparator based on priority and specified order.
     * @param isAsc True if order is ascending, false otherwise.
     * @return Comparator.
     */
    public static Comparator<Person> generatePriorityComparator(boolean isAsc) {
        return (Person o1, Person o2) -> {
            Optional<Priority> p1 = o1.getPriority();
            Optional<Priority> p2 = o2.getPriority();
            if (p1.isPresent() && p2.isPresent()) {
                return isAsc ? p1.get().compareTo(p2.get()) : p2.get().compareTo(p1.get());
            } else {
                return OPTIONAL_COMPARATOR.compare(p1, p2);
            }
        };
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonSortComparator)) {
            return false;
        }

        PersonSortComparator otherPersonSortComparator = (PersonSortComparator) other;
        return comparator.equals(otherPersonSortComparator.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("comparator", comparator).toString();
    }

    @Override
    public int compare(Person o1, Person o2) {
        return comparator.compare(o1, o2);
    }

    /**
     * Parses user input {@code field} into a {@code SortField}
     *
     * @param field provided in user command
     * @return corresponding SortField
     */
    public static SortField parseSortField(String field) {
        requireNonNull(field);
        String normalizedField = field.trim().toLowerCase();
        switch (normalizedField) {
        case "name":
            return SortField.NAME;
        case "graduation": // Fallthrough
        case "grad":
            return SortField.GRAD;
        case "course":
            return SortField.COURSE;
        case "specialisation": // Fallthrough
        case "specialization": // Fallthrough
        case "spec":
            return SortField.SPEC;
        case "priority":
            return SortField.PRIORITY;
        case "none":
            return SortField.NONE;
        default:
            return SortField.INVALID;
        }
    }

    /**
     * Returns true if a given sort field is valid.
     */
    public static boolean isValidSortField(SortField field) {
        requireNonNull(field);
        return field != SortField.INVALID;
    }
    

    /**
     * Parses user input {@code order} into a {@code SortOrder}
     *
     * @param order provided in user command
     * @return corresponding SortOrder
     */
    public static SortOrder parseSortOrder(String order) {
        requireNonNull(order);
        String normalizedOrder = order.trim().toLowerCase();
        switch (normalizedOrder) {
        case "asc": // Fallthrough
        case "ascending":
            return SortOrder.ASCENDING;
        case "desc": // Fallthrough
        case "descending":
            return SortOrder.DESCENDING;
        default:
            return SortOrder.INVALID;
        }
    }

    /**
     * Returns true if a given sort order is valid.
     */
    public static boolean isValidSortOrder(SortOrder order) {
        requireNonNull(order);
        return order != SortOrder.INVALID;
    }

    /**
     * Fields to sort by.
     */
    public enum SortField {
        NAME,
        GRAD,
        COURSE,
        SPEC,
        PRIORITY,
        NONE,
        INVALID
    }

    /**
     * Orders to sort by.
     */
    public enum SortOrder {
        ASCENDING,
        DESCENDING,
        INVALID
    }
}
