import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    private List<Student> students = new ArrayList<>();

    public void addStudent(String studentId, String studentName, double marks) {
        students.add(new Student(studentId, studentName, marks));
    }

    public void editStudent(String studentId, String newName, double newMarks) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.setStudentName(newName);
                student.setMarks(newMarks);
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public void deleteStudent(String studentId) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentId().equals(studentId)) {
                iterator.remove();
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public void searchStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }


    public void mergeSort(List<Student> studentList, int left, int right) {
        if (left < right) { int middle = (left + right) / 2;

            mergeSort(studentList, left, middle);
            mergeSort(studentList, middle + 1, right);

            merge(studentList, left, middle, right);
        }
    }

    private void merge(List<Student> studentList, int left, int middle, int right) {

        int n1 = middle - left + 1;
        int n2 = right - middle;


        List<Student> leftArray = new ArrayList<>(n1);
        List<Student> rightArray = new ArrayList<>(n2);

        for (int i = 0; i < n1; i++)
            leftArray.add(studentList.get(left + i));
        for (int j = 0; j < n2; j++)
            rightArray.add(studentList.get(middle + 1 + j));

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray.get(i).getMarks() >= rightArray.get(j).getMarks()) {
                studentList.set(k, leftArray.get(i));
                i++;
            } else {
                studentList.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            studentList.set(k, leftArray.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            studentList.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }

    public void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the list to sort.");
        } else {
            mergeSort(students, 0, students.size() - 1);
            System.out.println("Students sorted by marks in descending order:");
            displayAllStudents();
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the list.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement management = new StudentManagement();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Sort Students");
            System.out.println("6. Display All Students");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter student marks: ");
                    double marks = scanner.nextDouble(); management.addStudent(id, name, marks);
                    break;
                case 2:
                    System.out.print("Enter student ID to edit: ");
                    String editId = scanner.nextLine().trim();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine().trim();
                    System.out.print("Enter new marks: ");
                    double newMarks = scanner.nextDouble();
                    management.editStudent(editId, newName, newMarks);
                    break;
                case 3:
                    System.out.print("Enter student ID to delete: ");
                    String deleteId = scanner.nextLine().trim();
                    management.deleteStudent(deleteId);
                    break;
                case 4:
                    System.out.print("Enter student ID to search: ");
                    String searchId = scanner.nextLine().trim();
                    management.searchStudent(searchId);
                    break;
                case 5:
                    management.sortStudents();
                    break;
                case 6:
                    management.displayAllStudents();
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
