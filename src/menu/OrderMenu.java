package menu;
import coffeeshop.Order;
import service.OrderService;

import java.io.IOException;
import java.util.Scanner;

public class OrderMenu {
    Scanner scanner;
    OrderService orderService;

    public OrderMenu() {
        orderService = OrderService.getOrderSercive();
        scanner = new Scanner(System.in);
    }


    public void menu() throws IOException {
        int choice;
        do {
            System.out.println("---OrderMenu---");
            System.out.println("1.Add order");
            System.out.println("2.Remove order");
            System.out.println("3.Display oders");
            System.out.println("0.Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 -> addOrder();
                case 2 -> removeById();
                case 3 -> displayOrders();
                case 0 -> System.out.println("Exit");
                default -> System.out.println("Invalid selection!!! Please choose again!");
            }

        }while (choice != 0);
    }

    private void displayOrders() {
        while (orderService.checkOrderList()){
            orderService.display();
            break;
        }
    }
    private void removeById() throws NullPointerException, IOException {
        while (orderService.checkOrderList()){
            System.out.println("Remove oder");
            System.out.println("Enter orderID you wanna delete:");
            String id = scanner.nextLine();
            while (!orderService.checkID(id)){
                System.out.println("ID is not exist, please re-enter another id : ");
                id = scanner.nextLine();
            }
            orderService.calculatorProductRemove(orderService.remove(id));
            System.out.println("Delete successfully!!");
            break;
        }

    }

    private void addOrder() throws IOException {
        System.out.println("Add order");
        System.out.println("Enter number of order you wanna add: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter order " + (i+1));
            System.out.println("Enter OrderID");
            String id = scanner.nextLine();
            while (orderService.checkID(id)){
                System.out.println("ID existed, please re-enter another id : ");
                id = scanner.nextLine();
            }
            System.out.println("Enter product_name: ");
            String  product_name = scanner.nextLine();
            System.out.println("Enter product_quantity: ");
            int order_product_quantyti = scanner.nextInt();
            scanner.nextLine();
            while (!orderService.checkProduct(product_name,order_product_quantyti)){
                System.out.println("Sorry customers. Your order is not available or is out of stock. Please order another dish!!");
                System.out.println("Enter product_name: ");
                product_name = scanner.nextLine();
                System.out.println("Enter product_quantity: ");
                order_product_quantyti = scanner.nextInt();
                scanner.nextLine();
            }
            orderService.calculatorProductAdd(product_name,order_product_quantyti);
            System.out.println("Enter order_date");
            String order_date = scanner.nextLine();
            System.out.println("Enter product_price");
            int product_price = scanner.nextInt();
            scanner.nextLine();

            Order order = new Order(id,product_name,order_date,order_product_quantyti,product_price);
            orderService.add(order);
        }
        orderService.saveFile();
        System.out.println("Add successfully!!");
    }
}
