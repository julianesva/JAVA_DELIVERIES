import java.time.LocalDate;  
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List; 
  
public class Library {  
    private List<Book> books;  
    private List<Patron> patrons;  
    private HashMap<String, Integer> checkedOutBooks;  
  
    public Library() {  
        books = new ArrayList<>();  
        patrons = new ArrayList<>();  
        checkedOutBooks = new HashMap<>();  
    }  
  /* 
    public void addBook(Book book) {  
        books.add(book);  
    }  
  
    public void removeBook(Book book) {  
        books.remove(book);  
    }  
  
    public void addPatron(Patron patron) {  
        patrons.add(patron);  
    } 
  */ 
  
    public boolean checkOutBook(String bookTitle) {  
        if(checkedOutBooks.containsKey(bookTitle)){
            Integer bookQuant = checkedOutBooks.get(bookTitle);  
            if (bookQuant > 0) {  
                checkedOutBooks.put(bookTitle, currentCount - 1);
                return true;
            }  
            else{
                System.out.println("Sorry, we currently do not have that book in storage.");
            }

        }
        else{
            System.out.println("Sorry, that book is not in the library.");
        }
        return false;  
    }  
  
    public boolean returnBook(String bookTitle) {  
        if(checkedOutBooks.containsKey(bookTitle)){
            Integer bookQuant = checkedOutBooks.get(bookTitle);  
            checkedOutBooks.put(bookTitle, bookQuant + 1);
            return true;
        }
        else{
            System.out.println("Sorry, that book is not from our library.");
            return false;  
        }
    }  
  /* 
    public double calculateFine(Patron patron) {  
        Book book = checkedOutBooks.get(patron);  
        if (book != null && book.isCheckedOut()) {  
            LocalDate today = LocalDate.now();  
            long daysOverdue = ChronoUnit.DAYS.between(book.getDueDate(), today);  
            if (daysOverdue > 0) {  
                return daysOverdue * 0.50; // $0.50 fine per day overdue  
            }  
        }  
        return 0.0;  
    }  
  
    public List<Book> listAvailableBooks() {  
        List<Book> availableBooks = new ArrayList<>();  
        for (Book book : books) {  
            if (!book.isCheckedOut()) {  
                availableBooks.add(book);  
            }  
        }  
        return availableBooks;  
    }  
  
    public List<Patron> listPatrons() {  
        return patrons;  
    }  
  */
}  
