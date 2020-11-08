package controller.servlet.front;

import app.AppContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface View {
    default void doGet(HttpServletRequest req, HttpServletResponse resp, AppContext appContext) throws ServletException, IOException {}
    default void doPost(HttpServletRequest req, HttpServletResponse resp, AppContext appContext) throws ServletException, IOException {}
}
