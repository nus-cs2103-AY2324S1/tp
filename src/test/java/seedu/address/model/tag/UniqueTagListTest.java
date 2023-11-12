package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.TEST_TAG;
import static seedu.address.testutil.TypicalTags.TEST_TAG_CATEGORY_STRING;
import static seedu.address.testutil.TypicalTags.TEST_TAG_NAME_STRING;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.exceptions.DuplicateTagException;

public class UniqueTagListTest {

    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @BeforeEach
    public void clearList() {
        if (uniqueTagList.contains(TEST_TAG)) {
            uniqueTagList.remove(TEST_TAG);
        }
    }

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(TEST_TAG));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        uniqueTagList.add(TEST_TAG);
        assertTrue(uniqueTagList.contains(TEST_TAG));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        uniqueTagList.add(TEST_TAG);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.add(TEST_TAG));
    }

    @Test
    public void getTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.getTag(null, null));
    }

    @Test
    public void getTag_tagCategoryDoesNotExist_throwsParseException() {
        uniqueTagList.add(TEST_TAG);
        assertThrows(ParseException.class, () -> uniqueTagList.getTag(TEST_TAG_NAME_STRING, "categoryNotInList"));
    }

    @Test
    public void getTag_tagNameDoesNotExist_throwsParseException() {
        uniqueTagList.add(TEST_TAG);
        assertThrows(ParseException.class, () -> uniqueTagList.getTag("nameNotInList", TEST_TAG_CATEGORY_STRING));
    }

    @Test
    public void getTag_tagDoesNotExist_throwsParseException() {
        System.out.println(uniqueTagList.contains(TEST_TAG));
        assertThrows(ParseException.class, () -> uniqueTagList.getTag(TEST_TAG_NAME_STRING, TEST_TAG_CATEGORY_STRING));
    }

    @Test
    public void getTag_multipleTagsAcrossDifferentCategoriesAndCategoryNotSpecified_throwsParseException() {
        uniqueTagList.add(new Tag("test", "category1"));
        uniqueTagList.add(new Tag("test", "category2"));
        assertThrows(ParseException.class, () -> uniqueTagList.getTag("test", ""));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueTagList.asUnmodifiableObservableList().remove(0));
    }

}


