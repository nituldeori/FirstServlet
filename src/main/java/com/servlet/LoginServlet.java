package com.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Narayan"),
                @WebInitParam(name = "password", value = "BridgeLabz@123")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String user = request.getParameter("user");
        String userRegex = "^[A-Z]{1}[a-zA-Z\\s]{2,}$";
        String pwdRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%&]).{8,}$";
        String pwd = request.getParameter("pwd");

        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        if(!Pattern.matches(userRegex, user)){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>UserName is not in correct format</font>");
            rd.include(request, response);
        }
        else if(!Pattern.matches(pwdRegex, pwd)){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Password is not in correct format</font>");
            rd.include(request, response);
        }
        else if(userID.equals(user) && password.equals(pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }
    }
}
