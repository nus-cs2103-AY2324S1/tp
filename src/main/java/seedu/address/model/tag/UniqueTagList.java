package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * Represents a list of unique tags. This class provides methods to manage a collection of tags,
 * ensuring that no duplicate tags are allowed in the list.
 */
public class UniqueTagList implements Iterable<Tag> {

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
     * Checks if the list contains a tag with the specified name.
     *
     * @param nameToCheck The name to check for in the list. Must not be null.
     * @return True if a tag with the specified name is found in the list, false otherwise.
     */
    public boolean contains(String nameToCheck) {
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

        if (foundTag.isPresent()) {
            long occurrence = internalList.stream()
                    .filter(tag -> tag.tagName.equals(tagName) && tag.tagCategory.contains(tagCategory))
                    .count();
            if (occurrence > 1) {
                throw new ParseException("Multiple tags exists with the same name! "
                        + "Specify the category of the tag when adding it to a person e.g. edit 1 t/experience 3");
            }
            return foundTag.get();
        } else if (!tagCategory.isEmpty()) {
            Tag newTag = new Tag(tagName, tagCategory);
            add(newTag);
            return newTag;
        }
        return new Tag(tagName, "uncategorised");
    }

    /**
     * Replaces a target tag with an edited tag.
     *
     * @param target The tag to be replaced.
     * @param editedTag The edited tag to replace the target.
     * @throws TagNotFoundException If the target tag is not found in the list.
     * @throws DuplicateTagException If the edited tag is already present in the list.
     */
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TagNotFoundException();
        }

        if (!target.equals(editedTag) && contains(editedTag)) {
            throw new DuplicateTagException();
        }

        internalList.set(index, editedTag);
    }

    /**
     * Removes a tag from the list.
     *
     * @param toRemove The tag to remove.
     * @throws PersonNotFoundException If the tag to remove is not found in the list.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Replaces the current list of tags with a new list.
     *
     * @param replacement The new list of tags to replace the current list.
     */
    public void setTags(UniqueTagList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Sets the list of tags with a given list of tags.
     *
     * @param tags The list of tags to set.
     * @throws DuplicatePersonException If the tags are not unique in the list.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!tagsAreUnique(tags)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(tags);
    }

    /**
     * Returns an unmodifiable view of the internal tag list.
     *
     * @return An unmodifiable ObservableList of tags.
     */
    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
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

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    private boolean tagsAreUnique(List<Tag> tags) {
        for (int i = 0; i < tags.size() - 1; i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                if (tags.get(i).equals(tags.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
