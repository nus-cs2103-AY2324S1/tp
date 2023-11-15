package seedu.address.model.stall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_PRICE_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.FRIED_RICE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.testutil.ItemBuilder;

public class MenuTest {
    private final Menu menu = new Menu();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), menu.getItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMenu_replacesData() {
        Menu newData = new Menu();
        menu.resetData(newData);
        assertEquals(newData, menu);
    }

    @Test
    public void resetData_withDuplicateItems_throwsDuplicateItemException() {
        // Two items with the same identity fields
        Item editedFriedRice1 = new ItemBuilder(FRIED_RICE).withPrice(VALID_ITEM_PRICE_2).build();
        Item editedFriedRice2 = new ItemBuilder(FRIED_RICE).withPrice(VALID_ITEM_PRICE_2).build();
        List<Item> newItems = Arrays.asList(editedFriedRice1, editedFriedRice2);
        MenuStub newData = new MenuStub(newItems);

        assertThrows(DuplicateItemException.class, () -> menu.resetData(newData));
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> menu.hasItem(null));
    }

    @Test
    public void hasItem_itemNotInMenu_returnsFalse() {
        assertFalse(menu.hasItem(FRIED_RICE));
    }

    @Test
    public void hasItem_itemInMenu_returnsTrue() {
        menu.addItem(FRIED_RICE);
        assertTrue(menu.hasItem(FRIED_RICE));
    }

    @Test
    public void hasItem_itemWithSameIdentityFieldsInMenu_returnsFalse() {
        menu.addItem(FRIED_RICE);
        Item editedFriedRice = new ItemBuilder(FRIED_RICE).withName("Chicken Rice").build();
        assertFalse(menu.hasItem(editedFriedRice));
    }

    @Test
    public void getItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> menu.getItemList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = Menu.class.getCanonicalName() + "{items=" + menu.getItemList() + "}";
        assertEquals(expected, menu.toString());
    }


    /**
     * A stub ReadOnlyMenu whose items list can violate interface constraints.
     */
    private static class MenuStub implements ReadOnlyMenu {
        private final ObservableList<Item> items = FXCollections.observableArrayList();

        MenuStub(Collection<Item> items) {
            this.items.setAll(items);
        }

        @Override
        public ObservableList<Item> getItemList() {
            return items;
        }
    }
}
