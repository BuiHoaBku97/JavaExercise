package session12.ex1.presentation;

import session12.ex1.models.AdminStaff;
import session12.ex1.models.Lecturer;
import session12.ex1.models.Staff;

import java.util.Scanner;

public class EduCareer {
    private static Staff[] allStaffs = new Staff[100];
    private static int totalStaff = 0;
    private static Scanner scanner = new Scanner(System.in);
    //-------------------------------------------------------------------

    static void main() {
        do{
            printMainMenu();
            int option = getMenuOption("Choose your option: ");
            switch (option){
                case 1:
                    addStaff();
                    break;
                case 2:
                    displayAllStaff();
                    break;
                case 3:
                    editStaff();
                    break;
                case 4:
                    deleteStaff();
                    break;
                case 5:
                    System.out.println("Exit App!");
                    return;
                default:
                    System.out.println("Type your option from: 1 - 5");
            }
        }
        while (true);
    }

    static void addStaff(){
        int option = -1;
        do{
            option = getMenuOption("Choose staff type: 0-Lecturer ; 1-AdminStaff.... ");
            if ( option == 0 || option == 1 ){
                break;
            }

            if(option == -1){
                return;
            }

            System.out.println("Type 0 / 1");
        }
        while(true);

        Staff newStaff = inputStaff(option, totalStaff);
        allStaffs[totalStaff++] = newStaff;
    }

    private static Staff inputStaff(int option, int id){
        String name = getNextInput("Enter name: ");
        Double salary = getDoubleInput("Enter base salary: ", true);
        if ( option == 0 ){
            Double teachingHours = getDoubleInput("Enter teaching hours: ", true);
            return new Lecturer(id, name, salary, teachingHours);
        }

        Double bonus = getDoubleInput("Enter bonus: ", true);
        return new AdminStaff(id, name, salary, bonus);
    }

    private static void displayAllStaff(){
        if ( totalStaff == 0 ){
            System.out.println("Staff list empty!");
            return;
        }

        System.out.println("All staffs:");
        for (int i = 0; i < totalStaff; i++){
            var info = allStaffs[i].toString();
            info += "   Total salary: " + allStaffs[i].calculateTotalSalary();
            System.out.println(info);
        }
    }

    private static void editStaff(){
        do{
            int id = getMenuOption("Enter id staff: ");
            if ( id == -1 ){
                return;
            }

            if ( id < 0 || id >= totalStaff || allStaffs[id] == null ){
                System.out.println("ID not exist!");
                continue;
            }

            String message = "Enter edit field: 1-Name  2-Base Salary   3-" + (allStaffs[id] instanceof Lecturer ? "Teaching Hours" : "Bonus") + ":  ";
            do{
                int fieldOption = getMenuOption(message);
                if ( fieldOption > 3 || fieldOption < 1) {
                    System.out.println("Enter: 1 -> 3");
                    continue;
                }

                if (fieldOption == 1){
                    allStaffs[id].setName(getNextInput("Enter new name: "));
                }
                else if (fieldOption==2){
                    allStaffs[id].setBaseSalary(getDoubleInput("Enter new base salary: ", true));
                }
                else {
                    if ( allStaffs[id] instanceof Lecturer lecturer ){
                        lecturer.setTeachingHours(getDoubleInput("Enter new teaching hours", true));
                    }
                    else if ( allStaffs[id] instanceof AdminStaff adminStaff ){
                        adminStaff.setBonus(getDoubleInput("Enter new bonus: ", true));
                    }
                }
                return;
            }
            while (true);

        }
        while (true);
    }

    private static void deleteStaff(){
        do{
            int id = getMenuOption("Enter id staff: ");
            if ( id == -1 ){
                return;
            }

            if ( id < 0 || id >= totalStaff || allStaffs[id] == null ){
                System.out.println("ID not exist!");
                continue;
            }

            for (int i = id; i < totalStaff-1; i ++){
                allStaffs[i] = allStaffs[i+1];
                allStaffs[i].setId(i);
            }

            allStaffs[totalStaff--] = null;
            return;
        }
        while (true);
    }

    static void printMainMenu(){
        System.out.println("===========HR Management===========");
        System.out.println("1. Add staff");
        System.out.println("2. Display all staff");
        System.out.println("3. Update");
        System.out.println("4. Delete staff");
        System.out.println("5. Exit");
    }

    private static Double getDoubleInput(String message, boolean onlyPositiveValue){
        do{
            try{
                var value = Double.parseDouble(getNextInput(message));
                if ( onlyPositiveValue && value < 0 ){
                    System.out.println("Require positive number");
                    continue;
                }
                return value;
            }
            catch (NumberFormatException e){
                System.out.println("Type valid format number");
            }
        }
        while (true);
    }

    private static int getMenuOption(String message){
        do{
            try{
                var option = Integer.parseInt(getNextInput(message));
                return option;
            }
            catch (NumberFormatException x ){
                System.out.println("Type valid format number");
            }
        }
        while (true);
    }

    private static String getNextInput(String message){
        if (!message.isEmpty()){
            System.out.print(message);
        }

        return scanner.nextLine().trim();
    }
}
