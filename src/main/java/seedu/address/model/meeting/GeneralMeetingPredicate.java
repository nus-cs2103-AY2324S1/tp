package seedu.address.model.meeting;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * The predicate class that brings together of all the other predicate class for Meeting.
 */
public class GeneralMeetingPredicate implements Predicate<Meeting> {
    private final TitleContainsKeywordsPredicate titlePredicate;
    private final LocationContainsKeywordsPredicate locationPredicate;
    private final MeetingTimeContainsPredicate meetingTimePredicate;
    private final AttendeeContainsKeywordsPredicate attendeePredicate;
    private final MeetingTagContainsKeywordsPredicate tagPredicate;

    /**
     * Constructs a predicate class that fulfills all the argument predicates
     * @param titlePredicate A predicate that test a meeting's title.
     * @param locationPredicate A predicate that test a meeting's location.
     * @param meetingTimePredicate A predicate that test a meeting's time.
     * @param attendeePredicate A predicate that test a meeting's attendees.
     * @param tagPredicate A predicate that test a meeting's tags.
     */
    public GeneralMeetingPredicate(TitleContainsKeywordsPredicate titlePredicate,
                                             LocationContainsKeywordsPredicate locationPredicate,
                                             MeetingTimeContainsPredicate meetingTimePredicate,
                                             AttendeeContainsKeywordsPredicate attendeePredicate,
                                             MeetingTagContainsKeywordsPredicate tagPredicate) {
        this.titlePredicate = titlePredicate;
        this.locationPredicate = locationPredicate;
        this.meetingTimePredicate = meetingTimePredicate;
        this.attendeePredicate = attendeePredicate;
        this.tagPredicate = tagPredicate;
    }

    /**
     * Constructs a predicate class that fulfills all the argument predicates
     * @param titleKeyWords String array that will be used to construct TitleContainsKeywordsPredicate
     * @param locationKeyWords String array that will be used to construct LocationContainsKeywordsPredicate
     * @param start The start time of MeetingTimeContainsPredicate
     * @param end the end time of MeetingTimeContainsPredicate
     * @param attendeeKeyWords String array that will be used to construct AttendeeContainsKeywordsPredicate
     * @param tagKeyWords String array that will be used to construct TagContainsKeywordsPredicate
     */
    public GeneralMeetingPredicate(String[] titleKeyWords, String[] locationKeyWords, LocalDateTime start,
                                           LocalDateTime end, String[] attendeeKeyWords, String[] tagKeyWords) {
        this.titlePredicate = new TitleContainsKeywordsPredicate(Arrays.asList(titleKeyWords));
        this.locationPredicate = new LocationContainsKeywordsPredicate(Arrays.asList(locationKeyWords));
        this.meetingTimePredicate = new MeetingTimeContainsPredicate(start, end);
        this.attendeePredicate = new AttendeeContainsKeywordsPredicate(Arrays.asList(attendeeKeyWords));
        this.tagPredicate = new MeetingTagContainsKeywordsPredicate(Arrays.asList(tagKeyWords));
    }

    @Override
    public boolean test(Meeting meeting) {
        return titlePredicate.test(meeting)
                && locationPredicate.test(meeting)
                && meetingTimePredicate.test(meeting)
                && attendeePredicate.test(meeting)
                && tagPredicate.test(meeting);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GeneralMeetingPredicate)) {
            return false;
        }

        GeneralMeetingPredicate otherGeneralMeetingPredicate = (GeneralMeetingPredicate) other;
        return titlePredicate.equals(otherGeneralMeetingPredicate.titlePredicate)
                && locationPredicate.equals(otherGeneralMeetingPredicate.locationPredicate)
                && meetingTimePredicate.equals(otherGeneralMeetingPredicate.meetingTimePredicate)
                && attendeePredicate.equals(otherGeneralMeetingPredicate.attendeePredicate)
                && tagPredicate.equals(otherGeneralMeetingPredicate.tagPredicate);
    }

}
