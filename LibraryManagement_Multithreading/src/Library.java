import java.util.HashMap;  
import java.util.Map;
  
public class Library {  
    private HashMap<String, Integer> storageBooks;  
  
    public Library() {  
        storageBooks = new HashMap<>(); 
        storageBooks.put("Book 1", 3);
        storageBooks.put("Book 2", 3);
        storageBooks.put("Book 3", 3);
        storageBooks.put("Book 4", 3);
        storageBooks.put("Book 5", 3); 
    }  
  
    public boolean checkOutBook(String bookTitle) {  
        if(storageBooks.containsKey(bookTitle)){
            Integer bookQuant = storageBooks.get(bookTitle);  
            if (bookQuant > 0) {  
                storageBooks.put(bookTitle, bookQuant - 1);
                System.out.println("Ckecking Out: " + bookTitle + " Reamining Books: " + (bookQuant - 1));
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
        if(storageBooks.containsKey(bookTitle)){
            Integer bookQuant = storageBooks.get(bookTitle);  
            if (bookQuant < 3) { 
                storageBooks.put(bookTitle, bookQuant + 1);
                System.out.println("Returning: " + bookTitle + " Reamining Books: " + (bookQuant + 1));
                return true;
            }
            else{
                System.out.println("Sorry, that book that you are tying to return is not from our library.");
            }
        }
        else{
            System.out.println("Sorry, that book is not from our library.");  
        }
        return false;
    } 
    
    // Method to print the HashMap that stores the inventory.
    public void printBooksInventory() {
        System.out.println("Final Library Inventory:");
        for (Map.Entry<String, Integer> entry : storageBooks.entrySet()) {
            String bookName = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(bookName + ": " + quantity);
        }
    }
}  
