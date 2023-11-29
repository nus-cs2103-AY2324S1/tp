package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DENTIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALIZATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;

import java.util.Set;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddDentistCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.AddTreatmentCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditDentistCommand.EditDentistDescriptor;
import seedu.address.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.tag.Tag;
import seedu.address.model.treatment.Treatment;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add-dentist command string for adding the {@code dentist}.
     */
    public static String getAddDentistCommand(Dentist dentist) {
        return AddDentistCommand.COMMAND_WORD + " " + getDentistDetails(dentist);
    }

    /**
     * Returns an add-patient command string for adding the {@code patient}.
     */
    public static String getAddPatientCommand(Patient patient) {
        return AddPatientCommand.COMMAND_WORD + " " + getPatientDetails(patient);
    }

    /**
     * Returns an add-appointment command string for adding the {@code appointment}.
     */
    public static String getAddAppointmentCommand(Appointment appointment) {
        return AddAppointmentCommand.COMMAND_WORD + " " + getAppointmentDetails(appointment);
    }

    /**
     * Returns an add-treatment command string for adding the {@code treatment}.
     */
    public static String getAddTreatmentCommand(Treatment treatment) {
        return AddTreatmentCommand.COMMAND_WORD + " " + getTreatmentDetails(treatment);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code dentist}'s details.
     */
    public static String getDentistDetails(Dentist dentist) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + dentist.getName().fullName + " ");
        sb.append(PREFIX_PHONE + dentist.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + dentist.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + dentist.getAddress().value + " ");
        sb.append(PREFIX_SPECIALIZATION + dentist.getSpecialization().toString() + " ");
        sb.append(PREFIX_YOE + dentist.getYoe().toString() + " ");
        dentist.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code patient}'s details.
     */
    public static String getPatientDetails(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + patient.getName().fullName + " ");
        sb.append(PREFIX_PHONE + patient.getPhone().value + " ");
        sb.append(PREFIX_BIRTHDATE + patient.getBirthdate().value + " ");
        sb.append(PREFIX_GENDER + patient.getGender().value + " ");
        sb.append(PREFIX_REMARK + patient.getRemark().value + " ");
        sb.append(PREFIX_TREATMENT + patient.getTreatmentName().toString() + " ");
        sb.append(PREFIX_ADDRESS + patient.getAddress().value + " ");
        sb.append(PREFIX_EMAIL + patient.getEmail().value + " ");
        patient.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DENTIST + Long.toString(appointment.getDentistId()) + " ");
        sb.append(PREFIX_PATIENT + Long.toString(appointment.getPatientId()) + " ");
        sb.append(PREFIX_START + "2023-10-12 16:00");
        sb.append(PREFIX_TREATMENT + appointment.getTreatment() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getTreatmentDetails(Treatment treatment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TREATMENT + treatment.getName().getTreatmentName() + " ");
        sb.append(PREFIX_COST + treatment.getCost().toString() + " ");
        sb.append(PREFIX_TIME + treatment.getTime().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditDentistDescriptor}'s details.
     */
    public static String getEditDentistDescriptorDetails(EditDentistDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getSpecialization().ifPresent(
                specialization -> sb.append(PREFIX_SPECIALIZATION).append(specialization.toString()).append(" "));
        descriptor.getYoe().ifPresent(yoe -> sb.append(PREFIX_YOE).append(yoe.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getBirthdate()
                .ifPresent(birthdate -> sb.append(PREFIX_BIRTHDATE).append(birthdate.value).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.value).append(" "));
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.value).append(" "));
        descriptor.getTreatmentName()
                .ifPresent(treatmentName -> sb.append(PREFIX_TREATMENT).append(treatmentName.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
