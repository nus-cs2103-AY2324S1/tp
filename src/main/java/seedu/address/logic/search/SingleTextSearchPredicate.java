package seedu.address.logic.search;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Searches for a single string of text
 */
class SingleTextSearchPredicate implements SearchPredicate {
    private final String text;
    private final boolean isCaseIgnored;

    public SingleTextSearchPredicate(String search, boolean isCaseIgnored) {
        this.text = isCaseIgnored? search.toLowerCase() : search;
        this.isCaseIgnored = isCaseIgnored;
    }

    @Override
    public FieldRanges test(Map<String, String> p) {
        FieldRanges fieldRanges = new FieldRanges();
        for (Entry<String, String> entry : p.entrySet()) {
            int index;
            if (isCaseIgnored) {
                index = entry.getValue().toLowerCase().indexOf(text);
            } else {
                index = entry.getValue().indexOf(text);
            }
            if (index != -1) {
                fieldRanges.put(entry.getKey(), new Range(index, index + text.length()-1));
            }
        }
        return fieldRanges;
    }
}
