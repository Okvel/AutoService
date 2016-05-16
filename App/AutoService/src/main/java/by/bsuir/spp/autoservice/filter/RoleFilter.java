package by.bsuir.spp.autoservice.filter;

import by.bsuir.spp.autoservice.command.CommandHelper;
import by.bsuir.spp.autoservice.command.util.RoleEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.do"})
public class RoleFilter implements Filter {
    private static final String COMMAND_SUFFIX = ".do";
    private static final String ATTRIBUTE_NAME_COMMAND = "command";
    private static final String SESSION_ATTRIBUTE_ROLE = "role";
    private static final String HOME_COMMAND = "TO_HOME_PAGE";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String commandName = UriQueryParser.takeCommandName(httpServletRequest.getRequestURI(), COMMAND_SUFFIX);
        if (commandName != null) {
            String role = (String) httpServletRequest.getSession().getAttribute(SESSION_ATTRIBUTE_ROLE);
            CommandHelper requiredRole = CommandHelper.valueOf(commandName.toUpperCase());
            if ((role != null && requiredRole.getRoles().contains(RoleEnum.valueOf(role))) ||
                    (requiredRole.getRoles().contains(RoleEnum.ANY))) {
                httpServletRequest.setAttribute(ATTRIBUTE_NAME_COMMAND, commandName);
            } else {
                httpServletRequest.setAttribute(ATTRIBUTE_NAME_COMMAND, HOME_COMMAND);
            }
        } else {
            httpServletRequest.setAttribute(ATTRIBUTE_NAME_COMMAND, HOME_COMMAND);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
