import java.util.ArrayList;
import java.util.HashMap;

public class CourseManagement {

    private static ArrayList<Course> courses = new ArrayList<>();
    private static HashMap<Student, HashMap<Course, Double>> overallGrades = new HashMap<>();

    public static Course addCourse(String code, String name, int capacity) {
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(code)) {
                System.out.println("Course with code " + code + " already exists.");
                return null;
            }
        }

        Course newCourse = new Course(code, name, capacity);
        courses.add(newCourse);
        System.out.println("Course added: " + newCourse);
        return newCourse;
    }

    public static void enrollStudent(Student student, Course course) {

        if (!course.enrollStudent()) {
            System.out.println("Sorry, " + course.getCourseName() + " is full.");
            return;
        }

        boolean enrolled = student.enrollInCourse(course);

        if (enrolled) {
            Course.incrementEnrolled();
            overallGrades.putIfAbsent(student, new HashMap<>());
            System.out.println(student.getName() + " enrolled in " + course.getCourseName());
        }
    }

    public static void assignGrade(Student student, Course course, double grade) {

        Double before = student.getGrade(course);
        student.assignGrade(course, grade);
        Double after = student.getGrade(course);

        if (after != null && !after.equals(before)) {
            overallGrades.putIfAbsent(student, new HashMap<>());
            overallGrades.get(student).put(course, grade);

            System.out.println("Grade " + grade + " assigned to "
                    + student.getName() + " for " + course.getCourseName());
        }
    }

    public static void calculateOverallGrade(Student student) {

        HashMap<Course, Double> grades = student.getAllGrades();

        if (grades.isEmpty()) {
            System.out.println(student.getName() + " has no grades yet.");
            return;
        }

        double total = 0;
        int count = 0;

        for (double g : grades.values()) {
            total += g;
            count++;
        }

        double avg = total / count;

        System.out.printf("Overall grade for %s: %.2f%% (%s)%n",
                student.getName(), avg, letterGrade(avg));
    }

    private static String letterGrade(double score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }
}
