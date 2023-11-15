package seedu.address.model.util;

import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Utility class for handling operations related to {@code Tag} objects within the application.
 */
public class TagUtil {

    public static final String EMERGENCY_TAG_LIMIT_MESSAGE =
            "Cannot add more than two contacts with the 'emergency' tag";

    /**
     * Checks if adding a person with the 'emergency' tag will exceed the allowed limit of 2.
     *
     * @param editPersonDescriptor the editPersonDescriptor
     * @param currentPersons the current list of persons
     * @return true if it is safe to add or edit the person, false otherwise
     */
    public static boolean canAddOrEditEmergencyTag(EditPersonDescriptor editPersonDescriptor,
                                                   List<Person> currentPersons,
                                                   Person personBeingEdited) {
        Set<Tag> editedTags = editPersonDescriptor.getTags().orElse(null);

        // If the personBeingEdited had an emergency tag, decrement the count by one
        long decrement = personBeingEdited != null && personBeingEdited.getTags()
                .contains(new Tag("Emergency")) ? 1 : 0;

        // If the editedTags doesn't contain emergency tag, then it's safe
        if (editedTags == null || editedTags.stream().noneMatch(Tag::isEmergencyTag)) {
            return true;
        }

        long emergencyTagCount = currentPersons.stream()
                .flatMap(p -> p.getTags().stream())
                .filter(Tag:: isEmergencyTag)
                .count() - decrement;

        return emergencyTagCount < 2;
    }


    /**
     * Checks if adding a person with the 'emergency' tag will exceed the allowed limit of 2.
     *
     * @param person the person to be added or edited
     * @param currentPersons the current list of persons
     * @return true if it is safe to add or edit the person, false otherwise
     */
    public static boolean canAddOrEditEmergencyTag(Person person, List<Person> currentPersons) {
        if (person.getTags().stream().anyMatch(Tag::isEmergencyTag)) {
            long emergencyTagCount = currentPersons.stream()
                .flatMap(p -> p.getTags().stream())
                .filter(Tag::isEmergencyTag)
                .count();
            return emergencyTagCount < 2;
        }
        return true;
    }
}
