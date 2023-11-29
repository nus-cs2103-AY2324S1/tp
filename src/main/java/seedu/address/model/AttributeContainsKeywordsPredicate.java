package seedu.address.model;

import java.util.function.Predicate;

import seedu.address.model.person.patients.Patient;

/**
 * A predicate to filter patients based on a specified attribute and keywords.
 *
 * This class implements the Predicate interface for filtering Patient objects.
 * It allows you to specify an attribute and a set of keywords, and then it checks if
 * the specified attribute of a Patient matches the given keywords.
 *
 * The available attributes for filtering include: gender, birthday, email, phone,
 * tags, name, remark, treatment, and address.
 *
 */
public class AttributeContainsKeywordsPredicate implements Predicate<Patient> {
    private final String attribute;
    private final String keywords;

    /**
     * Constructs an AttributeContainsKeywordsPredicate.
     *
     * @param attribute The attribute to filter by (e.g., gender, birthday, email).
     * @param keywords The keywords to search for within the specified attribute.
     */
    public AttributeContainsKeywordsPredicate(String attribute, String keywords) {
        this.attribute = attribute;
        this.keywords = keywords;
    }

    /**
     * Tests if a Patient object satisfies the predicate condition.
     *
     * @param patient The Patient object to be tested.
     * @return true if the specified attribute of the patient matches the keywords;
     *         false otherwise.
     */
    @Override
    public boolean test(Patient patient) {

        switch (attribute.toLowerCase()) {
        case "gender":
            String gender = patient.getGender().toString().toLowerCase();
            return gender.contains(keywords.toLowerCase());
        case "birthday":
            String bday = patient.getBirthdate().toString().toLowerCase();
            return bday.contains(keywords.toLowerCase());
        case "email":
            String email = patient.getEmail().toString().toLowerCase();
            return email.contains(keywords.toLowerCase());
        case "phone":
            String phone = patient.getPhone().toString().toLowerCase();
            return phone.contains(keywords.toLowerCase());
        case "tags":
            String tags = patient.getTags().toString().toLowerCase();
            return tags.contains(keywords.toLowerCase());
        case "name":
            String name = patient.getName().toString().toLowerCase();
            return name.contains(keywords.toLowerCase());
        case "remark":
            String remark = patient.getRemark().toString().toLowerCase();
            return remark.contains(keywords.toLowerCase());
        case "treatment":
            String treatment = patient.getTreatmentName().toString().toLowerCase();
            return treatment.contains(keywords.toLowerCase());
        case "address":
            String add = patient.getAddress().toString().toLowerCase();
            return add.contains(keywords.toLowerCase());
        default:
            return false;
        }
    }
}
