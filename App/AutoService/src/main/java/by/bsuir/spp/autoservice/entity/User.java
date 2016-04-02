package by.bsuir.spp.autoservice.entity;

public class User extends Entity<Long> {
    private String login;
    private String password;
    private UserRole role;
    private Person personInfo;
    private Integer experience;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setPersonInfo(Person personInfo) {
        this.personInfo = personInfo;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public Person getPersonInfo() {
        return personInfo;
    }

    public Integer getExperience() {
        return experience;
    }
}
