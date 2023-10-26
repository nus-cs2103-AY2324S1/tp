package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.ALAN_MEMBER;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Member;
import seedu.address.testutil.MemberBuilder;


public class AddMemberCommandTest {

    @Test
    public void constructor_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMemberCommand(null));
    }

    @Test
    public void execute_memberAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMemberAdded modelStub = new ModelStubAcceptingMemberAdded();
        Member validMember = new MemberBuilder().build();

        CommandResult commandResult = new AddMemberCommand(validMember).execute(modelStub);

        assertEquals(String.format(AddMemberCommand.MESSAGE_SUCCESS, Messages.format(validMember)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMember), modelStub.memberAdded);
    }

    @Test
    public void equals() {
        Member alan = new MemberBuilder().withName("Alan").withEmail("alan@gmail.com").withPhone("81684544")
                .withTelegram("@alanedabest").build();
        Member bob = new MemberBuilder().withName("Bob").withEmail("bob@gmail.com").withPhone("90021929")
                .withTelegram("@bobisdaworst").build();
        AddMemberCommand addAlanCommand = new AddMemberCommand(alan);
        AddMemberCommand addBobCommand = new AddMemberCommand(bob);

        // same object -> returns true
        assertTrue(addAlanCommand.equals(addAlanCommand));

        // same values -> returns true
        AddMemberCommand addAliceCommandCopy = new AddMemberCommand(alan);
        assertTrue(addAlanCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAlanCommand.equals(1));

        // null -> returns false
        assertFalse(addAlanCommand.equals(null));

        // different person -> returns false
        assertFalse(addAlanCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddMemberCommand addMemberCommand = new AddMemberCommand(ALAN_MEMBER);
        String expected = AddMemberCommand.class.getCanonicalName() + "{toAdd=" + ALAN_MEMBER + "}";
        assertEquals(expected, addMemberCommand.toString());
    }

    /**
     * A Model stub that contains a single Member.
     */
    private class ModelStubWithMember extends ModelStub {
        private final Member member;

        ModelStubWithMember(Member member) {
            requireNonNull(member);
            this.member = member;
        }

        @Override
        public boolean hasMember(Member member) {
            requireNonNull(member);
            return this.member.isSamePerson(member);
        }
    }

    private class ModelStubAcceptingMemberAdded extends ModelStub {
        final ArrayList<Member> memberAdded = new ArrayList<>();

        @Override
        public boolean hasMember(Member member) {
            requireNonNull(member);
            return memberAdded.stream().anyMatch(member::isSamePerson);
        }

        @Override
        public void addMember(Member member) {
            requireNonNull(member);
            memberAdded.add(member);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
