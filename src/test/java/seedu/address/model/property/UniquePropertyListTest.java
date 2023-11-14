package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PRICE_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_TAG_BIG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProperties.AQUAVISTA;
import static seedu.address.testutil.TypicalProperties.SKYVIEW;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;
import seedu.address.testutil.PropertyBuilder;

public class UniquePropertyListTest {

    private final UniquePropertyList uniquePropertyList = new UniquePropertyList();

    @Test
    public void contains_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.contains(null));
    }

    @Test
    public void contains_propertyNotInList_returnsFalse() {
        assertFalse(uniquePropertyList.contains(AQUAVISTA));
    }

    @Test
    public void contains_propertyInList_returnsTrue() {
        uniquePropertyList.add(AQUAVISTA);
        assertTrue(uniquePropertyList.contains(AQUAVISTA));
    }

    @Test
    public void contains_propertyWithSameIdentityFieldsInList_returnsTrue() {
        uniquePropertyList.add(AQUAVISTA);
        Property editedAuqavista = new PropertyBuilder(AQUAVISTA).withPrice(VALID_PRICE_SKYVIEW).withTags(VALID_TAG_BIG)
                .build();
        assertTrue(uniquePropertyList.contains(editedAuqavista));
    }

    @Test
    public void add_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.add(null));
    }

    @Test
    public void add_duplicateProperty_throwsDuplicatePropertyException() {
        uniquePropertyList.add(AQUAVISTA);
        assertThrows(DuplicatePropertyException.class, () -> uniquePropertyList.add(AQUAVISTA));
    }

    @Test
    public void setProperty_nullTargetProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperty(null, AQUAVISTA));
    }

    @Test
    public void setProperty_nullEditedProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperty(AQUAVISTA, null));
    }

    @Test
    public void setProperty_targetPropertyNotInList_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> uniquePropertyList.setProperty(AQUAVISTA, AQUAVISTA));
    }

    @Test
    public void setProperty_editedPropertyIsSameProperty_success() {
        uniquePropertyList.add(AQUAVISTA);
        uniquePropertyList.setProperty(AQUAVISTA, AQUAVISTA);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(AQUAVISTA);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasSameIdentity_success() {
        uniquePropertyList.add(AQUAVISTA);
        Property editedAquavista = new PropertyBuilder(AQUAVISTA).withPrice(VALID_PRICE_SKYVIEW).withTags(VALID_TAG_BIG)
                .build();
        uniquePropertyList.setProperty(AQUAVISTA, editedAquavista);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(editedAquavista);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasDifferentIdentity_success() {
        uniquePropertyList.add(AQUAVISTA);
        uniquePropertyList.setProperty(AQUAVISTA, SKYVIEW);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(SKYVIEW);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasNonUniqueIdentity_throwsDuplicatePropertyException() {
        uniquePropertyList.add(AQUAVISTA);
        uniquePropertyList.add(SKYVIEW);
        assertThrows(DuplicatePropertyException.class, () -> uniquePropertyList.setProperty(AQUAVISTA, SKYVIEW));
    }

    @Test
    public void remove_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.remove(null));
    }

    @Test
    public void remove_propertyDoesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> uniquePropertyList.remove(AQUAVISTA));
    }

    @Test
    public void remove_existingProperty_removesProperty() {
        uniquePropertyList.add(AQUAVISTA);
        uniquePropertyList.remove(AQUAVISTA);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_nullUniquePropertyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperties((UniquePropertyList) null));
    }

    @Test
    public void setProperty_uniquePropertyList_replacesOwnListWithProvidedUniquePropertyList() {
        uniquePropertyList.add(AQUAVISTA);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(SKYVIEW);
        uniquePropertyList.setProperties(expectedUniquePropertyList);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperties((List<Property>) null));
    }

    @Test
    public void setProperties_list_replacesOwnListWithProvidedList() {
        uniquePropertyList.add(AQUAVISTA);
        List<Property> propertyList = Collections.singletonList(SKYVIEW);
        uniquePropertyList.setProperties(propertyList);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(SKYVIEW);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperties_listWithDuplicateProperties_throwsDuplicatePropertyException() {
        List<Property> listWithDuplicateProperties = Arrays.asList(AQUAVISTA, AQUAVISTA);
        assertThrows(DuplicatePropertyException.class, ()
                -> uniquePropertyList.setProperties(listWithDuplicateProperties));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePropertyList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniquePropertyList.asUnmodifiableObservableList().toString(), uniquePropertyList.toString());
    }
}
