package by.bsuir.spp.autoservice.command;

import by.bsuir.spp.autoservice.command.impl.ShowUsersCommand;

public enum CommandHelper {
    SHOW_USERS {
        {
            this.command = new ShowUsersCommand();
        }
    };

    BaseCommand command;

    public BaseCommand getCommand() {
        return command;
    }
}
