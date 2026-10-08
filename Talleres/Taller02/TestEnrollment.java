public class TestEnrollment {

    public static void main(String[] args) {
        Course course = new Course("Programacion Orientada a Objetos");
        Student firstStudent = new Student("Ana Perez", "2026001");
        Student secondStudent = new Student("Luis Gomez", "2026002");
        Student thirdStudent = new Student("Maria Rodriguez", "2026003");

        course.addStudent(firstStudent);
        course.addStudent(secondStudent);
        course.addStudent(thirdStudent);

        System.out.println("Curso: " + course.getName());
        System.out.println("Estudiantes inscritos:");
        for (Student student : course.getStudents()) {
            System.out.println("- " + student.getName() + " (" + student.getCode() + ")");
        }
    }
}
