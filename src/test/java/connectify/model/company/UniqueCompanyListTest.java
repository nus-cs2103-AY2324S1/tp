package connectify.model.company;

import static connectify.testutil.Assert.assertThrows;
import static connectify.testutil.TypicalCompanies.COMPANY_1;
import static connectify.testutil.TypicalCompanies.COMPANY_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import connectify.model.company.exceptions.CompanyNotFoundException;
import connectify.model.company.exceptions.DuplicateCompanyException;

public class UniqueCompanyListTest {

    private final UniqueCompanyList uniqueCompanyList = new UniqueCompanyList();

    /**
     * Tests that checking containment of a null company throws a {@code NullPointerException}.
     */
    @Test
    public void contains_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.contains(null));
    }

    /**
     * Tests that checking containment of a company not in the list returns false.
     */
    @Test
    public void contains_companyNotInList_returnsFalse() {
        assertFalse(uniqueCompanyList.contains(COMPANY_1));
    }

    /**
     * Tests that checking containment of a company in the list returns true.
     */
    @Test
    public void contains_companyInList_returnsTrue() {
        uniqueCompanyList.add(COMPANY_1);
        assertTrue(uniqueCompanyList.contains(COMPANY_1));
    }

    @Test
    public void add_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.add(null));
    }

    @Test
    public void add_duplicateCompany_throwsDuplicateCompanyException() {
        uniqueCompanyList.add(COMPANY_1);
        assertThrows(DuplicateCompanyException.class, () -> uniqueCompanyList.add(COMPANY_1));
    }

    @Test
    public void setCompany_nullTargetCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.setCompany(null, COMPANY_1));
    }

    @Test
    public void setCompany_targetCompanyNotInList_throwsCompanyNotFoundException() {
        assertThrows(CompanyNotFoundException.class, () -> uniqueCompanyList.setCompany(COMPANY_1, COMPANY_1));
    }

    @Test
    public void setCompany_editedCompanyIsSameCompany_success() {
        uniqueCompanyList.add(COMPANY_1);
        uniqueCompanyList.setCompany(COMPANY_1, COMPANY_1);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(COMPANY_1);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompany_editedCompanyHasDifferentIdentity_success() {
        uniqueCompanyList.add(COMPANY_1);
        uniqueCompanyList.setCompany(COMPANY_1, COMPANY_2);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(COMPANY_2);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompany_editedCompanyHasNonUniqueIdentity_throwsDuplicateCompanyException() {
        uniqueCompanyList.add(COMPANY_1);
        uniqueCompanyList.add(COMPANY_2);
        assertThrows(DuplicateCompanyException.class, () -> uniqueCompanyList.setCompany(COMPANY_1, COMPANY_2));
    }

    @Test
    public void remove_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.remove(null));
    }

    @Test
    public void remove_companyDoesNotExist_throwsCompanyNotFoundException() {
        assertThrows(CompanyNotFoundException.class, () -> uniqueCompanyList.remove(COMPANY_1));
    }

    @Test
    public void remove_existingCompany_removesCompany() {
        uniqueCompanyList.add(COMPANY_1);
        uniqueCompanyList.remove(COMPANY_1);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompanies_nullUniqueCompanyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.setCompanies((UniqueCompanyList) null));
    }

    @Test
    public void setCompanies_uniqueCompanyList_replacesOwnListWithProvidedUniqueCompanyList() {
        uniqueCompanyList.add(COMPANY_1);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(COMPANY_2);
        uniqueCompanyList.setCompanies(expectedUniqueCompanyList);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompanies_list_replacesOwnListWithProvidedList() {
        uniqueCompanyList.add(COMPANY_1);
        List<Company> companyList = Collections.singletonList(COMPANY_2);
        uniqueCompanyList.setCompanies(companyList);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(COMPANY_2);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompanies_listWithDuplicateCompanies_throwsDuplicateCompanyException() {
        List<Company> listWithDuplicateCompanies = Arrays.asList(COMPANY_1, COMPANY_1);
        assertThrows(DuplicateCompanyException.class, () -> uniqueCompanyList.setCompanies(listWithDuplicateCompanies));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueCompanyList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueCompanyList.asUnmodifiableObservableList().toString(), uniqueCompanyList.toString());
    }

    /**
     * Tests the iteration over the list's contents.
     */
    @Test
    public void iterator_checkContents_success() {
        uniqueCompanyList.add(COMPANY_1);
        uniqueCompanyList.add(COMPANY_2);
        Iterator<Company> iterator = uniqueCompanyList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), COMPANY_1);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), COMPANY_2);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        assertTrue(uniqueCompanyList.equals(uniqueCompanyList));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        assertFalse(uniqueCompanyList.equals(5));
    }

    @Test
    public void equals_sameData_returnsTrue() {
        uniqueCompanyList.add(COMPANY_1);
        UniqueCompanyList otherUniqueCompanyList = new UniqueCompanyList();
        otherUniqueCompanyList.add(COMPANY_1);
        assertTrue(uniqueCompanyList.equals(otherUniqueCompanyList));
    }


}
