package main;

public class Book {

    // Fields to store the book's title, author, and publication year
    private String title;
    private String author;
    private String year;

    // Constructor to initialize the Book object with title, author, and year
    public Book(String title, String author, String year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Getter method to return the book's title
    public String getTitle(){
        return title;
    }

    // Getter method to return the book's author
    public String getAuthor(){
        return author;
    }

    // Getter method to return the book's publication year
    public String getYear(){
        return year;
    }

    // Overrides the default toString method to return a formatted string of book details
    @Override
    public String toString(){
        return title + " by " + author + " (" + year + ")";
    }
}

