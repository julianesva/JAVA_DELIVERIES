package main;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize the library with some books
        initializeLibrary();
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    displayfilterOptions();
                    break;
                case 3:
                    displaySortOptions();
                    break;
                case 4:
                    displayAvailableBooks();
                    break;
                case 5:
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void initializeLibrary() {
        // Add some initial books to the library
        Book book1 = new Book("Power laws", "Frank Sinatra", "2010");
        Book book2 = new Book("Atomic Habits", "James Bond", "2008");
        Book book3 = new Book("Gold rule of businesses", "Robert Grill", "2016");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        
        System.out.println("Library initialized with 3 books.");
    }
    
    private static void displayMenu() {
        System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
        System.out.println("1. Add a new book");
        System.out.println("2. Find books");
        System.out.println("3. Sort books");
        System.out.println("4. Available Books");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }
    
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }
    
    private static void addNewBook() {
        System.out.println("\n========== ADD NEW BOOK ==========");
        
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        
        System.out.print("Enter publication year: ");
        String year = scanner.nextLine();
        
        Book newBook = new Book(title, author, year);
        library.addBook(newBook);
        
        System.out.println("Book added successfully!");
        System.out.println("New book details: " + newBook);
    }
    

    private static void displayfilterOptions() {
        System.out.println("\n========== Find Books ==========");
        System.out.println("1. Find books by author");
        System.out.println("2. Find books published before a specific year");
        System.out.println("3. Back to main menu");
        System.out.print("Enter your choice (1-3): ");
        
        int sortChoice = getUserChoice();
        
        switch (sortChoice) {
            case 1:
                findBooksByAuthor();
                break;
            case 2:
                findBooksPublishBeforeSpecYear();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Returning to main menu.");
        }
    }

    private static void findBooksByAuthor() {
        System.out.println("\n========== FIND BOOKS BY AUTHOR ==========");
        
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        
        List<Book> booksByAuthor = library.findBooksByAuthor(author);
        
        if (booksByAuthor.isEmpty()) {
            System.out.println("No books found by author: " + author);
        } else {
            System.out.println("Found " + booksByAuthor.size() + " book(s) by " + author + ":");
            booksByAuthor.forEach(System.out::println);
        }
    }

    private static void findBooksPublishBeforeSpecYear() {
        System.out.println("\n========== FIND BOOKS PUBLISHED BEFORE A SPECIFIC YEAR ==========");
        
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        
        List<Book> booksBeforeSpecYear = library.findBooksPublishedBefore(year);
        
        if (booksBeforeSpecYear.isEmpty()) {
            System.out.println("No books found published before: " + year);
        } else {
            System.out.println("Found " + booksBeforeSpecYear.size() + " published book(s) before:  " + year + ":");
            booksBeforeSpecYear.forEach(System.out::println);
        }
    }
    
    private static void displaySortOptions() {
        System.out.println("\n========== SORT BOOKS ==========");
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by publication year");
        System.out.println("3. Back to main menu");
        System.out.print("Enter your choice (1-3): ");
        
        int sortChoice = getUserChoice();
        
        switch (sortChoice) {
            case 1:
                displaySortedBooksByTitle();
                break;
            case 2:
                displaySortedBooksByYear();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Returning to main menu.");
        }
    }
    
    private static void displaySortedBooksByTitle() {
        System.out.println("\n========== BOOKS SORTED BY TITLE ==========");
        List<Book> sortedBooks = library.sortBooksByTitle();
        
        if (sortedBooks.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Books sorted by title:");
            sortedBooks.forEach(System.out::println);
        }
    }
    
    private static void displaySortedBooksByYear() {
        System.out.println("\n========== BOOKS SORTED BY YEAR ==========");
        List<Book> sortedBooks = library.sortBooksByYear();
        
        if (sortedBooks.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Books sorted by publication year:");
            sortedBooks.forEach(System.out::println);
        }
    }

    private static void displayAvailableBooks(){
        System.out.println("\n========== AVAILABLE BOOKS IN THE LIBRARY ==========");
        List<Book> availableBooks = library.getBooks();
        
        if (availableBooks.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Available Books:");
            availableBooks.forEach(System.out::println);
        }
    }
}