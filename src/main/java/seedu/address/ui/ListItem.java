package seedu.address.ui;

/**
 * Represents a list item in the UI.
 */
public class ListItem {
    private String title;
    private String description;
    private String mainAccessory;

    private String accessory;

    /**
     * Constructor for ListItem.
     *
     * @param title         title of the list item.
     * @param description   description of the list item.
     * @param mainAccessory main accessory of the list item.
     */

    public ListItem(String title, String description, String mainAccessory) {
        this.title = title;
        this.description = description;
        this.mainAccessory = mainAccessory;
    }

    /**
     * Constructor for ListItem.
     *
     * @param title         title of the list item.
     * @param description   description of the list item.
     * @param mainAccessory main accessory of the list item.
     * @param accessory     accessory of the list item.
     */
    public ListItem(String title, String description, String mainAccessory, String accessory) {
        this(title, description, mainAccessory);
        this.accessory = accessory;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMainAccessory() {
        return mainAccessory;
    }

    public String getAccessory() {
        return accessory;
    }
}
