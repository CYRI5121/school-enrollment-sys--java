public class Course {

    private String courseCode;
    private String courseName;
    private int maxCapacity;

    private int currentEnrollment = 0;

    private static int totalEnrolledStudents = 0;

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getMaxCapacity() { return maxCapacity; }

    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public boolean enrollStudent() {
        if (currentEnrollment >= maxCapacity) {
            return false;
        }
        currentEnrollment++;
        return true;
    }

    public static void incrementEnrolled() { totalEnrolledStudents++; }

    public static int getTotalEnrolledStudents() { return totalEnrolledStudents; }

    @Override
    public String toString() {
        return courseCode + " – " + courseName +
               " (" + currentEnrollment + "/" + maxCapacity + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course)) return false;
        Course other = (Course) obj;
        return courseCode.equalsIgnoreCase(other.courseCode);
    }

    @Override
    public int hashCode() {
        return courseCode.toLowerCase().hashCode();
    }
}
