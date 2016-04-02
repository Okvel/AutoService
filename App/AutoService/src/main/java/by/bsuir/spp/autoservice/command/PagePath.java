package by.bsuir.spp.autoservice.command;

public enum PagePath {
    USERS_PAGE {
        {
            this.page = "/jsp/users.jsp";
        }
    };

    String page;

    public String getPage() {
        return page;
    }
}
