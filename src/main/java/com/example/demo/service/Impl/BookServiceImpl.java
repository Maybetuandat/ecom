package com.example.demo.service.Impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Book;
import com.example.demo.model.Item;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book updateBook(Book book, MultipartFile image) {
        // TODO Auto-generated method stub

        if (!image.isEmpty()) {
            try {
                // Xóa ảnh cũ
                Files.deleteIfExists(Paths.get("src/main/resources/static/img/product_img" + book.getImageUrl()));
                // Lưu ảnh mới
                String fileName = image.getOriginalFilename();
                Path path = Paths.get("src/main/resources/img/product_img/" + fileName);
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                book.setImageUrl(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Nếu người dùng không chọn ảnh mới, thì giữ nguyên ảnh cũ
            Book existingItem = bookRepository.findById(book.getId()).orElse(null);
            book.setImageUrl(existingItem.getImageUrl());
        }
        System.out.println("BookServiceImpl: " + book);
        return bookRepository.save(book);
    }

}
