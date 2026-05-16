import java.util.ArrayList;
import java.util.HashMap;

public class Student {

    private String name;
    private String studentId;
    private ArrayList<Course> enrolledCourses;
    private HashMap<Course, Double> grades;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public String getName() { return name; }
    public String getStudentId() { return studentId; }
    public ArrayList<Course> getEnrolledCourses() { return enrolledCourses; }

    public void setName(String name) { this.name = name; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public boolean enrollInCourse(Course course) {
        if (enrolledCourses.contains(course)) {
            System.out.println(name + " is already enrolled in " + course.getCourseName());
            return false;
        }
        enrolledCourses.add(course);
        return true;
    }

    public void assignGrade(Course course, double grade) {
        if (!enrolledCourses.contains(course)) {
            System.out.println(name + " is not enrolled in " + course.getCourseName());
            return;
        }
        if (grade < 0 || grade > 100) {
            System.out.println("Grade must be between 0 and 100.");
            return;
        }
        grades.put(course, grade);
    }

    public Double getGrade(Course course) {
        return grades.getOrDefault(course, null);
    }

    public HashMap<Course, Double> getAllGrades() { return grades; }

    @Override
    public String toString() {
        return "Student[" + studentId + "] " + name;
    }
}
