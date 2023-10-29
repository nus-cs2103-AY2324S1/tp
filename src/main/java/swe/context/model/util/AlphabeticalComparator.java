package swe.context.model.util;

import java.util.Comparator;

import swe.context.model.contact.Contact;



/**
 * Sorts {@link Contact}s alphabetically.
 */
public class AlphabeticalComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact a, Contact b) {
        //TODO case insensitive?
        return a.getName().value.compareTo(b.getName().value);
    }
}
