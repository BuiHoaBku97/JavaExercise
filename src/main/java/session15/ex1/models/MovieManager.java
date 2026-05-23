package session15.ex1.models;

import java.util.ArrayList;
import java.util.List;

public class MovieManager<T extends Movie> {
    private final ArrayList<T> movies;

    public MovieManager() {
        this.movies = new ArrayList<>();
    }

    public void add(T movie) {
        movies.add(movie);
    }

    public boolean update(String id, T newMovie) {
        int index = findIndexById(id);
        if (index == -1) {
            return false;
        }

        movies.set(index, newMovie);
        return true;
    }

    public boolean delete(String id) {
        int index = findIndexById(id);
        if (index == -1) {
            return false;
        }

        movies.remove(index);
        return true;
    }

    public T findById(String id) {
        for (T movie : movies) {
            if (movie.getId().equalsIgnoreCase(id)) {
                return movie;
            }
        }
        return null;
    }

    public List<T> searchByTitle(String title) {
        List<T> results = new ArrayList<>();
        for (T movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(movie);
            }
        }
        return results;
    }

    public List<T> filterByRating(double rating) {
        List<T> results = new ArrayList<>();
        for (T movie : movies) {
            if (movie.getRating() > rating) {
                results.add(movie);
            }
        }
        return results;
    }

    public List<T> getAll() {
        return movies;
    }

    public boolean isEmpty() {
        return movies.isEmpty();
    }

    private int findIndexById(String id) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
}
