package main;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements BookManager {

    //Method to add a book to the list of books that the library have. 
    @Override
    public void addBook(Book book){
        Books.add(book);
    }

    //Methos to return all the books in the library. 
    @Override
    public List<Book> getBooks(){
        return Books;
    }

    //Method to find books of a specific author an return them in a list. 
    @Override
    public List<Book> findBooksByAuthor(String author){
        return Books.stream()
                            .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                            .collect(Collectors.toList());
    }

    //Method to filter books previous to an especific year of publication. 
    @Override
    public List<Book> findBooksPublishedBefore(int year) {
        return Books.stream()
                    .filter(book -> {
                        try {
                            return Integer.parseInt(book.getYear()) < year;
                        } catch (NumberFormatException e) {
                            return false; // skip books with invalid year format
                        }
                    })
                    .collect(Collectors.toList());
    }
    
    //Method to sort books by title using their ASCII values. 
    @Override
    public List<Book> sortBooksByTitle(){
        return Books.stream()
                            .sorted((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()))
                            .collect(Collectors.toList());
    }

    //Methos to sort books by year and return this ones.
    @Override
    public List<Book> sortBooksByYear() {
        return Books.stream()
                    .sorted((b1, b2) -> {
                        try {
                            return Integer.compare(Integer.parseInt(b1.getYear()), Integer.parseInt(b2.getYear()));
                        } catch (NumberFormatException e) {
                            return 0; // or handle differently if needed
                        }
                    })
                    .collect(Collectors.toList());
    }
    
    
}
