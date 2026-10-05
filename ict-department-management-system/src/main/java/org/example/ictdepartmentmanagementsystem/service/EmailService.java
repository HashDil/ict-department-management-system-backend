package org.example.ictdepartmentmanagementsystem.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Resend resend;

    public EmailService(@Value("${resend.api.key}") String apiKey) {
        this.resend = new Resend(apiKey);
    }

    public void sendDefaultCredentials(
            String fullName,
            String email,
            String enrollmentNumber,
            String defaultPassword) {

        System.out.println("========== RESEND EMAIL START ==========");
        System.out.println("Sending email to: " + email);

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
                        "<td style='padding: 8px;'><strong>Enrollment Number:</strong></td>" +
                        "<td style='padding: 8px;'>" + enrollmentNumber + "</td>" +
                        "</tr>" +

                        "<tr>" +
                        "<td style='padding: 8px;'><strong>Default Password:</strong></td>" +
                        "<td style='padding: 8px;'>" + defaultPassword + "</td>" +
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

        try {

            CreateEmailOptions params = CreateEmailOptions.builder()
                    .from("ICT Department <onboarding@resend.dev>")
                    .to(email)
                    .subject(
                            "Department of Information and Communication Technology - Your Login Credentials"
                    )
                    .html(htmlContent)
                    .build();

            System.out.println("========== CALLING RESEND API ==========");

            var response = resend.emails().send(params);

            System.out.println("========== EMAIL SENT SUCCESSFULLY ==========");
            System.out.println("Resend Email ID: " + response.getId());

        } catch (ResendException e) {

            System.out.println("========== RESEND EMAIL FAILED ==========");
            e.printStackTrace();

            throw new RuntimeException("Failed to send email", e);
        }
    }
}