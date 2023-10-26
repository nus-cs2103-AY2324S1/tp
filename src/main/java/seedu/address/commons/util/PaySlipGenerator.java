package seedu.address.commons.util;

import java.io.IOException;
import java.text.DecimalFormat;
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

import seedu.address.model.person.Person;

/**
 * Generates a PDF payslip for an employee.
 */
public class PaySlipGenerator {
    private static final String TEMPLATE_PATH = "payslips/template.pdf";
    private static final String OUTPUT_DIRECTORY_PATH = "payslips/";

    /**
     * Generates a PDF payslip for an employee.
     * @param employee The employee to generate the payslip for.
     * @throws IOException If the template file cannot be found.
     */
    public static void generateReport(Person employee) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(TEMPLATE_PATH),
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
        DecimalFormat df = new DecimalFormat("0.00");
        fieldMap.put("employerName", "XXX Limited");
        fieldMap.put("employeeName", employee.getName().toString());
        fieldMap.put("basicPay", employee.getSalary().toString());
        fieldMap.put("totalDeductions", df.format(employee.getSalary().getTotalDeductions()));
        fieldMap.put("netPay", df.format(employee.getSalary().getNetSalary()));
        return fieldMap;
    }
}
