import java.util.*;

public class ePortfolio {
    public static void main(String[] args) {

        int choice = 0;
        Scanner sc = new Scanner(System.in);

        do { 
                System.out.println("Menu:");
                System.out.println("(1) Buy");
                System.out.println("(2) Sell");
                System.out.println("(3) Update");
                System.out.println("(4) Get Gain");
                System.out.println("(5) Search");
                System.out.println("Enter Responce:");
                choice = sc.nextInt();


                switch (choice) {
                    case 1:
                        System.out.println("Buy");
                        break;

                     case 2:
                        System.out.println("Sell");
                        break;

                     case 3:
                        System.out.println("Update");
                        break;   

                      case 4:
                        System.out.println("Get Gain");
                        break;

                      case 5:
                        System.out.println("Search");
                        break;

                    default:
                        System.out.println("You have entered wrong number!");
                        break;
                }


        } while (choice != 6);
                sc.close();


        
    }
}