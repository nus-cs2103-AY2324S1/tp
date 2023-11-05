package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Hint;
import seedu.address.model.card.Question;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditCardDescriptor objects.
 */
public class EditCardDescriptorBuilder {

    private EditCommand.EditCardDescriptor descriptor;

    public EditCardDescriptorBuilder() {
        descriptor = new EditCommand.EditCardDescriptor();
    }

    public EditCardDescriptorBuilder(EditCommand.EditCardDescriptor descriptor) {
        this.descriptor = new EditCommand.EditCardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCardDescriptor} with fields containing {@code Card}'s details
     */
    public EditCardDescriptorBuilder(Card card) {
        descriptor = new EditCommand.EditCardDescriptor();
        descriptor.setQuestion(card.getQuestion());
        descriptor.setAnswer(card.getAnswer());
        descriptor.setTags(card.getTags());
        descriptor.setHint((card.getHint()));
    }

    /**
     * Sets the {@code Question} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withQuestion(String question) {
        descriptor.setQuestion(new Question(question));
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withAnswer(String answer) {
        descriptor.setAnswer(new Answer(answer));
        return this;
    }

    /**
     * Sets the {@code Tags} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withTags(List<Tag> tags) {
        descriptor.setTags(tags);
        return this;
    }

    /**
     * @returns an EditCardDescriptorBuilder
     */
    public EditCardDescriptorBuilder withTags() {
        descriptor.setTags(new ArrayList<>());
        return this;
    }

    /**
     * Sets the {@code Hint} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withHint(String hint) {
        descriptor.setHint(new Hint(hint));
        return this;
    }

    /**
     * Sets the {@code Hint} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withHint(Hint hint) {
        descriptor.setHint(hint);
        return this;
    }

    public EditCommand.EditCardDescriptor build() {
        return descriptor;
    }
}
