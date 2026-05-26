package session16.ex3.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageManager<T extends Message> {
    private final ArrayList<T> messages;

    public MessageManager() {
        this.messages = new ArrayList<>();
    }

    public void add(T message) {
        messages.add(message);
    }

    public List<T> getAll() {
        return messages;
    }

    public List<T> filterBySender(String sender) {
        return messages.stream()
                .filter( m -> m.getSender().equalsIgnoreCase(sender))
                .toList();
    }

    public List<T> filterByDate(LocalDate date) {
        return messages.stream()
                .filter(message -> message.getTimestamp().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }
}
