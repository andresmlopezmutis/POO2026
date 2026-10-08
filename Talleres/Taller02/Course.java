import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course {

    private String name;
    private final List<Student> students;

    public Course(String name) {
        if (!setName(name)) {
            throw new IllegalArgumentException("El nombre del curso no puede estar vacio");
        }
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        this.name = name;
        return true;
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }
        return students.add(student);
    }
}
