package com.demo.api.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @PostMapping("/save")
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/update/{id}/{page}")
    public String updateBook(@PathVariable int id, @PathVariable int page) {
        Book book = bookRepository.findById(id).get();
        book.setPages(page);
        bookRepository.save(book);
        return "updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
        return "deleted";
    }

    @GetMapping("/getInfo/{id}")
    public void getInfo(@PathVariable(name = "id") int id) {
        System.out.println(bookRepository.findLastChangeRevision(id));
    }

}