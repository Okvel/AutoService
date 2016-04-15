package by.bsuir.spp.autoservice.entity;

public class Car extends Entity<Long> {
    private CarModel model;
    private String registrationNumber;
    private String vin;

    public void setModel(CarModel model) {
        this.model = model;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public CarModel getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getVin() {
        return vin;
    }
}
