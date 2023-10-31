package seedu.address.model.task;

import java.util.ArrayList;
import java.util.Comparator;

public class TaskList {
    private final ArrayList<Task> list;
    private Comparator<Task> sortingOrder;

    public TaskList() {
        this.list = new ArrayList<Task>(100);
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task get(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void add(Task task) {
        list.add(task);
        list.sort(sortingOrder);
    }

    public Task delete(int index) {
        Task toDelete = list.get(index);
        list.remove(index);
        list.sort(sortingOrder);
        return toDelete;
    }

    public void setSortDeadline() {
        sortingOrder = new Task.TaskDeadlineComparator();
    }

    public void setSortDescriptor() {
        sortingOrder = new Task.TaskDescriptorComparator();
    }
}
