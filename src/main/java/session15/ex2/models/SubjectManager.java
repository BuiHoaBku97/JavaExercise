package session15.ex2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SubjectManager<T extends Subject> {
    private final ArrayList<T> subjects;

    public SubjectManager() {
        this.subjects = new ArrayList<>();
    }

    public void add(T subject) {
        subjects.add(subject);
    }

    public boolean delete(String code) {
        Optional<T> subject = findByCode(code);
        subject.ifPresent(subjects::remove);
        return subject.isPresent();
    }

    public Optional<T> findByCode(String code) {
        return subjects.stream()
                .filter(subject -> subject.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    public Optional<T> searchByName(String name) {
        return subjects.stream()
                .filter(subject -> subject.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst();
    }

    public List<T> filterByCreditsGreaterThan(int credits) {
        return subjects.stream()
                .filter(subject -> subject.getCredits() > credits)
                .collect(Collectors.toList());
    }

    public List<T> getAll() {
        return subjects;
    }
}
