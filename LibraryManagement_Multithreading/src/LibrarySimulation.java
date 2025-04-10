import java.util.Random;  
  
  
class User implements Runnable {  
    private Library library;  
    private Random random;  
  
    public User(Library library) {  
        this.library = library;  
        this.random = new Random();  
    }  
  
    @Override  
    public void run() {  
        for (int i = 0; i < 5; i++) {  
            int booknum = random.nextInt(5);
            String booktitle = "Book " + (booknum + 1);
  
            if (random.nextBoolean()) {  
                library.checkOutBook(booktitle);
            } else {  
                library.returnBook(booktitle);
            }  
  
            try {  
                Thread.sleep(random.nextInt(1000)); // Sleep for 0-999 ms  
            } catch (InterruptedException e) {  
                Thread.currentThread().interrupt();  
            }  
        }  
    }  
}  
  
public class LibrarySimulation {  
    public static void main(String[] args) {  
        Library library = new Library();
  
        // Create and start multiple user threads  
        Thread user1 = new Thread(new User(library), "User 1");  
        Thread user2 = new Thread(new User(library), "User 2");  
        Thread user3 = new Thread(new User(library), "User 3");  
  
        user1.start();  
        user2.start();  
        user3.start();  
  
        // Wait for all threads to finish  
        try {  
            user1.join();  
            user2.join();  
            user3.join();  
        } catch (InterruptedException e) {   
            Thread.currentThread().interrupt();  
        }  

        library.printBooksInventory();
    }  
}  
