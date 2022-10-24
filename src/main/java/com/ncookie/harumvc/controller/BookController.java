package com.ncookie.harumvc.controller;

import com.ncookie.harumvc.domain.Book;
import com.ncookie.harumvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("book/create");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createPost(BookForm bookForm) {
        ModelAndView mav = new ModelAndView();
        Book book = new Book();

        System.out.println(bookForm.toString());

        book.setTitle(bookForm.getTitle());
        book.setCategory(bookForm.getCategory());
        book.setPrice(bookForm.getPrice());

        long bookId = this.bookService.save(book);

        if (bookId == 0) {
            mav.setViewName("redirect:/create");
        } else {
            mav.setViewName("redirect:/detail?bookId=" + bookId);
        }

        return mav;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String bookDetail(@RequestParam(value = "bookId") String id, Model model) {
        Book book = null;

        Optional<Book> oBook = bookService.findOne(Long.parseLong(id));
        if (oBook.isPresent()) {
            book = oBook.get();
        }

        model.addAttribute("book", book);

        return "book/detail";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam(value = "bookId") String id, Model model) {
        Optional<Book> oBook = bookService.findOne(Long.parseLong(id));
        if (oBook.isPresent()) {
            Book book = oBook.get();
            model.addAttribute(book);
        }

        return "book/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateBook(@RequestParam(value = "bookId") String id, BookForm bookForm) {
        Optional<Book> oBook = bookService.findOne(Long.parseLong(id));
        if (oBook.isPresent()) {
            Book book = oBook.get();

            book.setTitle(bookForm.getTitle());
            book.setCategory(bookForm.getCategory());
            book.setPrice(bookForm.getPrice());

            long bookId = this.bookService.save(book);
        }

        return "redirect:/detail?bookId=" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(@RequestParam(value = "bookId") String id) {
        bookService.delete(Long.parseLong(id));

        return "redirect:/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String selectList(@RequestParam(value = "keyword") Optional<String> keyword, Model model) {
        if (keyword.isPresent()) {
            List<Book> books = bookService.findByKeyword(keyword.get());
            model.addAttribute("books", books);
            model.addAttribute("keyword", keyword.get());
        } else {
            List<Book> books = bookService.findAll();
            model.addAttribute("books", books);
        }

        return "book/list";
    }

    @GetMapping("/test")
    @ResponseBody
    public String testmethod(@RequestParam String test) {
        return "TEST : " + test;
    }
}
