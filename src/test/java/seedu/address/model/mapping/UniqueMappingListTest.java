package seedu.address.model.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.CS3230S_TO_STANFORD_STAN3230;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.mapping.exceptions.DuplicateMappingException;
import seedu.address.model.mapping.exceptions.MappingNotFoundException;
import seedu.address.testutil.MappingBuilder;

public class UniqueMappingListTest {

    private static final String OTHER_MISC_INFO = "SOME OTHER MISC INFO";

    private final UniqueMappingList uniqueMappingList = new UniqueMappingList();

    // Tests contains, add

    @Test
    public void contains_nullMapping_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMappingList.contains(null));
    }

    @Test
    public void contains_mappingNotInList_returnsFalse() {
        assertFalse(uniqueMappingList.contains(CS2040S_TO_NTU_S0402SC));
    }

    @Test
    public void contains_mappingInList_returnsTrue() {
        uniqueMappingList.add(CS2040S_TO_NTU_S0402SC);
        assertTrue(uniqueMappingList.contains(CS2040S_TO_NTU_S0402SC));
    }

    /**
     * Tests that unique identifier / primary key of Mapping
     */
    @Test
    public void contains_mappingWithSameIdentityFieldsInList_returnsTrue() {
        uniqueMappingList.add(CS2040S_TO_NTU_S0402SC);
        Mapping mappingWithSameIdentityFields =
                new MappingBuilder().withLocalCode(CS2040S_TO_NTU_S0402SC.getLocalCode().getValue())
                        .withPartnerCode(CS2040S_TO_NTU_S0402SC.getPartnerCode().getValue())
                        .withUniversityName(CS2040S_TO_NTU_S0402SC.getUniversityName().getValue())
                        .withMappingMiscInformation(OTHER_MISC_INFO).build();
        assertTrue(uniqueMappingList.contains(mappingWithSameIdentityFields));
    }

    @Test
    public void add_nullMapping_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMappingList.add(null));
    }

    @Test
    public void add_duplicateMapping_throwsDuplicateMappingException() {
        uniqueMappingList.add(CS2040S_TO_NTU_S0402SC);
        assertThrows(DuplicateMappingException.class, () -> uniqueMappingList.add(CS2040S_TO_NTU_S0402SC));
    }

    // Tests remove

    @Test
    public void remove_nullMapping_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMappingList.remove(null));
    }

    @Test
    public void remove_mappingDoesNotExist_throwsMappingNotFoundException() {
        assertThrows(MappingNotFoundException.class, () -> uniqueMappingList.remove(CS2040S_TO_NTU_S0402SC));
    }

    @Test
    public void remove_existingMapping_removesMapping() {
        uniqueMappingList.add(CS2040S_TO_NTU_S0402SC);
        uniqueMappingList.remove(CS2040S_TO_NTU_S0402SC);
        UniqueMappingList expectedUniqueMappingList = new UniqueMappingList();
        assertEquals(expectedUniqueMappingList, uniqueMappingList);
    }

    // Tests setMappings

    @Test
    public void setMappings_nullUniqueMappingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueMappingList.setMappings((UniqueMappingList) null));
    }

    @Test
    public void setMappings_uniqueMappingList_replacesOwnListWithProvidedUniqueMappingList() {
        uniqueMappingList.add(CS2040S_TO_NTU_S0402SC);
        UniqueMappingList expectedUniqueMappingList = new UniqueMappingList();
        expectedUniqueMappingList.add(CS2040S_TO_NTU_S0402SC);
        uniqueMappingList.setMappings(expectedUniqueMappingList);
        assertEquals(expectedUniqueMappingList, uniqueMappingList);
    }

    @Test
    public void setMappings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueMappingList.setMappings((List<Mapping>) null));
    }

    @Test
    public void setMappings_list_replacesOwnListWithProvidedList() {
        uniqueMappingList.add(CS3230S_TO_STANFORD_STAN3230);
        List<Mapping> mappingList = Collections.singletonList(CS2040S_TO_NTU_S0402SC);
        uniqueMappingList.setMappings(mappingList);
        UniqueMappingList expectedUniqueMappingList = new UniqueMappingList();
        expectedUniqueMappingList.add(CS2040S_TO_NTU_S0402SC);
        assertEquals(expectedUniqueMappingList, uniqueMappingList);
    }

    @Test
    public void setMappings_listWithDuplicateMappings_throwsDuplicateMappingException() {
        List<Mapping> listWithDuplicateMappings = Arrays.asList(CS2040S_TO_NTU_S0402SC, CS2040S_TO_NTU_S0402SC);
        assertThrows(DuplicateMappingException.class, () -> uniqueMappingList.setMappings(
                listWithDuplicateMappings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueMappingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueMappingList.asUnmodifiableObservableList().toString(), uniqueMappingList.toString());
    }
}
