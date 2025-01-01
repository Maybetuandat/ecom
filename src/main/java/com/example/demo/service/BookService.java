package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Book;

@Service
public interface BookService {

    public Book updateBook(Book book, MultipartFile file);

}
