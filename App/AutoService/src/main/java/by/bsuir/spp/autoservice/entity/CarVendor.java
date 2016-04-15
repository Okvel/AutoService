package by.bsuir.spp.autoservice.entity;

public class CarVendor extends Entity<Long> {
    private String vendorName;

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }
}
