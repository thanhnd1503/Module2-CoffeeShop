import menu.OrderMenu;
import menu.ProductMenu;
import menu.StaffMenu;
import service.CoffeeShopService;
import service.StaffService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ProductMenu productMenu = new ProductMenu();
        OrderMenu orderMenu = new OrderMenu();
        StaffMenu staffMenu = new StaffMenu();
        CoffeeShopService coffeeShopService = CoffeeShopService.getCoffeeShopService();
        int choice;
        do {
            System.out.println("--MENUMANAGEMENT--");
            System.out.println("1.Product");
            System.out.println("2.Order");
            System.out.println("3.Staff");
            System.out.println("4.Profit of Shop");


            System.out.println("CHOOSE YOUR OPTION :");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 -> productMenu.menu();
                case 2 -> orderMenu.menu();
                case 3 -> staffMenu.menu();
                case 4 -> System.out.println("total profit is :" + coffeeShopService.setWalletShop());
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid selection!!! Please choose again!");
            }
        }while (choice != 0);
    }
}