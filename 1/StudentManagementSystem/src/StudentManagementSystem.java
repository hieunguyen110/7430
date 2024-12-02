

import java.util.*;

class Student {
    String id;
    String name;
    double marks;

    Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    String getRank() {
        if (marks < 5.0) return "Fail";
        else if (marks < 6.5) return "Medium";
        else if (marks < 7.5) return "Good";
        else if (marks < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Marks: %.2f, Rank: %s", id, name, marks, getRank());
    }
}

public class StudentManagementSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students (Bubble Sort)");
            System.out.println("5. Search Student");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> deleteStudent();
                case 4 -> bubbleSortStudents();
                case 5 -> searchStudent();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void addStudent() {
        String id = getUniqueStudentID();
        String name = getValidName();
        System.out.print("Enter Marks: ");
        double marks = validateMarks();
        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully.");
    }

    private static String getUniqueStudentID() {
        while (true) {
            System.out.print("Enter Student ID: ");
            String id = sc.nextLine();
            if (students.stream().anyMatch(student -> student.id.equals(id))) {
                System.out.println("ID already exists. Please enter a unique ID.");
            } else {
                return id;
            }
        }
    }

    private static String getValidName() {
        while (true) {
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            if (name.matches("[a-zA-Z\\s]+")) {
                return name.trim();
            } else {
                System.out.println("Invalid name. Only letters and spaces are allowed. Please try again.");
            }
        }
    }

    private static void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        String id = sc.nextLine();
        for (Student student : students) {
            if (student.id.equals(id)) {
                student.name = getValidName();
                System.out.print("Enter New Marks: ");
                student.marks = validateMarks();
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = sc.nextLine();
        if (students.removeIf(student -> student.id.equals(id))) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void bubbleSortStudents() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).marks < students.get(j + 1).marks) {
                    // Swap
                    Collections.swap(students, j, j + 1);
                }
            }
        }
        System.out.println("Students sorted using Bubble Sort.");
        students.forEach(System.out::println); // Output sorted students
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        String id = sc.nextLine();
        for (Student student : students) {
            if (student.id.equals(id)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static double validateMarks() {
        while (true) {
            try {
                double marks = Double.parseDouble(sc.nextLine());
                if (marks >= 0 && marks <= 10) return marks;
                else System.out.println("Marks must be between 0 and 10. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter numbers only.");
            }
        }
    }
}