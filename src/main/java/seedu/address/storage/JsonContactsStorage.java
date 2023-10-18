package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.Contacts;
import seedu.address.model.ReadOnlyContacts;



/**
 * Handles reading and saving {@link Contacts} to and from the contacts storage
 * JSON file.
 */
public class JsonContactsStorage implements ContactsStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonContactsStorage.class);

    private final Path path;

    public JsonContactsStorage(Path path) {
        this.path = path;
    }

    @Override
    public Path getContactsPath() {
        return this.path;
    }

    @Override
    public Optional<Contacts> readContacts() throws DataLoadingException {
        Optional<JsonContacts> jsonContactsOptional = JsonUtil.readJsonFile(
            this.path,
            JsonContacts.class
        );
        if (!jsonContactsOptional.isPresent()) {
            return Optional.empty();
        }
        JsonContacts jsonContacts = jsonContactsOptional.get();

        try {
            Contacts contacts = jsonContacts.toModelType();
            return Optional.of(contacts);
        } catch (IllegalValueException e) {
            logger.info(String.format(
                "Found illegal values after reading contacts storage JSON file."
                + "\nPath: %s"
                + "\nDetails: %s",
                this.path,
                StringUtil.getDetails(e)
            ));
            throw new DataLoadingException(e);
        }
    }

    @Override
    public void saveContacts(ReadOnlyContacts contacts) throws IOException {
        JsonUtil.saveJsonFile(new JsonContacts(contacts), this.path);
    }
}
