package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ConsultationListBook;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationList;

/**
 * A utility class containing a list of {@code Consultation} objects to be used in tests.
 */
public class TypicalConsultations {

    public static final Consultation CONSULTATION1 = new ConsultationBuilder()
            .withDate("11/11/2002")
            .withTime("10:00")
            .withStudents(TypicalPersons.FIONA)
            .build();

    public static final Consultation CONSULTATION2 = new ConsultationBuilder()
            .withDate("12/12/2022")
            .withTime("19:00")
            .withStudents(TypicalPersons.ALICE, TypicalPersons.GEORGE)
            .build();

    public static final Consultation CONSULTATION3 = new ConsultationBuilder()
            .withDate("03/07/2023")
            .withTime("12:00")
            .withStudents(TypicalPersons.CARL, TypicalPersons.FIONA, TypicalPersons.BENSON)
            .build();

    public static final Consultation CONSULTATION4 = new ConsultationBuilder()
            .withDate("03/03/2023")
            .withTime("13:30")
            .withStudents(TypicalPersons.ELLE)
            .build();

    public static final Consultation CONSULTATION_UNKNOWN_PERSON = new ConsultationBuilder()
            .withDate("03/03/2023")
            .withTime("13:30")
            .withStudents(TypicalPersons.AMY)
            .build();

    public static final Consultation CONSULTATION_TO_REMOVE_FROM = new ConsultationBuilder()
            .withDate("12/12/2022")
            .withTime("19:00")
            .withStudents(TypicalPersons.ALICE, TypicalPersons.GEORGE, TypicalPersons.FIONA)
            .build();
    public static final Consultation CONSULTATION_WITH_STUDENTS_TO_REMOVE = new ConsultationBuilder()
            .withDate("12/12/2022")
            .withTime("19:00")
            .withStudents(TypicalPersons.ALICE)
            .build();

    private TypicalConsultations() {} // prevents initialising

    public static ConsultationListBook getTypicalConsultationListBook() {
        ConsultationListBook clb = new ConsultationListBook();
        for (Consultation consultation : getTypicalConsultations()) {
            clb.addConsultation(consultation);
        }
        return clb;
    }

    public static ConsultationList getTypicalConsultations() {
        ConsultationList typicalConsultations = new ConsultationList();
        List<Consultation> consultationList = new ArrayList<>(Arrays.asList(CONSULTATION1, CONSULTATION2,
                CONSULTATION_TO_REMOVE_FROM));
        typicalConsultations.setConsultationList(consultationList);
        return typicalConsultations;
    }


}
