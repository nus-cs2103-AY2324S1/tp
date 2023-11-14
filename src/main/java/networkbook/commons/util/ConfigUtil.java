package networkbook.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import networkbook.commons.core.Config;
import networkbook.commons.exceptions.DataLoadingException;
import networkbook.commons.exceptions.NullValueException;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {

    public static Optional<Config> readConfig(Path configFilePath) throws DataLoadingException, NullValueException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }

}
