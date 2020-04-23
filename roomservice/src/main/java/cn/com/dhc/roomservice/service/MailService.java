package cn.com.dhc.roomservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * MailService
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;


    /**
     * 发送HTML模板邮件
     *
     * @param tos
     * @param subject
     * @param content
     */
    public boolean sendHtmlMail(String[] tos, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(tos);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            System.out.println("html邮件发送成功");
            return true;
        } catch (MessagingException e) {
            System.out.println("发送html邮件时发生异常！" + e);
            return false;
        }
    }
}
