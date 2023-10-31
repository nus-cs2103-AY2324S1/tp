package seedu.ccacommander.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
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



public class ViewEventCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCcaCommander(), new UserPrefs());
        expectedModel = new ModelManager(model.getCcaCommander(), new UserPrefs());
    }

    @Test
    public void execute_indexSpecifiedListsNotFiltered_showsMembersInEvent() {
        showMembersInEventAtIndex(expectedModel, INDEX_FIRST_EVENT);

        assertEquals(expectedModel.getFilteredEnrolmentList(), model.getFilteredEnrolmentList());
        assertEquals(expectedModel.getFilteredMemberList(), model.getFilteredMemberList());
        assertEquals(expectedModel.getFilteredEventList(), model.getFilteredEventList());
    }

    @Test
    public void execute_indexSpecifiedMemberListIsFiltered_showsMembersInEvent() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);
        showMemberAtIndex(expectedModel, INDEX_FIRST_MEMBER);

        showMembersInEventAtIndex(expectedModel, INDEX_FIRST_EVENT);
        showMembersInEventAtIndex(model, INDEX_FIRST_EVENT);

        assertEquals(expectedModel.getFilteredEnrolmentList(), model.getFilteredEnrolmentList());
        assertEquals(expectedModel.getFilteredMemberList(), model.getFilteredMemberList());
        assertEquals(expectedModel.getFilteredEventList(), model.getFilteredEventList());
    }

    @Test
    public void execute_indexSpecifiedEventListIsFiltered_showsMembersInEvent() {
        showEventAtIndex(model, INDEX_SECOND_EVENT);
        showEventAtIndex(expectedModel, INDEX_SECOND_EVENT);

        showMembersInEventAtIndex(expectedModel, INDEX_FIRST_EVENT);
        showMembersInEventAtIndex(model, INDEX_FIRST_EVENT);

        assertEquals(expectedModel.getFilteredEnrolmentList(), model.getFilteredEnrolmentList());
        assertEquals(expectedModel.getFilteredMemberList(), model.getFilteredMemberList());
        assertEquals(expectedModel.getFilteredEventList(), model.getFilteredEventList());
    }

    @Test
    public void execute_invalidEventIndex_throwsCommandException() {
        Model model = new ModelManager();
        Index targetIndex = Index.fromOneBased(1); // No events in the model
        ViewEventCommand viewEventCommand = new ViewEventCommand(targetIndex);

        assertThrows(CommandException.class,
                MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, () -> viewEventCommand.execute(model));
    }

    @Test
    public void equals() {
        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);
        ViewEventCommand viewEventCommand1 = new ViewEventCommand(index1);
        ViewEventCommand viewEventCommand2 = new ViewEventCommand(index2);

        // Same object -> returns true
        assertEquals(viewEventCommand1, viewEventCommand1);

        // Same values -> returns true
        ViewEventCommand viewEventCommand1Copy = new ViewEventCommand(index1);
        assertEquals(viewEventCommand1, viewEventCommand1Copy);

        // Different types -> returns false
        assertEquals(false, viewEventCommand1.equals(1));

        // null -> returns false
        assertEquals(false, viewEventCommand1.equals(null));

        // Different command -> returns false
        assertEquals(false, viewEventCommand1.equals(viewEventCommand2));
    }
    /**
     * Updates {@code model}'s {@code filteredMembers} list to show only the members in the {@code Event}
     * as specified by the given {@code eventIndex} in the {@code model}'s displayed event list.
     */
    private Event showMembersInEventAtIndex(Model model, Index targetIndex) {
        List<Member> lastShownMemberList = model.getFilteredMemberList();
        List<Event> lastShownEventList = model.getFilteredEventList();
        List<Enrolment> enrolmentList = model.getFilteredEnrolmentList();

        Event event = lastShownEventList.get(targetIndex.getZeroBased());
        Name eventName = event.getName();

        // loop through enrolment list, check if each enrolment.getEventName() = event.getName()
        // then add enrolment.getName() to
        // Collection<Name> memberNames
        Collection<Name> namesCollection = new HashSet<>();
        for (Enrolment enrolment: enrolmentList) {
            if (enrolment.getEventName().equals(eventName)) {
                Name memName = enrolment.getMemberName();
                namesCollection.add(memName);
                for (Member member: lastShownMemberList) {
                    if (member.getName().equals(memName)) {
                        member.setHours(enrolment.getHours());
                        member.setRemark(enrolment.getRemark());
                    }
                }
            }

        }
        return event;
    }

}
