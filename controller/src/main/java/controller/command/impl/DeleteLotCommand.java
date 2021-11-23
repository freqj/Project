package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
import service.LotsService;
import service.exeption.ServiceExeption;
import service.impl.LotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteLotCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        Router router;

        LotsService service = new LotServiceImpl();

        try {
            if(service.deleteLotByID(Integer.parseInt(req.getParameter("lotID"))))
            {
                router = new Router("index.jsp", Router.RouteType.REDIRECT);
            }else {
                req.setAttribute("errormessage", "Elimination error" );
                router = new Router("lotpage.jsp", Router.RouteType.FORWARD);
            }
        } catch (ServiceExeption e) {
            throw new CommandException("Delete command failed", e);
        }

        return router;

    }
}
