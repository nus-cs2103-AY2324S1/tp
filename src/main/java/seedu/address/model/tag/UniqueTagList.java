package seedu.address.model.tag;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueTagList {
    private static Map<String, List<Tag>> categories = new HashMap<>();

    public void createTags(String category, String tagName) {
        if (!categories.containsKey(category)) {
            ArrayList<Tag> tags = new ArrayList<>();
            tags.add(new Tag(tagName));
            categories.put(category, tags);
        } else {
            System.out.println("here");
            List<Tag> tags = categories.get(category);
            if (tags.contains(new Tag(tagName))) {
                return;
            }
            tags.add(new Tag(tagName));
            categories.put(category, tags);
        }
    }

    public static Tag getTag(String tagName) throws ParseException {
        for (List<Tag> tags : categories.values()) {
            for (Tag tag : tags) {
                if (tag.tagName.equals(tagName)) {
                    return tag; // Found the tag, return it
                }
            }
        }
        throw new ParseException("Tag does not exist!"); // Tag not found in any category
    }

    public Map<String, List<Tag>> getCategories() {
        return categories;
    }
}
