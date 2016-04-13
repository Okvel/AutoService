package by.bsuir.spp.autoservice.entity;

public class User extends Entity<Long> {
    private Credentials credentials;
    private UserRole role;
    private Person personInfo;

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setPersonInfo(Person personInfo) {
        this.personInfo = personInfo;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public UserRole getRole() {
        return role;
    }

    public Person getPersonInfo() {
        return personInfo;
    }
}
