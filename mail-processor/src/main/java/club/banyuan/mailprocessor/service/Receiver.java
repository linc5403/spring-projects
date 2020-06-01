package club.banyuan.mailprocessor.service;

import club.banyuan.mailprocessor.bean.MailMessage;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Receiver {
    @Autowired
    private MailService mailService;

    @Autowired
    private Gson gson;

    @RabbitListener(queues = "spring-boot")
    public void receiveMessage(String mail, Channel channel, Message message) throws InterruptedException, IOException {
        // 三个参数, 第一个表示发送方发送的消息,
        // 第二个为channel,发送ack的时候用,
        // 第三个message的属性中包含该消息的tag,也是用于ACK
        MailMessage mailBody = gson.fromJson(mail, MailMessage.class);

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        mailService.sendMail(mailBody.getReceiver(), mailBody.getSubject(), mailBody.getContent());
        // 调用channel对象的basicAck方法发送ACK, 注意第二个参数的false是指只回复当前这个消息的ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println("send ack");
    }
}
