package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.entity.Feedback;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.*;

public class FeedbackServiceTest extends ServiceTest {
    private static FeedbackService service = FeedbackService.getInstance();

    @Test
    public void selectAllFeedback() {
        try {
            ArrayList<Feedback> feedbacks = new ArrayList<>(service.findAll());
            assertFalse(feedbacks.isEmpty());
        } catch (ServiceException ex) {
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void saveTest() {
        Feedback feedback = new Feedback();
        feedback.setFirstName("Drakosha");
        feedback.setLastName("Just");
        feedback.setText("Awesome service!");
        try {
            assertTrue(service.save(feedback));
        } catch (ServiceException ex) {
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void saveNullTest() {
        try {
            assertFalse(service.save(null));
        } catch (ServiceException ex) {
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }
}
