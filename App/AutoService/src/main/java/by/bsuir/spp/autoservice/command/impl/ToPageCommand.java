package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ToPageCommand implements BaseCommand {
    private PagePath page;

    public ToPageCommand(PagePath page) {
        this.page = page;
    }

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        return page;
    }
}
