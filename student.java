package com.nikhitha;

import java.io.IOException;
import java.util.Scanner;

public class student {

    public static void main(String[] args) throws IOException {

        Student_details stu_details = new Student_details();
        stu_details.display();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter register number to delete a student data");
        String regno_delete = in.nextLine();
        stu_details.deleteStudent(regno_delete);

        System.out.println("Enter student details to be added:");
        stu_details.add();
        System.out.print("Enter register number for the name to be updated : ");
        String regno_update = in.nextLine();
        System.out.print("New name : ");
        String newName = in.nextLine();
        stu_details.update(regno_update,newName);

        System.out.println("Enter student register number to generate student report : ");
        String regno_report = in.nextLine();
        stu_details.report(regno_report);

    }
}

