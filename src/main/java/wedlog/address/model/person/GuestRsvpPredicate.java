package wedlog.address.model.person;

import java.util.function.Predicate;

import wedlog.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Guest}'s {@code Rsvp} matches the given input.
 */
public class GuestRsvpPredicate implements Predicate<Guest> {
    private final String input;

    /**
     * Constructor for GuestRsvpPredicate.
     */
    public GuestRsvpPredicate(String input) {
        this.input = input;
    }

    @Override
    public boolean test(Guest guest) {
        assert guest != null : "Guest passed to GuestRsvpPredicate should not be null!";
        return input.isEmpty()
                ? guest.getRsvpStatus().value.toLowerCase().contains("unknown") // check if value is unknown
                : guest.getRsvpStatus().value.equalsIgnoreCase(input); // check if value is exactly same
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GuestRsvpPredicate)) {
            return false;
        }

        GuestRsvpPredicate otherRsvpPredicate = (GuestRsvpPredicate) other;
        return input.equals(otherRsvpPredicate.input);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("input", input).toString();
    }
}
