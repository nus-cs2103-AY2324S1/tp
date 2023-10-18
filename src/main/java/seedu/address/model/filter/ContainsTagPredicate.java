package seedu.address.model.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the tag given.
 */
public class ContainsTagPredicate extends SerializablePredicate {
    private final Tag tag;


    /**
     * Constructor a {@code ContainsTagPredicate} with a tag.
     *
     * @param tag Tag to check for in a person.
     */
    public ContainsTagPredicate(Tag tag) {
        super(person -> person.getTags().stream().anyMatch(personTag ->
                StringUtil.containsWordIgnoreCase(personTag.getTagName(), tag.getTagName())));
        this.tag = tag;
    }

    @JsonCreator
    public static ContainsTagPredicate create(@JsonProperty("tag") Tag tag) {
        return new ContainsTagPredicate(tag);
    }

    @Override
    public String toString() {
        return "Tag Filter: " + tag.getTagName();
    }
}
