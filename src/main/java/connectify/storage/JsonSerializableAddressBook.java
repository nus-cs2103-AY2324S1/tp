package connectify.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import connectify.commons.exceptions.IllegalValueException;
import connectify.model.AddressBook;
import connectify.model.ReadOnlyAddressBook;
import connectify.model.company.Company;
import connectify.model.company.CompanyNameComparator;
import connectify.model.person.Person;
import connectify.model.person.PersonNameComparator;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_COMPANY = "Companies list contains duplicate company(s).";

    private final List<JsonAdaptedCompany> companies = new ArrayList<>();


    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("companies") List<JsonAdaptedCompany> companies) {
        if (companies != null) {
            this.companies.addAll(companies);
        }
    }

    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        companies.addAll(source.getCompanyList().stream().map(JsonAdaptedCompany::new).collect(Collectors.toList()));
    }

    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedCompany jsonAdaptedCompany : companies) {
            Company company = jsonAdaptedCompany.toModelType();
            if (addressBook.hasCompany(company)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COMPANY);
            }
            addressBook.addCompany(company);

            for (int i = 0; i < company.getPersonList().size(); i++) {
                Person person = company.getPersonList().get(i);
                if (addressBook.hasPerson(person)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
                }
                person.setParentCompany(company);
                addressBook.addPerson(person);
            }
        }
        addressBook.sort(new CompanyNameComparator(), new PersonNameComparator());

        return addressBook;
    }
}
