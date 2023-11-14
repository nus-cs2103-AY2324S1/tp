package seedu.address.testutil;

import seedu.address.model.NpcTrack;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code NpcTrack ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private NpcTrack npcTrack;

    public AddressBookBuilder() {
        npcTrack = new NpcTrack();
    }

    public AddressBookBuilder(NpcTrack npcTrack) {
        this.npcTrack = npcTrack;
    }

    /**
     * Adds a new {@code Person} to the {@code NpcTrack} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        npcTrack.addPerson(person);
        return this;
    }

    public NpcTrack build() {
        return npcTrack;
    }
}
