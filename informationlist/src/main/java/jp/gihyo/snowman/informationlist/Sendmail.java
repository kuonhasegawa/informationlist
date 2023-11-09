package jp.gihyo.snowman.informationlist;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Sendmail {
    @Autowired
    private MailSender mailSender;

    @RequestMapping(value = "/sendmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> sendmail(@RequestBody MemoForm form) {
        String body = "メッセージ: \n" + form.getMessage();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("kuon.hasegawa@gmail.com");
        msg.setTo("kuon.hasegawa@gmail.com"); // 適宜変更してください
        msg.setSubject("SnowMan出演情報");
        msg.setText("各個人の情報\n\n---------------------------\n" + body + "\n---------------------------");
        mailSender.send(msg);
        return Arrays.asList("OK");
    }
}
