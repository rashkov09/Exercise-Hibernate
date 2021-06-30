import exercises.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Exercise number:");
        int exerciseNumber = Integer.parseInt(sc.nextLine());
        Exercise exercise ;

        switch (exerciseNumber){
            case 2:
                exercise = new ChangeCasing();
                break;
            case 3:
                exercise = new ContainsEmployee();
                break;
            case 4:
                exercise = new EmployeesWithSalaryOver50000();
                break;
            case 5:
                exercise = new EmployeesFromDepartment();
                break;
            case 6:
                exercise = new AddingNewAddressAndUpdateEmployee();
                break;
            case 7:
                exercise = new AddressesWithEmployeeCount();
                break;
            case 8:
                exercise = new GetEmployeeWithProject();
                break;
            case 9:
                exercise = new FindLatest10Projects();
                break;
            case 10:
                exercise = new IncreaseSalaries();
                break;

            default:
                System.out.println("Wrong exercise number. Please try again.");
               return;
        }
            exercise.run();
    }
}
