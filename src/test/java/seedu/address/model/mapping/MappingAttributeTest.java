package seedu.address.model.mapping;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class MappingAttributeTest {
    @Test
    public void isValidMappingAttributeForSearch() {
        // invalid mappingAttribute
        assertFalse(MappingAttribute.isValidAttributeForSearch(""));
        assertFalse(MappingAttribute.isValidAttributeForSearch(" "));
        assertFalse(MappingAttribute.isValidAttributeForSearch("a"));
        assertFalse(MappingAttribute.isValidAttributeForSearch("123"));
        assertFalse(MappingAttribute.isValidAttributeForSearch("LOCAL"));
        assertFalse(MappingAttribute.isValidAttributeForSearch("localunit"));
        assertFalse(MappingAttribute.isValidAttributeForSearch(null));
        assertFalse(MappingAttribute.isValidAttributeForSearch("LOCALCODE"));
        assertFalse(MappingAttribute.isValidAttributeForSearch("LOCALNAME"));
        assertFalse(MappingAttribute.isValidAttributeForSearch("PARTNERCODE"));
        assertFalse(MappingAttribute.isValidAttributeForSearch("PARTNERNAME"));
        assertFalse(MappingAttribute.isValidAttributeForSearch("UNIVERSITY"));
        assertFalse(MappingAttribute.isValidAttributeForSearch("DESCRIPTION"));

        // valid mappingAttribute
        assertTrue(MappingAttribute.isValidAttributeForSearch("localcode"));
        assertTrue(MappingAttribute.isValidAttributeForSearch("localname"));
        assertTrue(MappingAttribute.isValidAttributeForSearch("partnercode"));
        assertTrue(MappingAttribute.isValidAttributeForSearch("partnername"));
        assertTrue(MappingAttribute.isValidAttributeForSearch("university"));
        assertTrue(MappingAttribute.isValidAttributeForSearch("information"));

        // Additional invalid cases
        assertFalse(MappingAttribute.isValidAttributeForSearch("local code"));
        assertFalse(MappingAttribute.isValidAttributeForSearch("LocalCode"));
        assertFalse(MappingAttribute.isValidAttributeForSearch("LocalName"));
    }

    @Test
    public void isValidMappingAttributeForSort() {
        // invalid mappingAttribute
        assertFalse(MappingAttribute.isValidAttributeForSort(""));
        assertFalse(MappingAttribute.isValidAttributeForSort(" "));
        assertFalse(MappingAttribute.isValidAttributeForSort("a"));
        assertFalse(MappingAttribute.isValidAttributeForSort("123"));
        assertFalse(MappingAttribute.isValidAttributeForSort("LOCAL"));
        assertFalse(MappingAttribute.isValidAttributeForSort("localunit"));
        assertFalse(MappingAttribute.isValidAttributeForSort(null));
        assertFalse(MappingAttribute.isValidAttributeForSort("LOCALCODE"));
        assertFalse(MappingAttribute.isValidAttributeForSort("LOCALNAME"));
        assertFalse(MappingAttribute.isValidAttributeForSort("PARTNERCODE"));
        assertFalse(MappingAttribute.isValidAttributeForSort("PARTNERNAME"));
        assertFalse(MappingAttribute.isValidAttributeForSort("UNIVERSITY"));
        assertFalse(MappingAttribute.isValidAttributeForSort("DESCRIPTION"));

        // valid mappingAttribute
        assertTrue(MappingAttribute.isValidAttributeForSort("localcode"));
        assertTrue(MappingAttribute.isValidAttributeForSort("localname"));
        assertTrue(MappingAttribute.isValidAttributeForSort("partnercode"));
        assertTrue(MappingAttribute.isValidAttributeForSort("partnername"));
        assertTrue(MappingAttribute.isValidAttributeForSort("university"));
        assertTrue(MappingAttribute.isValidAttributeForSort("information"));

        // Additional invalid cases
        assertFalse(MappingAttribute.isValidAttributeForSort("local code"));
        assertFalse(MappingAttribute.isValidAttributeForSort("LocalCode"));
        assertFalse(MappingAttribute.isValidAttributeForSort("LocalName"));
        assertFalse(MappingAttribute.isValidAttributeForSort("units"));
        assertFalse(MappingAttribute.isValidAttributeForSort("description"));
    }
}
