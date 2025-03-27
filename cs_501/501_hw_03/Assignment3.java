import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Assignment3 {

    public static class Person {
        private int id;
        public String name;
        protected LocalDate birthdate;

        public Person(int id, String name, LocalDate birthdate) {
            this.id = id;
            this.name = name;
            this.birthdate = birthdate;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public LocalDate getBirthdate() {
            return birthdate;
        }

        @Override
        public String toString() {
            return "Person[id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
        }
    }

    public static class Student extends Person {
        private final List<Course> enrolledCourses;

        public Student(int id, String name, LocalDate birthdate) {
            super(id, name, birthdate);
            this.enrolledCourses = new ArrayList<>();
        }

        public void enroll(Course course) {
            enrolledCourses.add(course);
        }

        public void drop(Course course) {
            enrolledCourses.remove(course);
        }

        public List<Course> getEnrolledCourses() {
            return enrolledCourses;
        }

        @Override
        public String toString() {
            return "Student[id=" + getId() + ", name=" + getName() + ", birthdate=" + getBirthdate() + ", enrolledCourses=" + enrolledCourses.size() + "]";
        }
    }

    public static class Professor extends Person {
        private final List<Course> taughtCourses;

        public Professor(int id, String name, LocalDate birthdate) {
            super(id, name, birthdate);
            this.taughtCourses = new ArrayList<>();
        }

        public void addCourse(Course course) {
            taughtCourses.add(course);
        }

        public void removeCourse(Course course) {
            taughtCourses.remove(course);
        }

        public List<Course> getTaughtCourses() {
            return taughtCourses;
        }

        @Override
        public String toString() {
            return "Professor[id=" + getId() + ", name=" + getName() + ", birthdate=" + getBirthdate() + ", taughtCourses=" + taughtCourses.size() + "]";
        }
    }

    public static class Course {
        private String curriculum;
        private String assignedRoom;
        private List<Student> students;
        private Professor professor;

        public Course(String curriculum, String assignedRoom) {
            this.curriculum = curriculum;
            this.assignedRoom = assignedRoom;
            this.students = new ArrayList<>();
            this.professor = null;
        }

        public void addStudent(Student student) {
            students.add(student);
        }

        public void removeStudent(Student student) {
            students.remove(student);
        }

        public void setProfessor(Professor professor) {
            this.professor = professor;
        }

        public void removeProfessor() {
            this.professor = null;
        }

        public String getCurriculum() {
            return curriculum;
        }

        public String getAssignedRoom() {
            return assignedRoom;
        }

        public List<Student> getStudents() {
            return students;
        }

        public Professor getProfessor() {
            return professor;
        }

        @Override
        public String toString() {
            return "Course[curriculum=" + curriculum + ", assignedRoom=" + assignedRoom + ", students=" + students.size() + ", professor=" + (professor != null ? professor.getName() : "null") + "]";
        }
    }

    public interface AreaCalculable<T extends Number> {
        double calculateArea();
    }

    public abstract static class Shape<T extends Number> implements AreaCalculable<T> {
        protected T unit;

        public Shape(T unit) {
            this.unit = unit;
        }

        public T getUnit() {
            return unit;
        }

        public void setUnit(T unit) {
            this.unit = unit;
        }

        public abstract double calcPerimeter();
    }

    public static class Circle<T extends Number> extends Shape<T> {
        private T radius;

        public Circle(T radius, T unit) {
            super(unit);
            this.radius = radius;
        }

        public T getRadius() {
            return radius;
        }

        public void setRadius(T radius) {
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius.doubleValue() * radius.doubleValue();
        }

        @Override
        public double calcPerimeter() {
            return 2 * Math.PI * radius.doubleValue();
        }

        @Override
        public String toString() {
            return "Circle[radius=" + radius + ", unit=" + unit + "]";
        }
    }

    public static class Triangle<T extends Number> extends Shape<T> {
        private T base;
        private T height;
        private T side;

        public Triangle(T base, T height, T side, T unit) {
            super(unit);
            this.base = base;
            this.height = height;
            this.side = side;
        }

        public T getBase() {
            return base;
        }

        public void setBase(T base) {
            this.base = base;
        }

        public T getHeight() {
            return height;
        }

        public void setHeight(T height) {
            this.height = height;
        }

        public T getSide() {
            return side;
        }

        public void setSide(T side) {
            this.side = side;
        }

        @Override
        public double calculateArea() {
            return 0.5 * base.doubleValue() * height.doubleValue();
        }

        @Override
        public double calcPerimeter() {
            return base.doubleValue() + height.doubleValue() + side.doubleValue();
        }

        @Override
        public String toString() {
            return "Triangle[base=" + base + ", height=" + height + ", side=" + side + ", unit=" + unit + "]";
        }
    }

    public static class Rectangle<T extends Number> extends Shape<T> {
        private T length;
        private T width;

        public Rectangle(T length, T width, T unit) {
            super(unit);
            this.length = length;
            this.width = width;
        }

        public T getLength() {
            return length;
        }

        public void setLength(T length) {
            this.length = length;
        }

        public T getWidth() {
            return width;
        }

        public void setWidth(T width) {
            this.width = width;
        }

        @Override
        public double calculateArea() {
            return length.doubleValue() * width.doubleValue();
        }

        @Override
        public double calcPerimeter() {
            return 2 * (length.doubleValue() + width.doubleValue());
        }

        @Override
        public String toString() {
            return "Rectangle[length=" + length + ", width=" + width + ", unit=" + unit + "]";
        }
    }

    public interface Payable {
        double BASE_PAY = 50000;
        double calculateSalary(int performanceScore);
    }

    public interface Trainable {
        void addCertification(String certification);
    }

    public interface Reportable {
        int generatePerformanceScore();
    }

    public static class Employee implements Payable, Trainable, Reportable {
        private String name;
        private int id;
        private List<String> certifications;

        public Employee(String name, int id) {
            this.name = name;
            this.id = id;
            this.certifications = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public List<String> getCertifications() {
            return certifications;
        }

        @Override
        public double calculateSalary(int performanceScore) {
            return BASE_PAY + (BASE_PAY * (performanceScore / 100.0));
        }

        @Override
        public void addCertification(String certification) {
            certifications.add(certification);
        }

        @Override
        public int generatePerformanceScore() {
            return Math.min(certifications.size() * 10, 100);

        }

        @Override
        public String toString() {
            return "Employee[name=" + name + ", id=" + id + ", certifications=" + certifications + "]";
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1: School Hierarchy ---");
        Student student1 = new Student(1, "Alice", LocalDate.of(2000, 1, 1));
        Student student2 = new Student(2, "Bob", LocalDate.of(2001, 2, 2));
        Professor professor1 = new Professor(3, "Charlie", LocalDate.of(1980, 3, 3));
        Course course1 = new Course("Math", "Room 101");
        Course course2 = new Course("Science", "Room 102");

        student1.enroll(course1);
        student1.enroll(course2);
        student2.enroll(course1);
        professor1.addCourse(course1);
        professor1.addCourse(course2);
        course1.addStudent(student1);
        course1.addStudent(student2);
        course1.setProfessor(professor1);

        System.out.println(student1);
        System.out.println(student2);
        System.out.println(professor1);
        System.out.println(course1);
        System.out.println(course2);

        System.out.println();

        System.out.println("--- Problem 2: Shape Hierarchy ---");
        Circle<Double> circle = new Circle<>(5.0, 1.0);
        Triangle<Integer> triangle = new Triangle<>(3, 4, 5, 1);
        Rectangle<Double> rectangle = new Rectangle<>(6.0, 8.0, 1.0);

        System.out.println(circle);
        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Circle Perimeter: " + circle.calcPerimeter());

        System.out.println(triangle);
        System.out.println("Triangle Area: " + triangle.calculateArea());
        System.out.println("Triangle Perimeter: " + triangle.calcPerimeter());

        System.out.println(rectangle);
        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        System.out.println("Rectangle Perimeter: " + rectangle.calcPerimeter());

        System.out.println();

        System.out.println("--- Problem 3: Employee Management System ---");
        Employee employee1 = new Employee("David", 101);
        employee1.addCertification("Java");
        employee1.addCertification("Python");

        System.out.println(employee1);
        System.out.println("Employee Salary: " + employee1.calculateSalary(employee1.generatePerformanceScore()));
        System.out.println("Employee Performance Score: " + employee1.generatePerformanceScore());

        Employee employee2 = new Employee("Eve", 102);
        employee2.addCertification("C++");
        employee2.addCertification("Javascript");
        employee2.addCertification("SQL");
        employee2.addCertification("Docker");
        employee2.addCertification("Kubernetes");
        employee2.addCertification("AWS");
        employee2.addCertification("GCP");
        employee2.addCertification("Azure");
        employee2.addCertification("Terraform");
        employee2.addCertification("Jenkins");
        employee2.addCertification("Git");

        System.out.println(employee2);
        System.out.println("Employee Salary: " + employee2.calculateSalary(employee2.generatePerformanceScore()));
        System.out.println("Employee Performance Score: " + employee2.generatePerformanceScore());
    }
}