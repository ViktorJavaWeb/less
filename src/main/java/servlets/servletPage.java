package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by ustenko on 20.09.16.
 */

public class servletPage extends HttpServlet { //Строка 12 объявляет класс, который наследуется от базового класса HttpServlet.

    //В строке объявляется и иннициализируктся строковая переменная, которая задает путь к файлу со стилями
    private String styleSheet = "<link type=\"text/css\" rel=\"stylesheet\" href=\"style.css\">";

    //Строки с 15 по 21 содержат кусок текста, который реализует короткий скрипт для сокрытия / отображения детальной информации.
    private String collapseScript =
                           "<script type=\"text/javascript\"> function toggle(nnn) {\n"
                           +"if (document.getElementById(nnn).style.display == 'none') {\n"
                           +"  document.getElementById(nnn).style.display = 'block'}\n"
                           +"else {\n"
                           +"  document.getElementById(nnn).style.display = 'none'}\n"
                           +"} </script>";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, boolean isPost)
            throws ServletException, IOException {

        //В самом начале метода, происходит установка типа содержимого для ответа, а так же получение объекта out,
        // который будет использоваться для отправки ответа клиенту (строки 25 и 26)
        response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();

        try {
                      out.println("<html><head>");
                      out.println(styleSheet);
                      out.println("</head><body>");
                      out.println("<table width=\"95%\" align=\"center\"><tr><td>");

            //====== Строки 33-38 создают переменные для сохранения месяца и года.

                      // Установим год и месяц
                      String groupCode = "";
                      String monthStr = "";
                      String yearStr = "";
                      int month = 0;
                      int year = 0;
            //====================

                      java.util.GregorianCalendar currentDate = new java.util.GregorianCalendar();
                      java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd.MM.yyyy");
                      int currentYear = currentDate.get(Calendar.YEAR);


            //====== Строки 44-59 извлекают из параметров запроса месяц, год и код группы, причем параметры
            // извлекаются только если запрос был сделан методом POST. Это предотвращает возможность передавать параметры методом GET минуя форму

                      if (isPost) {
                            groupCode = request.getParameter("group");
                            if (null == groupCode) groupCode = "";
                            monthStr = request.getParameter("month");
                            if (null == monthStr) monthStr = "";
                            yearStr = request.getParameter("year");
                            if (null == yearStr) yearStr = "";
                            if ((!monthStr.isEmpty()) && (monthStr.matches("[0-9]+")))
                                  month = Integer.parseInt(monthStr);
                            if (0 == month)
                                  month = currentDate.get(Calendar.MONTH);
                            if ((!yearStr.isEmpty()) && (yearStr.matches("[0-9]+")))
                                  year = Integer.parseInt(yearStr);
                            if (0 == year)
                                  year = currentYear;
                      }
            //====================

            //====== Строки 61-70 осуществляют загрузку JDBC-драйвера для MS SQL и соединение с базой данных. Так же, создается объект класса Statement, используемый в дальнейшем для запроса групп.

                      // Соединяемся с MS SQL.
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        String msUrl = "jdbc:sqlserver://sqlsrv:1433;" +
                                "databaseName=schooldb;";
                        Properties msProps = new Properties();
                        msProps.setProperty("user", "webstat");
                        msProps.setProperty("password", "webstatpwd");
                        Connection msConnection = DriverManager.getConnection(msUrl, msProps);
                        Statement msStatement = msConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //====================


            //====== Строки 73-107 осуществляют запрос к СУБД для выборки всех групп, которые обучаются в настоящее время в учебном заведении.
            //Результат запроса (groupsResultSet) используется для организации формы запроса данных. Помимо списка групп, в форме имеется так же список месяцев и лет. Кнопка "Запросить" передает выбранные пользователем данные на сервер, где они будут обработаны этим же сервлетом.

                      // Форма для коерректировки запроса
                      ResultSet groupsResultSet = msStatement.executeQuery(
                                "SELECT gr_name, gr_pcode FROM groups WHERE (gr_attributes = 0) "
                                + "AND (gr_isZaoch = 0) ORDER BY gr_course DESC, gr_name;");
                      out.println(printHeader("Успеваемость и посещаемость"));
                      out.println("<form action=\"monthmarks\" method=\"POST\">");
                      out.println("<table cellspacing=\"3\" cellpadding=\"0\" align=\"center\">");
                      out.println("<tr><td align=\"left\">Группа</td>"
                                + "<td align=\"left\">Месяц</td>"
                                + "<td align=\"left\">Год</td><td></td></tr>");
                      out.println("<tr><td align=\"left\"><select name=\"group\">");
                      while (groupsResultSet.next()) {
                            String selected = "";
                            if (groupCode.equals(groupsResultSet.getString("gr_pcode")))
                                  selected = "selected ";
                            out.println("<option "+selected+"value=\""+groupsResultSet.getString("gr_pcode")+
                                      "\">"+groupsResultSet.getString("gr_name")+"</option>");
                          } //while
                      out.println("</select></td><td align=\"left\"><select name=\"month\">");
                      for (int x = 1; x <= 12; x++) {
                            String selected = "";
                            if (month == x)
                                  selected = "selected ";
                            out.println("<option "+selected+"value=\""+x+"\">"+getMonth(x)+"</option>");
                          }
                      out.println("</select></td><td align=\"left\"><select name=\"year\">");
                      for (int x = 0; x < 2; x++) {
                            String selected = "";
                           if (year == (currentYear - x))
                                 selected = "selected ";
                           out.println("<option "+selected+"value=\""+(currentYear-x)+"\">"+(currentYear-x)+"</option>");
                         }
                     out.println("</select></td>");
                     out.println("<td align=\"left\"><input type=\"submit\" value=\"Запросить\"/></td></tr>");
                     out.println("</table></form><hr>");
                     groupsResultSet.close();

            //====================



                     if (!(groupCode.equals("")) && (month > 0) && (year > 0)) {
                           out.println(printHeader("Отчет об успеваемости и посещаемости"));

                // проверяем наличие группы в базе данных
                           PreparedStatement msGroup = msConnection.prepareStatement(
                                     "SELECT gr_name, gr_pcode FROM groups WHERE (gr_pcode=?);",
                                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                           msGroup.setString(1, groupCode);
                           ResultSet groupResultSet = msGroup.executeQuery();
                           if (groupResultSet.first()) {

                                 // группа есть - работаем дальше
                                 out.println(collapseScript);
                                 out.println("<p align=\"center\">студентов группы "+groupResultSet.getString("gr_name")
                                           + " за "+getMonth(month)+" месяц "+year+" года.</p>");
                                 PreparedStatement detailsStatement = msConnection.prepareStatement(
                                           "EXEC getWebStudentsReport ?, ?, ?",
                                           ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                 detailsStatement.setString(1, groupCode);
                                 detailsStatement.setInt(2, month);
                                 detailsStatement.setInt(3, year);
                                 ResultSet detailsResult = detailsStatement.executeQuery();
                                 int x = 0;
                                 out.println("<div><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n"
                                           + "<tr><th width=\"70%\">Номер зачетной книжки</th>\n"
                                           + "<th width=\"10%\">Средний балл</th>\n"
                                           + "<th width=\"10%\">Пропусков всего</th>\n"
                                           + "<th width=\"10%\">Пропусков неуважительно</th></tr></table></div>");

                                 if (detailsResult.first())
                                       do {
                                         x += 1;
                                         String color = "green"; // green

                        //====== Список студентов отображается в виде таблицы, с подстветкой строк разными цветами в зависимости от величины среднего балла (строки 140-143)
                            //Детализированная информация по умолчанию скрыта и отображается при щелчке мышью на идентификаторе студента.
                            // В качестве идентификатора студента используется номер его зачетной книжки, чтобы слегка обезличить информацию.

                                         float avgMark = detailsResult.getFloat("st_mark");
                                         if (avgMark < 4) color = "yellow"; //yellow
                                         if (avgMark < 3) color = "red"; //red
                                         out.println("<div><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n"
                                                   + "<tr class=\""+color+"\"><td width=\"70%\"><a href=\"javascript: toggle("+x+")\">\n"
                                                   +detailsResult.getString("st_studnumber")+"</a></td>\n"
                                                   + "<td width=\"10%\" align=\"center\">"+detailsResult.getFloat("st_mark")+"</td>\n"
                                                   + "<td width=\"10%\" align=\"center\">"+detailsResult.getInt("st_all")+"</td>\n"
                                                   + "<td width=\"10%\" align=\"center\">"+detailsResult.getInt("st_illegal")
                                                   +"</td></tr></table></div>");
                        //====================

                                         // выводим список студентов с оценками по этому предмету
                                         PreparedStatement subdetailsStatement = msConnection.prepareStatement(
                                                   "SELECT sb_name, mk_mark FROM subjects, marks WHERE (mk_stcode=?)"
                                                   + " AND (mk_month=?) AND (mk_year=?) AND (mk_sbcode=sb_pcode) "
                                                   + "ORDER BY sb_name;",
                                                 ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                         subdetailsStatement.setString(1, detailsResult.getString("st_pcode"));
                                         subdetailsStatement.setInt(2, month);
                                         subdetailsStatement.setInt(3, year);
                                         ResultSet subdetailsResult = subdetailsStatement.executeQuery();
                                           if (subdetailsResult.first()) {
                                                 // start table
                                                 out.println("<table border=\"0\" width=\"100%\" align=\"center\" id=\""
                                                           +x+"\" style=\"display: none;\">");
                                                 out.println("<tr><th>Дисциплина</th><th width=\"30%\">Оценка</th></tr>");
                                                 do {
                                                       int mark = subdetailsResult.getInt("mk_mark");
                                                       out.println("<tr><td>"+subdetailsResult.getString("sb_name")
                                                               +"</td><td align=\"center\">"+getMark(mark)+"</td></tr>");
                                                     } while (subdetailsResult.next());
                                                 // end table
                                                 out.println("</table>");
                                               }
                                           else {
                                                 // дисциплин нет - выводим сообщение и все.
                                                 out.println("<div id=\""+x+"\" style=\"display: none;\">Нет оценок за этот период</div>");
                                               }
                                           subdetailsResult.close();
                                       } while (detailsResult.next());
                                 else {

                                       // Студентов нет - выводим сообщение и все.
                                       out.println("<p align=\"center\">Статистика для такой комбинации параметров отсутствует</p>");
                                     }
                                 detailsResult.close();
                               } else {
                                 // Группы нет - выводим сообщение и все.
                                 out.println("<p align=\"center\">Вы не можете просмотреть статистику для этой группы</p>");
                               }
                           groupResultSet.close();
                         } else {
                           out.println("<div>Нет данных </div>");
                         }
                     out.println("<hr><p align=\"center\"><small>ФГОУ СПО ПГТК им. В.П.Романова "
                               +dateFormat.format(currentDate.getTime())+"</small>");
                     out.println("</table></body></html>");
            //======Строки 195-200 "отлавливают" исключения и выводят соответствующее сообщение об ошибке.
                   }catch (ClassNotFoundException e) {
                     out.println(printError("Ошибка при работе с базой данных<br>"+e.toString()));
                   } catch (SQLException e) {
                     out.println(printError("Ошибка при работе с базой данных<br>"+e.toString()));
                   } catch (NumberFormatException e) {
                     out.println(printError("Ошибка при обработке данных<br>"+e.toString()));
            //====================

        //====== Строки 201 и 204 гарантированно закрывают соединение с базой данных, а так же завершают вывод ответа клиенту.
                   } finally {
                     msConnection.close();
                     out.close();
                  }
        //====================
             }


            //====== Строки 207-217 - это Netbeans-овская стандартная реализация методов doPost и doGet (перенаправление на метод processRequest).
            // Для того. чтобы отличить POST запросы от запросов GET при разборе параметров, я добавил значения для третьего параметра.

                 @Override
         protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
               processRequest(request, response, false);
             }

                 @Override
         protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
                 processRequest(request, response, true);
             }

    //====================

    //====== Строки 219-222 - позволяют получить информацию о сервлете.
                 @Override
         public String getServletInfo() {
                 return "Статистика успеваемости и посещаемости за месяц";
             }// </editor-fold>
    //====================

    //====== Строки 224-255 позволяют получить символьное представление месяца и оценки
                 private String getMonth(int month) {
               switch (month) {
                     case 1:  return "Январь";
                     case 2:  return "Февраль";
                     case 3:  return "Март";
                     case 4:  return "Апрель";
                     case 5:  return "Май";
                     case 6:  return "Июнь";
                     case 7:  return "Июль";
                     case 8:  return "Август";
                     case 9:  return "Сентябрь";
                     case 10: return "Октябрь";
                     case 11: return "Ноябрь";
                     case 12: return "Декабрь";
                     default: return "";
                   }
             }

                 private String getMark(int mark) {
               switch (mark) {
                     case 0:  return "<font color=\"red\">без оценки</font>";
                     case 1: case 2:  return "<font color=\"red\">неудовлетворительно</font>";
                     case 3:  return "удовлетворительно";
                     case 4:  return "хорошо";
                     case 5:  return "отлично";
                     case 10: return "не аттестован(а)";
                     case 11: return "освобожден(а)";
                     case 12: return "не изучал(а)";
                     case 13: return "зачет";
                     default: return "";
                   }
             }
    //====================

                 private String printError(String msg) {
               return "<div class=\"error\">"+msg+"</div>";
             }

    //====== Строки 261-263 - это метод, который используется для вывода заголовков.
                 private String printHeader(String text) {
               return "<h2 align=\"center\">"+text+"</h2>";
             }
     //====================
}


