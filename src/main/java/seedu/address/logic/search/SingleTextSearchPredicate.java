package seedu.address.logic.search;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Searches for a single string of text
 */
class SingleTextSearchPredicate extends SearchPredicate {
    private final String textToFind;

    public SingleTextSearchPredicate(String search) {
        textToFind = search;
    }

    @Override
    public FieldRanges test(Map<String, String> p) {
        FieldRanges fieldRanges = new FieldRanges();

        for (Map.Entry<String, String> entry : p.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int index;
            if (isFlagApplied(Flag.FULL_WORD_MATCHING_ONLY)) {
                index = index(value, textToFind);
            } else {
                String regex = "\\b" + textToFind + "\\b";
                index = index(value, regex);
            }

            if (index != -1) {
                fieldRanges.put(key, new Range(index, index + textToFind.length() - 1));
                break;
            }
        }

        return fieldRanges;
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
}
