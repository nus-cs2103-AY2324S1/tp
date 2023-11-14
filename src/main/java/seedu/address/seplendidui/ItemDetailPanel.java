package seedu.address.seplendidui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.SeplendidLogic;
import seedu.address.model.LocalCourseCatalogue;
import seedu.address.model.PartnerCourseCatalogue;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.note.Note;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.University;

/**
 * Panel containing the details of an item.
 */
public class ItemDetailPanel<SeplendidDataType> extends UiPart<Region> {
    private static final String MESSAGE_MAPPING_ITEM_DETAIL_PANEL_ERROR = "Error with mapping item detail panel.";
    private static final String FXML = "ItemDetailPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItemDetailPanel.class);

    // For access to catalogues
    private SeplendidLogic seplendidLogic;

    @FXML
    private VBox itemDetailView;

    // TODO: Decide if detail panel actually needs seplendid logic
    /**
     * Creates a {@code ItemDetailPanel}.
     */
    public ItemDetailPanel(SeplendidLogic seplendidLogic) {
        super(FXML);
        this.seplendidLogic = seplendidLogic;
    }

    /**
     * Set the details of the selected item.
     *
     * @param item The selected item
     */
    public void setItemDetail(SeplendidDataType item) {
        if (item == null) {
            itemDetailView.getChildren().clear();
            return;
        }

        // Directly set the item to the container
        if (item instanceof LocalCourse) {
            itemDetailView.getChildren().setAll(new LocalCourseDetail((LocalCourse) item).getRoot());
        } else if (item instanceof PartnerCourse) {
            itemDetailView.getChildren().setAll(new PartnerCourseDetail((PartnerCourse) item).getRoot());
        } else if (item instanceof University) {
            itemDetailView.getChildren().setAll(new UniversityDetail((University) item).getRoot());
        } else if (item instanceof Mapping) {
            Mapping mapping = (Mapping) item;
            // We know that RTT of getLocalCourseCatalogue() is LocalCourseCatalogue.
            LocalCourseCatalogue localCourseCatalogue =
                    (LocalCourseCatalogue) seplendidLogic.getLocalCourseCatalogue();
            Optional<LocalCourse> localCourse = localCourseCatalogue.getLocalCourseIfExists(mapping.getLocalCode());
            // We know that RTT of getPartnerCourseCatalogue() is PartnerCourseCatalogue.
            PartnerCourseCatalogue partnerCourseCatalogue =
                    (PartnerCourseCatalogue) seplendidLogic.getPartnerCourseCatalogue();
            Optional<PartnerCourse> partnerCourse =
                    partnerCourseCatalogue.getPartnerCourseIfExists(
                            mapping.getPartnerCode(), mapping.getUniversityName());
            if (localCourse.isEmpty() || partnerCourse.isEmpty()) {
                logger.warning(MESSAGE_MAPPING_ITEM_DETAIL_PANEL_ERROR);
                return;
            }

            itemDetailView.getChildren().setAll(new MappingDetail(
                    mapping,
                    localCourse.get().getLocalName().toString(),
                    localCourse.get().getLocalUnit().toString(),
                    partnerCourse.get().getPartnerName().toString(),
                    partnerCourse.get().getPartnerUnit().toString()).getRoot());
        } else if (item instanceof Note) {
            itemDetailView.getChildren().setAll(new NoteDetail((Note) item).getRoot());
        }
    }
}
