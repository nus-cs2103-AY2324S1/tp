package networkbook.testutil;

import networkbook.model.NetworkBook;
import networkbook.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code NetworkBook ab = new NetworkBookBuilder().withPerson("John", "Doe").build();}
 */
public class NetworkBookBuilder {

    private NetworkBook networkBook;

    public NetworkBookBuilder() {
        networkBook = new NetworkBook();
    }

    public NetworkBookBuilder(NetworkBook networkBook) {
        this.networkBook = networkBook;
    }

    /**
     * Adds a new {@code Person} to the {@code NetworkBook} that we are building.
     */
    public NetworkBookBuilder withPerson(Person person) {
        networkBook.addPerson(person);
        return this;
    }

    public NetworkBook build() {
        return networkBook;
    }
}
