package main;


//Class to practice inheritance in java. 
public class Ebook extends Book {

    private int fileSizeKB;

    public Ebook(String title, String author, String year, int fileSizeKB) {
        super(title, author, year); // Calls the constructor of Book
        this.fileSizeKB = fileSizeKB;
    }

    // Getter methods for the Ebook-specific attributes

    public int getFileSizeKB() {
        return fileSizeKB;
    }

    //Setter methods for the Ebook-specific attributes

    public void setFileSizeKB(int fileSizeKB) {
        this.fileSizeKB = fileSizeKB;
    }

}
