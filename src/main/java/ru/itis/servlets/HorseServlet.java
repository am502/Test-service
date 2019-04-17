package ru.itis.servlets;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.itis.config.AppConfig;
import ru.itis.dto.HorseDto;
import ru.itis.service.HorseService;
import ru.itis.service.impl.HorseServiceImpl;
import ru.itis.validation.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hourse/servlet/count")
public class HorseServlet extends HttpServlet {

    private HorseService horseService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HorseDto horseDto = HorseDto.builder()
                .width(Integer.parseInt(req.getParameter("width")))
                .height(Integer.parseInt(req.getParameter("height")))
                .start(req.getParameter("start").toLowerCase())
                .end(req.getParameter("end").toLowerCase())
                .build();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(String.valueOf(horseService.findShortestPath(horseDto)));
        resp.getWriter().flush();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HorseServiceImpl horseServiceImpl = (HorseServiceImpl) context.getBean("horseServiceImpl");
        horseServiceImpl.setValidation((Validation) context.getBean("validation"));
        horseService = horseServiceImpl;
    }
}
