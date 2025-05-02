package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;
    private List<Book> testBooks;

    @BeforeEach
    void setUp() {
        library = new Library();
        testBooks = new ArrayList<>();
        Book book1 = new Book("Power laws", "Frank Sinatra", "2010");
        Book book2 = new Book("Atomic Habits", "James Bond", "2008");
        Book book3 = new Book("Gold rule of businesses", "Robert Grill", "2016");
        testBooks.add(book1);
        testBooks.add(book2);
        testBooks.add(book3);

        // Clear the shared static list and add test books
        BookManager.Books.clear(); // Access the static variable through the interface
        BookManager.Books.addAll(testBooks);
    }

    @Test
    void testAddBook() {
        Book newBook = new Book("The Obstacle Is the Way", "James Miller", "2018");
        library.addBook(newBook);
        assertTrue(BookManager.Books.contains(newBook)); // Check the static list
    }

    @Test
    void testGetBooks() {
        assertEquals(testBooks.size(), library.getBooks().size());
        assertTrue(library.getBooks().containsAll(testBooks)); // Checks the list returned by the instance
    }

    @Test
    void testFindBooksByAuthor() {
        List<Book> frankSinatraBooks = library.findBooksByAuthor("Frank Sinatra");
        assertEquals(1, frankSinatraBooks.size());
        assertEquals("Power laws", frankSinatraBooks.get(0).getTitle());

        List<Book> jamesBondBooks = library.findBooksByAuthor("James Bond");
        assertEquals(1, jamesBondBooks.size());
        assertEquals("Atomic Habits", jamesBondBooks.get(0).getTitle());

        List<Book> nonExistentAuthorBooks = library.findBooksByAuthor("Nonexistent Author");
        assertTrue(nonExistentAuthorBooks.isEmpty());
    }

    @Test
    void testFindBooksPublishedBefore() {
        List<Book> booksBefore2015 = library.findBooksPublishedBefore(2015);
        assertEquals(2, booksBefore2015.size());
        assertEquals("Power laws", booksBefore2015.get(0).getTitle());

        List<Book> booksBefore2000 = library.findBooksPublishedBefore(2000);
        assertTrue(booksBefore2000.isEmpty());

        List<Book> booksBefore2020 = library.findBooksPublishedBefore(2020);
        assertEquals(3, booksBefore2020.size());
        assertTrue(booksBefore2020.stream().allMatch(book -> Integer.parseInt(book.getYear()) < 2020));
    }

    @Test
    void testSortBooksByTitle() {
        List<Book> sortedBooks = library.sortBooksByTitle();
        List<String> expectedTitles = testBooks.stream()
                .map(Book::getTitle)
                .sorted()
                .collect(Collectors.toList());
        List<String> actualTitles = sortedBooks.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
        assertEquals(expectedTitles, actualTitles);
    }

    @Test
    void testSortBooksByYear() {
        List<Book> sortedBooks = library.sortBooksByYear();
        List<Integer> expectedYears = testBooks.stream()
                .map(Book::getYear)
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        List<Integer> actualYears = sortedBooks.stream()
                .map(Book::getYear)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        assertEquals(expectedYears, actualYears);
    }
}