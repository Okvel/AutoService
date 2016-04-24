package by.bsuir.spp.autoservice.entity;

/**
 * Created by Рылеев on 24.04.2016.
 */
public class Feedback extends Entity<Long> {
    String lastName;
    String firstName;
    String text;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
