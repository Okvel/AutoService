package by.bsuir.spp.autoservice.command;

public enum PagePath {
    USERS_PAGE("/jsp/users.jsp");

    PagePath(String page) {
        this.page = page;
    }

    private String page;

    public String getPage() {
        return page;
    }
}
