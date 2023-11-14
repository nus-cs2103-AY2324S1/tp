package seedu.lovebook.testutil;

import seedu.lovebook.logic.commands.SetPrefCommand.SetPreferenceDescriptor;
import seedu.lovebook.model.date.Age;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.Height;
import seedu.lovebook.model.date.Income;
import seedu.lovebook.model.date.horoscope.Horoscope;

/**
 * A utility class to help with building SetPreferenceDescriptor objects.
 */
public class SetPreferenceDescriptorBuilder {

    private SetPreferenceDescriptor descriptor;

    public SetPreferenceDescriptorBuilder() {
        descriptor = new SetPreferenceDescriptor();
    }

    public SetPreferenceDescriptorBuilder(SetPreferenceDescriptor descriptor) {
        this.descriptor = new SetPreferenceDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code date}'s details
     */
    public SetPreferenceDescriptorBuilder(Date date) {
        descriptor = new SetPreferenceDescriptor();
        descriptor.setAge(date.getAge());
        descriptor.setHeight(date.getHeight());
        descriptor.setIncome(date.getIncome());
        descriptor.setHoroscope(date.getHoroscope());
    }

    /**
     * Sets the {@code Age} of the {@code EditPersonDescriptor} that we are building.
     */
    public SetPreferenceDescriptorBuilder withAge(String age) {
        descriptor.setAge(new Age(age));
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code EditPersonDescriptor} that we are building.
     */
    public SetPreferenceDescriptorBuilder withHeight(String height) {
        descriptor.setHeight(new Height(height));
        return this;
    }

    /**
     * Sets the {@code Income} of the {@code EditPersonDescriptor} that we are building.
     */
    public SetPreferenceDescriptorBuilder withIncome(String income) {
        descriptor.setIncome(new Income(income));
        return this;
    }

    /**
     * Sets the {@code Income} of the {@code EditPersonDescriptor} that we are building.
     */
    public SetPreferenceDescriptorBuilder withHoroscope(String horoscope) {
        descriptor.setHoroscope(new Horoscope(horoscope));
        return this;
    }

    public SetPreferenceDescriptor build() {
        return descriptor;
    }
}
