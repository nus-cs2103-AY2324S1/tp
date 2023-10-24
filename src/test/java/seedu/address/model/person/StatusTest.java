package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StatusTest {


    @Test
    public void toStringMethod() {
        assertEquals(new Status().toString(), "Preliminary");
    }

    @Test
    public void constructorTestingOffered() {
        assertEquals(new Status("offered"), new Status(StatusTypes.OFFERED));
    }

    @Test
    public void constructorTestingPrelim() {
        assertEquals(new Status("preliminary"), new Status(StatusTypes.PRELIMINARY));
    }
    @Test
    public void constructorTestingInt() {
        assertEquals(new Status("interviewed"),
            new Status(StatusTypes.INTERVIEWED));
    }
    @Test
    public void constructorTestingRej() {
        assertEquals(new Status("rejected"),
            new Status(StatusTypes.REJECTED));
    }

    @Test
    public void getStatusTypeTestR() {
        assertEquals(new Status("rejected").getStatusType(),
            StatusTypes.REJECTED);
    }
    @Test
    public void getStatusTypeTestI() {
        assertEquals(new Status("interviewed").getStatusType(),
            StatusTypes.INTERVIEWED);
    }
    @Test
    public void getStatusTypeTestP() {
        assertEquals(new Status("preliminary").getStatusType(),
            StatusTypes.REJECTED);
    }
    @Test
    public void getStatusTypeTestO() {
        assertEquals(new Status("offered").getStatusType(),
            StatusTypes.OFFERED);
    }
    @Test
    public void setStatusTypeTest() {
        Status testStatus = new Status();
        testStatus.setStatusType(StatusTypes.INTERVIEWED);
        assertEquals(testStatus.getStatusType(), StatusTypes.INTERVIEWED);
    }
}
