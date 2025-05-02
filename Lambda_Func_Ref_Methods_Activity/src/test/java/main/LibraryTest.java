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
        // Initialize a new Library instance before each test
        library = new Library();
        
        // Create a list of test books to work with
        testBooks = new ArrayList<>();
        Book book1 = new Book("Power laws", "Frank Sinatra", "2010");
        Book book2 = new Book("Atomic Habits", "James Bond", "2008");
        Book book3 = new Book("Gold rule of businesses", "Robert Grill", "2016");
        testBooks.add(book1);
        testBooks.add(book2);
        testBooks.add(book3);

        // Ensure the shared static list is cleared and populated with test data
        BookManager.Books.clear();
        BookManager.Books.addAll(testBooks);
    }

    @Test
    void testAddBook() {
        // Verify that a new book can be added to the library and appears in the shared static list
        Book newBook = new Book("The Obstacle Is the Way", "James Miller", "2018");
        library.addBook(newBook);
        assertTrue(BookManager.Books.contains(newBook));
    }

    @Test
    void testGetBooks() {
        // Verify that getBooks() returns all the books added to the library
        assertEquals(testBooks.size(), library.getBooks().size());
        assertTrue(library.getBooks().containsAll(testBooks));
    }

    @Test
    void testFindBooksByAuthor() {
        // Verify finding books by specific authors returns the correct results
        List<Book> frankSinatraBooks = library.findBooksByAuthor("Frank Sinatra");
        assertEquals(1, frankSinatraBooks.size());
        assertEquals("Power laws", frankSinatraBooks.get(0).getTitle());

        List<Book> jamesBondBooks = library.findBooksByAuthor("James Bond");
        assertEquals(1, jamesBondBooks.size());
        assertEquals("Atomic Habits", jamesBondBooks.get(0).getTitle());

        // Verify that searching for a non-existent author returns an empty list
        List<Book> nonExistentAuthorBooks = library.findBooksByAuthor("Nonexistent Author");
        assertTrue(nonExistentAuthorBooks.isEmpty());
    }

    @Test
    void testFindBooksPublishedBefore() {
        // Verify that books published before 2015 are correctly filtered
        List<Book> booksBefore2015 = library.findBooksPublishedBefore(2015);
        assertEquals(2, booksBefore2015.size());
        assertEquals("Power laws", booksBefore2015.get(0).getTitle());

        // Verify that no books are returned for a year earlier than all books in the list
        List<Book> booksBefore2000 = library.findBooksPublishedBefore(2000);
        assertTrue(booksBefore2000.isEmpty());

        // Verify that all books published before 2020 are included
        List<Book> booksBefore2020 = library.findBooksPublishedBefore(2020);
        assertEquals(3, booksBefore2020.size());
        assertTrue(booksBefore2020.stream().allMatch(book -> Integer.parseInt(book.getYear()) < 2020));
    }

    @Test
    void testSortBooksByTitle() {
        // Verify that books are sorted alphabetically by title
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
        // Verify that books are sorted in ascending order by year of publication
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
