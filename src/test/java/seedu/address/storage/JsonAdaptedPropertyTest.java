package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedProperty.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProperties.AQUAVIEW;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.Price;
import seedu.address.model.property.PropAddress;
import seedu.address.model.property.PropName;
import seedu.address.model.property.PropPhone;

public class JsonAdaptedPropertyTest {
    private static final String INVALID_PROPNAME = " ";
    private static final String INVALID_PROPPHONE = "+651234";
    private static final String INVALID_PRICE = "-1000";
    private static final String INVALID_PROPADDRESS = "";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_PROPNAME = AQUAVIEW.getName().toString();
    private static final String VALID_PROPPHONE = AQUAVIEW.getPhone().toString();
    private static final String VALID_PROPADDRESS = AQUAVIEW.getAddress().toString();
    private static final String VALID_PRICE = AQUAVIEW.getPrice().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = AQUAVIEW.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPropertyDetails_returnsProperty() throws Exception {
        JsonAdaptedProperty property = new JsonAdaptedProperty(AQUAVIEW);
        assertEquals(AQUAVIEW, property.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(INVALID_PROPNAME, VALID_PROPADDRESS, VALID_PROPPHONE, VALID_PRICE, VALID_TAGS);
        String expectedMessage = PropName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedProperty property = new JsonAdaptedProperty(null, VALID_PROPADDRESS, VALID_PROPPHONE,
                VALID_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PropName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedProperty property = new JsonAdaptedProperty(VALID_PROPNAME, INVALID_PROPADDRESS, VALID_PROPPHONE,
                VALID_PRICE, VALID_TAGS);
        String expectedMessage = PropAddress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedProperty property = new JsonAdaptedProperty(VALID_PROPNAME, null, VALID_PROPPHONE,
                VALID_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PropAddress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedProperty property = new JsonAdaptedProperty(VALID_PROPNAME, VALID_PROPADDRESS, INVALID_PROPPHONE,
                VALID_PRICE, VALID_TAGS);
        String expectedMessage = PropPhone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedProperty property = new JsonAdaptedProperty(VALID_PROPNAME, VALID_PROPADDRESS, null,
                VALID_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PropPhone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedProperty property = new JsonAdaptedProperty(VALID_PROPNAME, VALID_PROPADDRESS, VALID_PROPPHONE,
                INVALID_PRICE, VALID_TAGS);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedProperty property = new JsonAdaptedProperty(VALID_PROPNAME, VALID_PROPADDRESS, VALID_PROPPHONE,
                null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(VALID_PROPNAME, VALID_PROPADDRESS, VALID_PROPPHONE, VALID_PRICE, invalidTags);
        assertThrows(IllegalValueException.class, property::toModelType);
    }

}
