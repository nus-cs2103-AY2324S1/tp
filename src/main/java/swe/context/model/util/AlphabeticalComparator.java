package swe.context.model.util;

import java.util.Comparator;

import swe.context.model.contact.Contact;



/**
 * Sorts {@link Contact}s alphabetically.
 */
public class AlphabeticalComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact a, Contact b) {
        String aName = a.getName().value;
        String bName = b.getName().value;

        int n = aName.toUpperCase().compareTo(bName.toUpperCase());
        if (n != 0) {
            return n;
        }

        // Only take true capitalization into account if names would otherwise
        // be identical
        return aName.compareTo(bName);
    }
}
