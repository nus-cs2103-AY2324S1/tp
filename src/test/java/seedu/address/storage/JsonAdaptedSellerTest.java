package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSellers.SALICE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;

public class JsonAdaptedSellerTest {
    private static final String INVALID_NAME = "  ";
    private static final String INVALID_PHONE = "+kjfladkj";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "   ";
    private static final String INVALID_SELLING_ADDRESS = " ";
    private static final String INVALID_INFO = " ";

    private static final String VALID_NAME = SALICE.getName().toString();
    private static final String VALID_PHONE = SALICE.getPhone().toString();
    private static final String VALID_EMAIL = SALICE.getEmail().toString();
    private static final String VALID_ADDRESS = SALICE.getAddress().toString();
    private static final String VALID_SELLING_ADDRESS = SALICE.getSellingAddress().toString();
    private static final String VALID_INFO = SALICE.getHouseInfo().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = SALICE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_PRIORITY = SALICE.getPriority().toString();
    @Test
    public void toModelType_validSellerDetails_returnsSeller() throws Exception {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(SALICE);
        assertEquals(SALICE, seller.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SELLING_ADDRESS, VALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SELLING_ADDRESS, VALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SELLING_ADDRESS, VALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_SELLING_ADDRESS, VALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                VALID_SELLING_ADDRESS, VALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_SELLING_ADDRESS, VALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                VALID_SELLING_ADDRESS, VALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_SELLING_ADDRESS, VALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidInfo_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SELLING_ADDRESS, INVALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(HouseInfo.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullInfo_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SELLING_ADDRESS, null, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, HouseInfo.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidSellerAddress_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                INVALID_SELLING_ADDRESS, VALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(Address.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullSellerAddress_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_INFO, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SELLING_ADDRESS, VALID_INFO, invalidTags, VALID_PRIORITY);
        assertThrows(IllegalValueException.class, seller::toModelType);
    }

}
