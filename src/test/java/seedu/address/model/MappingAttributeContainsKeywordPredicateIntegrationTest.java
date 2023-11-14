package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.COMP3000;
import static seedu.address.testutil.TypicalObjects.CS1231S;
import static seedu.address.testutil.TypicalObjects.CS1231S_TO_EDINBURGH_COMP1000;
import static seedu.address.testutil.TypicalObjects.CS2030S;
import static seedu.address.testutil.TypicalObjects.CS2030S_TO_ZURICH_COMP3000;
import static seedu.address.testutil.TypicalObjects.CS2040S;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.CS3230;
import static seedu.address.testutil.TypicalObjects.CS3230S_TO_STANFORD_STAN3230;
import static seedu.address.testutil.TypicalObjects.S0402SC;
import static seedu.address.testutil.TypicalObjects.STAN3230;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.MappingAttribute;
import seedu.address.model.mapping.MappingAttributeContainsKeywordPredicate;
import seedu.address.model.partnercourse.PartnerCourse;

public class MappingAttributeContainsKeywordPredicateIntegrationTest {

    private SeplendidModelManager modelManager = new SeplendidModelManager();

    @BeforeEach
    public void setUp() {
        List<Mapping> mappings = List.of(CS2040S_TO_NTU_S0402SC,
                CS3230S_TO_STANFORD_STAN3230,
                CS1231S_TO_EDINBURGH_COMP1000,
                CS2030S_TO_ZURICH_COMP3000);
        MappingCatalogue mappingCatalogue = new MappingCatalogue();
        mappingCatalogue.setMappings(mappings);
        modelManager.setMappingCatalogue(mappingCatalogue);

        List<LocalCourse> localCourses = List.of(CS2040S, CS3230, CS1231S, CS2030S);
        LocalCourseCatalogue localCourseCatalogue = new LocalCourseCatalogue();
        localCourseCatalogue.setLocalCourses(localCourses);
        modelManager.setLocalCourseCatalogue(localCourseCatalogue);

        List<PartnerCourse> partnerCourses = List.of(S0402SC, STAN3230, COMP1000, COMP3000);
        PartnerCourseCatalogue partnerCourseCatalogue = new PartnerCourseCatalogue();
        partnerCourseCatalogue.setPartnerCourses(partnerCourses);
        modelManager.setPartnerCourseCatalogue(partnerCourseCatalogue);
    }

    @Test
    public void test_searchAttributeByLocalCode_filteredListHasMappings() {
        MappingAttributeContainsKeywordPredicate predicateForOneMapping =
                new MappingAttributeContainsKeywordPredicate("CS2040S", MappingAttribute.LOCALCODE);
        modelManager.updateFilteredMappingList(predicateForOneMapping);
        ObservableList<Mapping> filteredListForOneMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForOneMappingPredicate = new Mapping[] {CS2040S_TO_NTU_S0402SC};
        assertArrayEquals(expectedArrayForOneMappingPredicate, filteredListForOneMappingPredicate.toArray());

        MappingAttributeContainsKeywordPredicate predicateForTwoMappings =
                new MappingAttributeContainsKeywordPredicate("CS20", MappingAttribute.LOCALCODE);
        modelManager.updateFilteredMappingList(predicateForTwoMappings);
        ObservableList<Mapping> filteredListForTwoMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForTwoMappingPredicate = new Mapping[] {CS2040S_TO_NTU_S0402SC,
                                                                       CS2030S_TO_ZURICH_COMP3000};
        assertArrayEquals(expectedArrayForTwoMappingPredicate, filteredListForTwoMappingPredicate.toArray());

        MappingAttributeContainsKeywordPredicate predicateForFourMappings =
                new MappingAttributeContainsKeywordPredicate("CS", MappingAttribute.LOCALCODE);
        modelManager.updateFilteredMappingList(predicateForFourMappings);
        ObservableList<Mapping> filteredListForFourMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForFourMappingPredicate = new Mapping[] {CS2040S_TO_NTU_S0402SC,
                                                                        CS3230S_TO_STANFORD_STAN3230,
                                                                        CS1231S_TO_EDINBURGH_COMP1000,
                                                                        CS2030S_TO_ZURICH_COMP3000};
        assertArrayEquals(expectedArrayForFourMappingPredicate, filteredListForFourMappingPredicate.toArray());
    }

    @Test
    public void test_searchAttributeByLocalName_filteredListHasMappings() {
        MappingAttributeContainsKeywordPredicate predicateForOneMapping =
                new MappingAttributeContainsKeywordPredicate("Discrete", MappingAttribute.LOCALNAME);

        predicateForOneMapping.initialiseGetLocalPartnercourse(modelManager::getLocalCourseIfExists,
                modelManager::getPartnerCourseIfExists);

        modelManager.updateFilteredMappingList(predicateForOneMapping);
        ObservableList<Mapping> filteredListForOneMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForOneMappingPredicate = new Mapping[] {CS1231S_TO_EDINBURGH_COMP1000};
        assertArrayEquals(expectedArrayForOneMappingPredicate, filteredListForOneMappingPredicate.toArray());

        MappingAttributeContainsKeywordPredicate predicateForTwoMappings =
                new MappingAttributeContainsKeywordPredicate("Algorithms", MappingAttribute.LOCALNAME);

        predicateForTwoMappings.initialiseGetLocalPartnercourse(modelManager::getLocalCourseIfExists,
                modelManager::getPartnerCourseIfExists);

        modelManager.updateFilteredMappingList(predicateForTwoMappings);
        ObservableList<Mapping> filteredListForTwoMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForTwoMappingPredicate = new Mapping[] {CS2040S_TO_NTU_S0402SC,
                                                                       CS3230S_TO_STANFORD_STAN3230};
        assertArrayEquals(expectedArrayForTwoMappingPredicate, filteredListForTwoMappingPredicate.toArray());

        MappingAttributeContainsKeywordPredicate predicateForFourMappings =
                new MappingAttributeContainsKeywordPredicate("i", MappingAttribute.LOCALNAME);

        predicateForFourMappings.initialiseGetLocalPartnercourse(modelManager::getLocalCourseIfExists,
                modelManager::getPartnerCourseIfExists);

        modelManager.updateFilteredMappingList(predicateForFourMappings);
        ObservableList<Mapping> filteredListForFourMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForFourMappingPredicate = new Mapping[] {CS2040S_TO_NTU_S0402SC,
                                                                        CS3230S_TO_STANFORD_STAN3230,
                                                                        CS1231S_TO_EDINBURGH_COMP1000,
                                                                        CS2030S_TO_ZURICH_COMP3000};
        assertArrayEquals(expectedArrayForFourMappingPredicate, filteredListForFourMappingPredicate.toArray());
    }

    @Test
    public void test_searchAttributeByUniversityName_filteredListHasMappings() {
        MappingAttributeContainsKeywordPredicate predicateForOneMapping =
                new MappingAttributeContainsKeywordPredicate("Edinburgh", MappingAttribute.UNIVERSITY);
        modelManager.updateFilteredMappingList(predicateForOneMapping);
        ObservableList<Mapping> filteredListForOneMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForOneMappingPredicate = new Mapping[] {CS1231S_TO_EDINBURGH_COMP1000};
        assertArrayEquals(expectedArrayForOneMappingPredicate, filteredListForOneMappingPredicate.toArray());

        MappingAttributeContainsKeywordPredicate predicateForFourMappings =
                new MappingAttributeContainsKeywordPredicate("University", MappingAttribute.UNIVERSITY);
        modelManager.updateFilteredMappingList(predicateForFourMappings);
        ObservableList<Mapping> filteredListForFourMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForFourMappingPredicate = new Mapping[] {CS2040S_TO_NTU_S0402SC,
                                                                        CS3230S_TO_STANFORD_STAN3230,
                                                                        CS1231S_TO_EDINBURGH_COMP1000,
                                                                        CS2030S_TO_ZURICH_COMP3000};
        assertArrayEquals(expectedArrayForFourMappingPredicate, filteredListForFourMappingPredicate.toArray());
    }

    @Test
    public void test_searchAttributeByInformation_filteredListHasMappings() {
        MappingAttributeContainsKeywordPredicate predicateForFourMappings =
                new MappingAttributeContainsKeywordPredicate("NIL", MappingAttribute.INFORMATION);
        modelManager.updateFilteredMappingList(predicateForFourMappings);
        ObservableList<Mapping> filteredListForFourMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForFourMappingPredicate = new Mapping[] {CS2040S_TO_NTU_S0402SC,
                                                                        CS3230S_TO_STANFORD_STAN3230,
                                                                        CS1231S_TO_EDINBURGH_COMP1000,
                                                                        CS2030S_TO_ZURICH_COMP3000};
        assertArrayEquals(expectedArrayForFourMappingPredicate, filteredListForFourMappingPredicate.toArray());
    }

    @Test
    public void test_searchAttributeByPartnerCode_filteredListHasMappings() {
        MappingAttributeContainsKeywordPredicate predicateForOneMapping =
                new MappingAttributeContainsKeywordPredicate("COMP1000", MappingAttribute.PARTNERCODE);
        modelManager.updateFilteredMappingList(predicateForOneMapping);
        ObservableList<Mapping> filteredListForOneMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForOneMappingPredicate = new Mapping[] {CS1231S_TO_EDINBURGH_COMP1000};
        assertArrayEquals(expectedArrayForOneMappingPredicate, filteredListForOneMappingPredicate.toArray());

        MappingAttributeContainsKeywordPredicate predicateForTwoMappings =
                new MappingAttributeContainsKeywordPredicate("comp", MappingAttribute.PARTNERCODE);
        modelManager.updateFilteredMappingList(predicateForTwoMappings);
        ObservableList<Mapping> filteredListForTwoMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForTwoMappingPredicate = new Mapping[] {CS1231S_TO_EDINBURGH_COMP1000,
                                                                       CS2030S_TO_ZURICH_COMP3000};
        assertArrayEquals(expectedArrayForTwoMappingPredicate, filteredListForTwoMappingPredicate.toArray());

        MappingAttributeContainsKeywordPredicate predicateForFourMappings =
                new MappingAttributeContainsKeywordPredicate("0", MappingAttribute.PARTNERCODE);
        modelManager.updateFilteredMappingList(predicateForFourMappings);
        ObservableList<Mapping> filteredListForFourMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForFourMappingPredicate = new Mapping[] {CS2040S_TO_NTU_S0402SC,
                                                                        CS3230S_TO_STANFORD_STAN3230,
                                                                        CS1231S_TO_EDINBURGH_COMP1000,
                                                                        CS2030S_TO_ZURICH_COMP3000};
        assertArrayEquals(expectedArrayForFourMappingPredicate, filteredListForFourMappingPredicate.toArray());
    }

    @Test
    public void test_searchAttributeByPartnerName_filteredListHasMappings() {
        MappingAttributeContainsKeywordPredicate predicateForOneMapping =
                new MappingAttributeContainsKeywordPredicate("Programming", MappingAttribute.PARTNERNAME);

        predicateForOneMapping.initialiseGetLocalPartnercourse(modelManager::getLocalCourseIfExists,
                modelManager::getPartnerCourseIfExists);

        modelManager.updateFilteredMappingList(predicateForOneMapping);
        ObservableList<Mapping> filteredListForOneMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForOneMappingPredicate = new Mapping[] {CS1231S_TO_EDINBURGH_COMP1000};
        assertArrayEquals(expectedArrayForOneMappingPredicate, filteredListForOneMappingPredicate.toArray());

        MappingAttributeContainsKeywordPredicate predicateForTwoMappings =
                new MappingAttributeContainsKeywordPredicate("Algorithms", MappingAttribute.PARTNERNAME);

        predicateForTwoMappings.initialiseGetLocalPartnercourse(modelManager::getLocalCourseIfExists,
                modelManager::getPartnerCourseIfExists);

        modelManager.updateFilteredMappingList(predicateForTwoMappings);
        ObservableList<Mapping> filteredListForTwoMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForTwoMappingPredicate = new Mapping[] {CS2040S_TO_NTU_S0402SC,
                                                                       CS3230S_TO_STANFORD_STAN3230};
        assertArrayEquals(expectedArrayForTwoMappingPredicate, filteredListForTwoMappingPredicate.toArray());

        MappingAttributeContainsKeywordPredicate predicateForFourMappings =
                new MappingAttributeContainsKeywordPredicate("i", MappingAttribute.PARTNERNAME);

        predicateForFourMappings.initialiseGetLocalPartnercourse(modelManager::getLocalCourseIfExists,
                modelManager::getPartnerCourseIfExists);

        modelManager.updateFilteredMappingList(predicateForFourMappings);
        ObservableList<Mapping> filteredListForThreeMappingPredicate = modelManager.getFilteredMappingList();
        Mapping[] expectedArrayForThreeMappingPredicate = new Mapping[] {CS2040S_TO_NTU_S0402SC,
                                                                         CS3230S_TO_STANFORD_STAN3230,
                                                                         CS1231S_TO_EDINBURGH_COMP1000,
                                                                         CS2030S_TO_ZURICH_COMP3000};
        assertArrayEquals(expectedArrayForThreeMappingPredicate, filteredListForThreeMappingPredicate.toArray());
    }

}
