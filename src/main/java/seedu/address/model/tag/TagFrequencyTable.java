package seedu.address.model.tag;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Represents a table of tag frequencies for both persons and tasks in the address book.
 */
public class TagFrequencyTable {

    private final Map<Tag, Integer> personTagFrequencyTable;
    private final Map<Tag, Integer> taskTagFrequencyTable;
    private final Map<Tag, Integer> combinedTagFrequencyTable;

    /**
     * Constructs a new {@code TagFrequencyTable} from a list of {@code Person}s and {@code Task}s.
     *
     * @param personList List of {@code Person}s.
     * @param taskList List of {@code Task}s.
     */
    public TagFrequencyTable(List<Person> personList, List<Task> taskList) {
        this.personTagFrequencyTable = new HashMap<>();
        this.taskTagFrequencyTable = new HashMap<>();
        this.combinedTagFrequencyTable = new HashMap<>();

        personList.forEach(this::processPerson);
        taskList.forEach(this::processTask);
    }

    /**
     * Processes the tags of a person and increments the frequency of each tag.
     *
     * @param p The Person whose tags are to be processed.
     */
    private void processPerson(Person p) {
        p.getTags().forEach(tag -> {
            personTagFrequencyTable.put(tag, personTagFrequencyTable.getOrDefault(tag, 0) + 1);
            combinedTagFrequencyTable.put(tag, combinedTagFrequencyTable.getOrDefault(tag, 0) + 1);
        });
    }

    /**
     * Processes the tags of a task and increments the frequency of each tag.
     *
     * @param t The Task whose tags are to be processed.
     */
    private void processTask(Task t) {
        t.getTags().forEach(tag -> {
            taskTagFrequencyTable.put(tag, taskTagFrequencyTable.getOrDefault(tag, 0) + 1);
            combinedTagFrequencyTable.put(tag, combinedTagFrequencyTable.getOrDefault(tag, 0) + 1);
        });
    }

    public Map<Tag, Integer> getPersonTagFrequencyTable() {
        return new HashMap<>(personTagFrequencyTable);
    }

    public Map<Tag, Integer> getTaskTagFrequencyTable() {
        return new HashMap<>(taskTagFrequencyTable);
    }

    public Map<Tag, Integer> getCombinedTagFrequencyTable() {
        return new HashMap<>(combinedTagFrequencyTable);
    }

    /**
     * Returns a formatted string of the tag frequency table.
     * The output is formatted in descending order of tag frequency, and then in alphabetical order.
     *
     * @return Formatted String representation of the tag frequency table.
     */
    public String format() {
        ArrayList<String> formattedTagList = new ArrayList<>();

        // Compares two entries in the table, and sorts them in descending order of frequency.
        // If two entries have the same frequency, they are sorted in alphabetical order.
        // Solution inspired by https://stackoverflow.com/a/33552682
        Comparator<Map.Entry<Tag, Integer>> comparator = (e1, e2) ->
                e1.getValue().equals(e2.getValue())
                        ? e1.getKey().compareTo(e2.getKey())
                        : e2.getValue().compareTo(e1.getValue());

        combinedTagFrequencyTable.entrySet()
                .stream()
                .sorted(comparator)
                .map(entry -> String.format("%s: %d items (%d Persons, %d Tasks)",
                        entry.getKey(),
                        entry.getValue(),
                        personTagFrequencyTable.getOrDefault(entry.getKey(), 0),
                        taskTagFrequencyTable.getOrDefault(entry.getKey(), 0)))
                .forEach(formattedTagList::add);

        return String.join("\n", formattedTagList);
    }

    /**
     * Checks if the tag frequency table is empty.
     *
     * @return True if the tag frequency table is empty.
     */
    public boolean isEmpty() {
        return combinedTagFrequencyTable.isEmpty();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("personTagFrequencyTable", personTagFrequencyTable)
                .add("taskTagFrequencyTable", taskTagFrequencyTable)
                .add("combinedTagFrequencyTable", combinedTagFrequencyTable)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagFrequencyTable)) {
            return false;
        }

        TagFrequencyTable otherTable = (TagFrequencyTable) other;
        return personTagFrequencyTable.equals(otherTable.personTagFrequencyTable)
               && taskTagFrequencyTable.equals(otherTable.taskTagFrequencyTable)
               && combinedTagFrequencyTable.equals(otherTable.combinedTagFrequencyTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personTagFrequencyTable, taskTagFrequencyTable, combinedTagFrequencyTable);
    }
}
