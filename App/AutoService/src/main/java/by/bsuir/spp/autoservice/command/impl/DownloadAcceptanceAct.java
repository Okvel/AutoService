package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.DocumentFormat;
import by.bsuir.spp.autoservice.service.DocumentService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DownloadAcceptanceAct implements BaseCommand {
    private static final String REQUEST_PARAMETER_LINK = "link";
    private static final String REQUEST_PARAMETER_FORMAT = "format";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        DocumentService service = DocumentService.getInstance();
        try {
            DocumentFormat format = DocumentFormat.valueOf(request.getParameter(REQUEST_PARAMETER_FORMAT));
            String link = service.getAcceptanceActDocument(format);
            request.setAttribute(REQUEST_PARAMETER_LINK, link);
        } catch (ServiceException ex) {
            throw new CommandException(ex);
        }
        return PagePath.DOWNLOAD;
    }
}
