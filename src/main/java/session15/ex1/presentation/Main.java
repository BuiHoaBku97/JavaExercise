package session15.ex1.presentation;

import session15.ex1.models.Movie;
import session15.ex1.models.MovieManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MovieManager<Movie> movieManager = new MovieManager<>();

    public static void main(String[] args) {
        initializeMovies();

        do {
            printMenu();
            int choice = getIntInput("Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    deleteMovie();
                    break;
                case 3:
                    updateMovie();
                    break;
                case 4:
                    displayMovies(movieManager.getAll(), "Danh sách phim:");
                    break;
                case 5:
                    searchMovieByTitle();
                    break;
                case 6:
                    filterMoviesByRating();
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Vui lòng chọn chức năng từ 1 đến 7.");
            }
        } while (true);
    }

    private static void initializeMovies() {
        movieManager.add(new Movie("01", "Na tra", "Nguyễn Công Hưởng", LocalDate.of(2025, 2, 23), 9.5));
        movieManager.add(new Movie("02", "Tây Du Ký", "Ngô Thừa Ân", LocalDate.of(1973, 1, 1), 7.9));
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Chọn chức năng:");
        System.out.println("1. Thêm phim");
        System.out.println("2. Xóa phim");
        System.out.println("3. Sửa phim");
        System.out.println("4. Hiển thị phim");
        System.out.println("5. Tìm kiếm phim theo tên");
        System.out.println("6. Lọc phim theo rating");
        System.out.println("7. Thoát");
    }

    private static void addMovie() {
        Movie movie = inputMovie("Nhập ID phim: ");
        movieManager.add(movie);
        System.out.println("Phim đã được thêm thành công.");
    }

    private static void deleteMovie() {
        String id = getStringInput("Nhập ID phim cần xóa: ");
        if (movieManager.delete(id)) {
            System.out.println("Phim đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy phim muốn xóa!");
        }
    }

    private static void updateMovie() {
        String id = getStringInput("Mời nhập id phim muốn sửa: ");
        if (movieManager.findById(id) == null) {
            System.out.println("Không tìm thấy phim với id = " + id);
            return;
        }

        Movie movie = inputMovie("Nhập ID phim mới: ");
        if (movieManager.update(id, movie)) {
            System.out.println("Cập nhật phim thành công!");
        }
    }

    private static void searchMovieByTitle() {
        String title = getStringInput("Nhập tiêu đề phim để tìm kiếm: ");
        List<Movie> results = movieManager.searchByTitle(title);

        if (results.isEmpty()) {
            System.out.println("Không tìm thấy phim");
            return;
        }

        displayMovies(results, "Phim tìm thấy:");
    }

    private static void filterMoviesByRating() {
        double rating = getDoubleInput("Nhập rating tối thiểu để lọc: ");
        List<Movie> results = movieManager.filterByRating(rating);

        if (results.isEmpty()) {
            System.out.println("Không có phim nào có rating lớn hơn " + rating);
            return;
        }

        displayMovies(results, "Phim có rating lớn hơn " + rating + ":");
    }

    private static Movie inputMovie(String idMessage) {
        String id = getStringInput(idMessage);
        String title = getStringInput("Nhập tiêu đề phim: ");
        String director = getStringInput("Nhập đạo diễn: ");
        LocalDate releaseDate = getDateInput("Nhập ngày phát hành (yyyy-MM-dd): ");
        double rating = getDoubleInput("Nhập rating: ");

        return new Movie(id, title, director, releaseDate, rating);
    }

    private static void displayMovies(List<Movie> movies, String title) {
        System.out.println(title);
        if (movies.isEmpty()) {
            System.out.println("Danh sách phim trống.");
            return;
        }

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private static String getStringInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String message) {
        do {
            try {
                return Integer.parseInt(getStringInput(message));
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
            }
        } while (true);
    }

    private static double getDoubleInput(String message) {
        do {
            try {
                return Double.parseDouble(getStringInput(message));
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số thực hợp lệ!");
            }
        } while (true);
    }

    private static LocalDate getDateInput(String message) {
        do {
            try {
                return LocalDate.parse(getStringInput(message));
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Ngày phát hành phải đúng định dạng yyyy-MM-dd!");
            }
        } while (true);
    }
}
