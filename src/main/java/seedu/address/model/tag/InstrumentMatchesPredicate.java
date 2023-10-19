package seedu.address.model.tag;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.musician.Musician;

/**
 * Tests that a {@code Musician}'s {@code Instrument} tag matches any of the keywords given.
 */
public class InstrumentMatchesPredicate extends TagMatchesPredicate {

    public InstrumentMatchesPredicate(List<String> instrument) {
        super(instrument);
    }

    @Override
    public boolean test(Musician musician) {
        List<String> instruments = super.getTagNames();
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

        return super.equals(other);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("instruments", super.getTagNames()).toString();
    }

}
