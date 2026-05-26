package session16.ex5.models;

public class Student {
    private final String name;
    private final String major;
    private final double score;

    public Student(String name, String major, double score) {
        this.name = name;
        this.major = major;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", score=" + score +
                '}';
    }
}
