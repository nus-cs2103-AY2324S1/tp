package seedu.address.logic.commands;

import java.util.Arrays;

public enum CommandWord {
    DELETE("delete"), ADD("add"), ADD_SHORTCUT("alias"),
    EDIT("edit"), EXIT("exit"), FIND("find"),
    HELP("help"), LIST("list"), VIEW("view"),
    UNDO("undo"), REDO("redo"), CLEAR("clear");

    public final String keyword;

    CommandWord(String str) {
        keyword = str;
    }

    public static boolean isCommandWord(String str) {
        return Arrays.stream(CommandWord.values()).anyMatch(commandWord -> commandWord.keyword.equals(str));
    }

}
