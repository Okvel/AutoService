package by.bsuir.spp.autoservice.entity;

public abstract class Entity <T extends Number> {
    private T id;

    public void setId(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
