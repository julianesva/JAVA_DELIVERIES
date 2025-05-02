package main;
import java.util.List;
import java.util.ArrayList;

public interface BookManager {

    List<Book> Books = new ArrayList<>();

    void addBook(Book book);

    List<Book> getBooks();

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksPublishedBefore(int year);

    List<Book> sortBooksByTitle();

    List<Book> sortBooksByYear();

    default void printAllBooks(){
        getBooks().forEach(System.out::println);
    }

    
}
