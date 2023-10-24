package seedu.ccacommander.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.ModelManager;
import seedu.ccacommander.model.UserPrefs;
import seedu.ccacommander.model.attendance.Attendance;
import seedu.ccacommander.model.attendance.Hours;
import seedu.ccacommander.model.attendance.Remark;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.testutil.AttendanceBuilder;
import seedu.ccacommander.testutil.EventBuilder;

import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ccacommander.testutil.TypicalAttendances.ALICE_AURORA;
import static seedu.ccacommander.testutil.TypicalAttendances.BENSON_BOXING;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalCcaCommander;

/**
 * Contains integration tests (interaction with the Model) for {@code AddMemberCommand}.
 */
public class AddMemberCommandIntegrationTest {
    final Index VALID_INDEX_ONE = Index.fromOneBased(1);
    final Index VALID_INDEX_TWO = Index.fromOneBased(2);
    final Hours VALID_HOURS_A = ALICE_AURORA.getHours();
    final Hours VALID_HOURS_B = BENSON_BOXING.getHours();
    final Remark VALID_REMARK_A = ALICE_AURORA.getRemark();
    final Remark VALID_REMARK_B = BENSON_BOXING.getRemark();

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCcaCommander(), new UserPrefs());
    }

    @Test
    public void execute_newAttendance_success() {
        Attendance validAttendance = new AttendanceBuilder().withMemberName(ALICE_AURORA.getMemberName().name)
                .withEventName(BENSON_BOXING.getEventName().name)
                .withHours(ALICE_AURORA.getHours().value.toString())
                .withRemark(ALICE_AURORA.getRemark().value)
                .build();

        Model expectedModel = new ModelManager(model.getCcaCommander(), new UserPrefs());
        expectedModel.createAttendance(validAttendance);

        assertCommandSuccess(new AddMemberCommand(VALID_INDEX_ONE, VALID_INDEX_TWO, VALID_HOURS_A, VALID_REMARK_A),
                model, String.format(AddMemberCommand.MESSAGE_SUCCESS, Messages.format(validAttendance)),
                expectedModel);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        assertCommandFailure(new AddMemberCommand(VALID_INDEX_ONE, VALID_INDEX_ONE, VALID_HOURS_A, VALID_REMARK_A),
                model, AddMemberCommand.MESSAGE_DUPLICATE_ATTENDANCE);
    }

}
