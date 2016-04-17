package by.bsuir.spp.autoservice.entity;

public class Car extends Entity<Long> {
    private String vendor;
    private String model;
    private String registrationNumber;
    private String vin;

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVendor() {
        return vendor;
    }

    public String getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getVin() {
        return vin;
    }
}
