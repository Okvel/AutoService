package by.bsuir.spp.autoservice.command;

import by.bsuir.spp.autoservice.command.impl.*;
import by.bsuir.spp.autoservice.command.util.RoleEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum CommandHelper {
    TO_HOME_PAGE(new ToPageCommand(PagePath.HOME), RoleEnum.ANY),
    TO_ABOUT_PAGE(new ToPageCommand(PagePath.ABOUT), RoleEnum.ANY),
    TO_ADD_ACCEPTANCE_ACT_PAGE(new ToPageCommand(PagePath.ADD_ACCEPTANCE_ACT), RoleEnum.ADMIN),
    TO_ACCEPTANCE_ACTS(new ToPageCommand(PagePath.ACCEPTANCE_ACTS), RoleEnum.ADMIN),
    TO_REPAIR_REPORTS(new ToPageCommand(PagePath.REPAIR_REPORTS), RoleEnum.MANAGER),
    TO_DETAIL_ORDERS(new ToPageCommand(PagePath.DETAIL_ORDERS), RoleEnum.MANAGER),
    TO_PASSING_ACTS(new ToPageCommand(PagePath.PASSING_ACTS), RoleEnum.ADMIN),
    TO_ADD_PASSING_ACT_PAGE(new ToPageCommand(PagePath.ADD_PASSING_ACT), RoleEnum.ADMIN),
    TO_SERVICE_LIST_PAGE(new ToPageCommand(PagePath.SERVICE_LIST), RoleEnum.ANY),
    TO_LOGIN_PAGE(new ToPageCommand(PagePath.LOGIN), RoleEnum.ANY),
    LOGIN(new SignInCommand(), RoleEnum.ANY),
    LOGOUT(new SignOutCommand(), RoleEnum.ADMIN, RoleEnum.MANAGER, RoleEnum.MECHANIC),
    REGISTRATION_USER(new RegistrationCommand(), RoleEnum.MANAGER),
    SHOW_REPORTS(new ShowRepairReportListCommand(), RoleEnum.MANAGER),
    SHOW_REPORT(new ShowRepairReportCommand(), RoleEnum.MANAGER),
    SHOW_DISMISS(new ShowDismissFormCommand(), RoleEnum.MANAGER),
    SHOW_ACCEPTANCE_ACT_LIST(new ShowAcceptanceActListCommand(), RoleEnum.ADMIN),
    SHOW_PASSING_ACT_LIST(new ShowAcceptanceActListCommand(), RoleEnum.ADMIN),
    SHOW_DETAIL_ORDERS(new ShowFillDetailInvoiceCommand(), RoleEnum.MANAGER),
    SHOW_DETAIL_ORDER(new ShowDetailOrderCommand(), RoleEnum.MANAGER),
    SHOW_ACT(new ShowActCommand(), RoleEnum.ADMIN),
    SAVE_ACT(new SaveActCommand(), RoleEnum.ADMIN),
    SAVE_DETAIL_INVOICE(new SaveDetailInvoiceCommand(), RoleEnum.MANAGER),
    DISMISS(new DismissEmployeeCommand(), RoleEnum.MANAGER),
    SHOW_REGISTER(new ShowRegistrationPageCommand(), RoleEnum.MANAGER);

    private BaseCommand command;
    private ArrayList<RoleEnum> roles;

    CommandHelper(BaseCommand command, RoleEnum role) {
        this.command = command;
        roles = new ArrayList<>();
        roles.add(role);
    }

    CommandHelper(BaseCommand command, RoleEnum... role) {
        this.command = command;
        List<RoleEnum> tempRoleList = Arrays.asList(role);
        roles = new ArrayList<>(tempRoleList);
    }

    public BaseCommand getCommand() {
        return command;
    }

    public List<RoleEnum> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    void fillAnyUserList() {
        this.roles.add(RoleEnum.ADMIN);
        this.roles.add(RoleEnum.MANAGER);
        this.roles.add(RoleEnum.MECHANIC);
    }
}
