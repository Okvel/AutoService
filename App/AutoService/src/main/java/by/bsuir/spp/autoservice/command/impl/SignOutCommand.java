package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class SignOutCommand implements BaseCommand {
    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        return PagePath.HOME_PAGE;
    }
}
