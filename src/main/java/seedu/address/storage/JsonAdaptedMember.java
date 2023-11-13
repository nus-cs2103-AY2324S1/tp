package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.member.Member;
import seedu.address.storage.exceptions.json.IllegalJsonMemberValueException;


/**
 * Jackson-friendly version of {@link Member}.
 */
class JsonAdaptedMember {

    private final String memberName;

    /**
     * Constructs a {@code JsonAdaptedMember} with the given {@code memberName}.
     */
    @JsonCreator
    public JsonAdaptedMember(String memberName) {
        this.memberName = memberName;
    }

    /**
     * Converts a given {@code Member} into this class for Jackson use.
     */
    public JsonAdaptedMember(Member source) {
        memberName = source.memberName;
    }

    @JsonValue
    public String getMemberName() {
        return memberName;
    }

    /**
     * Converts this Jackson-friendly adapted member object into the model's {@code Member} object.
     *
     * @throws IllegalJsonMemberValueException if there were any data constraints violated in the adapted member.
     */
    public Member toModelType() throws IllegalJsonMemberValueException {
        if (!Member.isValidName(memberName)) {
            throw new IllegalJsonMemberValueException(Member.MESSAGE_CONSTRAINTS);
        }
        return new Member(memberName);
    }

}
