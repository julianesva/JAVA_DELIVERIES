import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;  
  
public class LibraryTest {  


    //================================Library Class Tests================================
    @Test  
    public void testBookCreated() {  
        Library library = new Library();  
        Book book = new Book("Agustin Lara", "Brave New World");  
        library.addBook(book);  
        assertTrue(library.listAvailableBooks().contains(book));  
    }  


    @Test
    public void testAddBookWithError(){
        Library library = new Library();  
        Book book = new Book("Sergio Ramos", "Moby Dick");  
        library.addBook(book);  
        Book duplicatebBook = new Book("Sergio Ramos", "Moby Dick");  
        library.addBook(duplicatebBook); 

        assertFalse(library.addBook(duplicateBook));
    }

    @Test  
    public void testRemoveBookCreated() {  
        Library library = new Library();  
        Book book = new Book("Agustin Lara", "Brave New World");  
        library.addBook(book);  
        library.removeBook(book);
        assertFalse(library.listAvailableBooks().contains(book));  
    }  

    //Adding a new patron to the library test.
    @Test
    public void testRegisterPatron(){
        Library library = new Library();  
        Patron patron = new Patron("Julian Espinoza Valenzuela");  
        library.addPatron(patron);  
        assertTrue(library.listPatrons().contains(patron));  
    }

    @Test
    public void testCheckOutaBook(){
        Library library = new Library();  
        Patron patron = new Patron("Julian Espinoza Valenzuela");  
        library.addPatron(patron);  
        Book book = new Book("Sergio Ramos", "Moby Dick");  
        library.addBook(book);  
        library.checkOutBook(patron, book, 3);
        
        assertTrue(book.isCheckedOut());  
    }

    @Test
    public void testCheckOutaNonexistentBook(){
        Library library = new Library();  
        Patron patron = new Patron("Julian Espinoza Valenzuela");  
        library.addPatron(patron);  
        Book book = new Book("Sergio Ramos", "Moby Dick");  
        
        assertFalse(library.checkOutBook(patron, book, 3));  
    }

    @Test
    public void testReturnaBook(){
        Library library = new Library();  
        Patron patron = new Patron("Julian Espinoza Valenzuela");  
        library.addPatron(patron);  
        Book book = new Book("Sergio Ramos", "Moby Dick");  
        library.addBook(book);  
        Book book2 = new Book("Agustin Lara", "Brave New World");  
        library.addBook(book2);  
        library.checkOutBook(patron, book, 3);
        library.checkOutBook(patron, book2, 3);
        library.returnBook(patron);
        
        assertFalse(book2.isCheckedOut());  
    }

    @Test  
    public void testCalculateFineAfterReturn() {  
        // Setup  
        Library library = new Library();  
        Patron patron = new Patron("Alice Smith");  
        Book book = new Book("Design Patterns", "Erich Gamma");  
  
        library.addBook(book);  
        library.addPatron(patron);  
          
        // Check out for 2 days  
        library.checkOutBook(patron, book, 2);   
          
        // Simulate that 2 days have passed, and set the due date  
        book.setDueDate(LocalDate.now().minusDays(2));  
  
        // Return the book  
        library.returnBook(patron);   
  
        // Act: Calculate fine after returning  
        double fineAfterReturn = library.calculateFine(patron);   
  
        // Assert: No fine should be calculated after return  
        assertEquals(0, fineAfterReturn, "The fine should be zero after returning the book.");  
    }

    @Test  
    public void testListAvailableBooks() {  
        Library library = new Library();  
        Book book = new Book("Agustin Lara", "Brave New World");  
        library.addBook(book);  
        Book book2 = new Book("Fernanda Suarez", "Atomic Habits");  
        library.addBook(book2); 
        Book book3 = new Book("Luis Gonzalez", "50 Power Rules");  
        library.addBook(book3); 
        List<Book> availableBooks = new ArrayList<>();  
        availableBooks = library.listAvailableBooks();
        // Assert the expected number of books
        assertEquals(3, availableBooks.size());
        
        // Assert that all added books are in the list
        assertTrue(availableBooks.contains(book));
        assertTrue(availableBooks.contains(book2));
        assertTrue(availableBooks.contains(book3));
    }  

    @Test
    public void testRegisteredPatrons(){
        Library library = new Library();  
        Patron patron = new Patron("Julian Espinoza Valenzuela");  
        library.addPatron(patron);  
        Patron patron2 = new Patron("Militza Paola Barragan");  
        library.addPatron(patron2);  

        assertEquals(2, library.patrons.size());
        
        // Assert that all added books are in the list
        assertTrue(library.patrons.contains(patron));
        assertTrue(library.patrons.contains(patron2));
    }

    //================================Patron Class Tests================================
    @Test  
    public void testCreatePatron() {  
        Patron patron = new Patron("Julian Espinoza Valenzuela");  

        assertEquals("Julian Espinoza Valenzuela", patron.getName());
    }  

    // In this test we are testing the next methods: Patron creation, checkOutBook(), returnBook(), getCheckedOutBooks().
    @Test  
    public void testgetCheckOutBooks() {  
        Patron patron = new Patron("Julian Espinoza Valenzuela");  
        Book book = new Book("Agustin Lara", "Brave");
        Book book2 = new Book("Fernanda Suarez", "Atomic Habits");  
        Book book3 = new Book("Luis Gonzalez", "50 Power Rules");  

        patron.checkOutBook(book);
        patron.checkOutBook(book2);
        patron.checkOutBook(book3);

        patron.returnBook(book);

        List<Book> patronbooks = new ArrayList<>();  
        patronbooks = patron.getCheckedOutBooks();


        assertEquals(2, patronbooks.size());
        
        
        assertTrue(patron.checkedOutBooks.contains(book2));
        assertTrue(patron.checkedOutBooks.contains(book3));
    }  

    @Test  
    public void testCheckedOutBook() {  
        Patron patron = new Patron("Julian Espinoza Valenzuela");  
        Book book = new Book("Agustin Lara", "Brave"); 

        patron.checkOutBook(book);
        
        assertTrue(patron.hasCheckedOutBook(book));
    }  



    //================================Book Class Tests================================

    //In this test we are testing the next methods: book creation, getAuthor(), getTitle().
    @Test  
    public void testBookCreation() {  
        Book book = new Book("Agustin Lara", "Brave New World");  

        assertEquals("Agustin Lara", book.getAuthor());
        assertEquals("Brave New World", book.getTitle());
    }  

    //In this test we are testing the next methods: book creation, checkOut(), isCheckedOut(), getDueDate().
    @Test  
    public void testCheckOutBook() {  
        Book book = new Book("Agustin Lara", "Brave New World");  
        book.checkOut(2);
        
        assertTrue(book.isCheckedOut());
        LocalDate expectedDueDate = LocalDate.now().plusDays(2);
        assertEquals(expectedDueDate, book.getDueDate());
    }  

    @Test  
    public void testReturnBook() {  
        Book book = new Book("Agustin Lara", "Brave New World");  
        book.checkOut(2);

        book.returnBook();
        
        assertFalse(book.isCheckedOut());
        assertEquals(null, book.getDueDate());
    }  

    @Test  
    public void testSetDueDate() {  
        Book book = new Book("Agustin Lara", "Brave New World");  
        book.checkOut(2);

        book.setDueDate(2025, 4, 10);

        LocalDate expectedDueDate = LocalDate.of(2025, 4, 10);
        assertEquals(expectedDueDate, book.getDueDate());
        
    }  







}  
 