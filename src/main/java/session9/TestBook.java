package session9;

public class TestBook {
    public static void main(String[] args) {
        Book[] books = new Book[3];

        books[0] = new Book("Lap trinh Java co ban", "Nguyen Van A", 120000);
        books[1] = new Book("Huong dan OOP", "Tran Thi B", 150000);
        books[2] = new Book("Cau truc du lieu", "Le Van C", 180000);

        System.out.println("Danh sach sach:");
        for (Book book : books) {
            book.printInfo();
        }
    }
}
