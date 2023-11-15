//package seedu.address.testutil;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import seedu.address.logic.commands.EditCommand.EditIngredientDescriptor;
//import seedu.address.model.Name;
//import seedu.address.model.ingredient.Ingredient;
//import seedu.address.model.tag.Tag;
//
///**
// * A utility class to help with building EditIngredientDescriptor objects.
// */
//public class EditIngredientDescriptorBuilder {
//
//    private EditIngredientDescriptor descriptor;
//
//    public EditIngredientDescriptorBuilder() {
//        descriptor = new EditIngredientDescriptor();
//    }
//
//    public EditIngredientDescriptorBuilder(EditIngredientDescriptor descriptor) {
//        this.descriptor = new EditIngredientDescriptor(descriptor);
//    }
//
//    /**
//     * Returns an {@code EditIngredientDescriptor} with fields containing {@code ingredient}'s details
//     */
//    public EditIngredientDescriptorBuilder(Ingredient ingredient) {
//        descriptor = new EditIngredientDescriptor();
//        descriptor.setName(ingredient.getName());
//    }
//
//    /**
//     * Sets the {@code Name} of the {@code EditIngredientDescriptor} that we are building.
//     */
//    public EditIngredientDescriptorBuilder withName(String name) {
//        descriptor.setName(new Name(name));
//        return this;
//    }
//
//    public EditIngredientDescriptor build() {
//        return descriptor;
//    }
//}
