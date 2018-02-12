package com.hpdts.books.controller;

import com.hpdts.books.model.Book;
import com.hpdts.books.rabbitMQ.Sender;
import com.hpdts.books.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private Sender sender;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books/{title}", method = POST)
    public ResponseEntity<?> saveBook( @PathVariable("title") String title) {
        Map<String, Object> json = new HashMap<>();
        try{
            if(StringUtils.isEmpty(title)) {
                String messageTitleEmpty = "Expecting a title input";
                logger.warn(messageTitleEmpty);
                json.put("success", false);
                json.put("message", messageTitleEmpty);
                return new ResponseEntity<>(json, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            sender.queueMessage(title);
            String message = "Title = " + title +  " queued";
            json.put("message", message);
            logger.info(message);

        }catch (Exception ex){
            String messageException = "saveBook error " + ex.getMessage();
            logger.error(messageException, ex);
            json.put("success", false);
            json.put("message", messageException);
            return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        json.put("success", true);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }


    @RequestMapping(value = "/books", method = GET)
    @ResponseBody
    public ResponseEntity<?> getByField( @RequestParam("search") String search) {
        Map<String, Object> json = new HashMap<>();
        List<Book> books = new ArrayList<>();

        try{
            if(StringUtils.isEmpty(search)) {
                String messageAuthorEmpty = "Expecting a search input";
                logger.warn(messageAuthorEmpty);
                json.put("success", false);
                json.put("message", messageAuthorEmpty);
                return new ResponseEntity<>(json, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            String[] searches = search.split(":");
            if(!StringUtils.isEmpty(searches[0]) && !StringUtils.isEmpty(searches[1])){
                if(searches[0].equals("author")){
                    String author = searches[1];
                    books = bookService.findBooksByAuthor(author);
                    logger.info("search by author: " + author);
                }else if(searches[0].equals("editor")){
                    String editor = searches[1];
                    books = bookService.findBooksByEditor(editor);
                    logger.info("search by editor: " + editor);
                }else{
                    json.put("success", false);
                    json.put("message", "We only support search?editor:NAME and search?author:NAME");
                    return new ResponseEntity<>(json, HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }
        }catch (Exception ex){
            String messageException = "getByAuthor error " + ex.getMessage();
            logger.error(messageException, ex);
            json.put("success", false);
            json.put("message", messageException);
            return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        json.put("success", true);

        if(books.isEmpty()){
            return new ResponseEntity<>(json, HttpStatus.NO_CONTENT);
        }else {
            json.put("books", books);
            return new ResponseEntity<>(json, HttpStatus.OK);
        }
    }

}
