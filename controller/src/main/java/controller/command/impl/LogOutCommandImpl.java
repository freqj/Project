package controller.command.impl;

import controller.command.Command;
import controller.command.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Ref;

public class LogOutCommandImpl implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Router router;
        HttpSession session = req.getSession();
        session.invalidate();

        return new Router("index.jsp", Router.RouteType.REDIRECT);

    }
}
