package by.bsuir.spp.autoservice.controller;

import by.bsuir.spp.autoservice.entity.UserRole;
import by.bsuir.spp.autoservice.service.UserRoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "controller", urlPatterns = "*.do", loadOnStartup = 0)
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRoleService service = new UserRoleService();
        ArrayList<UserRole> roles = service.findAll();
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
