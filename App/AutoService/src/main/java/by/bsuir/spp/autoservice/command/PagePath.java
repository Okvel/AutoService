package by.bsuir.spp.autoservice.command;

public enum PagePath {
    HOME("index.jsp"),
    ABOUT("jsp/about.jsp"),
    SERVICE_LIST("jsp/servicelist.jsp"),
    LOGIN("jsp/login.jsp"),
    DISMISS("jsp/dismiss.jsp");

    PagePath(String page) {
        this.page = page;
    }

    private String page;

    public String getPage() {
        return page;
    }
}
