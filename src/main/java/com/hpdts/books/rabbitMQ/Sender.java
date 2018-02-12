package com.hpdts.books.rabbitMQ;

import com.hpdts.books.BooksApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Sender{

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;

    public Sender(Receiver receiver, RabbitTemplate rabbitTemplate,
                  ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    public void queueMessage(String bookTitle) throws Exception {
        logger.info("Sending message... {}", bookTitle);
        rabbitTemplate.convertAndSend(BooksApplication.queueName, bookTitle);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
