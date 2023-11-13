package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.member.Member;
import seedu.address.storage.exceptions.json.IllegalJsonMemberValueException;




class JsonAdaptedMemberTest {

    @Test
    void constructor_validMemberInstance_returnsJsonAdaptedMember() {
        final String memberName = "John";
        final Member member = new Member(memberName);
        final JsonAdaptedMember jsonAdaptedMember = new JsonAdaptedMember(member);

        assertEquals(new JsonAdaptedMember(memberName).getMemberName(), jsonAdaptedMember.getMemberName());
    }

    @Test
    void toModelType_invalidWhitespaceName_throwsIllegalJsonValueException() {
        final String memberName = " ";
        JsonAdaptedMember member = new JsonAdaptedMember(memberName);

        assertThrows(IllegalJsonMemberValueException.class, member::toModelType);
    }

    @Test
    void toModelType_invalidSlashName_throwsIllegalJsonValueException() {
        final String memberName = "/friend";
        JsonAdaptedMember member = new JsonAdaptedMember(memberName);

        assertThrows(IllegalJsonMemberValueException.class, member::toModelType);
    }

    @Test
    void toModelType_nullName_throwsNullPointerException() {
        final String memberName = null;
        JsonAdaptedMember member = new JsonAdaptedMember(memberName);

        assertThrows(NullPointerException.class, member::toModelType);
    }

    @Test
    void toModelType_validName_returnsMember() {
        final String memberName = "John";
        JsonAdaptedMember member = new JsonAdaptedMember(memberName);

        try {
            assertEquals(new Member(memberName), member.toModelType());
        } catch (IllegalJsonMemberValueException e) {
            assertThrows(IllegalJsonMemberValueException.class, member::toModelType);
        }
    }
}
