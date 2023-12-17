package xyz.mattring.buncho;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FeatureFlagsTest {

    @Test
    void isEnabled() {
        final FeatureFlags ff = new FeatureFlags() {};

        final String baseDir = ff.getBaseDir();
        try {
            Files.createDirectories(Paths.get(baseDir));

            // clear everything out
            Files.deleteIfExists(Paths.get(baseDir + "/bar_true"));
            Files.deleteIfExists(Paths.get(baseDir + "/bar_false"));
            System.clearProperty("buncho.bar");

            assertFalse(ff.isEnabled("bar"));

            System.setProperty("buncho.bar", "true");
            assertTrue(ff.isEnabled("bar"));

            System.setProperty("buncho.bar", "false");
            assertFalse(ff.isEnabled("bar"));

            Files.createFile(Paths.get(baseDir + "/bar_true"));
            assertTrue(ff.isEnabled("bar")); // file wins

            Files.createFile(Paths.get(baseDir + "/bar_false"));
            assertFalse(ff.isEnabled("bar")); // file wins

            System.setProperty("buncho.bar", "true"); //
            assertFalse(ff.isEnabled("bar")); // file wins

            // clear everything out
            Files.deleteIfExists(Paths.get(baseDir + "/bar_true"));
            Files.deleteIfExists(Paths.get(baseDir + "/bar_false"));
            System.clearProperty("buncho.bar");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}