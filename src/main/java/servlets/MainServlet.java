package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ustenko on 20.09.16.
 */
public class MainServlet {


        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            PrintWriter out = resp.getWriter();
            out.print("<h1>Hello Servlet</h1>");

            super.doGet(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            super.doPost(req, resp);
        }


}

