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
import java.util.List;
import java.util.Map;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name="ThongKe", urlPatterns={"/thongke"})
public class ThongKe extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ThongKe</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ThongKe at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    DAO dao = new DAO();
    
    // Thống kê sản phẩm
    Map<String, Object> productStatistics = dao.productHot();
     List<Map<String, Object>> hotProductsLast7Days = dao.getHotProductsLast7Days();
    request.setAttribute("hotProductsLast7Days", hotProductsLast7Days);
    
    
    if (!productStatistics.isEmpty()) {
        String productName = (String) productStatistics.get("productName");
        int damua = (int) productStatistics.get("damua");
        
        request.setAttribute("productName", productName);
        request.setAttribute("damua", damua);
    }
    
    // Thống kê người dùng
    Map<String, Object> userStatistics = dao.accHOT(); 
    
    if (!userStatistics.isEmpty()) {
        String username = (String) userStatistics.get("username");
        int totalOrders = (int) userStatistics.get("totalOrders");
        
        request.setAttribute("username", username);
        request.setAttribute("totalOrders", totalOrders);
    }

    // Tổng tiền 12 tháng
    double[] totalMoneyByMonth = dao.totalMoney12Months();
    request.setAttribute("totalMoneyByMonth", totalMoneyByMonth);
    
    List<Map<String, Object>> topBuyersLast7Days = dao.getTopBuyersLast7Days();
    request.setAttribute("topBuyersLast7Days", topBuyersLast7Days);
    
    double totalRevenueLast3Days = dao.totalDate(3); // Thống kê trong 3 ngày gần nhất
    double totalRevenueLast7Days = dao.totalDate(7); // Thống kê trong 7 ngày gần nhất
    double totalRevenueLast14Days = dao.totalDate(14);
    double totalRevenueLast21Days = dao.totalDate(21);
    request.setAttribute("totalRevenueLast3Days", totalRevenueLast3Days);
    request.setAttribute("totalRevenueLast7Days", totalRevenueLast7Days);
    request.setAttribute("totalRevenueLast14Days", totalRevenueLast14Days);
    request.setAttribute("totalRevenueLast21Days", totalRevenueLast21Days);
    request.getRequestDispatcher("ThongKe.jsp").forward(request, response);
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
