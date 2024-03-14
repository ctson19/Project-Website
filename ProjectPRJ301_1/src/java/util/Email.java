/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    // Email: phanhuynh681@gmail.com
    // Password: suvp xujm odgw qynb
    static final String from = "soncthe171406@fpt.edu.vn";
    static final String password = "quvi brzu fnvy idvs";

    public void sendEmail(String to, String otp) {
        // Properties: Khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.username", "soncthe171406@fpt.edu.vn");
        props.put("mail.smtp.password", "son192003");
        props.put("mail.smtp.port", "587"); // Use port 587 for TLS, or 465 for SSL
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Gửi email
        MimeMessage msg = new MimeMessage(session);
        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");

            // Người gửi
            msg.setFrom(new InternetAddress(from));

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // Tiêu đề email
            msg.setSubject("Mã OTP lấy lại mật khẩu", "UTF-8");

            // Quy định ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            msg.setReplyTo(InternetAddress.parse(to, false));

            // Nội dung
            msg.setContent("<html>" +
                    "<body>" +
                    "<h1>" +
                    "Son PC" +
                    "</h1>" +
                    "Mã OTP lấy lại mật khẩu của bạn là: " +
                    "<b>" + otp + "</b>" +
                    "<p>Nhanh không hết hạn</p>" +
                    "</body>" +
                    "</html>",
                    "text/html; charset=UTF-8");

            // Gửi email
            Transport.send(msg);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}





