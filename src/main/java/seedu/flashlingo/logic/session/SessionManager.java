package seedu.flashlingo.logic.session;

/**
 * The SessionManager class implements the Session interface and is responsible for managing language learning sessions.
 */
public class SessionManager {
    private static SessionManager instance = null;
    private static boolean isReviewSession = false;

    private SessionManager() {
        // Private constructor to prevent external instantiation.
    }
    /**
     * Returns the instance of the SessionManager class.
     * @return The instance of the SessionManager class.
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    /**
     * Sets the session to be a review session or not.
     * @param editedReviewSession The boolean value to set the session to.
     */
    public void setSession(boolean editedReviewSession) {
        isReviewSession = editedReviewSession;
    }
    /**
     * Returns whether the session is a review session.
     * @return The boolean value of whether the session is a review session.
     */
    public boolean isReviewSession() {
        return isReviewSession;
    }
}

