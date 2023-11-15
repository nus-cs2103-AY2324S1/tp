package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.BUTTER;
import static seedu.address.testutil.TypicalIngredients.FLOUR;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.ingredient.exceptions.DuplicateIngredientException;
//import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;
import seedu.address.testutil.IngredientBuilder;

public class UniqueIngredientListTest {

    private final UniqueIngredientList uniqueIngredientList = new UniqueIngredientList();

    @Test
    public void contains_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.contains(null));
    }

    @Test
    public void contains_ingredientNotInList_returnsFalse() {
        assertFalse(uniqueIngredientList.contains(FLOUR));
    }

    @Test
    public void contains_ingredientInList_returnsTrue() {
        uniqueIngredientList.add(FLOUR);
        assertTrue(uniqueIngredientList.contains(FLOUR));
    }

    @Test
    public void contains_ingredientWithSameIdentityFieldsInList_returnsTrue() {
        uniqueIngredientList.add(FLOUR);
        Ingredient editedAlice = new IngredientBuilder(FLOUR)
                .build();
        assertTrue(uniqueIngredientList.contains(editedAlice));
    }

    @Test
    public void add_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.add(null));
    }

    /* No longer in use as add just adds on
    @Test
    public void add_duplicateIngredient_throwsDuplicateIngredientException() {
        uniqueIngredientList.add(FLOUR);
        assertThrows(DuplicateIngredientException.class, () -> uniqueIngredientList.add(FLOUR));
    }

    @Test
    public void setIngredient_nullTargetIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredient(null, ALICE));
    }

    @Test
    public void setIngredient_nullEditedIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredient(ALICE, null));
    }

    @Test
    public void setIngredient_targetIngredientNotInList_throwsIngredientNotFoundException() {
        assertThrows(IngredientNotFoundException.class, () -> uniqueIngredientList.setIngredient(ALICE, ALICE));
    }

    @Test
    public void setIngredient_editedIngredientIsSameIngredient_success() {
        uniqueIngredientList.add(ALICE);
        uniqueIngredientList.setIngredient(ALICE, ALICE);
        UniqueIngredientList expecteduniqueIngredientList = new UniqueIngredientList();
        expecteduniqueIngredientList.add(ALICE);
        assertEquals(expecteduniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_editedIngredientHasSameIdentity_success() {
        uniqueIngredientList.add(ALICE);
        Ingredient editedAlice = new IngredientBuilder(ALICE)
                .build();
        uniqueIngredientList.setIngredient(ALICE, editedAlice);
        UniqueIngredientList expecteduniqueIngredientList = new UniqueIngredientList();
        expecteduniqueIngredientList.add(editedAlice);
        assertEquals(expecteduniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_editedIngredientHasDifferentIdentity_success() {
        uniqueIngredientList.add(ALICE);
        uniqueIngredientList.setIngredient(ALICE, BUTTER);
        UniqueIngredientList expecteduniqueIngredientList = new UniqueIngredientList();
        expecteduniqueIngredientList.add(BUTTER);
        assertEquals(expecteduniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_editedIngredientHasNonUniqueIdentity_throwsDuplicateIngredientException() {
        uniqueIngredientList.add(ALICE);
        uniqueIngredientList.add(BUTTER);
        assertThrows(DuplicateIngredientException.class, () -> uniqueIngredientList.setIngredient(ALICE, BUTTER));
    }
    */
    @Test
    public void remove_existingIngredient_removesIngredient() {
        uniqueIngredientList.add(FLOUR);
        uniqueIngredientList.remove(FLOUR);
        UniqueIngredientList expecteduniqueIngredientList = new UniqueIngredientList();
        assertEquals(expecteduniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredients_nulluniqueIngredientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueIngredientList.setIngredients((UniqueIngredientList) null));
    }

    @Test
    public void setIngredients_uniqueIngredientList_replacesOwnListWithProvideduniqueIngredientList() {
        uniqueIngredientList.add(FLOUR);
        UniqueIngredientList expecteduniqueIngredientList = new UniqueIngredientList();
        expecteduniqueIngredientList.add(BUTTER);
        uniqueIngredientList.setIngredients(expecteduniqueIngredientList);
        assertEquals(expecteduniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredients((List<Ingredient>) null));
    }

    @Test
    public void setIngredients_list_replacesOwnListWithProvidedList() {
        uniqueIngredientList.add(FLOUR);
        List<Ingredient> ingredientList = Collections.singletonList(BUTTER);
        uniqueIngredientList.setIngredients(ingredientList);
        UniqueIngredientList expecteduniqueIngredientList = new UniqueIngredientList();
        expecteduniqueIngredientList.add(BUTTER);
        assertEquals(expecteduniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredients_listWithDuplicateIngredients_throwsDuplicateIngredientException() {
        List<Ingredient> listWithDuplicateIngredients = Arrays.asList(FLOUR, FLOUR);
        assertThrows(DuplicateIngredientException.class, () ->
                uniqueIngredientList.setIngredients(listWithDuplicateIngredients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueIngredientList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueIngredientList.asUnmodifiableObservableList().toString(), uniqueIngredientList.toString());
    }
}
