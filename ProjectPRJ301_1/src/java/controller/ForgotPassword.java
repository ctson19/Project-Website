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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Email;
import util.generateOTP;

/**
 *
 * @author Admin
 */
@WebServlet(name="ForgotPassword", urlPatterns={"/forgot-password"})
public class ForgotPassword extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        DAO d = new DAO();
        
            generateOTP generate = new generateOTP();
            String otp = generate.generateOTP(6);
            Cookie cookieOtp = new Cookie("otp", otp);
            Cookie cookieEmail = new Cookie("cEmail", email);
            cookieOtp.setMaxAge(60 * 10);
            cookieEmail.setMaxAge(60 * 60);
            Email sendEmail = new Email();
            sendEmail.sendEmail(email, otp);
            response.addCookie(cookieOtp);
            response.addCookie(cookieEmail);
            response.sendRedirect("otp-password");
        
        }
    }





