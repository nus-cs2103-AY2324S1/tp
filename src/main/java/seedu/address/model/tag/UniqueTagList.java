package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * Represents a list of unique tags. This class provides methods to manage a collection of tags,
 * ensuring that no duplicate tags are allowed in the list.
 */
public class UniqueTagList {

    private static final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if the list contains a specific tag.
     *
     * @param toCheck The tag to check for in the list.
     * @return true if the tag is in the list, false otherwise.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Checks if the list contains a tag with the specified category.
     *
     * @param categoryToCheck The category to check for in the list. Must not be null.
     * @return True if a tag with the specified category is found in the list, false otherwise.
     */
    public boolean containsTagCategory(String categoryToCheck) {
        requireNonNull(categoryToCheck);
        for (Tag tag : internalList) {
            if (tag.tagCategory.equals(categoryToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the list contains a tag with the specified name.
     *
     * @param nameToCheck The category to check for in the list. Must not be null.
     * @return True if a tag with the specified name is found in the list, false otherwise.
     */
    public boolean containsTagName(String nameToCheck) {
        requireNonNull(nameToCheck);
        for (Tag tag : internalList) {
            if (tag.tagName.equals(nameToCheck)) {
                return true;
            }
        }
        return false;
    }



    /**
     * Adds a tag to the list.
     *
     * @param toAdd The tag to add.
     * @throws DuplicateTagException If the tag to add already exists in the list.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTagException();
        }
        internalList.add(toAdd);
    }

    /**
     * Retrieves a tag by its name.
     *
     * @param tagName The name of the tag to retrieve.
     * @return The tag with the specified name.
     * @throws ParseException If the tag is not found in the list.
     */
    public Tag getTag(String tagName, String tagCategory) throws ParseException {

        Optional<Tag> foundTag = internalList.stream()
                .filter(tag -> tag.tagName.equals(tagName) && tag.tagCategory.contains(tagCategory))
                .findFirst();

        if (!tagCategory.isEmpty()) {
            // tag category is specified
            for (Tag tag : internalList) {
                if (tag.tagName.equals(tagName) && tag.tagCategory.equals(tagCategory)) {
                    return tag;
                }
            }
            throw new ParseException("Tag category does not exist!");
        } else if (foundTag.isPresent()) {
            // tag category not specified
            long occurrence = internalList.stream()
                    .filter(tag -> tag.tagName.equals(tagName) && tag.tagCategory.contains(tagCategory))
                    .count();
            // if tag occurs more than once in tag list
            if (occurrence > 1) {
                throw new ParseException("Multiple tags exists with the same name! "
                        + "Specify the category of the tag when adding it to a person e.g. edit 1 t/experience 3");
            }
            return foundTag.get();
        }
        // tag is uncategorised
        Tag uncategorisedTag = new Tag(tagName, "uncategorised");
        this.add(uncategorisedTag); // add uncategorised tag to unique tag list
        return uncategorisedTag;
    }

    /**
     * Returns an unmodifiable view of the internal tag list.
     *
     * @return An unmodifiable ObservableList of tags.
     */
    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Removes a tag from the list.
     *
     * @param toRemove The tag to remove.
     * @throws TagNotFoundException If the tag to remove is not found in the list.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TagNotFoundException();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UniqueTagList)) {
            return false;
        }

        UniqueTagList otherUniqueTagList = (UniqueTagList) other;
        return internalList.equals(otherUniqueTagList.internalList);
    }
}
