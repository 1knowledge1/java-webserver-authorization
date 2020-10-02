package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends  HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) { this.accountService = accountService; }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        System.out.println("Новый пользователь:\n" + request.getRemoteAddr());
        

        if (login == null || pass == null || accountService.getUserByLogin(login) != null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        else {
            accountService.addNewUser(new UserProfile(login, pass, null));
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("You are registered successfully. Hello, " + login);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    //change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo
    }

    //unregister
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //todo
    }

}