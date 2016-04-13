package by.bsuir.spp.autoservice.command;

import by.bsuir.spp.autoservice.command.impl.RegistrationCommand;
import by.bsuir.spp.autoservice.command.impl.ShowUsersCommand;

public enum CommandHelper {
    REGISTRATION_USER(new RegistrationCommand()),
    SHOW_USERS(new ShowUsersCommand());

    private BaseCommand command;

    CommandHelper(BaseCommand command) {
        this.command = command;
    }

    public BaseCommand getCommand() {
        return command;
    }
}
