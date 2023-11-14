package seedu.address.logic.commands.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.CS1231S_TO_EDINBURGH_COMP1000;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.CS3230S_TO_STANFORD_STAN3230;
import static seedu.address.testutil.TypicalObjects.MA1521_TO_SNU_COMP4000;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogueForMappingTests;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogueForMappingTests;
import static seedu.address.testutil.TypicalObjects.getTypicalNoteCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogueForMappingTests;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.MappingAttribute;
import seedu.address.model.mapping.MappingComparatorByAttribute;

//@@author lamchenghou

public class MappingSortCommandTest {
    private SeplendidModel model;
    private SeplendidModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new SeplendidModelManager(new UserPrefs(),
                getTypicalLocalCourseCatalogueForMappingTests(),
                getTypicalPartnerCourseCatalogueForMappingTests(),
                getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogueForMappingTests(),
                getTypicalNoteCatalogue());
        expectedModel = new SeplendidModelManager(new UserPrefs(),
                getTypicalLocalCourseCatalogueForMappingTests(),
                getTypicalPartnerCourseCatalogueForMappingTests(),
                getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogueForMappingTests(),
                getTypicalNoteCatalogue());
    }

    @Test
    public void execute_sortByLocalCode_success() {
        MappingComparatorByAttribute comparator = new MappingComparatorByAttribute(MappingAttribute.LOCALCODE);
        MappingSortCommand mappingSortCommand = new MappingSortCommand(comparator);

        expectedModel.updateSortedMappingList(comparator);

        assertSeplendidCommandSuccess(mappingSortCommand,
                model,
                String.format(MappingSortCommand.MESSAGE_SUCCESS, MappingAttribute.LOCALCODE.toString().toLowerCase()),
                expectedModel);

        List<Mapping> sortedList = new ArrayList<>();
        sortedList.add(CS1231S_TO_EDINBURGH_COMP1000);
        sortedList.add(CS2040S_TO_NTU_S0402SC);
        sortedList.add(CS3230S_TO_STANFORD_STAN3230);
        sortedList.add(MA1521_TO_SNU_COMP4000);

        assertEquals(sortedList, model.getSortedMappingList());
    }

    @Test
    public void execute_sortByLocalName_success() {
        MappingComparatorByAttribute comparator = new MappingComparatorByAttribute(MappingAttribute.LOCALNAME);

        comparator.initialiseGetLocalPartnerCourse(model::getLocalCourseIfExists,
                model::getPartnerCourseIfExists);

        MappingSortCommand mappingSortCommand = new MappingSortCommand(comparator);

        expectedModel.updateSortedMappingList(comparator);

        assertSeplendidCommandSuccess(mappingSortCommand,
                model,
                String.format(MappingSortCommand.MESSAGE_SUCCESS, MappingAttribute.LOCALNAME.toString().toLowerCase()),
                expectedModel);

        List<Mapping> sortedList = new ArrayList<>();
        sortedList.add(MA1521_TO_SNU_COMP4000);
        sortedList.add(CS2040S_TO_NTU_S0402SC);
        sortedList.add(CS3230S_TO_STANFORD_STAN3230);
        sortedList.add(CS1231S_TO_EDINBURGH_COMP1000);

        assertEquals(sortedList, model.getSortedMappingList());
    }

    @Test
    public void execute_sortByPartnerCode_success() {
        MappingComparatorByAttribute comparator = new MappingComparatorByAttribute(MappingAttribute.PARTNERCODE);
        MappingSortCommand mappingSortCommand = new MappingSortCommand(comparator);

        expectedModel.updateSortedMappingList(comparator);

        assertSeplendidCommandSuccess(mappingSortCommand,
                model,
                String.format(MappingSortCommand.MESSAGE_SUCCESS,
                        MappingAttribute.PARTNERCODE.toString().toLowerCase()),
                expectedModel);

        List<Mapping> sortedList = new ArrayList<>();
        sortedList.add(CS1231S_TO_EDINBURGH_COMP1000);
        sortedList.add(MA1521_TO_SNU_COMP4000);
        sortedList.add(CS2040S_TO_NTU_S0402SC);
        sortedList.add(CS3230S_TO_STANFORD_STAN3230);

        assertEquals(sortedList, model.getSortedMappingList());
    }

    @Test
    public void execute_sortByPartnerName_success() {
        MappingComparatorByAttribute comparator = new MappingComparatorByAttribute(MappingAttribute.PARTNERNAME);

        comparator.initialiseGetLocalPartnerCourse(model::getLocalCourseIfExists,
                model::getPartnerCourseIfExists);

        MappingSortCommand mappingSortCommand = new MappingSortCommand(comparator);

        expectedModel.updateSortedMappingList(comparator);

        assertSeplendidCommandSuccess(mappingSortCommand,
                model,
                String.format(MappingSortCommand.MESSAGE_SUCCESS,
                        MappingAttribute.PARTNERNAME.toString().toLowerCase()),
                expectedModel);

        List<Mapping> sortedList = new ArrayList<>();
        sortedList.add(CS2040S_TO_NTU_S0402SC);
        sortedList.add(CS3230S_TO_STANFORD_STAN3230);
        sortedList.add(MA1521_TO_SNU_COMP4000);
        sortedList.add(CS1231S_TO_EDINBURGH_COMP1000);

        assertEquals(sortedList, model.getSortedMappingList());
    }

    @Test
    public void execute_sortByUniversity_success() {
        MappingComparatorByAttribute comparator = new MappingComparatorByAttribute(MappingAttribute.UNIVERSITY);
        MappingSortCommand mappingSortCommand = new MappingSortCommand(comparator);

        expectedModel.updateSortedMappingList(comparator);

        assertSeplendidCommandSuccess(mappingSortCommand,
                model,
                String.format(MappingSortCommand.MESSAGE_SUCCESS, MappingAttribute.UNIVERSITY.toString().toLowerCase()),
                expectedModel);

        List<Mapping> sortedList = new ArrayList<>();
        sortedList.add(CS2040S_TO_NTU_S0402SC);
        sortedList.add(MA1521_TO_SNU_COMP4000);
        sortedList.add(CS3230S_TO_STANFORD_STAN3230);
        sortedList.add(CS1231S_TO_EDINBURGH_COMP1000);

        assertEquals(sortedList, model.getSortedMappingList());
    }

    @Test
    public void execute_sortByInformation_success() {
        MappingComparatorByAttribute comparator = new MappingComparatorByAttribute(MappingAttribute.INFORMATION);
        MappingSortCommand mappingSortCommand = new MappingSortCommand(comparator);

        expectedModel.updateSortedMappingList(comparator);

        assertSeplendidCommandSuccess(mappingSortCommand,
                model,
                String.format(MappingSortCommand.MESSAGE_SUCCESS,
                        MappingAttribute.INFORMATION.toString().toLowerCase()),
                expectedModel);

        List<Mapping> sortedList = new ArrayList<>();
        sortedList.add(MA1521_TO_SNU_COMP4000);
        sortedList.add(CS3230S_TO_STANFORD_STAN3230);
        sortedList.add(CS2040S_TO_NTU_S0402SC);
        sortedList.add(CS1231S_TO_EDINBURGH_COMP1000);

        assertEquals(sortedList, model.getSortedMappingList());
    }

    @Test
    public void equals() {
        MappingComparatorByAttribute mappingComparatorByLocalCode =
                new MappingComparatorByAttribute(MappingAttribute.LOCALCODE);
        MappingComparatorByAttribute mappingComparatorByLocalName =
                new MappingComparatorByAttribute(MappingAttribute.LOCALNAME);
        MappingComparatorByAttribute mappingComparatorByPartnerCode =
                new MappingComparatorByAttribute(MappingAttribute.PARTNERCODE);
        MappingComparatorByAttribute mappingComparatorByPartnerName =
                new MappingComparatorByAttribute(MappingAttribute.PARTNERNAME);
        MappingComparatorByAttribute mappingComparatorByUniversity =
                new MappingComparatorByAttribute(MappingAttribute.UNIVERSITY);
        MappingComparatorByAttribute mappingComparatorByInformation =
                new MappingComparatorByAttribute(MappingAttribute.INFORMATION);

        MappingSortCommand mappingSortCommandByLocalCode = new MappingSortCommand(mappingComparatorByLocalCode);
        MappingSortCommand mappingSortCommandByLocalName = new MappingSortCommand(mappingComparatorByLocalName);
        MappingSortCommand mappingSortCommandByPartnerCode = new MappingSortCommand(mappingComparatorByPartnerCode);
        MappingSortCommand mappingSortCommandByPartnerName = new MappingSortCommand(mappingComparatorByPartnerName);
        MappingSortCommand mappingSortCommandByUniversity = new MappingSortCommand(mappingComparatorByUniversity);
        MappingSortCommand mappingSortCommandByInformation = new MappingSortCommand(mappingComparatorByInformation);

        // same object -> returns true
        assertEquals(mappingSortCommandByLocalCode, mappingSortCommandByLocalCode);
        assertEquals(mappingSortCommandByLocalName, mappingSortCommandByLocalName);
        assertEquals(mappingSortCommandByPartnerCode, mappingSortCommandByPartnerCode);
        assertEquals(mappingSortCommandByPartnerName, mappingSortCommandByPartnerName);
        assertEquals(mappingSortCommandByUniversity, mappingSortCommandByUniversity);
        assertEquals(mappingSortCommandByInformation, mappingSortCommandByInformation);

        // same values -> returns true
        MappingSortCommand mappingSortCommandByLocalCodeCopy = new MappingSortCommand(
                mappingComparatorByLocalCode);
        assertEquals(mappingSortCommandByLocalCode, mappingSortCommandByLocalCodeCopy);

        MappingSortCommand mappingSortCommandByLocalNameCopy = new MappingSortCommand(
                mappingComparatorByLocalName);
        assertEquals(mappingSortCommandByLocalName, mappingSortCommandByLocalNameCopy);
        MappingSortCommand mappingSortCommandByPartnerCodeCopy = new MappingSortCommand(
                mappingComparatorByPartnerCode);
        assertEquals(mappingSortCommandByPartnerCode, mappingSortCommandByPartnerCodeCopy);
        MappingSortCommand mappingSortCommandByPartnerNameCopy = new MappingSortCommand(
                mappingComparatorByPartnerName);
        assertEquals(mappingSortCommandByPartnerName, mappingSortCommandByPartnerNameCopy);
        MappingSortCommand mappingSortCommandByUniversityCopy = new MappingSortCommand(
                mappingComparatorByUniversity);
        assertEquals(mappingSortCommandByUniversity, mappingSortCommandByUniversityCopy);
        MappingSortCommand mappingSortCommandByInformationCopy = new MappingSortCommand(
                mappingComparatorByInformation);
        assertEquals(mappingSortCommandByInformation, mappingSortCommandByInformationCopy);

        // different types -> returns false
        assertNotEquals(1, mappingSortCommandByLocalCode);
        assertNotEquals(4.12, mappingSortCommandByLocalName);
        assertNotEquals(1.234, mappingSortCommandByPartnerCode);
        assertNotEquals(4.95, mappingSortCommandByPartnerName);
        assertNotEquals(4.99, mappingSortCommandByUniversity);
        assertNotEquals(5.0, mappingSortCommandByInformation);

        // null -> returns false
        assertNotEquals(null, mappingSortCommandByLocalCode);
        assertNotEquals(null, mappingSortCommandByLocalName);
        assertNotEquals(null, mappingSortCommandByPartnerCode);
        assertNotEquals(null, mappingSortCommandByPartnerName);
        assertNotEquals(null, mappingSortCommandByUniversity);
        assertNotEquals(null, mappingSortCommandByInformation);

        // different task -> returns false
        assertNotEquals(mappingSortCommandByLocalCode, mappingSortCommandByLocalName);
        assertNotEquals(mappingSortCommandByLocalName, mappingSortCommandByPartnerCode);
        assertNotEquals(mappingSortCommandByPartnerCode, mappingSortCommandByPartnerName);
        assertNotEquals(mappingSortCommandByPartnerName, mappingSortCommandByUniversity);
        assertNotEquals(mappingSortCommandByUniversity, mappingSortCommandByInformation);

        // different command -> returns false
        assertFalse(new MappingSortCommand(new MappingComparatorByAttribute(MappingAttribute.LOCALCODE))
                .equals(new MappingListCommand()));
    }

    @Test
    public void toStringMethod() {
        MappingComparatorByAttribute comparator = new
                MappingComparatorByAttribute(MappingAttribute.LOCALCODE);
        MappingSortCommand mappingSearchCommand = new MappingSortCommand(comparator);
        String expected = MappingSortCommand.class.getCanonicalName()
                + "{mappingComparator=" + comparator + "}";
        assertEquals(expected, mappingSearchCommand.toString());
    }
}
