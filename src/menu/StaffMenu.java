package menu;

import interfaces.Staff;
import factory.StaffFactory;
import service.StaffService;

import java.io.IOException;
import java.util.Scanner;

public class StaffMenu {
   Scanner scanner ;
   StaffService staffService;

    public StaffMenu() {
        scanner = new Scanner(System.in);
        staffService = StaffService.getStaffService();
    }

    public void menu() throws IOException {
       int choice;
       do{
           System.out.println("----StaffMenu----");
           System.out.println("1.Add staff");
           System.out.println("2.Remove staff");
           System.out.println("3.Fix staff");
           System.out.println("4.Display stafflist");
           System.out.println("0.Exit");
           choice = scanner.nextInt();
           scanner.nextLine();
           switch (choice){
               case 1 -> addStaff();
               case 2 -> removeStaff();
               case 3 -> fixById();
               case 4 -> displayStaffList();
               case 0 -> System.out.println("Exit");
               default -> System.out.println("Invalid selection!!! Please choose again!");
           }
       }while(choice!=0);
   }

    private void displayStaffList() {
        while (staffService.checkStaffList()){
            staffService.display();
            break;
        }
    }

    private void fixById() throws IOException {
        while (staffService.checkStaffList()){
            System.out.println("Fix staff by ID");
            System.out.println("Enter staffID you want to edit:");
            String id = scanner.nextLine();
            while (!staffService.checkId(id)){
                System.out.println("ID not exists, please re-enter another id : ");
                id = scanner.nextLine();
            }
            System.out.println("Enter new name : ");
            String name = scanner.nextLine();
            System.out.println("Enter type of staff you want to edit (not new type): ");
            String type = scanner.nextLine();
            System.out.println("Enter new numberShifts or enter 0 if not a part time staff");
            int numberShifts = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new numberOfWorkingDays or 0 if not a full time staff");
            int numberOfWorkingDays = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new wallet : ");
            int wallet = scanner.nextInt();
            scanner.nextLine();
            if (type.equals("staffFullTime")){
                staffService.fixStaffFullTime(id,name, numberOfWorkingDays,wallet);
            } else if (type.equals("staffPartTime")) {
                staffService.fixStaffPartTime(id,name,numberShifts,wallet);
            }
            staffService.saveToFile();
            System.out.println("Fix successfully!!");
        }
    }

    private void removeStaff() throws IOException {
       while (staffService.checkStaffList()){
           System.out.println("Remove");
           System.out.println("Enter staffID you want to delete : ");
           String id = scanner.nextLine();
           while (!staffService.checkId(id)){
               System.out.println("ID not exists, please re-enter another id : ");
               id = scanner.nextLine();
           }
           staffService.remove(id);
           staffService.saveToFile();
           System.out.println("Deleted successfully!!");
           break;
       }

    }

    private void addStaff() throws IOException {
        System.out.println("Add staff:");
        System.out.println("Enter number staff you wanna add: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter staff " + (i+1));
            System.out.println("Enter Id : ");
            String id = scanner.nextLine();
            while (staffService.checkId(id)){
                System.out.println("ID already exists, please re-enter another id : ");
                id = scanner.nextLine();
            }
            System.out.println("Enter name : ");
            String name = scanner.nextLine();
            System.out.println("Enter wallet: ");
            int wallet = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter type: ");
            String type = scanner.nextLine();
            System.out.println("Enter numberOfShifts or 0 if not a part time staff: ");
            int numberOfShifts = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter numberOfWorkingDays or 0 if not a full time staff: ");
            int numberOfWorkingDays = scanner.nextInt();
            scanner.nextLine();

            Staff staff = StaffFactory.getStaff(id,name,numberOfShifts,type,numberOfWorkingDays,wallet);
            staffService.add(staff);
            staffService.calcSalary();
        }
        staffService.saveToFile();
        System.out.println("Add successfully!!");
    }


}
