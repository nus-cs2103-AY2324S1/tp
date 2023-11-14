package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.musician.Musician;

/**
 * Tests that a {@code Musician}'s {@code Instrument} tag matches any of the keywords given.
 */
public class InstrumentMatchesPredicate implements Predicate<Musician> {

    private final List<String> instruments;

    public InstrumentMatchesPredicate(List<String> instruments) {
        this.instruments = instruments;
    }

    @Override
    public boolean test(Musician musician) {
        return instruments.stream()
                .anyMatch(instrumentToMatch -> musician.getInstruments().stream().anyMatch(
                        musicianInstrument -> StringUtil.containsWordIgnoreCase(
                                musicianInstrument.tagName, instrumentToMatch
                )));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InstrumentMatchesPredicate)) {
            return false;
        }

        InstrumentMatchesPredicate otherInstrumentMatchesPredicate = (InstrumentMatchesPredicate) other;
        return instruments.equals(otherInstrumentMatchesPredicate.instruments);
    }

    @Override
    public int hashCode() {
        return instruments.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("instruments", instruments).toString();
    }
}
