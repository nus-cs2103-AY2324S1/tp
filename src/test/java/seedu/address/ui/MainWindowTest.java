package seedu.address.ui;

import static org.testfx.api.FxAssert.verifyThat;

import org.junit.jupiter.api.Test;

import org.testfx.matcher.base.NodeMatchers;

public class MainWindowTest extends MainAppTest {


    @Test
    public void testFillInnerParts() {
        // verifyThat("#personListPanel", isVisible());

        // verifyThat("#appointmentListPanelPlaceholder #personListPanel", isVisible());

        // verifyThat("#statusbarPlaceholder #statusBarFooter", isVisible());

        verifyThat("#commandBoxPlaceholder", NodeMatchers.isVisible());

    }
}
