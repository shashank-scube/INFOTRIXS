package Employeecrud;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class EmployeeManagementSystem {
	private static final String DATA_FILE = "employees.txt";
    private static final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        loadEmployeeData();
        showMenu();
    }

    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Search Employee by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    searchEmployee();
                    break;
                case 5:
                    saveEmployeeData();
                    System.out.println("Exiting the Employee Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void addEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Position: ");
        String position = scanner.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        employees.add(new Employee(id, name, position, salary));
        System.out.println("Employee added successfully!");
    }

    private static void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    private static void updateEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.print("Enter new Employee Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new Employee Position: ");
                String position = scanner.nextLine();
                System.out.print("Enter new Employee Salary: ");
                double salary = scanner.nextDouble();

                employee.setName(name);
                employee.setPosition(position);
                employee.setSalary(salary);
                System.out.println("Employee updated successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found with ID: " + id);
        }
    }

    private static void searchEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID to search: ");
        int id = scanner.nextInt();

        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.println("Employee found:\n" + employee);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found with ID: " + id);
        }
    }

    private static void loadEmployeeData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String position = data[2];
                double salary = Double.parseDouble(data[3]);
                employees.add(new Employee(id, name, position, salary));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Employee data file not found. Starting with an empty employee list.");
        } catch (IOException e) {
            System.out.println("Error occurred while loading employee data. Starting with an empty employee list.");
        }
    }

    private static void saveEmployeeData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Employee employee : employees) {
                writer.write(employee.getId() + "," + employee.getName() + "," +
                        employee.getPosition() + "," + employee.getSalary());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving employee data.");
        }
    }
}
