package seedu.address.model.employee;

import static seedu.address.model.employee.Employee.MAX_NUM_OF_LEAVES;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of leaves taken by an employee.
 * Guarantees: immutable
 */
public class LeaveList {

    public final ArrayList<Leave> leaveList;

    /**
     * The constructor for an empty LeaveList.
     */
    public LeaveList() {
        this.leaveList = new ArrayList<>();
    }

    /**
     * The constructor for a (non-empty) LeaveList.
     *
     * @param leaves The arraylist of leaves.
     */
    public LeaveList(ArrayList<Leave> leaves) {
        this.leaveList = leaves;
    }

    /**
     * Returns the specific leave in this LeaveList.
     *
     * @param index The zero-based index of the leave.
     * @return Returns the leave with the specified index.
     */
    public Leave getLeave(int index) {
        return leaveList.get(index);
    }

    /**
     * Returns the number of leaves in this LeaveList.
     *
     * @return Returns the size of the list.
     */
    public int getSize() {
        return leaveList.size();
    }

    /**
     * Adds a new leave of type Leave to this LeaveList.
     *
     * @param leave The leave that is added to the list.
     */
    public void addLeave(Leave leave) {
        leaveList.add(leave);
    }

    /**
     * Deletes the Leave object if it exists inside the LeaveList.
     *
     * @param leave The leave that is deleted to the list.
     * @return true if Leave object existed in the list before being deleted, and false otherwise
     */
    public boolean deleteLeave(Leave leave) {
        return leaveList.remove(leave);
    }

    /**
     * Returns true if the LeaveList contains the specified Leave.
     *
     * @param date The Leave to be checked.
     */
    public boolean contains(Leave date) {
        return leaveList.contains(date);
    }

    /**
     * Returns true if the LeaveList contains a Leave of the current date.
     */
    public boolean getCurrentLeaveStatus() {
        if (leaveList.isEmpty()) {
            return false;
        } else {
            return leaveList.contains(new Leave(LocalDate.now()));
        }
    }

    /**
     * Returns true if the LeaveList contains a Leave of the specified date.
     *
     * @param date The Leave date to be checked.
     */
    public boolean getLeaveStatus(LocalDate date) {
        if (leaveList.isEmpty()) {
            return false;
        } else {
            return leaveList.contains(new Leave(date));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LeaveList)) {
            return false;
        }

        LeaveList otherLeaveList = (LeaveList) other;
        return leaveList.equals(otherLeaveList.leaveList);
    }

    @SuppressWarnings("unchecked") // since cloning leavelist
    public LeaveList getCopiedLeaveList() {
        ArrayList<Leave> copiedList = (ArrayList<Leave>) leaveList.clone();
        return new LeaveList(copiedList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!leaveList.isEmpty()) {
            for (int i = 0; i < leaveList.size(); i++) {
                sb.append(i + 1).append(". ").append(leaveList.get(i));
                sb.append("\n");
            }
        } else {
            sb.append("No leaves taken");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return leaveList.hashCode();
    }
}
