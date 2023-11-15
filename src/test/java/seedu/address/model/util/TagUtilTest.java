package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class TagUtilTest {

    // Tests related to adding new persons

    @Test
    public void addingEmergencyTag_whenNoneExist_returnsTrue() {
        Person newPerson = new PersonBuilder().withTags("emergency").build();
        List<Person> currentPersons = Arrays.asList(new PersonBuilder().withTags("friend").build());
        assertTrue(TagUtil.canAddOrEditEmergencyTag(newPerson, currentPersons));
    }

    @Test
    public void addingEmergencyTag_whenOneExists_returnsTrue() {
        Person newPerson = new PersonBuilder().withTags("emergency").build();
        List<Person> currentPersons = Arrays.asList(new PersonBuilder().withTags("emergency").build());
        assertTrue(TagUtil.canAddOrEditEmergencyTag(newPerson, currentPersons));
    }

    @Test
    public void addingEmergencyTag_whenTwoExist_returnsFalse() {
        Person newPerson = new PersonBuilder().withTags("emergency").build();
        List<Person> currentPersons = Arrays.asList(
                new PersonBuilder().withTags("emergency").build(),
                new PersonBuilder().withTags("emergency").build()
        );
        assertFalse(TagUtil.canAddOrEditEmergencyTag(newPerson, currentPersons));
    }

    @Test
    public void addingNonEmergencyTag_whenTwoEmergencyTagsExist_returnsTrue() {
        Person newPerson = new PersonBuilder().withTags("friend").build();
        List<Person> currentPersons = Arrays.asList(
                new PersonBuilder().withTags("emergency").build(),
                new PersonBuilder().withTags("emergency").build()
        );
        assertTrue(TagUtil.canAddOrEditEmergencyTag(newPerson, currentPersons));
    }

    // Tests related to editing existing persons

    @Test
    public void editingToAddEmergencyTag_whenNoneExist_returnsTrue() {
        EditCommand.EditPersonDescriptor descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setTags(new HashSet<>(Arrays.asList(new Tag("emergency"))));
        List<Person> currentPersons = Arrays.asList(new PersonBuilder().withTags("friend").build());
        assertTrue(TagUtil.canAddOrEditEmergencyTag(descriptor, currentPersons, null));
    }

    @Test
    public void editingToAddEmergencyTag_whenOneExists_returnsTrue() {
        EditCommand.EditPersonDescriptor descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setTags(new HashSet<>(Arrays.asList(new Tag("emergency"))));
        List<Person> currentPersons = Arrays.asList(new PersonBuilder().withTags("emergency").build());
        assertTrue(TagUtil.canAddOrEditEmergencyTag(descriptor, currentPersons, null));
    }

    @Test
    public void editingToAddEmergencyTag_whenTwoExist_returnsFalse() {
        EditCommand.EditPersonDescriptor descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setTags(new HashSet<>(Arrays.asList(new Tag("emergency"))));
        List<Person> currentPersons = Arrays.asList(
                new PersonBuilder().withTags("emergency").build(),
                new PersonBuilder().withTags("emergency").build()
        );
        assertFalse(TagUtil.canAddOrEditEmergencyTag(descriptor, currentPersons, null));
    }

    @Test
    public void editingToRemoveEmergencyTag_whenTwoExist_returnsTrue() {
        EditCommand.EditPersonDescriptor descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setTags(new HashSet<>(Arrays.asList(new Tag("friend"))));
        List<Person> currentPersons = Arrays.asList(
                new PersonBuilder().withTags("emergency").build(),
                new PersonBuilder().withTags("emergency").build()
        );
        Person personBeingEdited = new PersonBuilder().withTags("emergency").build();
        assertTrue(TagUtil.canAddOrEditEmergencyTag(descriptor, currentPersons, personBeingEdited));
    }

}
