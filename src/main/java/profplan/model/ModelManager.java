package profplan.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import profplan.commons.core.GuiSettings;
import profplan.commons.core.LogsCenter;
import profplan.commons.core.Settings;
import profplan.commons.util.CollectionUtil;
import profplan.model.task.DueDate;
import profplan.model.task.Priority;
import profplan.model.task.Status;
import profplan.model.task.Task;

/**
 * Represents the in-memory model of the task list data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private static UserConfigs userConfigs;
    private final ProfPlan profPlan;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given profPlan and userPrefs.
     */
    public ModelManager(ReadOnlyProfPlan profPlan, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyUserConfigs userConfigs) {
        CollectionUtil.requireAllNonNull(profPlan, userPrefs);

        logger.fine("Initializing with task list: " + profPlan + " and user prefs " + userPrefs);

        this.profPlan = new ProfPlan(profPlan);
        this.userPrefs = new UserPrefs(userPrefs);
        ModelManager.userConfigs = new UserConfigs(userConfigs);
        filteredTasks = new FilteredList<>(this.profPlan.getTaskList());
    }

    public ModelManager() {
        this(new ProfPlan(), new UserPrefs(), new UserConfigs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getProfPlanFilePath() {
        return userPrefs.getProfPlanFilePath();
    }

    @Override
    public void setProfPlanFilePath(Path profPlanFilePath) {
        requireNonNull(profPlanFilePath);
        userPrefs.setProfPlanFilePath(profPlanFilePath);
    }

    //=========== UserConfigs ==================================================================================

    public static void setUserConfigs(ReadOnlyUserConfigs userConfigs) {
        requireNonNull(userConfigs);
        ModelManager.userConfigs.resetData(userConfigs);
    }

    public static ReadOnlyUserConfigs getUserConfigs() {
        return userConfigs;
    }

    public static Settings getSettings() {
        return userConfigs.getSettings();
    }

    public static void setSettings(Settings settings) {
        requireNonNull(settings);
        userConfigs.setSettings(settings);
    }

    //=========== ProfPlan ================================================================================

    @Override
    public void setProfPlan(ReadOnlyProfPlan profPlan) {
        this.profPlan.resetData(profPlan);
    }

    @Override
    public ReadOnlyProfPlan getProfPlan() {
        return profPlan;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return profPlan.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        profPlan.removeTask(target);
    }

    @Override
    public void deleteTask() {
        profPlan.removeTask();
    }

    @Override
    public void addTask(Task task) {
        profPlan.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void markTask(int index) throws IllegalArgumentException {
        Task temp = profPlan.getTaskList().get(index);
        temp.setStatus(Status.DONE_STATUS);
        profPlan.setTask(profPlan.getTaskList().get(index), temp);
    }

    @Override
    public void unmarkTask(int index) {
        Task temp = profPlan.getTaskList().get(index);
        temp.setStatus(Status.UNDONE_STATUS);
        profPlan.setTask(profPlan.getTaskList().get(index), temp);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        CollectionUtil.requireAllNonNull(target, editedTask);
        profPlan.setTask(target, editedTask);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedProfPlan}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void sortTaskByDueDate() {
        if (this.filteredTasks.isEmpty()) {
            throw new UnsupportedOperationException("Can not sort since there are no tasks "
                + "displayed currently in the UI");
        }
        profPlan.getTaskList().sort(Comparator.comparing(Task::getDueDate));
    }

    @Override
    public void sortTaskByPriority() {
        if (this.filteredTasks.isEmpty()) {
            throw new UnsupportedOperationException("Can not sort since there are no tasks "
                + "displayed currently in the UI");
        }
        profPlan.getTaskList().sort(Comparator.comparing(Task::getPriority));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return profPlan.equals(otherModelManager.profPlan)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredTasks.equals(otherModelManager.filteredTasks);
    }

    /**
     * Returns the task to do next based on the formula: priority/#daysToDueDate.
     * */
    @Override
    public Task getDoNextTask() {
        ObservableList<Task> internalUnmodifiableList = profPlan.getTaskList();
        Task recommendedTask = null;
        double highestComputedValue = Double.NEGATIVE_INFINITY; // Initialize with a very low value

        // Get the current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        String curDate = dateFormat.format(currentDate);

        // Iterate through the list to find the task with the highest computed value
        for (Task task : internalUnmodifiableList) {
            Priority priority = task.getPriority();
            DueDate dueDate = task.getDueDate();

            // Calculate the number of days left to the due date
            double daysLeft = getDaysUntilDueDate(dueDate, curDate);

            // Calculate the computed value (priority divided by days left)
            double computedValue = priorityValue(priority) / (double) daysLeft;
            System.out.println(task.getName().toString());
            System.out.println(computedValue);

            // Update the recommended task if we find a higher computed value
            if ((computedValue > highestComputedValue) && (task.getStatus().equals(Status.UNDONE_STATUS))) {
                highestComputedValue = computedValue;
                recommendedTask = task;
                System.out.println(task.getName().toString());
            }
        }

        return recommendedTask;
    }

    // Helper method to calculate the number of days left to the due date
    public double getDaysUntilDueDate(DueDate dueDate, String curDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date dueDateDate = dateFormat.parse(dueDate.toString());
            Date currentDate = dateFormat.parse(curDate);

            long difference = dueDateDate.getTime() - currentDate.getTime();
            if (difference < 0) {
                difference = 1;
            }
            double days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            if (days == 0) {
                days = 0.1;
            }
            System.out.println(days);
            return days;
        } catch (java.text.ParseException e) {
            // Handle parsing errors
            return -1;
        }
    }

    // Helper method to calculate the priority value
    private double priorityValue(Priority priority) {
        // convert the priority string to a numeric value.
        return Double.parseDouble(priority.toString());
    }

    public double getCompletionRate() {
        Predicate<? super Task> currentPredicate = filteredTasks.getPredicate();
        filteredTasks.setPredicate(PREDICATE_SHOW_ALL_TASKS);
        int totalTasks = filteredTasks.size();
        filteredTasks.setPredicate(x -> x.getStatus().equals(Status.DONE_STATUS));
        int doneTasks = filteredTasks.size();
        filteredTasks.setPredicate(currentPredicate);
        return (double) doneTasks / totalTasks;
    }

}

