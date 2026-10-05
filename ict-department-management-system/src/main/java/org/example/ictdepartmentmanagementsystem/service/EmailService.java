package org.example.ictdepartmentmanagementsystem.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final String sendGridApiKey;

    public EmailService(
            @Value("${SENDGRID_API_KEY}") String sendGridApiKey) {

        this.sendGridApiKey = sendGridApiKey;
    }

    public void sendDefaultCredentials(
            String fullName,
            String email,
            String enrollmentNumber,
            String defaultPassword) {

        System.out.println("========== SENDGRID EMAIL START ==========");
        System.out.println("Sending email to: " + email);

        try {

            Email from = new Email(
                    "hashdileepa@gmail.com",
                    "ICT Department"
            );

            Email to = new Email(email);

            String subject =
                    "Department of Information and Communication Technology - Your Login Credentials";

            String htmlContent =
                    "<html>" +
                            "<body style='font-family: Arial, sans-serif; line-height: 1.6;'>" +

                            "<h2>Department of Information and Communication Technology</h2>" +

                            "<p>Dear " + fullName + ",</p>" +

                            "<p>Your account has been created in the " +
                            "ICT Department Management System.</p>" +

                            "<p><strong>Your login credentials are:</strong></p>" +

                            "<table style='border-collapse: collapse;'>" +

                            "<tr>" +
                            "<td style='padding: 8px;'>" +
                            "<strong>Enrollment Number:</strong>" +
                            "</td>" +

                            "<td style='padding: 8px;'>" +
                            enrollmentNumber +
                            "</td>" +
                            "</tr>" +

                            "<tr>" +
                            "<td style='padding: 8px;'>" +
                            "<strong>Default Password:</strong>" +
                            "</td>" +

                            "<td style='padding: 8px;'>" +
                            defaultPassword +
                            "</td>" +
                            "</tr>" +

                            "</table>" +

                            "<p>Please log in and change your password immediately.</p>" +

                            "<p>" +
                            "<a href='https://ict-department-frontend-main-mvb9.vercel.app/login'>" +
                            "Login to ICT Department Management System" +
                            "</a>" +
                            "</p>" +

                            "<p>Regards,<br>" +
                            "Department of Information and Communication Technology<br>" +
                            "Uva Wellassa University</p>" +

                            "</body>" +
                            "</html>";

            Content content =
                    new Content("text/html", htmlContent);

            Mail mail =
                    new Mail(from, subject, to, content);

            SendGrid sendGrid =
                    new SendGrid(sendGridApiKey);

            Request request =
                    new Request();

            request.setMethod(Method.POST);

            request.setEndpoint("mail/send");

            request.setBody(mail.build());

            System.out.println(
                    "========== CALLING SENDGRID API =========="
            );

            Response response =
                    sendGrid.api(request);

            System.out.println(
                    "========== SENDGRID RESPONSE =========="
            );

            System.out.println(
                    "Status Code: " +
                            response.getStatusCode()
            );

            System.out.println(
                    "Body: " +
                            response.getBody()
            );

            if (response.getStatusCode() >= 200 &&
                    response.getStatusCode() < 300) {

                System.out.println(
                        "========== EMAIL SENT SUCCESSFULLY =========="
                );

            } else {

                throw new RuntimeException(
                        "SendGrid failed. Status code: " +
                                response.getStatusCode() +
                                ", body: " +
                                response.getBody()
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "========== SENDGRID EMAIL FAILED =========="
            );

            e.printStackTrace();

            throw new RuntimeException(
                    "Failed to send email using SendGrid",
                    e
            );
        }
    }
}