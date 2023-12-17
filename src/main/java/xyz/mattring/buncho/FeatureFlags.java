package xyz.mattring.buncho;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Implement this interface to enable very simple feature flags.
 */
public interface FeatureFlags {

    /**
     * @return the base directory for feature flags (defaults to ~/.buncho)
     */
    default String getBaseDir() {
        return System.getProperty("user.home") + "/.buncho";
    }

    /**
     * Checks for flag files or system properties to determine if a feature is enabled.
     *
     * @param featureName
     * @return true if the feature is enabled, false otherwise
     */
    default boolean isEnabled(String featureName) {
        if (featureName == null || featureName.isEmpty()) {
            return false;
        } else {
            final String baseFileName = getBaseDir() + '/' + featureName + '_';
            return
                    !Files.exists(Paths.get(baseFileName + "false"))
                            && (Files.exists(Paths.get(baseFileName + "true"))
                            || Boolean.parseBoolean(System.getProperty("buncho." + featureName)));
        }
    }

}
