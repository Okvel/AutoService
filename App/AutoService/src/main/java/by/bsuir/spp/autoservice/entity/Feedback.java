package by.bsuir.spp.autoservice.entity;

/**
 * Created by Рылеев on 24.04.2016.
 */
public class Feedback extends Entity<Long> {
    User user;
    String text;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
