import java.util.*;


public class BookLibrary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");

            String title = input[0];
            String author = input[1];
            String publisher = input[2];
            String releaseDate = input[3];
            String ISBNNumber = input[4];
            double price = Double.parseDouble(input[5]);

            Book book = new Book(title, author, publisher, releaseDate
                    , ISBNNumber, price);
            boolean containsBook = false;
            for (Book currentBook : books) {
                if (currentBook.getAuthor().equals(author)) {
                    currentBook.setPrice(currentBook.getPrice() + price);
                    containsBook = true;
                    break;
                }
            }

            if (!containsBook) {
                books.add(book);
            }
        }

        books.stream().sorted((b1, b2) ->
                {
                    if (b1.getPrice() == b2.getPrice()) {
                        return b1.getAuthor().compareTo(b2.getAuthor());
                    } else if (b1.getPrice() > b2.getPrice()) {
                        return -1;
                    } else {
                        return 1;
                    }
                })
        .forEach(book-> System.out.println(String.format("%s -> %.2f",
                book.getAuthor(),book.getPrice())));
    }
}


class Book {
    private String title;
    private String author;
    private String publisher;
    private String releaseDate;
    private String ISBNNumber;
    private double price;

    public Book(String title, String author, String publisher
            , String releaseDate, String ISBNNumber, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.ISBNNumber = ISBNNumber;
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Library {
    private String name;
    private ArrayList<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}