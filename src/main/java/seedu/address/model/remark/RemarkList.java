package seedu.address.model.remark;

import java.util.ArrayList;

/**
 * Represents a list of remarks for an employee.
 */
public class RemarkList {
    public final ArrayList<Remark> remarkList;

    public RemarkList() {
        this.remarkList = new ArrayList<>();
    }

    public RemarkList(ArrayList<Remark> remarks) {
        this.remarkList = remarks;
    }

    public Remark getRemark(int zeroBasedIndex) {
        return remarkList.get(zeroBasedIndex);
    }

    public int getSize() {
        return remarkList.size();
    }

    public void addRemark(Remark remark) {
        remarkList.add(remark);
    }

    public void deleteRemark(Remark remark) {
        remarkList.remove(remark);
    }

    public boolean contains(Remark remark) {
        return remarkList.contains(remark);
    }

    @SuppressWarnings("unchecked") // since cloning remark list
    public RemarkList getCopiedRemarkList() {
        ArrayList<Remark> copiedList = (ArrayList<Remark>) remarkList.clone();
        return new RemarkList(copiedList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkList)) {
            return false;
        }

        RemarkList otherRemarkList = (RemarkList) other;
        return remarkList.equals(otherRemarkList.remarkList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!remarkList.isEmpty()) {
            for (int i = 0; i < remarkList.size(); i++) {
                sb.append(i + 1).append(". ").append(remarkList.get(i));
                sb.append("\n");
            }
        } else {
            sb.append("No remarks");
        }
        return sb.toString();
    }

    /**
     * Returns true if a given ArrayList is a valid RemarkList.
     *
     * @param test List to be tested
     */
    public static boolean isValidRemarkList(ArrayList<Remark> test) {
        if (test.isEmpty()) {
            return true;
        }
        for (int i = 0; i < test.size(); i++) {
            if (Remark.isValidRemark(test.get(i).toString())) {
                return true;
            }
        }
        return false;
    }
}
