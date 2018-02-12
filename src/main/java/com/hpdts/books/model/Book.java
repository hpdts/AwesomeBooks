package com.hpdts.books.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    private Integer bookId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "EDITOR")
    private String editor;
}
