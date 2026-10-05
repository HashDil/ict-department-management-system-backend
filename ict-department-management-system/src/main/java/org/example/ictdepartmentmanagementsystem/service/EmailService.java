package org.example.ictdepartmentmanagementsystem.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendDefaultCredentials(String fullName, String email, String enrollmentNumber, String defaultPassword) {

        System.out.println("========== EMAIL START ==========");
        System.out.println("Sending email to: " + email);
        System.out.println("MAIL_USERNAME configured: " +
                System.getenv("MAIL_USERNAME"));


        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Department Of Information and Communication Technology - Your Login Credentials");
            message.setText("Dear "+fullName+" ,\n\n" +
                    "Your account has been created in the ICT Department Management System.\n\n" +
                    "Your login credentials are:\n" +
                    "  Enrollment Number : " + enrollmentNumber + "\n" +
                    "  Default Password  : " + defaultPassword + "\n\n" +
                    "Please log in and change your password immediately.\n\n" +
                    "Login here: https://ict-department-frontend-main-mvb9.vercel.app/login\n\n" +
                    "Regards,\n" +
                    "Department of Information and Communication Technology\n" +
                    "Uva Wellassa University"
            );

            mailSender.send(message);

            System.out.println("========== EMAIL SENT SUCCESSFULLY ==========");
        }catch (Exception e){
            System.out.println("Email could not be sent because of an exception");
        }
    }
}
