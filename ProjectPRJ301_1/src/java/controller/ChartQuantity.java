/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name="ChartQuantity", urlPatterns={"/chartquantity"})
public class ChartQuantity extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    DAO dao = new DAO();
    
    // Lấy số lượng từng tháng
    int quantityMonth1 = dao.quantityMonth(1);
    int quantityMonth2 = dao.quantityMonth(2);
    int quantityMonth3 = dao.quantityMonth(3);
    int quantityMonth4 = dao.quantityMonth(4);
    int quantityMonth5 = dao.quantityMonth(5);
    int quantityMonth6 = dao.quantityMonth(6);
    int quantityMonth7 = dao.quantityMonth(7);
    int quantityMonth8 = dao.quantityMonth(8);
    int quantityMonth9 = dao.quantityMonth(9);
    int quantityMonth10 = dao.quantityMonth(10);
    int quantityMonth11 = dao.quantityMonth(11);
    int quantityMonth12 = dao.quantityMonth(12);
    
    // Đặt số lượng vào request attribute để truyền đến "Chart.jsp"
    request.setAttribute("quantityMonth1", quantityMonth1);
    request.setAttribute("quantityMonth2", quantityMonth2);
    request.setAttribute("quantityMonth3", quantityMonth3);
    request.setAttribute("quantityMonth4", quantityMonth4);
    request.setAttribute("quantityMonth5", quantityMonth5);
    request.setAttribute("quantityMonth6", quantityMonth6);
    request.setAttribute("quantityMonth7", quantityMonth7);
    request.setAttribute("quantityMonth8", quantityMonth8);
    request.setAttribute("quantityMonth9", quantityMonth9);
    request.setAttribute("quantityMonth10", quantityMonth10);
    request.setAttribute("quantityMonth11", quantityMonth11);
    request.setAttribute("quantityMonth12", quantityMonth12);
    
    request.getRequestDispatcher("ChartQuantity.jsp").forward(request, response);
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
