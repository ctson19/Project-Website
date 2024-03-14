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
import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name="addToCart", urlPatterns={"/addtc"})
public class addToCart extends HttpServlet {
   
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
            out.println("<title>Servlet addToCart</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addToCart at " + request.getContextPath () + "</h1>");
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
        HttpSession sesion = request.getSession();
        String id_raw = request.getParameter("id");
        int id = Integer.parseInt(id_raw);
        DAO d = new DAO();
        Product p = d.getProductByID(id);
        
        List<Item> cart = null;
        int checkItemExist = 0;
        if (sesion.getAttribute("cart") == null) {
            //chưa có giỏ hàng thì tạo mới
            int numberItem = 0;
            cart = new ArrayList<>();
            Item item = new Item(p, 1, p.getPrice());
            cart.add(item);
            numberItem++;
            sesion.setAttribute("numberItem", numberItem);
        } else {
            //nếu đã có giỏ hàng thì lấy lại từ session
            cart = (List<Item>) sesion.getAttribute("cart");
            int numberItem1 = (int) sesion.getAttribute("numberItem");
            for (Item item : cart) {
                if (item.getProduct().getId() == p.getId()) {
                    checkItemExist = 1;
                }
            }
            if (checkItemExist == 0) {
                Item item = new Item(p, 1, p.getPrice());
                cart.add(item);
                numberItem1++;
            }
            sesion.setAttribute("numberItem", numberItem1);
        }
        sesion.setAttribute("cart", cart);
        sesion.setMaxInactiveInterval(3600);
        response.sendRedirect("home");
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
