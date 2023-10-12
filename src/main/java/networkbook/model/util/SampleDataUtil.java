package networkbook.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import networkbook.model.NetworkBook;
import networkbook.model.ReadOnlyNetworkBook;
import networkbook.model.person.*;
import networkbook.model.tag.Tag;

/**
 * Contains utility methods for populating {@code NetworkBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                new UniqueList<Email>().setItems(List.of(new Email("alexyeoh@example.com"))),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new Priority("low")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"),
                new UniqueList<Email>().setItems(List.of(new Email("berniceyu@example.com"))),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new Priority("high")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new UniqueList<Email>().setItems(List.of(new Email("charlotte@example.com"))),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new Priority("M")),
            new Person(new Name("David Li"), new Phone("91031282"),
                new UniqueList<Email>().setItems(List.of(new Email("lidavid@example.com"))),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new Priority("High")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new UniqueList<Email>().setItems(List.of(new Email("irfan@example.com"))),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new Priority("lOW")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new UniqueList<Email>().setItems(List.of(new Email("royb@example.com"))),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), null)
        };
    }

    public static ReadOnlyNetworkBook getSampleNetworkBook() {
        NetworkBook sampleAb = new NetworkBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
