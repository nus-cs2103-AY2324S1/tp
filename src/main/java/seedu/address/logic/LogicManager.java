package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.course.Course;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Pair<String, Integer>> getFilteredCourseList() {
        ObservableList<Person> personList = getFilteredPersonList();

        Map<String, Integer> courseCountMap = new HashMap<>();

        for (Person person : personList) {
            Set<Course> courses = person.getCourses();
            for (Course course : courses) {
                String courseName = course.courseName;
                courseCountMap.put(courseName, courseCountMap.getOrDefault(courseName, 0) + 1);
            }
        }

        List<Pair<String, Integer>> coursePairs = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : courseCountMap.entrySet()) {
            coursePairs.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        ObservableList<Pair<String, Integer>> observableCourseList = FXCollections.observableArrayList(coursePairs);
        return observableCourseList;
    }

    @Override
    public ObservableList<Pair<String, Integer>> getFilteredTagList() {
        ObservableList<Person> personList = getFilteredPersonList();

        Map<String, Integer> tagCountMap = new HashMap<>();
        tagCountMap.put("Friend", 0);
        tagCountMap.put("Close Friend", 0);
        tagCountMap.put("Emergency", 0);

        for (Person person : personList) {
            Set<Tag> tags = person.getTags();
            for (Tag tag : tags) {
                String tagName = tag.getTagName();
                if (tagCountMap.containsKey(tagName)) {
                    tagCountMap.put(tagName, tagCountMap.get(tagName) + 1);
                }
            }
        }

        List<Pair<String, Integer>> tagPairs = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : tagCountMap.entrySet()) {
            tagPairs.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        ObservableList<Pair<String, Integer>> observableTagList =
                FXCollections.observableArrayList(tagPairs);
        return observableTagList;
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
