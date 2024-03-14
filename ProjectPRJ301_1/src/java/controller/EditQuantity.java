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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name="EditQuantity", urlPatterns={"/editQuantity"})
public class EditQuantity extends HttpServlet {
   
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
            out.println("<title>Servlet EditQuantity</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditQuantity at " + request.getContextPath () + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();
    String action = request.getParameter("action");
    String id_raw = request.getParameter("pid");
    List<Item> cartItem = null;
    DAO d = new DAO();
    int idi=Integer.parseInt(id_raw);
    if (session.getAttribute("cart") != null) {
        cartItem = (List<Item>) session.getAttribute("cart");
        Product p = d.getProductByID(idi);
        Item item = d.getItemByProduct(p, cartItem);
        int curQuantity = item.getQuantity();
        int numberItem = (int) session.getAttribute("numberItem");
        
        if (action.equals("minus")) {
            if (curQuantity == 1) {
                cartItem.remove(item);
                numberItem--;
            } else {
                curQuantity--;
                item.setQuantity(curQuantity);
            }
        }
        
        if (action.equals("plus")) {
            curQuantity++;
            item.setQuantity(curQuantity);
        }
        
        // Cập nhật giá trị "totalMoney" trong phiên làm việc
        double total = d.getTotalMoney(cartItem);
        session.setAttribute("totalMoney", total);
        
        // Cập nhật số lượng sản phẩm và giỏ hàng trong phiên làm việc
        session.setAttribute("numberItem", numberItem);
        session.setAttribute("cart", cartItem);
        session.setMaxInactiveInterval(3600);
    }
    
    // Chuyển hướng đến trang Cart.jsp
    request.getRequestDispatcher("Cart.jsp").forward(request, response);
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
