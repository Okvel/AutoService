package by.bsuir.spp.autoservice.command;

import by.bsuir.spp.autoservice.command.impl.RegistrationCommand;
import by.bsuir.spp.autoservice.command.impl.ShowRepairReportListCommand;
import by.bsuir.spp.autoservice.command.impl.ShowUsersCommand;
import by.bsuir.spp.autoservice.command.util.RoleEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum CommandHelper {
    REGISTRATION_USER(new RegistrationCommand(), RoleEnum.MANAGER),
    SHOW_REPORTS(new ShowRepairReportListCommand(), RoleEnum.MANAGER);

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
