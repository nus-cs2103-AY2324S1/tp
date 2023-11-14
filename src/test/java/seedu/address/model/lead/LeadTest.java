package seedu.address.model.lead;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.lead.ColdLead;
import seedu.address.model.person.lead.HotLead;
import seedu.address.model.person.lead.Lead;
import seedu.address.model.person.lead.UnknownLead;
import seedu.address.model.person.lead.WarmLead;

public class LeadTest {

    /**
     * Creating leads
     */
    // EP: valid string: hot
    @Test
    public void leadCreation_validHotLeadString_returnsLead() {
        Lead hotLead = Lead.of("hot");
        assertEquals(HotLead.getInstance(), hotLead);
    }

    // EP: valid string: warm
    @Test
    public void leadCreation_validWarmLeadString_returnsLead() {
        Lead warmLead = Lead.of("warm");
        assertEquals(WarmLead.getInstance(), warmLead);
    }

    // EP: valid string: cold
    @Test
    public void leadCreation_validColdLeadString_returnsLead() {
        Lead coldLead = Lead.of("cold");
        assertEquals(ColdLead.getInstance(), coldLead);
    }

    // EP: valid string: unknown
    @Test
    public void leadCreation_validUnknownLeadString_returnsLead() {
        Lead unknownLead = Lead.of("unknown");
        assertEquals(UnknownLead.getInstance(), unknownLead);
    }

    // EP: valid string: mixed capitalization
    @Test
    public void leadCreation_validMixedLeadString_returnsLead() {
        Lead hotLead = Lead.of("hOT");
        assertEquals(HotLead.getInstance(), hotLead);
    }

    // EP: valid string: uppercase
    @Test
    public void leadCreation_validUppercaseLeadString_returnsLead() {
        Lead coldLead = Lead.of("COLD");
        assertEquals(ColdLead.getInstance(), coldLead);
    }

    // EP: null input
    @Test
    public void leadCreation_nullLeadString_throwsAssertionError() {
        String lead = null;
        String expectedMessage = "Lead should take non-null values";
        assertThrows(AssertionError.class, expectedMessage, () -> Lead.of(lead));
    }

    // EP: invalid strings
    @Test
    public void leadCreation_invalidLeadString_throwsIllegalArgumentException() {
        String lead = "medium";
        String expectedMessage = Lead.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, () -> Lead.of(lead));
    }

    /**
     * isValidLead
     */

    // EP: valid lead strings
    @Test
    public void isValidLead_validLeadString_returnsTrue() {
        assertTrue(Lead.isValidLead("hot"));
        assertTrue(Lead.isValidLead("warm"));
        assertTrue(Lead.isValidLead("cold"));
        assertTrue(Lead.isValidLead("unknown"));
    }

    // EP: valid lead strings with mixed capitalization
    @Test
    public void isValidLead_validMixedLeadString_returnsTrue() {
        assertTrue(Lead.isValidLead("Hot"));
        assertTrue(Lead.isValidLead("WaRM"));
        assertTrue(Lead.isValidLead("cOLd"));
        assertTrue(Lead.isValidLead("uNkNowN"));
    }

    // EP: valid uppercase lead strings
    @Test
    public void isValidLead_validUppercaseLeadString_returnsTrue() {
        assertTrue(Lead.isValidLead("HOT"));
        assertTrue(Lead.isValidLead("WARm"));
        assertTrue(Lead.isValidLead("COLD"));
        assertTrue(Lead.isValidLead("UNKNOWN"));
    }

    // EP: null string
    @Test
    public void isValidLead_nullLeadString_throwsAssertionError() {
        String lead = null;
        String expectedMessage = "lead should not be null";
        assertThrows(AssertionError.class, expectedMessage, () -> Lead.isValidLead(lead));
    }

    // EP: invalid string
    @Test
    public void isValidLead_invalidLeadString_returnsFalse() {
        assertFalse(Lead.isValidLead("medium"));
        assertFalse(Lead.isValidLead("warmcold"));
        assertFalse(Lead.isValidLead("hothot"));
        assertFalse(Lead.isValidLead("invalid"));
        assertFalse(Lead.isValidLead(""));
        assertFalse(Lead.isValidLead(" "));
    }

    /**
     * isHot
     */

    // EP: hot leads
    @Test
    public void isHot_hotLead_returnsTrue() {
        assertTrue(HotLead.getInstance().isHot());
    }

    // EP: non-hot leads
    @Test
    public void isHot_nonHotLeads_returnsFalse() {
        assertFalse(WarmLead.getInstance().isHot());
        assertFalse(UnknownLead.getInstance().isHot());
    }

    /**
     * isWarm
     */

    // EP: warm leads
    @Test
    public void isWarm_warmLead_returnsTrue() {
        assertTrue(WarmLead.getInstance().isWarm());
    }

    // EP: non-warm leads
    @Test
    public void isWarm_nonWarmLeads_returnsFalse() {
        assertFalse(ColdLead.getInstance().isWarm());
        assertFalse(HotLead.getInstance().isWarm());
    }

    /**
     * isCold
     */

    // EP: cold leads
    @Test
    public void isCold_coldLead_returnsTrue() {
        assertTrue(ColdLead.getInstance().isCold());
    }

    // EP: non-cold leads
    @Test
    public void isCold_nonColdLeads_returnsFalse() {
        assertFalse(UnknownLead.getInstance().isCold());
        assertFalse(WarmLead.getInstance().isCold());
    }

    /**
     * String representation
     */

    // EP: hot leads
    @Test
    public void toString_hotLead_returnsHot() {
        assertEquals("hot", HotLead.getInstance().toString());
    }

    // EP: warm leads
    @Test
    public void toString_warmLead_returnsWarm() {
        assertEquals("warm", WarmLead.getInstance().toString());
    }


    // EP: cold leads
    @Test
    public void toString_coldLead_returnsCold() {
        assertEquals("cold", ColdLead.getInstance().toString());
    }

    // EP: unknown leads
    @Test
    public void toString_unknownLead_returnsUnknown() {
        assertEquals("unknown", UnknownLead.getInstance().toString());
    }

    /**
     * Follow up period
     */

    // EP: hot leads
    @Test
    public void getFollowUpPeriod_hotLead_returnsCorrectPeriod() {
        assertEquals(HotLead.FOLLOW_UP_PERIOD, HotLead.getInstance().getFollowUpPeriod());
    }

    // EP: warm leads
    @Test
    public void getFollowUpPeriod_warmLead_returnsCorrectPeriod() {
        assertEquals(WarmLead.FOLLOW_UP_PERIOD, WarmLead.getInstance().getFollowUpPeriod());
    }

    // EP: cold leads
    @Test
    public void getFollowUpPeriod_coldLead_returnsCorrectPeriod() {
        assertEquals(ColdLead.FOLLOW_UP_PERIOD, ColdLead.getInstance().getFollowUpPeriod());
    }

    // EP: hot leads
    @Test
    public void getFollowUpPeriod_unknownLead_returnsCorrectPeriod() {
        assertEquals(UnknownLead.FOLLOW_UP_PERIOD, UnknownLead.getInstance().getFollowUpPeriod());
    }
}
