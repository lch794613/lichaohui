package lch.dy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class JSM  {

    @Autowired
    private JavaMailSenderImpl mailSender;

    public void sendTxtMail(String email,String code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        System.out.println("开始发邮件");
        simpleMailMessage.setTo(new String[] {email});
        simpleMailMessage.setFrom("383455971@qq.com");
        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试");
        simpleMailMessage.setText("你的邮箱验证码是"+code+" , 请在5分钟内输入 否则就会过期");
        // 发送邮件
        System.out.println("等待ing");
        mailSender.send(simpleMailMessage);
        System.out.println("邮件已发送");
    }
}
