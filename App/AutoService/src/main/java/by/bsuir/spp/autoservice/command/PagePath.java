package by.bsuir.spp.autoservice.command;

public enum PagePath {
    HOME("index.jsp"),
    ABOUT("jsp/about.jsp"),
    SERVICE_LIST("jsp/servicelist.jsp"),
    LOGIN("jsp/login.jsp"),
    DISMISS("jsp/dismiss.jsp"),
    REPAIR_REPORTS("jsp/repair_reports.jsp"),
    ACCEPTANCE_ACTS("jsp/acceptance_acts.jsp"),
    PASSING_ACTS("jsp/passing_acts.jsp"),
    ADD_ACCEPTANCE_ACT("jsp/add_acceptance_act.jsp"),
    ADD_PASSING_ACT("jsp/add_passing_act.jsp"),
    DETAIL_ORDERS("jsp/detail_orders.jsp"),
    FEEDBACK("jsp/feedback.jsp"),
    REGISTRATION("jsp/registration.jsp"),
    DOWNLOAD("jsp/download.jsp"),
    MESSAGE("jsp/message.jsp");
    PagePath(String page) {
        this.page = page;
    }

    private String page;

    public String getPage() {
        return page;
    }
}
