package seedu.address.seplendidui;

/**
 * Utility class for the UI.
 */
public class UiUtil {

    /**
     * View Model wrapping the ObservableList to display.
     */
    public enum ListViewModel {
        LOCAL_COURSE_LIST,
        PARTNER_COURSE_LIST,
        UNIVERSITY_LIST,
        NOTE_LIST,
        MAPPING_LIST,
        LOCAL_COURSE_SORT,
        PARTNER_COURSE_SORT,
        UNIVERSITY_SORT,
        MAPPING_SORT;


        @Override
        public String toString() {
            return super.toString();
        }
    }
}
