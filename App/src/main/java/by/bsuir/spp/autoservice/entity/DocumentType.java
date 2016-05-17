package by.bsuir.spp.autoservice.entity;

public enum DocumentType {
    PASSING_ACT("/tmp/passing_act"),
    ACCEPTANCE_ACT("/tmp/acceptance_act"),
    WORK_PERFORMED_SHEET("/tmp/work_performed_sheet"),
    ORDER_DETAILS_APPLICATION("/tmp/order_details_application"),
    PARTS_INVOICE("/tmp/parts_invoice");

    private String path;

    DocumentType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
