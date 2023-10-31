package seedu.address.commons.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import seedu.address.model.person.Payroll;
import seedu.address.model.person.Person;

/**
 * Generates a PDF payslip for an employee.
 */
public class PaySlipGenerator {
    private static final String OUTPUT_DIRECTORY_PATH = "./payslips/";
    private static final String TEMPLATE = PaySlipGenerator.class.getClassLoader()
            .getResource("template.pdf").toString();

    /**
     * Generates a PDF payslip for an employee.
     * @param employee The employee to generate the payslip for.
     * @throws IOException If the template file cannot be found.
     */
    public static void generateReport(Person employee) throws IOException {
        createOutputDirectory();

        PdfDocument pdfDocument = new PdfDocument(new PdfReader(TEMPLATE),
            new PdfWriter(OUTPUT_DIRECTORY_PATH + getFileName(employee)));

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, false);
        PdfFont font = PdfFontFactory.createFont();

        fillTextFields(form, getFieldMap(employee, employee.getLatestPayroll()), font);
        form.flattenFields();

        pdfDocument.close();
    }

    /**
     * Generates a PDF payslip for an employee.
     * @param employee The employee to generate the payslip for.
     * @param monthYear The month and year of the payslip.
     * @throws IOException If the template file cannot be found.
     */
    public static void generateReportWithMonth(Person employee, LocalDate monthYear) throws IOException {
        createOutputDirectory();

        PdfDocument pdfDocument = new PdfDocument(new PdfReader(TEMPLATE),
            new PdfWriter(OUTPUT_DIRECTORY_PATH + getFileName(employee)));

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, false);
        PdfFont font = PdfFontFactory.createFont();

        fillTextFields(form, getFieldMap(employee, employee.getPayrollWithStartDate(monthYear)), font);
        form.flattenFields();

        pdfDocument.close();
    }

    /**
     * Creates the output directory if it does not exist.
     */
    public static void createOutputDirectory() {
        File dir = new File(OUTPUT_DIRECTORY_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    /**
     * Generates the file name.
     * @param employee The employee to generate the payslip for.
     */
    public static String getFileName(Person employee) {
        String employeeName = employee.getName().toString();
        ArrayList<String> employeeNameList = new ArrayList<>();
        String[] employeeNameArray = employeeName.toLowerCase().split(" ");
        Collections.addAll(employeeNameList, employeeNameArray);
        return String.join("_", employeeNameList) + ".pdf";
    }

    /**
     * Fills the text fields in the PDF payslip.
     * @param form The PDF form.
     * @param fieldMap The map of field names to values.
     * @param font The font to use.
     */
    public static void fillTextFields(PdfAcroForm form, Map<String, String> fieldMap, PdfFont font) {
        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            form.getFormFields().get(entry.getKey()).setValue(entry.getValue())
                .setFontSize(12).setFont(font);
        }
    }

    /**
     * Returns the map of field names to values.
     * @param employee The employee to generate the payslip for.
     * @param payroll The payroll of the employee.
     * @return The map of field names to values.
     */
    public static Map<String, String> getFieldMap(Person employee, Payroll payroll) {
        Map<String, String> fieldMap = new HashMap<>();

        fieldMap.put("employerName", "XXX Limited");
        fieldMap.put("employeeName", employee.getName().toString());
        fieldMap.put("basicPay", payroll.getBasicSalaryString());
        fieldMap.put("totalDeductions", payroll.getTotalDeductionsString());
        fieldMap.put("netPay", payroll.getNetSalaryString());
        fieldMap.put("totalAllowances", payroll.getTotalAllowancesExceptBonusesString());
        fieldMap.put("grossPay", payroll.getGrossPayString());
        fieldMap.put("CPFDeduction", payroll.getEmployeeCpfDeductionsString());

        if (Double.parseDouble(payroll.getAnnualBonusesString()) > 0.0) {
            fieldMap.put("otherAdditionalPayments", payroll.getAnnualBonusesString());
            fieldMap.put("additionalPaymentReason1", "Annual Bonus");
            fieldMap.put("additionalPayment1", payroll.getAnnualBonusesString());
        } else {
            fieldMap.put("otherAdditionalPayments", "0.00");
        }

        if (Double.parseDouble(payroll.getTotalDeductionsString())
            > Double.parseDouble(payroll.getEmployeeCpfDeductionsString())) {
            String deductionReason1 = "";
            String deduction1 = "";

            if (Double.parseDouble(payroll.getAbsencesString()) != 0.0) {
                deductionReason1 += "Absence\n";
                deduction1 += payroll.getAbsencesString() + "\n";
            }

            if (Double.parseDouble(payroll.getNoPayLeavesString()) != 0.0) {
                deductionReason1 += "No-Pay Leave\n";
                deduction1 += payroll.getNoPayLeavesString() + "\n";
            }

            if (!deductionReason1.isEmpty()) {
                fieldMap.put("deductionReason1", deductionReason1);
                fieldMap.put("deduction1", deduction1);
            }
        }

        if (Double.parseDouble(payroll.getTotalAllowancesExceptBonusesString()) != 0.0) {
            if (Double.parseDouble(payroll.getTransportAllowancesString()) != 0.0) {
                fieldMap.put("allowanceReason1", "Transport Allowance");
                fieldMap.put("allowance1", payroll.getTransportAllowancesString());
            }
        }

        fieldMap.put("paymentStartDate", payroll.getStartDateString());
        fieldMap.put("paymentEndDate", payroll.getEndDateString());
        fieldMap.put("dateOfPayment", payroll.getPaymentDateString());
        return fieldMap;
    }

    /**
     * Removes the payslip file of an employee.
     * @param employeeToGenerate The employee to remove the payslip for.
     */
    public static void removeWrongFile(Person employeeToGenerate) {
        File payslip = new File("payslips/" + PaySlipGenerator.getFileName(employeeToGenerate));
        if (payslip.exists()) {
            payslip.delete();
        }
    }
}
