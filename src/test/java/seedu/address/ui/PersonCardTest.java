package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.model.socialmedialink.SocialMediaLink;

public class PersonCardTest {

    @BeforeAll
    public static void init() {
        JavaFxInitializer.initialize();
    }

    @AfterAll
    public static void cleanup() {
        JavaFxInitializer.cleanup();
    }

    @Test
    public void constructor_withPerson_nameMatches() {
        PersonCard personCard = new PersonCard(AMY, 1);
        assertEquals(AMY.getName().fullName, personCard.getName());
    }

    @Test
    public void constructor_withPerson_majorMatches() {
        PersonCard personCard = new PersonCard(AMY, 1);
        assertEquals(AMY.getMajor().value, personCard.getMajor());
    }

    @Test
    public void constructor_withPerson_yearMatches() {
        PersonCard personCard = new PersonCard(AMY, 1);
        assertEquals("Y" + AMY.getYear().value, personCard.getYear());
    }

    @Test
    public void constructor_withPerson_emailMatches() {
        PersonCard personCard = new PersonCard(AMY, 1);
        assertEquals(AMY.getEmail().value, personCard.getEmail());
    }

    @Test
    public void constructor_withPerson_tutorialsMatches() {
        PersonCard personCard = new PersonCard(AMY, 1);
        String tutorialsText = AMY.getTutorials().stream()
                .map(t -> "T" + t.getValue())
                .sorted()
                .collect(Collectors.joining(", "));
        assertEquals(tutorialsText, personCard.getTutorials());
    }

    @Test
    public void constructor_withPerson_socialMediaLinksMatches() {
        PersonCard personCard = new PersonCard(ALICE, 1);
        Set<SocialMediaLink> socialMediaLinks = personCard.getSocialMediaLinks();

        // ALICE.getSocialMediaLinks() returns a Set<SocialMediaLink>
        Set<SocialMediaLink> aliceSocialMediaLinks = ALICE.getSocialMediaLinks();

        // Extract the first link from ALICE's social media links
        Optional<SocialMediaLink> firstAliceLink = aliceSocialMediaLinks.stream().findFirst();

        assertTrue(firstAliceLink.isPresent()); // Ensure the first link exists

        if (firstAliceLink.isPresent()) {
            // Check if the personCard's social media links contain the first link
            assertTrue(socialMediaLinks.contains(firstAliceLink.get()));
        }
    }

    @Test
    public void openWebBrowser_validLink_doesNotThrowException() {
        PersonCard personCard = new PersonCard(ALICE, 1);
        String validLink = "https://example.com";

        assertDoesNotThrow(() -> personCard.openWebBrowser(validLink));
    }

}
