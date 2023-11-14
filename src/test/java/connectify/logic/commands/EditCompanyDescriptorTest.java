package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.DESC_COMPANY_A;
import static connectify.logic.commands.CommandTestUtil.DESC_COMPANY_B;
import static connectify.logic.commands.CommandTestUtil.VALID_COMPANY_ADDRESS_B;
import static connectify.logic.commands.CommandTestUtil.VALID_COMPANY_EMAIL_B;
import static connectify.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_B;
import static connectify.logic.commands.CommandTestUtil.VALID_COMPANY_PHONE_B;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import connectify.logic.commands.EditCompanyCommand.EditCompanyDescriptor;
import connectify.testutil.EditCompanyDescriptorBuilder;

public class EditCompanyDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCompanyDescriptor descriptorWithSameValues = new EditCompanyDescriptor(DESC_COMPANY_A);
        assertTrue(DESC_COMPANY_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_COMPANY_A.equals(DESC_COMPANY_A));

        // null -> returns false
        assertFalse(DESC_COMPANY_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_COMPANY_A.equals(5));

        // different values -> returns false
        assertFalse(DESC_COMPANY_A.equals(DESC_COMPANY_B));

        // different name -> returns false
        EditCompanyDescriptor editedCompany = new EditCompanyDescriptorBuilder(DESC_COMPANY_A)
                .withName(VALID_COMPANY_NAME_B).build();
        assertFalse(DESC_COMPANY_A.equals(editedCompany));

        // different phone -> returns false
        editedCompany = new EditCompanyDescriptorBuilder(DESC_COMPANY_A)
                .withPhone(VALID_COMPANY_PHONE_B).build();
        assertFalse(DESC_COMPANY_A.equals(editedCompany));

        // different email -> returns false
        editedCompany = new EditCompanyDescriptorBuilder(DESC_COMPANY_A)
                .withEmail(VALID_COMPANY_EMAIL_B).build();
        assertFalse(DESC_COMPANY_A.equals(editedCompany));

        // different address -> returns false
        editedCompany = new EditCompanyDescriptorBuilder(DESC_COMPANY_A)
                .withAddress(VALID_COMPANY_ADDRESS_B).build();
        assertFalse(DESC_COMPANY_A.equals(editedCompany));
    }

    @Test
    public void toStringMethod() {
        EditCompanyDescriptor editCompanyDescriptor = new EditCompanyDescriptor();
        String expected = EditCompanyDescriptor.class.getCanonicalName() + "{name="
                + editCompanyDescriptor.getName().orElse(null) + ", industry="
                + editCompanyDescriptor.getIndustry().orElse(null) + ", location="
                + editCompanyDescriptor.getLocation().orElse(null) + ", description="
                + editCompanyDescriptor.getDescription().orElse(null) + ", website="
                + editCompanyDescriptor.getWebsite().orElse(null) + ", phone="
                + editCompanyDescriptor.getPhone().orElse(null) + ", email="
                + editCompanyDescriptor.getEmail().orElse(null) + ", address="
                + editCompanyDescriptor.getAddress().orElse(null) + ", companyNote="
                + editCompanyDescriptor.getCompanyNote().orElse(null) + ", personList="
                + editCompanyDescriptor.getPersonList().orElse(null) + "}";
        assertEquals(expected, editCompanyDescriptor.toString());
    }
}
