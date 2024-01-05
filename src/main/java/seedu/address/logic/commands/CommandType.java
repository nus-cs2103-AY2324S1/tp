package seedu.address.logic.commands;
/**
 * Enum representing the type of commands.
 */
public enum CommandType {
    /**
     * Command type for graphical display
     */
    ADD,
    ADD_TASK,
    ADD_TO_CONSULT,
    CLEAR,
    CREATE_CONSULT,
    CREATE_SESSION,
    DELETE,
    DELETE_COMMENT,
    DELETE_CONSULT,
    DELETE_GRADE,
    DELETE_SESSION,
    DELETE_TASK,
    EDIT,
    EDIT_COMMENT,
    EDIT_GRADE,
    EDIT_GRADED_TEST,
    EXIT,
    FIND,
    HELP,
    LIST,
    REMOVE_FROM_CONSULT,
    TAB,
    TAKE_ATTENDANCE,
    UPDATE_SESSION_REMARK,
    UPDATE_TASK_PROGRESS,
    VIEW_ALL_ASSIGNMENTS,
    VIEW_ASSIGNMENTS,
    VIEW_ATTENDANCE,
    VIEW_TASKS;
}
