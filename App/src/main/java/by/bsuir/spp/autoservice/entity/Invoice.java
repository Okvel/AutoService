package by.bsuir.spp.autoservice.entity;

public class Invoice extends Entity<Long> {
    private User manager;
    private DetailApplication application;

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public DetailApplication getApplication() {
        return application;
    }

    public void setApplication(DetailApplication application) {
        this.application = application;
    }
}
