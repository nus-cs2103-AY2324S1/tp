package seedu.lovebook.testutil;

import seedu.lovebook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.lovebook.model.date.Age;
import seedu.lovebook.model.date.Avatar;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.Gender;
import seedu.lovebook.model.date.Height;
import seedu.lovebook.model.date.Income;
import seedu.lovebook.model.date.Name;
import seedu.lovebook.model.date.horoscope.Horoscope;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code date}'s details
     */
    public EditPersonDescriptorBuilder(Date date) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(date.getName());
        descriptor.setAge(date.getAge());
        descriptor.setGender(date.getGender());
        descriptor.setHeight(date.getHeight());
        descriptor.setIncome(date.getIncome());
        descriptor.setHoroscope(date.getHoroscope());
        descriptor.setAvatar(date.getAvatar());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAge(String age) {
        descriptor.setAge(new Age(age));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withHeight(String height) {
        descriptor.setHeight(new Height(height));
        return this;
    }

    /**
     * Sets the {@code Income} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withIncome(String income) {
        descriptor.setIncome(new Income(income));
        return this;
    }

    /**
     * Sets the {@code Income} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withHoroscope(String horoscope) {
        descriptor.setHoroscope(new Horoscope(horoscope));
        return this;
    }

    /**
     * Sets the {@code Avatar} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAvatar(String avatar) {
        descriptor.setAvatar(new Avatar(avatar));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
