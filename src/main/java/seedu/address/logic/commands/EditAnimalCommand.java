package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliAnimalSyntax.BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.SPECIES;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.PetId;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;
import seedu.address.model.animal.TaskList;

/**
 * Edits the details of an existing animal in the catalog.
 */
public class EditAnimalCommand extends AnimalCommand {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits an animal identified by the index number used in the displayed animal list.. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1"
            + "Parameters: "
            + NAME.getUsage() + " "
            + DATE_OF_BIRTH.getUsage() + " "
            + DATE_OF_ADMISSION.getUsage() + " "
            + SPECIES.getUsage()
            + BREED.getUsage()
            + SEX.getUsage();

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD + " "
            + "1 "
            + NAME.getPrefix() + "ChangGoose "
            + DATE_OF_ADMISSION.getPrefix() + "2019-01-01 "
            + SPECIES.getPrefix() + "Dog ";

    public static final String MESSAGE_SUCCESS = "Animal has been edited from: \n%s to: \n%s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided. \n%s";

    public static final String MESSAGE_DO_NOT_CHANGE_ID = "You cannot change the ID of an animal. \n%s";
    private final Index index;
    private final EditAnimalDescriptor animalDescriptor;

    /**
     * Creates an EditCommand to edit the specified {@code Animal}
     */
    public EditAnimalCommand(Index index, EditAnimalDescriptor animalDescriptor) {
        requireNonNull(index);
        requireNonNull(animalDescriptor);

        this.index = index;
        this.animalDescriptor = new EditAnimalDescriptor(animalDescriptor);
    }

    /**
     * Returns the help message of this command.
     *
     * @return a string containing the help message of this command.
     */
    public static String getHelp() {
        return String.format(AnimalMessages.MESSAGE_HELP_FORMAT, MESSAGE_USAGE, EXAMPLE_USAGE);
    }

    @Override
    public CommandResult execute(AnimalModel model) throws CommandException {
        requireNonNull(model);
        List<Animal> lastShownList = model.getFilteredAnimalList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(AnimalMessages.MESSAGE_INVALID_ANIMAL_DISPLAYED_INDEX);
        }

        Animal animalToEdit = lastShownList.get(index.getZeroBased());
        Animal editedAnimal = createEditedAnimal(animalToEdit, animalDescriptor);

        model.setAnimal(animalToEdit, editedAnimal);
        model.updateFilteredAnimalList(AnimalModel.PREDICATE_SHOW_ALL_ANIMALS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, AnimalMessages.format(animalToEdit),
                AnimalMessages.format(editedAnimal)), editedAnimal);
    }

    private static Animal createEditedAnimal(Animal animalToEdit, EditAnimalDescriptor animalDescriptor) {
        Name updatedName = animalDescriptor.getName().orElse(animalToEdit.getName());
        DateOfBirth updatedDateOfBirth = animalDescriptor.getDateOfBirth().orElse(animalToEdit.getDateOfBirth());
        AdmissionDate updatedAdmissionDate = animalDescriptor.getAdmissionDate()
                .orElse(animalToEdit.getAdmissionDate());
        Species updatedSpecies = animalDescriptor.getSpecies().orElse(animalToEdit.getSpecies());
        Breed updatedBreed = animalDescriptor.getBreed().orElse(animalToEdit.getBreed());
        Sex updatedSex = animalDescriptor.getSex().orElse(animalToEdit.getSex());
        PetId currentPetId = animalToEdit.getPetId();
        TaskList currentTaskList = animalToEdit.getTaskList();

        return new Animal(updatedName, currentPetId, updatedSpecies, updatedBreed, updatedSex,
                 updatedAdmissionDate, updatedDateOfBirth, currentTaskList);
    }


    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EditAnimalCommand
                && index.equals(((EditAnimalCommand) other).index)
                && animalDescriptor.equals(((EditAnimalCommand) other).animalDescriptor));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("animalDescriptor", animalDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the Animal with. Each non-empty field value will replace the
     * corresponding field value of the Animal.
     */
    public static class EditAnimalDescriptor {
        private Name name;
        private DateOfBirth dateOfBirth;
        private AdmissionDate admissionDate;
        private Species species;
        private Breed breed;
        private Sex sex;

        public EditAnimalDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditAnimalDescriptor(EditAnimalDescriptor toCopy) {
            setName(toCopy.name);
            setDateOfBirth(toCopy.dateOfBirth);
            setAdmissionDate(toCopy.admissionDate);
            setSpecies(toCopy.species);
            setBreed(toCopy.breed);
            setSex(toCopy.sex);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, dateOfBirth, admissionDate, species, breed, sex);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDateOfBirth(DateOfBirth dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Optional<DateOfBirth> getDateOfBirth() {
            return Optional.ofNullable(dateOfBirth);
        }

        public void setAdmissionDate(AdmissionDate admissionDate) {
            this.admissionDate = admissionDate;
        }

        public Optional<AdmissionDate> getAdmissionDate() {
            return Optional.ofNullable(admissionDate);
        }

        public void setSpecies(Species species) {
            this.species = species;
        }

        public Optional<Species> getSpecies() {
            return Optional.ofNullable(species);
        }

        public void setBreed(Breed breed) {
            this.breed = breed;
        }

        public Optional<Breed> getBreed() {
            return Optional.ofNullable(breed);
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }

        public Optional<Sex> getSex() {
            return Optional.ofNullable(sex);
        }

        @Override
        public boolean equals(Object other) {
            return this == other
                    || (other instanceof EditAnimalDescriptor
                    && Objects.equals(name, ((EditAnimalDescriptor) other).name)
                    && Objects.equals(dateOfBirth, ((EditAnimalDescriptor) other).dateOfBirth)
                    && Objects.equals(admissionDate, ((EditAnimalDescriptor) other).admissionDate)
                    && Objects.equals(species, ((EditAnimalDescriptor) other).species)
                    && Objects.equals(breed, ((EditAnimalDescriptor) other).breed)
                    && Objects.equals(sex, ((EditAnimalDescriptor) other).sex));
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("dateOfBirth", dateOfBirth)
                    .add("admissionDate", admissionDate)
                    .add("species", species)
                    .add("breed", breed)
                    .add("sex", sex)
                    .toString();
        }
    }


}
