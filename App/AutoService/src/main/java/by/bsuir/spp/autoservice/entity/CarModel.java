package by.bsuir.spp.autoservice.entity;

public class CarModel extends Entity<Long> {
    private CarVendor vendor;
    private String model;

    public void setVendor(CarVendor vendor) {
        this.vendor = vendor;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarVendor getVendor() {
        return vendor;
    }

    public String getModel() {
        return model;
    }
}
