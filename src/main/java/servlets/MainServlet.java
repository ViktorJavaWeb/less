package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by ustenko on 20.09.16.
 */
public class MainServlet extends HttpServlet {


    //В строке объявляется и иннициализируктся строковая переменная, которая задает путь к файлу со стилями
    private String styleSheet = "<link type=\"text/css\" rel=\"stylesheet\" href=\"style.css\">";


    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            PrintWriter out = response.getWriter();
            out.print("<h1>Hello Servlet</h1>");

            super.doGet(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response, boolean isPost)
                throws ServletException, IOException {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            try {
                out.println("<html><head>");
                out.println(styleSheet);
                out.println("</head><body>");
                out.println("<table width=\"95%\" align=\"center\"><tr><td>");

                // Установим
                String title = "";
                String auvtor = "";
                //String pages = "";
                int page = 0;

                if (isPost) {
                    title = request.getParameter("title");
                    if (null == title) title = "";
                    auvtor = request.getParameter("auvtor");
                    if (null == auvtor) auvtor = "";
                    //pages = request.getParameter("page");
                    //if (null == pages) pages = "";
                    //if ((!pages.isEmpty())  //Проверяет, является ли строка пустой
                    //        && (pages.matches("[0-9]+")))
                    //    page = Integer.parseInt(pages);
                    if (0 == page)
                        page = page;//??????????????

                }




       //     } catch (ClassNotFoundException e) {
       //        out.println(printError("Ошибка при работе с базой данных<br>" + e.toString()));
         //   } catch (SQLException e) {
        //        out.println(printError("Ошибка при работе с базой данных<br>" + e.toString()));
            } catch (NumberFormatException e) {
                out.println(printError("Ошибка при обработке данных<br>" + e.toString()));

            }
            super.doPost(request, response);
        }

    private boolean printError(String s) {
        return false;
    }


}

