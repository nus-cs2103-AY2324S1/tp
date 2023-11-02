package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HINT_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditCardDescriptor;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Hint;
import seedu.address.model.card.Question;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditCardDescriptorBuilder;

public class EditCardDescriptorTest {

    @Test
    public void constructor_nullEditCardDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditCardDescriptor(null));
    }

    @Test
    public void isAnyFieldEdited_noFieldChange_false() {
        EditCardDescriptor editCardDescriptor = new EditCardDescriptorBuilder().build();
        assertFalse(editCardDescriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_singleFieldChange_true() {
        EditCardDescriptor editCardDescriptor = new EditCardDescriptorBuilder().withQuestion("new question").build();
        assertTrue(editCardDescriptor.isAnyFieldEdited());

        editCardDescriptor = new EditCardDescriptorBuilder().withAnswer("new answer").build();
        assertTrue(editCardDescriptor.isAnyFieldEdited());

        editCardDescriptor = new EditCardDescriptorBuilder()
                .withTags(Collections.singletonList(new Tag("tag"))).build();
        assertTrue(editCardDescriptor.isAnyFieldEdited());
    }

    @Test
    public void setQuestion_validQuestion_successful() {
        Question validQuestion = new Question(VALID_QUESTION_CS1101S);
        EditCardDescriptor editCardDescriptor = new EditCardDescriptorBuilder().build();
        editCardDescriptor.setQuestion(validQuestion);
        assertEquals(validQuestion, editCardDescriptor.getQuestion().get());
    }

    @Test
    public void setAnswer_validAnswer_successful() {
        Answer validAnswer = new Answer(VALID_ANSWER_CS2100);
        EditCardDescriptor editCardDescriptor = new EditCardDescriptorBuilder().build();
        editCardDescriptor.setAnswer(validAnswer);
        assertEquals(validAnswer, editCardDescriptor.getAnswer().get());
    }

    @Test
    public void setTags_validAnswer_successful() {
        List<Tag> validTags = VALID_TAG_CS1101S;
        EditCardDescriptor editCardDescriptor = new EditCardDescriptorBuilder().build();
        editCardDescriptor.setTags(validTags);
        assertEquals(validTags, editCardDescriptor.getTags().get());
    }

    @Test
    public void setHint_validHint_successful() {
        Hint validHint = new Hint(VALID_HINT_CS2100);
        EditCardDescriptor editCardDescriptor = new EditCardDescriptorBuilder().build();
        editCardDescriptor.setHint(validHint);
        assertEquals(validHint, editCardDescriptor.getHint().get());
    }

    @Test
    public void equals() {
        // same values -> returns true
        EditCardDescriptor descriptorWithSameValues = new EditCardDescriptor(DESC_CS2100);
        assertTrue(DESC_CS2100.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2100.equals(DESC_CS2100));

        // null -> returns false
        assertFalse(DESC_CS2100.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2100.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2100.equals(DESC_CS1101S));

        // different question -> returns false
        EditCardDescriptor editedCS2100 = new EditCardDescriptorBuilder(DESC_CS2100)
                .withQuestion(VALID_QUESTION_CS1101S).build();
        assertFalse(DESC_CS2100.equals(editedCS2100));

        // different answer -> returns false
        editedCS2100 = new EditCardDescriptorBuilder(DESC_CS2100).withAnswer(VALID_ANSWER_CS1101S).build();
        assertFalse(DESC_CS2100.equals(editedCS2100));

        // different tag -> returns false
        editedCS2100 = new EditCardDescriptorBuilder(DESC_CS2100).withTags(VALID_TAG_CS1101S).build();
        assertFalse(DESC_CS2100.equals(editedCS2100));

        // different hint -> returns false
        editedCS2100 = new EditCardDescriptorBuilder(DESC_CS2100).withHint(new Hint.EmptyHint()).build();
        assertFalse(DESC_CS2100.equals(editedCS2100));
    }

    @Test
    public void toStringMethod() {
        EditCardDescriptor editCardDescriptor = new EditCardDescriptor();
        String expected = EditCardDescriptor.class.getCanonicalName()
                + "{"
                + "question=" + editCardDescriptor.getQuestion().orElse(null)
                + ", answer=" + editCardDescriptor.getAnswer().orElse(null)
                + ", tags=" + editCardDescriptor.getTags().orElse(null)
                + ", hint=" + editCardDescriptor.getHint().orElse(null)
                + "}";
        assertEquals(expected, editCardDescriptor.toString());
    }
}
