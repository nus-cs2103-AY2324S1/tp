package connectify.logic.commands;

import static connectify.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static connectify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static connectify.logic.parser.CliSyntax.PREFIX_NAME;
import static connectify.logic.parser.CliSyntax.PREFIX_PHONE;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import connectify.commons.core.index.Index;
import connectify.commons.util.CollectionUtil;
import connectify.commons.util.ToStringBuilder;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.company.Company;
import connectify.model.company.CompanyAddress;
import connectify.model.company.CompanyEmail;
import connectify.model.company.CompanyIndustry;
import connectify.model.company.CompanyLocation;
import connectify.model.company.CompanyName;
import connectify.model.company.CompanyNote;
import connectify.model.company.CompanyPhone;
import connectify.model.company.CompanyWebsite;
import connectify.model.person.PersonList;

/**
 * Edits the details of an existing company in the address book.
 */
public class EditCompanyCommand extends Command {

    public static final String COMMAND_WORD = "editCompany";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the company identified "
            + "by the index number used in the displayed company list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "NAME "
            + "PHONE "
            + "EMAIL "
            + "ADDRESS "
            + "TAG...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME
            + "TechCorp "
            + PREFIX_PHONE
            + "91234567 "
            + PREFIX_EMAIL
            + "techcorp@gmail.com "
            + PREFIX_ADDRESS
            + "123, Jurong West Ave 6, #08-111";

    public static final String MESSAGE_SUCCESS = "Edited Company: %1$s";
    public static final String MESSAGE_DUPLICATE_COMPANY = "This company already exists in Connectify.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    private final Index companyIndex;
    private final EditCompanyDescriptor editCompanyDescriptor;

    /**
     * @param companyIndex of the company in the filtered company list to edit
     * @param editCompanyDescriptor details to edit the company with
     */
    public EditCompanyCommand(Index companyIndex, EditCompanyDescriptor editCompanyDescriptor) {
        requireNonNull(companyIndex);
        requireNonNull(editCompanyDescriptor);

        this.companyIndex = companyIndex;
        this.editCompanyDescriptor = new EditCompanyDescriptor(editCompanyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model cannot be null";
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (companyIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToEdit = lastShownList.get(companyIndex.getZeroBased());
        Company editedCompany = createEditedCompany(companyToEdit, editCompanyDescriptor);

        if (!companyToEdit.isSameCompany(editedCompany) && model.hasCompany(editedCompany)) {
            throw new CommandException(MESSAGE_DUPLICATE_COMPANY);
        }

        model.setCompany(companyToEdit, editedCompany);
        model.updateFilteredCompanyList(Model.PREDICATE_SHOW_ALL_COMPANIES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(editedCompany)));
    }

    /**
     * Creates and returns a {@code Company} with the details of {@code companyToEdit}
     * edited with {@code editCompanyDescriptor}.
     */
    public Company createEditedCompany(Company companyToEdit, EditCompanyDescriptor editCompanyDescriptor) {
        requireNonNull(companyToEdit);

        CompanyName updatedName = editCompanyDescriptor.getName().orElse(companyToEdit.getName());
        CompanyIndustry updatedIndustry = editCompanyDescriptor.getIndustry().orElse(companyToEdit.getIndustry());
        CompanyLocation updatedLocation = editCompanyDescriptor.getLocation().orElse(companyToEdit.getLocation());
        String updatedDescription = editCompanyDescriptor.getDescription().orElse(companyToEdit.getDescription());
        CompanyWebsite updatedWebsite = editCompanyDescriptor.getWebsite().orElse(companyToEdit.getWebsite());
        CompanyPhone updatedPhone = editCompanyDescriptor.getPhone().orElse(companyToEdit.getPhone());
        CompanyEmail updatedEmail = editCompanyDescriptor.getEmail().orElse(companyToEdit.getEmail());
        CompanyAddress updatedAddress = editCompanyDescriptor.getAddress().orElse(companyToEdit.getAddress());
        CompanyNote updatedCompanyNote = editCompanyDescriptor.getCompanyNote().orElse(companyToEdit.getNote());
        PersonList updatedPersonList = editCompanyDescriptor.getPersonList().orElse(companyToEdit.getPersonList());

        return new Company(updatedName, updatedIndustry, updatedLocation, updatedDescription, updatedWebsite,
                updatedEmail, updatedPhone, updatedAddress, updatedCompanyNote, updatedPersonList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCompanyCommand)) {
            return false;
        }

        // state check
        EditCompanyCommand e = (EditCompanyCommand) other;
        return companyIndex.equals(e.companyIndex)
                && editCompanyDescriptor.equals(e.editCompanyDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("companyIndex", companyIndex)
                .add("editCompanyDescriptor", editCompanyDescriptor)
                .toString();
    }


    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditCompanyDescriptor {
        private CompanyName companyName;
        private CompanyIndustry companyIndustry;
        private CompanyLocation companyLocation;
        private String companyDescription;
        private CompanyWebsite companyWebsite;
        private CompanyEmail companyEmail;
        private CompanyPhone companyPhone;
        private CompanyAddress companyAddress;
        private CompanyNote companyNote;
        private PersonList personList;

        /**
         * Constructor for EditCompanyDescriptor.
         */
        public EditCompanyDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditCompanyDescriptor(EditCompanyDescriptor toCopy) {
            setName(toCopy.companyName);
            setIndustry(toCopy.companyIndustry);
            setLocation(toCopy.companyLocation);
            setDescription(toCopy.companyDescription);
            setWebsite(toCopy.companyWebsite);
            setPhone(toCopy.companyPhone);
            setEmail(toCopy.companyEmail);
            setAddress(toCopy.companyAddress);
            setCompanyNote(toCopy.companyNote);
            setPersonList(toCopy.personList);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(companyName, companyIndustry, companyLocation, companyDescription,
                    companyWebsite, companyEmail, companyPhone, companyAddress, companyNote);
        }

        /**
         * Sets {@code companyName} to this object's {@code companyName}.
         * @param companyName Name of company.
         */
        public void setName(CompanyName companyName) {
            this.companyName = companyName;
        }

        /**
         * Returns an optional of {@code companyName}.
         * @return Optional of {@code companyName}.
         */
        public Optional<CompanyName> getName() {
            return Optional.ofNullable(companyName);
        }

        /**
         * Sets {@code companyIndustry} to this object's {@code companyIndustry}.
         * @param companyIndustry Industry of company.
         */
        public void setIndustry(CompanyIndustry companyIndustry) {
            this.companyIndustry = companyIndustry;
        }

        /**
         * Returns an optional of {@code companyIndustry}.
         * @return Optional of {@code companyIndustry}.
         */
        public Optional<CompanyIndustry> getIndustry() {
            return Optional.ofNullable(companyIndustry);
        }

        /**
         * Sets {@code location} to this object's {@code location}.
         * @param location Location of company.
         */
        public void setLocation(CompanyLocation location) {
            this.companyLocation = location;
        }

        /**
         * Returns an optional of {@code location}.
         * @return Optional of {@code location}.
         */
        public Optional<CompanyLocation> getLocation() {
            return Optional.ofNullable(companyLocation);
        }

        /**
         * Sets {@code companyDescription} to this object's {@code companyDescription}.
         * @param companyDescription Description of company.
         */
        public void setDescription(String companyDescription) {
            this.companyDescription = companyDescription;
        }

        /**
         * Returns an optional of {@code companyDescription}.
         * @return Optional of {@code companyDescription}.
         */
        public Optional<String> getDescription() {
            return Optional.ofNullable(companyDescription);
        }

        /**
         * Sets {@code website} to this object's {@code website}.
         * @param website Website of company.
         */
        public void setWebsite(CompanyWebsite website) {
            this.companyWebsite = website;
        }

        /**
         * Returns an optional of {@code website}.
         * @return Optional of {@code website}.
         */
        public Optional<CompanyWebsite> getWebsite() {
            return Optional.ofNullable(companyWebsite);
        }

        /**
         * Sets {@code phone} to this object's {@code phone}.
         * @param companyPhone Phone number of company.
         */
        public void setPhone(CompanyPhone companyPhone) {
            this.companyPhone = companyPhone;
        }

        /**
         * Returns an optional of {@code phone}.
         * @return Optional of {@code phone}.
         */
        public Optional<CompanyPhone> getPhone() {
            return Optional.ofNullable(companyPhone);
        }

        /**
         * Sets {@code email} to this object's {@code email}.
         * @param companyEmail Email of company.
         */
        public void setEmail(CompanyEmail companyEmail) {
            this.companyEmail = companyEmail;
        }

        /**
         * Returns an optional of {@code email}.
         * @return Optional of {@code email}.
         */
        public Optional<CompanyEmail> getEmail() {
            return Optional.ofNullable(companyEmail);
        }

        /**
         * Sets {@code address} to this object's {@code address}.
         * @param companyAddress Address of company.
         */
        public void setAddress(CompanyAddress companyAddress) {
            this.companyAddress = companyAddress;
        }

        /**
         * Returns an optional of {@code address}.
         * @return Optional of {@code address}.
         */
        public Optional<CompanyAddress> getAddress() {
            return Optional.ofNullable(companyAddress);
        }

        /**
         * Sets {@code companyNote} to this object's {@code companyNote}.
         * @param companyNote Note about the company.
         */
        public void setCompanyNote(CompanyNote companyNote) {
            this.companyNote = companyNote;
        }

        /**
         * Returns an optional of {@code companyNote}.
         * @return Optional of {@code companyNote}.
         */
        public Optional<CompanyNote> getCompanyNote() {
            return Optional.ofNullable(companyNote);
        }

        /**
         * Sets {@code personList} to this object's {@code personList}.
         * @param personList List of persons in the company.
         */
        public void setPersonList(PersonList personList) {
            this.personList = personList;
        }

        /**
         * Returns an optional of {@code personList}.
         * @return Optional of {@code personList}.
         */
        public Optional<PersonList> getPersonList() {
            return Optional.ofNullable(personList);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCompanyDescriptor)) {
                return false;
            }

            EditCompanyCommand.EditCompanyDescriptor otherEditCompanyDescriptor =
                    (EditCompanyCommand.EditCompanyDescriptor) other;
            return Objects.equals(getName(), otherEditCompanyDescriptor.getName())
                    && Objects.equals(getIndustry(), otherEditCompanyDescriptor.getIndustry())
                    && Objects.equals(getLocation(), otherEditCompanyDescriptor.getLocation())
                    && Objects.equals(getDescription(), otherEditCompanyDescriptor.getDescription())
                    && Objects.equals(getWebsite(), otherEditCompanyDescriptor.getWebsite())
                    && Objects.equals(getPhone(), otherEditCompanyDescriptor.getPhone())
                    && Objects.equals(getEmail(), otherEditCompanyDescriptor.getEmail())
                    && Objects.equals(getAddress(), otherEditCompanyDescriptor.getAddress())
                    && Objects.equals(getCompanyNote(), otherEditCompanyDescriptor.getCompanyNote())
                    && Objects.equals(getPersonList(), otherEditCompanyDescriptor.getPersonList());
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", companyName)
                    .add("industry", companyIndustry)
                    .add("location", companyLocation)
                    .add("description", companyDescription)
                    .add("website", companyWebsite)
                    .add("phone", companyPhone)
                    .add("email", companyEmail)
                    .add("address", companyAddress)
                    .add("companyNote", companyNote)
                    .add("personList", personList)
                    .toString();
        }
    }
}
