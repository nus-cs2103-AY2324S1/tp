package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCUMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORGANISATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_1_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_2_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_2_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.testutil.TypicalProjects.getTypicalProjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.project.Project;

/**
 * A utility class containing a list of {@code Developer} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRole("Developer")
            .withOrganisation("Google").withDocument("https://www.google.com")
            .withProjects("AndroidApp").build();
    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432").withRole("Developer")
            .withOrganisation("Amazon").withDocument("https://www.amazon.com")
            .withProjects("owesMoney", "friends").build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz")
            .withAddress("wall street").withEmail("carl@egx.com")
            .withPhone("95352563").withRole("Developer")
            .withOrganisation("EpicGames").withDocument("https://www.epicgames.com")
            .withProjects("AndroidApp").build();
    public static final Client DANIEL = new ClientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withRole("Developer")
            .withOrganisation("Microsoft").withDocument("https://www.microsoft.com")
            .withProjects("friends").build();
    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer").withPhone("94822244")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withRole("Developer").withOrganisation("Apple").withDocument("https://www.apple.com")
            .withProjects("AppleApp").build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz").withPhone("94824273")
            .withEmail("lydia@example.com").withAddress("little tokyo").withRole("Developer")
            .withOrganisation("Facebook").withDocument("https://www.facebook.com")
            .withProjects("AppleApp").build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best").withPhone("94824423")
            .withEmail("anna@example.com").withAddress("4th street").withRole("Developer")
            .withOrganisation("Sony").withDocument("https://www.sony.com")
            .withProjects("SonyApp").build();
    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Meier").withPhone("84824243")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Client IDA = new ClientBuilder().withName("Ida Mueller").withPhone("84821313")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withProjects(VALID_PROJECT_2_AMY)
            .withRole(VALID_ROLE_AMY).withOrganisation(VALID_ORGANISATION_AMY)
            .withDocument(VALID_DOCUMENT_AMY).build();
    public static final Client BOB = new ClientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withProjects(VALID_PROJECT_1_BOB, VALID_PROJECT_2_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client: getTypicalClients()) {
            ab.addClient(client);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical clients integrated with projects.
     */
    public static AddressBook getTypicalAddressBookWithProjects() {
        AddressBook ab = new AddressBook();
        for (Client client: getTypicalClients()) {
            ab.addClient(client);
        }
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
