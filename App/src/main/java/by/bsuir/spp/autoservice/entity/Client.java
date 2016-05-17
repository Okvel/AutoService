package by.bsuir.spp.autoservice.entity;

public class Client extends Entity<Long> {
    private Person personInformation;
    private String passportId;

    public void setPersonInformation(Person personInformation) {
        this.personInformation = personInformation;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Person getPersonInformation() {
        return personInformation;
    }

    public String getPassportId() {
        return passportId;
    }
}
