package com.hpdts.books.rabbitMQ;


import com.hpdts.books.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private IBookService bookService;


    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
        bookService.getBooksAndSave(message);

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
