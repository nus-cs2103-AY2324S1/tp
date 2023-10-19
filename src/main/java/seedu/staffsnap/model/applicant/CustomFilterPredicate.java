package seedu.staffsnap.model.applicant;

import seedu.staffsnap.commons.util.StringUtil;
import seedu.staffsnap.model.interview.Interview;

import java.util.Set;
import java.util.function.Predicate;

public class CustomFilterPredicate implements Predicate<Applicant> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    // Data fields
    private final Email email;
    private final Position position;
    private final Set<Interview> interviews;

    /**
     * @param applicant the input argument
     * @return true if applicant matches all specified fields in the predicate
     */
    @Override
    public boolean test(Applicant applicant) {
        if (this.name != null) {
            if (!StringUtil.containsStringIgnoreCase(applicant.getName().toString(), this.name.toString())) {
                return false;
            }
        }
        if (this.phone != null) {
            if (!StringUtil.containsWordIgnoreCase(applicant.getPhone().toString(), this.phone.toString())) {
                return false;
            }
        }
        if (this.email != null) {
            if (!StringUtil.containsWordIgnoreCase(applicant.getEmail().toString(), this.email.toString())) {
                return false;
            }
        }
        if (this.position != null) {
            if (!StringUtil.containsWordIgnoreCase(applicant.getPosition().toString(), this.position.toString())) {
                return false;
            }
        }
        /*
         * TODO:
         * add filtering for interviews
         */
        return true;
    }

    public CustomFilterPredicate(Name name, Phone phone, Email email, Position position, Set<Interview> interviews) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.interviews = interviews;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "CustomFilterPredicate{" +
                "name=" + name +
                ", phone=" + phone +
                ", email=" + email +
                ", position=" + position +
                ", interviews=" + interviews +
                '}';
    }
}
