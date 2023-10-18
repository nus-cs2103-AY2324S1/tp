package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FilterSettingsTest {
    @Test
    public void toStringMethod() {
        FilterSettings filterSettings = new FilterSettings();
        String expected = FilterSettings.class.getCanonicalName() + "{filters=" + filterSettings.getFilters() + "}";
        assertEquals(expected, filterSettings.toString());
    }
}
