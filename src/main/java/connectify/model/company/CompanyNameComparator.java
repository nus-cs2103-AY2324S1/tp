package connectify.model.company;

import java.util.Comparator;

/**
 * Compares two {@code Company} objects by their names in alphabetical order.
 */
public class CompanyNameComparator implements Comparator<Company> {

    @Override
    public int compare(Company o1, Company o2) {
        String name1 = o1.getName().fullName;
        String name2 = o2.getName().fullName;
        return name1.compareTo(name2);
    }
}
