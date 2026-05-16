import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("  Course Enrollment & Grade Management      ");
        System.out.println("============================================");

        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": addCourse(); break;
                case "2": addStudent(); break;
                case "3": enrollStudent(); break;
                case "4": assignGrade(); break;
                case "5": calculateOverall(); break;
                case "6": listCourses(); break;
                case "7": listStudents(); break;
                case "8": updateCourse(); break;
                case "9": updateStudent(); break;
                case "10":
                    System.out.println("Total students enrolled: "
                            + Course.getTotalEnrolledStudents());
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add course");
        System.out.println("2. Add student");
        System.out.println("3. Enroll student");
        System.out.println("4. Assign grade");
        System.out.println("5. Calculate overall grade");
        System.out.println("6. List courses");
        System.out.println("7. List students");
        System.out.println("8. Update course");
        System.out.println("9. Update student");
        System.out.println("10. Total enrolled count");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private static void addCourse() {
        System.out.print("Code: ");
        String code = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Capacity: ");

        try {
            int cap = Integer.parseInt(scanner.nextLine());
            CourseManagement.addCourse(code, name, cap);
        } catch (Exception e) {
            System.out.println("Invalid number.");
        }
    }

    private static void addStudent() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();

        students.add(new Student(name, id));
        System.out.println("Student added.");
    }

    private static void enrollStudent() {
        Student s = pickStudent();
        Course c = pickCourse();
        if (s != null && c != null)
            CourseManagement.enrollStudent(s, c);
    }

    private static void assignGrade() {
        Student s = pickStudent();
        Course c = pickCourse();

        if (s == null || c == null) return;

        System.out.print("Grade: ");
        try {
            double g = Double.parseDouble(scanner.nextLine());
            CourseManagement.assignGrade(s, c, g);
        } catch (Exception e) {
            System.out.println("Invalid grade.");
        }
    }

    private static void calculateOverall() {
        Student s = pickStudent();
        if (s != null)
            CourseManagement.calculateOverallGrade(s);
    }

    private static void listCourses() {
        ArrayList<Course> courses = CourseManagement.getCourses();
        for (int i = 0; i < courses.size(); i++)
            System.out.println((i + 1) + ". " + courses.get(i));
    }

    private static void listStudents() {
        for (int i = 0; i < students.size(); i++)
            System.out.println((i + 1) + ". " + students.get(i));
    }

    private static void updateCourse() {
        Course c = pickCourse();
        if (c == null) return;

        System.out.print("New name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) c.setCourseName(name);

        System.out.print("New capacity: ");
        try {
            String cap = scanner.nextLine();
            if (!cap.isEmpty())
                c.setMaxCapacity(Integer.parseInt(cap));
        } catch (Exception ignored) {}

        System.out.println("Updated.");
    }

    private static void updateStudent() {
        Student s = pickStudent();
        if (s == null) return;

        System.out.print("New name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) s.setName(name);

        System.out.print("New ID: ");
        String id = scanner.nextLine();
        if (!id.isEmpty()) s.setStudentId(id);

        System.out.println("Updated.");
    }

    private static Student pickStudent() {
        listStudents();
        if (students.isEmpty()) return null;

        System.out.print("Choose student: ");
        try {
            return students.get(Integer.parseInt(scanner.nextLine()) - 1);
        } catch (Exception e) {
            return null;
        }
    }

    private static Course pickCourse() {
        listCourses();
        ArrayList<Course> courses = CourseManagement.getCourses();
        if (courses.isEmpty()) return null;

        System.out.print("Choose course: ");
        try {
            return courses.get(Integer.parseInt(scanner.nextLine()) - 1);
        } catch (Exception e) {
            return null;
        }
    }
}
