package com.example.methawee.myapplication.main.book;


import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.data.BookRepository;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class BookPresenter implements Observer {

    private BookView view;
    private BookRepository repository;

    ArrayList<Book> books;

    public BookPresenter(BookView view, BookRepository repository) {
        this.repository = repository;
        this.view = view;
        initialize();
    }

    public void initialize() {
        repository.addObserver(this);
        repository.fetchAllBooks();
    }

    @Override
    public void update(Observable obj, Object arg) {
        if (obj == repository) {
            books = new ArrayList<Book>(repository.getAllBooks());
            repository.sort(books);
            view.setBookList(books);
        }
    }

    public void search(String newText) {
        view.setBookList(repository.search(newText));
    }

}
