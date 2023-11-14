package seedu.address.model.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.CS2040S;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.S0402SC;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.UniversityName;

public class MappingAttributeContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstKeyword = "pottery";
        String secondKeyword = "crocheting";

        MappingAttributeContainsKeywordPredicate firstPredicate =
                new MappingAttributeContainsKeywordPredicate(firstKeyword, MappingAttribute.LOCALNAME);
        MappingAttributeContainsKeywordPredicate secondPredicate =
                new MappingAttributeContainsKeywordPredicate(secondKeyword, MappingAttribute.LOCALNAME);
        MappingAttributeContainsKeywordPredicate thirdPredicate =
                new MappingAttributeContainsKeywordPredicate(firstKeyword, MappingAttribute.LOCALCODE);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);


        // same values -> returns true
        MappingAttributeContainsKeywordPredicate firstPredicateCopy =
                new MappingAttributeContainsKeywordPredicate(firstKeyword, MappingAttribute.LOCALNAME);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different attribute queried -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_mappingAttributeContainsKeyword_returnsTrue() {
        // Attribute queried is local name
        MappingAttributeContainsKeywordPredicate predicateForLocalName =
                new MappingAttributeContainsKeywordPredicate("Struct", MappingAttribute.LOCALNAME);
        predicateForLocalName.initialiseGetLocalPartnercourse(this::getLocalCourseIfExistsStub,
                this::getPartnerCourseIfExistsStub);
        assertTrue(predicateForLocalName.test(CS2040S_TO_NTU_S0402SC));

        // Attribute queried is local code
        MappingAttributeContainsKeywordPredicate predicateForLocalCode =
                new MappingAttributeContainsKeywordPredicate("CS", MappingAttribute.LOCALCODE);
        assertTrue(predicateForLocalCode.test(CS2040S_TO_NTU_S0402SC));

        // Attribute queried is partner name
        MappingAttributeContainsKeywordPredicate predicateForPartnerName =
                new MappingAttributeContainsKeywordPredicate("Algo", MappingAttribute.PARTNERNAME);
        predicateForPartnerName.initialiseGetLocalPartnercourse(this::getLocalCourseIfExistsStub,
                this::getPartnerCourseIfExistsStub);
        assertTrue(predicateForPartnerName.test(CS2040S_TO_NTU_S0402SC));

        // Attribute queried is partner code
        MappingAttributeContainsKeywordPredicate predicateForPartnerCode =
                new MappingAttributeContainsKeywordPredicate("0402", MappingAttribute.PARTNERCODE);
        assertTrue(predicateForPartnerCode.test(CS2040S_TO_NTU_S0402SC));

        // Attribute queried is university
        MappingAttributeContainsKeywordPredicate predicateForUniversity =
                new MappingAttributeContainsKeywordPredicate("Nanyang", MappingAttribute.UNIVERSITY);
        assertTrue(predicateForUniversity.test(CS2040S_TO_NTU_S0402SC));

        // Attribute queried is information
        MappingAttributeContainsKeywordPredicate predicateForInformation =
                new MappingAttributeContainsKeywordPredicate("NIL", MappingAttribute.INFORMATION);
        assertTrue(predicateForInformation.test(CS2040S_TO_NTU_S0402SC));

    }

    @Test
    public void test_mappingAttributeDoesNotContainKeyword_returnsFalse() {

        // Non-matching keyword
        MappingAttributeContainsKeywordPredicate predicate =
                new MappingAttributeContainsKeywordPredicate("NIL", MappingAttribute.LOCALCODE);

        assertFalse(predicate.test(CS2040S_TO_NTU_S0402SC));

    }

    @Test
    public void toStringMethod() {
        String keyword = "CS2040S";
        MappingAttributeContainsKeywordPredicate predicate =
                new MappingAttributeContainsKeywordPredicate(keyword, MappingAttribute.LOCALCODE);

        String expected =
                MappingAttributeContainsKeywordPredicate.class.getCanonicalName()
                        + "{attribute=" + MappingAttribute.LOCALCODE + ", keyword=" + keyword + "}";

        assertEquals(expected, predicate.toString());
    }

    /**
     * This is a stub to return a LocalCourse Optional given a LocalCode used in the tests.
     */
    public Optional<LocalCourse> getLocalCourseIfExistsStub(LocalCode localCode) {
        switch (localCode.getValue()) {
        case "CS2040S":
            return Optional.of(CS2040S);
        default:
            return Optional.empty();
        }

    }

    /**
     * This is a stub to return a PartnerCourse Optional given a LocalCode used in the tests.
     */
    public Optional<PartnerCourse> getPartnerCourseIfExistsStub(PartnerCode partnerCode,
                                                                UniversityName universityName) {
        if (partnerCode.equals(S0402SC.getPartnerCode())
                && universityName.equals(S0402SC.getPartnerUniversity().getUniversityName())) {
            return Optional.of(S0402SC);
        } else {
            return Optional.empty();
        }
    }
}
