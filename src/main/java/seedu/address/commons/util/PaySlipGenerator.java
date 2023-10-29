package seedu.address.commons.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    private static final InputStream TEMPLATE = PaySlipGenerator.class.getClassLoader()
        .getResourceAsStream("template.pdf");

    /**
     * Generates a PDF payslip for an employee.
     * @param employee The employee to generate the payslip for.
     * @throws IOException If the template file cannot be found.
     */
    public static void generateReport(Person employee) throws IOException {
        File dir = new File(OUTPUT_DIRECTORY_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }

        PdfDocument pdfDocument = new PdfDocument(new PdfReader(TEMPLATE),
            new PdfWriter(OUTPUT_DIRECTORY_PATH + getFileName(employee)));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, false);
        PdfFont font = PdfFontFactory.createFont();

        fillTextFields(form, getFieldMap(employee), font);
        form.flattenFields();
        pdfDocument.close();
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
     * @return The map of field names to values.
     */
    public static Map<String, String> getFieldMap(Person employee) {
        Map<String, String> fieldMap = new HashMap<>();
        Payroll latest = employee.getLatestPayroll();
        fieldMap.put("employerName", "XXX Limited");
        fieldMap.put("employeeName", employee.getName().toString());
        fieldMap.put("basicPay", latest.getBasicSalaryString());
        fieldMap.put("totalDeductions", latest.getTotalDeductionsString());
        fieldMap.put("netPay", latest.getNetSalaryString());
        fieldMap.put("totalAllowances", latest.getTotalAllowancesExceptBonusesString());
        fieldMap.put("grossPay", latest.getGrossPayString());
        fieldMap.put("CPFDeduction", latest.getEmployeeCpfDeductionsString());

        if (Double.parseDouble(latest.getAnnualBonusesString()) > 0.0) {
            fieldMap.put("otherAdditionalPayments", latest.getAnnualBonusesString());
            fieldMap.put("additionalPaymentReason1", "Annual Bonus");
            fieldMap.put("additionalPayment1", latest.getAnnualBonusesString());
        } else {
            fieldMap.put("otherAdditionalPayments", "0.00");
        }

        if (Double.parseDouble(latest.getTotalDeductionsString())
            > Double.parseDouble(latest.getEmployeeCpfDeductionsString())) {
            String deductionReason1 = "";
            String deduction1 = "";

            if (Double.parseDouble(latest.getAbsencesString()) != 0.0) {
                deductionReason1 += "Absence\n";
                deduction1 += latest.getAbsencesString() + "\n";
            }

            if (Double.parseDouble(latest.getNoPayLeavesString()) != 0.0) {
                deductionReason1 += "No-Pay Leave\n";
                deduction1 += latest.getNoPayLeavesString() + "\n";
            }

            if (!deductionReason1.isEmpty()) {
                fieldMap.put("deductionReason1", deductionReason1);
                fieldMap.put("deduction1", deduction1);
            }
        }

        if (Double.parseDouble(latest.getTotalAllowancesExceptBonusesString()) != 0.0) {
            if (Double.parseDouble(latest.getTransportAllowancesString()) != 0.0) {
                fieldMap.put("allowanceReason1", "Transport Allowance");
                fieldMap.put("allowance1", latest.getTransportAllowancesString());
            }
        }

        return fieldMap;
    }
}
