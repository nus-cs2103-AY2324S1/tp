package seedu.ccacommander.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX;
import static seedu.ccacommander.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.ccacommander.logic.commands.CommandTestUtil.showMemberAtIndex;
import static seedu.ccacommander.testutil.Assert.assertThrows;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalCcaCommander;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.commands.exceptions.CommandException;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.ModelManager;
import seedu.ccacommander.model.UserPrefs;
import seedu.ccacommander.model.enrolment.Enrolment;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;
import seedu.ccacommander.model.shared.Name;

public class ViewMemberCommandTest {
    private Model model;
    private Model expectedModel;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCcaCommander(), new UserPrefs());
        expectedModel = new ModelManager(model.getCcaCommander(), new UserPrefs());
    }
    @Test
    public void execute_indexSpecifiedListsNotFiltered_showsEventsOfMember() {
        showEventsOfMemberAtIndex(expectedModel, INDEX_FIRST_EVENT);

        assertEquals(expectedModel.getFilteredEnrolmentList(), model.getFilteredEnrolmentList());
        assertEquals(expectedModel.getFilteredMemberList(), model.getFilteredMemberList());
        assertEquals(expectedModel.getFilteredEventList(), model.getFilteredEventList());
    }

    @Test
    public void execute_indexSpecifiedMemberListIsFiltered_showsMembersInEvent() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);
        showMemberAtIndex(expectedModel, INDEX_FIRST_MEMBER);

        showEventsOfMemberAtIndex(expectedModel, INDEX_FIRST_MEMBER);
        showEventsOfMemberAtIndex(model, INDEX_FIRST_MEMBER);

        assertEquals(expectedModel.getFilteredEnrolmentList(), model.getFilteredEnrolmentList());
        assertEquals(expectedModel.getFilteredMemberList(), model.getFilteredMemberList());
        assertEquals(expectedModel.getFilteredEventList(), model.getFilteredEventList());
    }

    @Test
    public void execute_indexSpecifiedEventListIsFiltered_showsMembersInEvent() {
        showEventAtIndex(model, INDEX_SECOND_EVENT);
        showEventAtIndex(expectedModel, INDEX_SECOND_EVENT);

        showEventsOfMemberAtIndex(expectedModel, INDEX_FIRST_MEMBER);
        showEventsOfMemberAtIndex(model, INDEX_FIRST_MEMBER);

        assertEquals(expectedModel.getFilteredEnrolmentList(), model.getFilteredEnrolmentList());
        assertEquals(expectedModel.getFilteredMemberList(), model.getFilteredMemberList());
        assertEquals(expectedModel.getFilteredEventList(), model.getFilteredEventList());
    }
    @Test
    public void execute_invalidMemberIndex_throwsCommandException() {
        Model model = new ModelManager();
        Index targetIndex = Index.fromOneBased(1); // No events in the model
        ViewMemberCommand viewMemberCommand = new ViewMemberCommand(targetIndex);

        assertThrows(CommandException.class,
                MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX, () -> viewMemberCommand.execute(model));
    }

    @Test
    public void equals() {
        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);
        ViewMemberCommand viewMemberCommand1 = new ViewMemberCommand(index1);
        ViewMemberCommand viewMemberCommand2 = new ViewMemberCommand(index2);

        // Same object -> returns true
        assertEquals(viewMemberCommand1, viewMemberCommand1);

        // Same values -> returns true
        ViewMemberCommand viewMemberCommand1Copy = new ViewMemberCommand(index1);
        assertEquals(viewMemberCommand1, viewMemberCommand1Copy);

        // Different types -> returns false
        assertEquals(false, viewMemberCommand1.equals(1));

        // null -> returns false
        assertEquals(false, viewMemberCommand1.equals(null));

        // Different command -> returns false
        assertEquals(false, viewMemberCommand1.equals(viewMemberCommand2));
    }
    private Member showEventsOfMemberAtIndex(Model model, Index targetIndex) {
        List<Member> lastShownMemberList = model.getFilteredMemberList();
        List<Event> lastShownEventList = model.getFilteredEventList();
        List<Enrolment> enrolmentList = model.getFilteredEnrolmentList();

        Member member = lastShownMemberList.get(targetIndex.getZeroBased());
        Name memberName = member.getName();


        Collection<Name> namesCollection = new HashSet<>();
        for (Enrolment enrolment: enrolmentList) {
            if (enrolment.getMemberName().equals(memberName)) {
                Name eventName = enrolment.getEventName();
                namesCollection.add(eventName);
                for (Event event: lastShownEventList) {
                    if (event.getName().equals(eventName)) {
                        event.setHours(enrolment.getHours());
                        event.setRemark(enrolment.getRemark());
                    }
                }
            }

        }
        return member;
    }
}
