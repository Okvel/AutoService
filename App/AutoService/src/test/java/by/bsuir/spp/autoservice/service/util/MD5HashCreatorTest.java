package by.bsuir.spp.autoservice.service.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MD5HashCreatorTest {
    @Test
    public void positiveTest() {
        final String SOURCE_STRING = "password";
        final String EXPECTED_RESULT = "5f4dcc3b5aa765d61d8327deb882cf99";
        assertEquals(EXPECTED_RESULT, MD5HashCreator.create(SOURCE_STRING));
    }

    @Test
    public void nullTest() {
        assertTrue(MD5HashCreator.create(null).isEmpty());
    }

    @Test
    public void emptyStringTest() {
        assertTrue(MD5HashCreator.create("").isEmpty());
    }
}
