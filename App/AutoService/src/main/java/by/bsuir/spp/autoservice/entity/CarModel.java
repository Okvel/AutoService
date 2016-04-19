package by.bsuir.spp.autoservice.entity;

/**
 * Created by Рылеев on 18.04.2016.
 */
public class CarModel extends Entity<Long> {
    private String name;
    private String vendor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
