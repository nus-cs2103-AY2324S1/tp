package networkbook.ui;

import static networkbook.logic.commands.CommandTestUtil.VALID_COURSE_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_GRADUATION_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_GRADUATION_FULL_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_LINK_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_LINK_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_SPECIALISATION_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_SPECIALISATION_BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import networkbook.MainApp;
import networkbook.model.person.Person;
import networkbook.testutil.PersonBuilder;

@ExtendWith(ApplicationExtension.class)
public class PersonCardTest {
    //NOTE: for GUI headless tests, running this single file or test case has issue on Windows,
    //possibly due to issue discussed in https://github.com/Santulator/Santulator/issues/14
    //To avoid the issue, either run gradle test command to perform all tests,
    //or use 'gradlew check coverage' in command line in root folder

    private static final String EXPECTED_EMPTY_FIELD_TEXT = "-";
    private static final Consumer<String> DUMMY_MAIN_CALLBACK = (str) -> {};

    @BeforeAll
    public static void setupForHeadlessTesting() {
        HeadlessHelper.setupForHeadlessTesting();
    }

    @BeforeEach
    public void setup() throws Exception {
        ApplicationTest.launch(MainApp.class);
    }

    // Helper function to test field labels
    private void testChildLabel(FlowPane flowPane, int index, String expectedText) {
        // Test that child label can be found
        Node node = flowPane.getChildren().get(index);
        assertTrue(() -> node instanceof Label);
        Label label = (Label) node;

        // Test that label is visible and has correct text
        assertEquals(expectedText, label.getText());
        assertTrue(label.isVisible());
    }

    // Helper function to test field hyperlinks
    private void testChildHyperlink(FlowPane flowPane, int index, String expectedText) {
        // Test that child hyperlink can be found
        Node node = flowPane.getChildren().get(index);
        assertTrue(() -> node instanceof Hyperlink);
        Hyperlink link = (Hyperlink) node;
        // Test that link is visible and has correct text
        assertEquals(expectedText, link.getText());
        assertTrue(link.isVisible());
    }

    @Test
    public void constructor_hasValidPerson_showsCorrectName() {
        Person person = new PersonBuilder().withName("Bob").withGraduation(VALID_GRADUATION_BOB).build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        Label nameLabel = personCard.getName();
        assertEquals("Bob", nameLabel.getText());
    }

    @Test
    public void populateGrad_hasValidGraduation_showsValidGraduation() {
        // EP: Has graduation
        Person person = new PersonBuilder().withName("Bob").withGraduation(VALID_GRADUATION_BOB).build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane graduationPane = personCard.getGraduation();
        testChildHyperlink(graduationPane, 0, VALID_GRADUATION_FULL_BOB);
    }

    @Test
    public void populateGrad_noGraduation_showsBlank() {
        // EP: No graduation
        Person person = new PersonBuilder().withName("Bob").withoutOptionalFields().build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane graduationPane = personCard.getGraduation();
        testChildLabel(graduationPane, 0, EXPECTED_EMPTY_FIELD_TEXT);
    }

    @Test
    public void populateHyperlinkListChildren_hasValidCourses_showsValidCourses() {
        // EP: Has courses
        Person person = new PersonBuilder().withName("Bob")
                .withCourses(List.of(VALID_COURSE_BOB, VALID_COURSE_AMY)).build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane coursePane = personCard.getCourses();
        testChildHyperlink(coursePane, 0, "1) " + VALID_COURSE_BOB);
        testChildHyperlink(coursePane, 1, "2) " + VALID_COURSE_AMY);
    }

    @Test
    public void populateHyperlinkListChildren_noCourses_showsBlank() {
        // EP: No courses
        Person person = new PersonBuilder().withName("Bob").withoutOptionalFields().build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane coursesPane = personCard.getCourses();
        testChildLabel(coursesPane, 0, EXPECTED_EMPTY_FIELD_TEXT);
    }

    @Test
    public void populateListChildren_hasValidPhones_showsValidPhones() {
        // EP: Has phones
        Person person = new PersonBuilder().withName("Bob")
                .withPhones(List.of(VALID_PHONE_BOB, VALID_PHONE_AMY)).build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane phonesPane = personCard.getPhones();
        testChildLabel(phonesPane, 0, "1) " + VALID_PHONE_BOB);
        testChildLabel(phonesPane, 1, "2) " + VALID_PHONE_AMY);
    }

    @Test
    public void populateListChildren_noPhones_showsBlank() {
        // EP: No phones
        Person person = new PersonBuilder().withName("Bob").withoutOptionalFields().build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane phonesPane = personCard.getPhones();
        testChildLabel(phonesPane, 0, EXPECTED_EMPTY_FIELD_TEXT);
    }

    @Test
    public void populateExternalHyperlinkListChildren_hasValidEmails_showsValidEmails() {
        // EP: Has emails
        Person person = new PersonBuilder().withName("Bob")
                .withEmails(List.of(VALID_EMAIL_BOB, VALID_EMAIL_AMY)).build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane emailsPane = personCard.getEmails();
        testChildHyperlink(emailsPane, 0, "1) " + VALID_EMAIL_BOB);
        testChildHyperlink(emailsPane, 1, "2) " + VALID_EMAIL_AMY);
    }

    @Test
    public void populateExternalHyperlinkListChildren_noEmails_showsBlank() {
        // EP: No emails
        Person person = new PersonBuilder().withName("Bob").withoutOptionalFields().build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane emailsPane = personCard.getEmails();
        testChildLabel(emailsPane, 0, EXPECTED_EMPTY_FIELD_TEXT);
    }

    @Test
    public void populateExternalHyperlinkListChildren_hasValidLinks_showsValidLinks() {
        // EP: Has links
        Person person = new PersonBuilder().withName("Bob")
                .withLinks(List.of(VALID_LINK_BOB, VALID_LINK_AMY)).build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane linksPane = personCard.getLinks();
        testChildHyperlink(linksPane, 0, "1) " + VALID_LINK_BOB);
        testChildHyperlink(linksPane, 1, "2) " + VALID_LINK_AMY);
    }

    @Test
    public void populateExternalHyperlinkListChildren_noLinks_showsBlank() {
        // EP: No links
        Person person = new PersonBuilder().withName("Bob").withoutOptionalFields().build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane linksPane = personCard.getLinks();
        testChildLabel(linksPane, 0, EXPECTED_EMPTY_FIELD_TEXT);
    }

    @Test
    public void populateHyperlinkListChildren_hasValidSpecialisations_showsValidSpecialisations() {
        // EP: Has specialisations
        Person person = new PersonBuilder().withName("Bob")
                .withSpecialisations(List.of(VALID_SPECIALISATION_BOB, VALID_SPECIALISATION_AMY)).build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane specsPane = personCard.getSpecialisations();
        testChildHyperlink(specsPane, 0, "1) " + VALID_SPECIALISATION_BOB);
        testChildHyperlink(specsPane, 1, "2) " + VALID_SPECIALISATION_AMY);
    }

    @Test
    public void populateHyperlinkListChildren_noSpecialisations_showsBlank() {
        // EP: No specialisations
        Person person = new PersonBuilder().withName("Bob").withoutOptionalFields().build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane specsPane = personCard.getSpecialisations();
        testChildLabel(specsPane, 0, EXPECTED_EMPTY_FIELD_TEXT);
    }

    @Test
    public void populatePriority_hasHighPriority_showsHighPriority() {
        // EP: Has high priority
        Person person = new PersonBuilder().withName("Bob").withPriority("high").build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane priorityPane = personCard.getPriority();
        testChildLabel(priorityPane, 0, "★★★");

        // Test that the style class is correct
        Node node = priorityPane.getChildren().get(0);
        node.getStyleClass().contains("priority_High");
    }
    @Test
    public void populatePriority_hasMediumPriority_showsMediumPriority() {
        // EP: Has medium priority
        Person person = new PersonBuilder().withName("Bob").withPriority("medium").build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane priorityPane = personCard.getPriority();
        testChildLabel(priorityPane, 0, "★★");

        // Test that the style class is correct
        Node node = priorityPane.getChildren().get(0);
        node.getStyleClass().contains("priority_Medium");
    }

    @Test
    public void populatePriority_hasLowPriority_showsLowPriority() {
        // EP: Has low priority
        Person person = new PersonBuilder().withName("Bob").withPriority("low").build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane priorityPane = personCard.getPriority();
        testChildLabel(priorityPane, 0, "★");

        // Test that the style class is correct
        Node node = priorityPane.getChildren().get(0);
        node.getStyleClass().contains("priority_Low");
    }

    @Test
    public void populatePriority_noPriority_showsBlank() {
        // EP: Has no priority
        Person person = new PersonBuilder().withName("Bob").build();
        PersonCard personCard = new PersonCard(person, 1, DUMMY_MAIN_CALLBACK);
        FlowPane priorityPane = personCard.getPriority();
        testChildLabel(priorityPane, 0, EXPECTED_EMPTY_FIELD_TEXT);
    }

    @Test
    public void linkHyperlink_onAction_callsMainCallback() {
        AtomicReference<String> callbackCommand = new AtomicReference<String>("");
        String expectedCommand = "open 1 /index 1";
        Consumer<String> callback = (str) -> callbackCommand.set(str);
        Person person = new PersonBuilder().withName("Bob").withLinks(List.of(VALID_LINK_BOB)).build();
        PersonCard personCard = new PersonCard(person, 1, callback);
        FlowPane linksPane = personCard.getLinks();
        Hyperlink link = (Hyperlink) linksPane.getChildren().get(0);
        link.fire();
        assertEquals(expectedCommand, callbackCommand.get());
    }

    @Test
    public void emailHyperlink_onAction_callsMainCallback() {
        AtomicReference<String> callbackCommand = new AtomicReference<String>("");
        String expectedCommand = "email 1 /index 1";
        Consumer<String> callback = (str) -> callbackCommand.set(str);
        Person person = new PersonBuilder().withName("Bob").withEmails(List.of(VALID_EMAIL_BOB)).build();
        PersonCard personCard = new PersonCard(person, 1, callback);
        FlowPane emailsPane = personCard.getEmails();
        Hyperlink link = (Hyperlink) emailsPane.getChildren().get(0);
        link.fire();
        assertEquals(expectedCommand, callbackCommand.get());
    }

}
