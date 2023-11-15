package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToStringBuilderTest {

    private ToStringBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new ToStringBuilder("TestObject");
    }

    @Test
    public void addField_singleField_success() {
        builder.add("fieldName", "fieldValue");
        String expected = "TestObject{" + "fieldName=fieldValue" + "}";
        assertEquals(expected, builder.toString());
    }

    @Test
    public void addField_multipleFields_success() {
        builder.add("field1", "value1")
                .add("field2", "value2")
                .add("field3", "value3");
        String expected = "TestObject{" + "field1=value1, field2=value2, field3=value3" + "}";
        assertEquals(expected, builder.toString());
    }

    @Test
    public void addField_emptyBuilder_noFields() {
        String expected = "TestObject{}";
        assertEquals(expected, builder.toString());
    }

    @Test
    public void addFieldWithNullValue_success() {
        builder.add("nullField", null);
        String expected = "TestObject{" + "nullField=null" + "}";
        assertEquals(expected, builder.toString());
    }

    @Test
    public void addFieldWithSpecialCharacters_success() {
        builder.add("specialField", "#$%^&*");
        String expected = "TestObject{" + "specialField=#$%^&*" + "}";
        assertEquals(expected, builder.toString());
    }

    @Test
    public void addFieldWithSpaces_success() {
        builder.add("spaceField", "This is a value with spaces");
        String expected = "TestObject{" + "spaceField=This is a value with spaces" + "}";
        assertEquals(expected, builder.toString());
    }
}

