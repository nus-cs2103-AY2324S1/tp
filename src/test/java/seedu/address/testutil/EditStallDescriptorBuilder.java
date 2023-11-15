package seedu.address.testutil;

import seedu.address.logic.commands.EditStallCommand;
import seedu.address.logic.commands.EditStallCommand.EditStallDescriptor;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;

/**
 * A utility class to help with building EditStallDescriptor objects.
 */
public class EditStallDescriptorBuilder {

    private EditStallDescriptor descriptor;

    public EditStallDescriptorBuilder() {
        descriptor = new EditStallCommand.EditStallDescriptor();
    }

    public EditStallDescriptorBuilder(EditStallDescriptor descriptor) {
        this.descriptor = new EditStallCommand.EditStallDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStallDescriptor} with fields containing {@code stall}'s details
     */
    public EditStallDescriptorBuilder(Stall stall) {
        descriptor = new EditStallDescriptor();
        descriptor.setName(stall.getName());
        descriptor.setLocation(stall.getLocation());
        descriptor.setMenu(stall.getMenu());
        if (stall.hasStallReview()) {
            descriptor.setRating(stall.getStallReview().getRating());
            descriptor.setDescription(stall.getStallReview().getDescription());
        }
    }

    /**
     * Sets the {@code Name} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditStallDescriptor build() {
        return descriptor;
    }
}
