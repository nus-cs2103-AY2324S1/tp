package seedu.address.logic.search;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Searches for a single string of text
 */
class SingleTextSearchMatcher extends SearchMatcher {
    private final String textToFind;

    public SingleTextSearchMatcher(String search) {
        textToFind = search;
    }

    @Override
    public FieldRanges test(Map<String, String> p) {

        Optional<Map.Entry<String, Range>> optionalMatch = p.entrySet().stream()
                .map(this::getFieldRangeEntryElseNull).filter(Objects::nonNull).findAny();

        FieldRanges fieldRanges = new FieldRanges();
        optionalMatch.ifPresent(entry -> fieldRanges.put(entry.getKey(), entry.getValue()));
        return fieldRanges;
    }

    private Map.Entry<String, Range> getFieldRangeEntryElseNull(Map.Entry<String, String> entry) {
        String strToSearch;
        if (entry.getValue() == null) {
            strToSearch = entry.getKey();
        } else {
            strToSearch = entry.getValue();
        }
        Range range = getRangeIfMatchElseNull(strToSearch);
        if (range == null) {
            return null;
        }
        return new AbstractMap.SimpleEntry<>(entry.getKey(), range);
    }

    private Range getRangeIfMatchElseNull(String target) {
        int index;
        if (isFlagApplied(Flag.FULL_WORD_MATCHING_ONLY)) {
            String regex = "\\b" + Pattern.quote(textToFind) + "\\b";
            index = index(target, regex);
        } else {
            index = index(target, Pattern.quote(textToFind));
        }
        if (index == -1) {
            return null;
        }
        return new Range(index, index + textToFind.length() - 1);
    }

    public int index(String toSearch, String regex) {

        Pattern pattern = isFlagApplied(Flag.CASE_SENSITIVITY)
                ? Pattern.compile(regex)
                : Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(toSearch);

        if (matcher.find()) {
            return matcher.start();
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return textToFind;
    }
}
