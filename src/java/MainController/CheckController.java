/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MainController;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lamph
 */
public class CheckController extends HttpServlet {

    private static final String SUCCESS = "index.jsp";
    private static final String ERROR = "index.jsp";

    private static int MAX_VALID_YR = 3000;
    private static int MIN_VALID_YR = 1000;

    protected boolean isLeap(int year) {
        // Return true if year is  
        // a multiple of 4 and not  
        // multiple of 100. 
        // OR year is multiple of 400. 
        return (((year % 4 == 0)
                && (year % 100 != 0))
                || (year % 400 == 0));
    }

    protected boolean isValidDate(int d,
            int m,
            int y) {
        // If year, month and day  
        // are not in given range 
        if (y > MAX_VALID_YR
                || y < MIN_VALID_YR) {
            return false;
        }
        if (m < 1 || m > 12) {
            return false;
        }
        if (d < 1 || d > 31) {
            return false;
        }

        // Handle February month 
        // with leap year 
        if (m == 2) {
            if (isLeap(y)) {
                return (d <= 29);
            } else {
                return (d <= 28);
            }
        }

        // Months of April, June,  
        // Sept and Nov must have  
        // number of days less than 
        // or equal to 30. 
        if (m == 4 || m == 6
                || m == 9 || m == 11) {
            return (d <= 30);
        }

        return true;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {

            int day = Integer.parseInt(request.getParameter("day"));
            int month = Integer.parseInt(request.getParameter("month"));
            int year = Integer.parseInt(request.getParameter("year"));

            isLeap(year);
            boolean check = isValidDate(day, month, year);
            if (check) {
                request.setAttribute("MESSAGE", +day + "/" + month + "/" + year + " is a valid date");
                url = SUCCESS;
            } else {
                request.setAttribute("MESSAGE", +day + "/" + month + "/" + year + " is NOT a valid date");
                url = ERROR; 
            }

        } catch (Exception e) {
            log("Error at AddController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
