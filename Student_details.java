package com.nikhitha;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student_details {
    private List<Student> students = new ArrayList<>();

    void display() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("students.txt"));

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 10) {
                String regno = parts[0].trim();
                String name = parts[1].trim();
                String address = parts[2].trim();
                String branch = parts[3].trim();
                int m1 = Integer.parseInt(parts[4].trim());
                int m2 = Integer.parseInt(parts[5].trim());
                int m3 = Integer.parseInt(parts[6].trim());
                int m4 = Integer.parseInt(parts[7].trim());
                int m5 = Integer.parseInt(parts[8].trim());
                int m6 = Integer.parseInt(parts[9].trim());

                students.add(new Student(regno, name, address, branch, m1, m2, m3, m4, m5, m6));
            }
        }
        printStudentDetails();
    }

    void deleteStudent(String regnoToDelete) throws IOException {
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getRegno().equalsIgnoreCase(regnoToDelete)) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            System.out.println("Student with RegNo " + regnoToDelete + " deleted successfully.");
            System.out.println("Data after deletion is :");
            saveToFile();
            printStudentDetails();
        } else {
            System.out.println("Student with RegNo " + regnoToDelete + " not found.");
        }
    }

    private void saveToFile() throws IOException {
        List<String> lines = new ArrayList<>();
        for (Student student : students) {
            String line = String.format("%s, %s, %s, %s, %d, %d, %d, %d, %d, %d",
                    student.getRegno(), student.getName(), student.getAddress(), student.getBranch(),
                    student.getM1(), student.getM2(), student.getM3(), student.getM4(), student.getM5(), student.getM6());
            lines.add(line);
        }
        Files.write(Paths.get("students.txt"), lines);
    }


    private void printStudentDetails() {
        System.out.println("RegNo\t\tName\t\t\tAddress\t\t\tBranch\t\tTotal marks");
        for (Student student : students) {
            int totals = student.getM1() + student.getM2() + student.getM3() + student.getM4() + student.getM5() + student.getM6();
            System.out.printf("%-10s\t%-13s\t%-12s\t%-12s\t%-10d\n",
                    student.getRegno(), student.getName(), student.getAddress(), student.getBranch(), totals);
        }
    }

    void add() throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.print("Regno: ");
        String regno = in.nextLine();
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.print("Address: ");
        String address = in.nextLine();
        System.out.print("Branch: ");
        String branch = in.nextLine();
        System.out.print("Marks (M1 M2 M3 M4 M5 M6): ");
        int m1 = in.nextInt();
        int m2 = in.nextInt();
        int m3 = in.nextInt();
        int m4 = in.nextInt();
        int m5 = in.nextInt();
        int m6 = in.nextInt();
        Student newStudent = new Student(regno, name, address, branch, m1, m2, m3, m4, m5, m6);
        addStudent(newStudent);
        System.out.println("Updated student details : ");
        printStudentDetails();
    }

    void addStudent(Student student) throws IOException {
        students.add(student);
        saveToFile();
    }

    void update(String regno_update, String newName) throws IOException {
        boolean found = false;
        for (Student student : students) {
            if (student.getRegno().equalsIgnoreCase(regno_update)) {
                student.name = newName;
                saveToFile();
                found = true;
                break; // Exit the loop once the student is found and updated
            }
        }
        if (found) {
            System.out.println("Student with Regno " + regno_update + " updated successfully");
            printStudentDetails();
        } else {
            System.out.println("Student with Regno " + regno_update + " not found");
        }
    }


    void report(String regno_report) {
        boolean found = false;
        for (Student student : students) {
            if (student.getRegno().equalsIgnoreCase(regno_report)) {
                int total = student.getM1() + student.getM2() + student.getM3() + student.getM4() + student.getM5() + student.getM6();
                float GPA = (float) total / 6;

                System.out.println("Report of this student:");
                System.out.println("Reg No : " + student.getRegno());
                System.out.println("Name : " + student.getName());
                System.out.println("Total marks : " + total);
                System.out.println("GPA : " + GPA);

                if (student.getM1() < 35 || student.getM2() < 35 || student.getM3() < 35 || student.getM4() < 35 || student.getM5() < 35 || student.getM6() < 35) {
                    System.out.println("Student has failed");
                } else if (GPA < 50) {
                    System.out.println("Student secured third class");
                } else if (GPA < 70) {
                    System.out.println("Student secured second class");
                } else {
                    System.out.println("Student secured distinction");
                }

                found = true;
                break; // Exit the loop once the student is found and reported
            }
        }

        if (!found) {
            System.out.println("Student with Regno " + regno_report + " not found.");
        }
    }


    class Student {
        String regno;
        String name;
        String address;
        String branch;
        int m1;
        int m2;
        int m3;
        int m4;
        int m5;
        int m6;

        public Student(String regno, String name, String address, String branch, int m1, int m2, int m3, int m4, int m5, int m6) {
            this.regno = regno;
            this.name = name;
            this.address = address;
            this.branch = branch;
            this.m1 = m1;
            this.m2 = m2;
            this.m3 = m3;
            this.m4 = m4;
            this.m5 = m5;
            this.m6 = m6;
        }

        public String getRegno() {
            return regno;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getBranch() {
            return branch;
        }

        public int getM1() {
            return m1;
        }

        public int getM2() {
            return m2;
        }

        public int getM3() {
            return m3;
        }

        public int getM4() {
            return m4;
        }

        public int getM5() {
            return m5;
        }

        public int getM6() {
            return m6;
        }
    }
}

