package seedu.address.logic.search;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Searches for a single string of text.
 * Applies full word matching on tags regardless of the flag's presence.
 */
class SingleTextSearchMatcher extends SearchMatcher {
    private final String textToFind;

    public static SingleTextSearchMatcher getQuotedMatch(String text) {
        SingleTextSearchMatcher matcher = new SingleTextSearchMatcher(text);
        matcher.setFlag(Flag.CASE_SENSITIVITY, true);
        matcher.setFlag(Flag.FULL_WORD_MATCHING_ONLY, true);
        return matcher;
    }

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
        Range range;
        if (entry.getValue() == null) {
            range = getRangeIfMatchElseNull(entry.getKey(), true);
        } else {
            range = getRangeIfMatchElseNull(entry.getValue(), isFlagApplied(Flag.FULL_WORD_MATCHING_ONLY));
        }
        if (range == null) {
            return null;
        }
        return new AbstractMap.SimpleEntry<>(entry.getKey(), range);
    }

    private Range getRangeIfMatchElseNull(String target, boolean isFullWord) {
        int index;
        if (isFullWord) {
            index = index(target, true);
        } else {
            index = index(target,false);
        }
        if (index == -1) {
            return null;
        }
        return new Range(index, index + textToFind.length() - 1);
    }



    public int index(String toSearch, boolean isFullWord) {
        String regex = isFullWord
                ? "\\b" + Pattern.quote(textToFind) + "\\b"
                : Pattern.quote(textToFind);
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
