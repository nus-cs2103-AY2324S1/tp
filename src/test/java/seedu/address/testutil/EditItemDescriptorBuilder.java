package seedu.address.testutil;

import seedu.address.logic.commands.EditItemCommand.EditItemDescriptor;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Price;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;


/**
 * A utility class to help with building EditItemDescriptor objects.
 */
public class EditItemDescriptorBuilder {

    private EditItemDescriptor descriptor;

    public EditItemDescriptorBuilder() {
        descriptor = new EditItemDescriptor();
    }

    public EditItemDescriptorBuilder(EditItemDescriptor descriptor) {
        this.descriptor = new EditItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItemDescriptor} with fields containing {@code item}'s details
     */
    public EditItemDescriptorBuilder(Item item) {
        descriptor = new EditItemDescriptor();
        descriptor.setItemName(item.getName());
        descriptor.setPrice(item.getPrice());
        if (item.hasItemReview()) {
            descriptor.setRating(item.getItemReview().getRating());
            descriptor.setDescription(item.getItemReview().getDescription());
        }
    }

    /**
     * Sets the {@code Name} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withName(String name) {
        descriptor.setItemName(new ItemName(name));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditItemDescriptor build() {
        return descriptor;
    }
}
