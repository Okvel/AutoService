package by.bsuir.spp.autoservice.entity;

import java.util.Date;

public class Act extends Entity<Long> {
    private User manager;
    private Client client;
    private Car car;
    private Date date;
    private ActType type;
    private String description;

    public void setManager(User manager) {
        this.manager = manager;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(ActType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getManager() {
        return manager;
    }

    public Client getClient() {
        return client;
    }

    public Car getCar() {
        return car;
    }

    public Date getDate() {
        return date;
    }

    public ActType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
