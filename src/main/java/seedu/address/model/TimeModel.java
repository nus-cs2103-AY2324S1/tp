package seedu.address.model;

import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimeModel {
    /**
     * Returns the interviews which clash with the current interview
     *
     * @author Tan Kerway
     * @param startTime the start time of the interview that the user wants to schedule
     * @param endTime the end time of the interview that the user wants to schedule
     * @param interviews the list of interviews that the user has already
     *                   scheduled
     * @return a list of interviews that clash with the interview to be
     *         scheduled
     */
    public static List<Interview> listInterviewClashes(LocalDateTime startTime,
                                                       LocalDateTime endTime, UniqueInterviewList interviews) {
        List<Interview> res = new ArrayList<>();
        for (Interview currentInterview : interviews) {
            // get the start time of the current interview
            LocalDateTime currentInterviewStartTime = currentInterview.getInterviewStartTime();
            // get the end time of the current interview
            LocalDateTime currentInterviewEndTime = currentInterview.getInterviewEndTime();
            // case 1: the current interview is completely within the interview to be added
            boolean completelyInside = (currentInterviewStartTime.isAfter(startTime)
                    && currentInterviewStartTime.isBefore(endTime))
                    && (currentInterviewEndTime.isAfter(startTime)
                    && currentInterviewEndTime.isBefore(endTime));
            // case 2: the interview to be added is completely within the current interview
            boolean completelyOutside = currentInterviewStartTime.isBefore(startTime)
                    && currentInterviewEndTime.isAfter(endTime);
            // case 3: the current interview end time falls within the interview to be added
            boolean endInside = currentInterviewEndTime.isAfter(startTime)
                    && currentInterviewEndTime.isBefore(endTime);
            // case 4: the current interview start time falls within the interview to be added
            boolean startInside = currentInterviewStartTime.isAfter(startTime)
                    && currentInterviewStartTime.isBefore(endTime);
            if (completelyInside || completelyOutside || endInside || startInside) {
                res.add(currentInterview);
            }
        }
        return res;
    }

    /**
     * Lists out all interviews that have a start time of today
     *
     * @author Tan Kerway
     * @param interviews the list of interviews that the user has
     * @return a list of interviews whose start time is today, as given by LocalDateTime.now()
     */
    public static List<Interview> listInterviewsToday(UniqueInterviewList interviews) {
        // get today's day, month, and year for checking
        LocalDateTime today = LocalDateTime.now();
        int todayDay = today.getDayOfMonth();
        int todayMonth = today.getMonthValue();
        int todayYear = today.getYear();
        List<Interview> res = new ArrayList<>();
        // loop over all the interviews, and add those that have today as the start time
        for (Interview interview : interviews) {
            LocalDateTime currentInterviewStartTime = interview.getInterviewStartTime();
            int currentInterviewDay = currentInterviewStartTime.getDayOfMonth();
            int currentInterviewMonth = currentInterviewStartTime.getMonthValue();
            int currentInterviewYear = currentInterviewStartTime.getYear();
            if (currentInterviewDay == todayDay
                    && currentInterviewMonth == todayMonth
                    && currentInterviewYear == todayYear) {
                res.add(interview); // add the current interview if its start date is today
            }
        }
        return res;
    }

    /**
     * Compiles a list of free times that the user has. Each element is a 2-element list where the
     * first element is the start of the free time block, and the second element is the end of the
     * free time block. Only places interviews that are within a 9-5 workday. Assumes that the given
     * interview list has no clashes. Also assumes that the start time of any scheduled interviews are less
     * than or equals to their corresponding end time.
     *
     * @author Tan Kerway
     * @param day the day that the user inputs
     * @param interviewList the list of interviews that the user has
     * @return a list of free time blocks that the user has on a given day
     */
    public static List<List<LocalDateTime>> listPocketsOfTimeOnGivenDay(
            LocalDateTime day,
            UniqueInterviewList interviewList) {
        // filter the interviews that fall on the given day, and sort in ascending
        // chronological order
        List<Interview> interviewsOnGivenDay =
                listInterviewsOnGivenDay(day, interviewList);
        UniqueInterviewList temp = new UniqueInterviewList();
        temp.setInterviews(interviewsOnGivenDay);
        List<Interview> interviewsOnGivenDaySorted = sortInterviewsInChronologicalAscendingOrder(temp);
        List<List<LocalDateTime>> res = new ArrayList<>();
        LocalDateTime startOfWorkDay = LocalDateTime.of(
                day.getYear(),
                day.getMonthValue(),
                day.getDayOfMonth(),
                9,
                0);
        LocalDateTime endOfWorkDay = LocalDateTime.of(
                day.getYear(),
                day.getMonthValue(),
                day.getDayOfMonth(),
                17,
                0);
        // track the previous end time of the interview
        LocalDateTime prevEnd = startOfWorkDay.plusDays(0);

        // find free time in 24h window
        for (Interview interview : interviewsOnGivenDaySorted) {
            // get the start time and end time
            LocalDateTime currentInterviewStartTime = interview.getInterviewStartTime();
            LocalDateTime currentInterviewEndTime = interview.getInterviewEndTime();
            // case 1: the workday is completely overlapped by the interview
            if (currentInterviewStartTime.isBefore(startOfWorkDay)
                    && currentInterviewEndTime.isAfter(endOfWorkDay)) {
                prevEnd = currentInterviewEndTime.plusDays(0);
                break;
            }
            // case 2: the workday is outside and before the workday
            if (currentInterviewEndTime.isBefore(startOfWorkDay)) {
                continue;
            }
            // case 3: the workday is outside and after the workday
            if (currentInterviewStartTime.isAfter(endOfWorkDay)) {
                break;
            }
            // case 4: the interview's start point is before the start of workday
            // and the interview's end point is after the start of the workday
            if (currentInterviewStartTime.isBefore(startOfWorkDay)
                    && currentInterviewEndTime.isAfter(startOfWorkDay)) {
                prevEnd = currentInterviewEndTime.plusDays(0);
                continue;
            }
            // get the current block of free time by taking the end of the last interval
            // and the start of the current interval
            List<LocalDateTime> currentFreeTime = new ArrayList<>();
            currentFreeTime.add(prevEnd);
            currentFreeTime.add(currentInterviewStartTime);
            res.add(currentFreeTime);
            prevEnd = currentInterviewEndTime;
        }

        // add stray free time, if any
        if (prevEnd.isBefore(endOfWorkDay)) {
            List<LocalDateTime> strayFreeTime = new ArrayList<>();
            strayFreeTime.add(prevEnd);
            strayFreeTime.add(endOfWorkDay);
            res.add(strayFreeTime);
        }
        return res;
    }

    /**
     * Sorts the list of interviews in ascending chronological order.
     *
     * @author Tan Kerway
     *
     */
    public static List<Interview> sortInterviewsInChronologicalAscendingOrder(UniqueInterviewList interviews) {
        List<Interview> res = new ArrayList<>();
        for (Interview interview : interviews) {
            res.add(interview);
        }
        res.sort(Comparator.comparing(Interview::getInterviewStartTime));
        return res;
    }

    /**
     * Compiles a list of interviews that the user has on a given day
     *
     * @author Tan Kerway
     *
     */
    public static List<Interview> listInterviewsOnGivenDay(LocalDateTime day, UniqueInterviewList interviews) {
        int todayDay = day.getDayOfMonth();
        int todayMonth = day.getMonthValue();
        int todayYear = day.getYear();
        List<Interview> res = new ArrayList<>();
        // loop over all the interviews, and add those that have today as the start time
        for (Interview interview : interviews) {
            LocalDateTime currentInterviewStartTime = interview.getInterviewStartTime();
            int currentInterviewDay = currentInterviewStartTime.getDayOfMonth();
            int currentInterviewMonth = currentInterviewStartTime.getMonthValue();
            int currentInterviewYear = currentInterviewStartTime.getYear();
            if (currentInterviewDay == todayDay
                    && currentInterviewMonth == todayMonth
                    && currentInterviewYear == todayYear) {
                res.add(interview); // add the current interview if its start date is today
            }
        }
        return res;
    }
}
