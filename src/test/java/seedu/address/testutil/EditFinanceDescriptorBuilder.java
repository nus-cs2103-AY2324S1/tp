package seedu.address.testutil;

import seedu.address.logic.commands.finance.EditFinanceCommand.EditFinanceDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.Finance;
import seedu.address.model.finance.TimeDue;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building EditFinanceDescriptor objects.
 */
public class EditFinanceDescriptorBuilder {

    private EditFinanceDescriptor descriptor;

    public EditFinanceDescriptorBuilder() {
        descriptor = new EditFinanceDescriptor();
    }


    /**
     * Returns an {@code EditFinanceDescriptor} with fields containing {@code finance}'s details
     */
    public EditFinanceDescriptorBuilder(Finance finance) {
        descriptor = new EditFinanceDescriptor();
        descriptor.setAmount(finance.getAmount());
        descriptor.setClient(finance.getClient());
        descriptor.setTimeDue(finance.getTimeDue());
        descriptor.setDescription(finance.getDescription());
    }

    /**
     * Sets the {@code Amount} of the {@code EditFinanceDescriptor} that we are building.
     */
    public EditFinanceDescriptorBuilder withAmount(String amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code Client} of the {@code EditFinanceDescriptor} that we are building.
     */
    public EditFinanceDescriptorBuilder withClient(String client) throws ParseException {
        descriptor.setClient(new Person(new Name(client), null, null, null,
                null, null));
        return this;
    }

    /**
     * Sets the {@code TimeDue} of the {@code EditFinanceDescriptor} that we are building.
     */
    public EditFinanceDescriptorBuilder withTimeDue(String timeDue) throws ParseException {
        descriptor.setTimeDue(new TimeDue(timeDue));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditFinanceDescriptor} that we are building.
     */
    public EditFinanceDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditFinanceDescriptor build() {
        return descriptor;
    }
}
