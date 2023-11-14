package seedu.lovebook.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;

/**
 * A selection model that does not allow for selection.
 * Created to remove the flickering bug when selecting a date.
 *
 * Taken from: https://stackoverflow.com/questions/20621752/javafx-make-listview-not-selectable-via-mouse
 */
public class NoSelectionModel<T> extends MultipleSelectionModel<T> {

    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return FXCollections.emptyObservableList();
    }

    @Override
    public ObservableList<T> getSelectedItems() {
        return FXCollections.emptyObservableList();
    }

    @Override
    public void selectIndices(int index, int... indices) {
    }

    @Override
    public void selectAll() {
    }

    @Override
    public void selectFirst() {
    }

    @Override
    public void selectLast() {
    }

    @Override
    public void clearAndSelect(int index) {
    }

    @Override
    public void select(int index) {
    }

    @Override
    public void select(T obj) {
    }

    @Override
    public void clearSelection(int index) {
    }

    @Override
    public void clearSelection() {
    }

    @Override
    public boolean isSelected(int index) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void selectPrevious() {
    }

    @Override
    public void selectNext() {
    }
}
