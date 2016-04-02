package by.bsuir.spp.autoservice.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum Commands {
    SHOW_USERS {
        {
            this.role.add(RoleEnum.ANY);
        }
    };

    ArrayList<RoleEnum> role;

    Commands() {
        role = new ArrayList<>();
    }

    public List<RoleEnum> getRole() {
        return Collections.unmodifiableList(role);
    }

    void fillAnyUserList() {
        this.role.add(RoleEnum.ADMIN);
        this.role.add(RoleEnum.MANAGER);
        this.role.add(RoleEnum.MECHANIC);
    }
}
