package by.bsuir.spp.autoservice.entity;

import java.util.Date;

public class RepairReport extends Entity<Long> {
    private Car car;
    private User mechanic;
    private Date startDate;
    private Date endDate;
    private String description;

    public void setCar(Car car) {
        this.car = car;
    }

    public void setMechanic(User mechanic) {
        this.mechanic = mechanic;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car getCar() {
        return car;
    }

    public User getMechanic() {
        return mechanic;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }
}
