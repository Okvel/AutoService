package by.bsuir.spp.autoservice.command;

import javax.servlet.http.HttpServletRequest;

public interface BaseCommand {
    PagePath execute(HttpServletRequest request) throws CommandException;
}
