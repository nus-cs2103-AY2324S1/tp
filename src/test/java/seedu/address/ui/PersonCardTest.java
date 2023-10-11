package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PersonCardTest {
    private Person[] persons = SampleDataUtil.getSamplePersons();

    class personCardFXMLRemoved extends PersonCard {
        private VBox fields = new VBox();

        private HBox cardPane = new HBox();
        private Label name = new Label();
        private Label id = new Label();
        private FlowPane tags = new FlowPane();
        public personCardFXMLRemoved(Person person) {
            super(person, 0, new String[]{"phone", "email", "address", "subjects", "tags"});
        }
    }

    @Test
    void build() {
        // could not create instance of PersonCard because of fxml file, so could not test build method
    }
}
