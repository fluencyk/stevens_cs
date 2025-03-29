/**
 * School: Stevens Institute of Technology
 * Course: CS-501_A
 * Instructor: Prof. Aughdon Breslin
 * ----- ----- ----- ----- ----- *
 * Homework: Assignment 03
 * ----- ----- ----- ----- ----- *
 * Student Name:  Yujun Kong
 * CWID: 1046 6820
 */

package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Assignment3 {

    public static class Person {
        private final int id;
        private final String name;
        private final LocalDate birthdate;

        public Person(int id, String name, LocalDate birthdate) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            if (birthdate == null) {
                throw new IllegalArgumentException("Birthdate cannot be null");
            }
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
            if (course == null) {
                throw new IllegalArgumentException("Course cannot be null");
            }
            if (!enrolledCourses.contains(course)) {
                enrolledCourses.add(course);
                course.addStudent(this);
            }
        }

        public void drop(Course course) {
            if (course == null) {
                throw new IllegalArgumentException("Course cannot be null");
            }
            enrolledCourses.remove(course);
        }

        public List<Course> getEnrolledCourses() {
            return new ArrayList<>(enrolledCourses);
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
            if (course == null) {
                throw new IllegalArgumentException("Course cannot be null");
            }
            taughtCourses.add(course);
        }

        public void removeCourse(Course course) {
            if (course == null) {
                throw new IllegalArgumentException("Course cannot be null");
            }
            taughtCourses.remove(course);
        }

        public List<Course> getTaughtCourses() {
            return new ArrayList<>(taughtCourses);
        }

        @Override
        public String toString() {
            return "Professor[id=" + getId() + ", name=" + getName() + ", birthdate=" + getBirthdate() + ", taughtCourses=" + taughtCourses.size() + "]";
        }
    }

    public static class Course {
        private final String curriculum;
        private final String assignedRoom;
        private final List<Student> students;
        private Professor professor;

        public Course(String curriculum, String assignedRoom) {
            this.curriculum = curriculum;
            this.assignedRoom = assignedRoom;
            this.students = new ArrayList<>();
            this.professor = null;
        }

        public void addStudent(Student student) {
            if (student == null) {
                throw new IllegalArgumentException("Student cannot be null");
            }
            if (!students.contains(student)) {
                students.add(student);
                student.enroll(this);
            }
        }

        public void removeStudent(Student student) {
            if (student == null) {
                throw new IllegalArgumentException("Student cannot be null");
            }
            students.remove(student);
        }

        public void setProfessor(Professor professor) {
            if (this.professor != null) {
                this.professor.removeCourse(this);
            }
            this.professor = professor;
            if (professor != null) {
                professor.addCourse(this);
            }
        }

        public void removeProfessor() {
            if (professor != null) {
                professor.removeCourse(this);
                professor = null;
            }
        }

        public String getAssignedRoom() {
            return assignedRoom;
        }

        public List<Student> getStudents() {
            return new ArrayList<>(students);
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
            if (radius == null || unit == null) {
                throw new IllegalArgumentException("Radius and unit cannot be null");
            }
            if (radius.doubleValue() <= 0) {
                throw new IllegalArgumentException("Radius must be positive");
            }
            this.radius = radius;
        }

        public T getRadius() {
            return radius;
        }

        public void setRadius(T radius) {
            if (radius == null) {
                throw new IllegalArgumentException("Radius cannot be null");
            }
            if (radius.doubleValue() <= 0) {
                throw new IllegalArgumentException("Radius must be positive");
            }
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
        private T side1;
        private T side2;
        private T side3;

        public Triangle(T side1, T side2, T side3, T unit) {
            super(unit);
            if (side1 == null || side2 == null || side3 == null || unit == null) {
                throw new IllegalArgumentException("Sides and unit cannot be null");
            }
            if (side1.doubleValue() <= 0 || side2.doubleValue() <= 0 || side3.doubleValue() <= 0) {
                throw new IllegalArgumentException("Sides must be positive");
            }
            if (side1.doubleValue() + side2.doubleValue() <= side3.doubleValue() ||
                    side2.doubleValue() + side3.doubleValue() <= side1.doubleValue() ||
                    side1.doubleValue() + side3.doubleValue() <= side2.doubleValue()) {
                throw new IllegalArgumentException("Sides must satisfy triangle inequality");
            }
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }

        public T getSide1() {
            return side1;
        }

        public void setSide1(T side1) {
            this.side1 = side1;
        }

        public T getSide2() {
            return side2;
        }

        public void setSide2(T side2) {
            this.side2 = side2;
        }

        public T getSide3() {
            return side3;
        }

        public void setSide3(T side3) {
            this.side3 = side3;
        }

        @Override
        public double calculateArea() {
            double s = calcPerimeter() / 2;
            return Math.sqrt(s * (s - side1.doubleValue()) * (s - side2.doubleValue()) * (s - side3.doubleValue()));
        }

        @Override
        public double calcPerimeter() {
            return side1.doubleValue() + side2.doubleValue() + side3.doubleValue();
        }

        @Override
        public String toString() {
            return "Triangle[side1=" + side1 + ", side2=" + side2 + ", side3=" + side3 + ", unit=" + unit + "]";
        }
    }

    public static class Rectangle<T extends Number> extends Shape<T> {
        private T length;
        private T width;

        public Rectangle(T length, T width, T unit) {
            super(unit);
            if (length == null || width == null || unit == null) {
                throw new IllegalArgumentException("Dimensions cannot be null");
            }
            if (length.doubleValue() <= 0 || width.doubleValue() <= 0) {
                throw new IllegalArgumentException("Dimensions must be positive");
            }
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
        private final String name;
        private final int id;
        private final List<String> certifications;

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
            return new ArrayList<>(certifications);
        }

        @Override
        public double calculateSalary(int performanceScore) {
            return BASE_PAY + (BASE_PAY * (performanceScore / 100.0));
        }

        @Override
        public void addCertification(String certification) {
            if (certification == null) {
                throw new IllegalArgumentException("Certification cannot be null");
            }
            certifications.add(certification);
        }

        @Override
        public int generatePerformanceScore() {
            return Math.min(50 + certifications.size() * 5, 100);
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
        Professor professor1 = new Professor(3, "Dr. Charlie", LocalDate.of(1980, 3, 3));
        Course mathCourse = new Course("Mathematics", "Room 101");
        Course scienceCourse = new Course("Science", "Room 102");

        student1.enroll(mathCourse);
        student1.enroll(scienceCourse);
        student2.enroll(mathCourse);
        professor1.addCourse(mathCourse);
        professor1.addCourse(scienceCourse);
        mathCourse.setProfessor(professor1);

        System.out.println("Initial State:");
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(professor1);
        System.out.println(mathCourse);
        System.out.println(scienceCourse);

        student1.drop(scienceCourse);
        mathCourse.removeStudent(student2);

        System.out.println("\nAfter Modifications:");
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(mathCourse);

        Professor professor2 = new Professor(4, "Dr. Dana", LocalDate.of(1975, 4, 4));
        mathCourse.setProfessor(professor2);
        scienceCourse.removeProfessor();

        System.out.println("\nAfter Professor Changes:");
        System.out.println(professor1);
        System.out.println(professor2);
        System.out.println(mathCourse);
        System.out.println(scienceCourse);

        System.out.println();

        System.out.println("--- Problem 2: Shape Hierarchy ---");

        Circle<Double> circle = new Circle<>(5.0, 1.0);
        Triangle<Integer> triangle = new Triangle<>(3, 4, 5, 1);
        Rectangle<Double> rectangle = new Rectangle<>(6.0, 8.0, 1.0);

        System.out.println("Initial Shapes:");
        System.out.println(circle);
        System.out.println("  Area: " + circle.calculateArea());
        System.out.println("  Perimeter: " + circle.calcPerimeter());

        System.out.println(triangle);
        System.out.println("  Area: " + triangle.calculateArea());
        System.out.println("  Perimeter: " + triangle.calcPerimeter());

        System.out.println(rectangle);
        System.out.println("  Area: " + rectangle.calculateArea());
        System.out.println("  Perimeter: " + rectangle.calcPerimeter());

        circle.setRadius(10.0);
        circle.setUnit(2.0);
        triangle.setSide1(5);
        triangle.setSide2(12);
        triangle.setSide3(13);
        triangle.setUnit(2);
        rectangle.setLength(10.0);
        rectangle.setWidth(5.0);
        rectangle.setUnit(3.0);

        System.out.println("\nAfter Modifications:");
        System.out.println(circle);
        System.out.println("  Area: " + circle.calculateArea());
        System.out.println("  Perimeter: " + circle.calcPerimeter());

        System.out.println(triangle);
        System.out.println("  Area: " + triangle.calculateArea());
        System.out.println("  Perimeter: " + triangle.calcPerimeter());

        System.out.println(rectangle);
        System.out.println("  Area: " + rectangle.calculateArea());
        System.out.println("  Perimeter: " + rectangle.calcPerimeter());

        System.out.println();

        System.out.println("--- Problem 3: Employee Management System ---");

        Employee emp1 = new Employee("David", 101);
        emp1.addCertification("Java");
        emp1.addCertification("Python");

        Employee emp2 = new Employee("Eve", 102);
        emp2.addCertification("C++");
        emp2.addCertification("JavaScript");
        emp2.addCertification("SQL");

        System.out.println("Initial Employees:");
        System.out.println(emp1);
        System.out.println("  Performance Score: " + emp1.generatePerformanceScore());
        System.out.println("  Salary: " + emp1.calculateSalary(emp1.generatePerformanceScore()));

        System.out.println(emp2);
        System.out.println("  Performance Score: " + emp2.generatePerformanceScore());
        System.out.println("  Salary: " + emp2.calculateSalary(emp2.generatePerformanceScore()));

        emp1.addCertification("AWS");
        emp2.addCertification("Docker");
        emp2.addCertification("Kubernetes");

        System.out.println("\nAfter Adding Certifications:");
        System.out.println(emp1);
        System.out.println("  Performance Score: " + emp1.generatePerformanceScore());
        System.out.println("  Salary: " + emp1.calculateSalary(emp1.generatePerformanceScore()));

        System.out.println(emp2);
        System.out.println("  Performance Score: " + emp2.generatePerformanceScore());
        System.out.println("  Salary: " + emp2.calculateSalary(emp2.generatePerformanceScore()));
    }
}
