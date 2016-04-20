package by.bsuir.spp.autoservice.command;

public enum PagePath {
    HOME_PAGE("index.jsp"),
    USERS_PAGE("/jsp/users.jsp");

    PagePath(String page) {
        this.page = page;
    }

    private String page;

    public String getPage() {
        return page;
    }
}
