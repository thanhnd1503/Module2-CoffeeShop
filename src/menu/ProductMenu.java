package menu;

import coffeeshop.Product;
import service.ProductService;

import java.io.IOException;
import java.util.Scanner;

public class ProductMenu {
    ProductService productService ;
    Scanner scanner ;
    public ProductMenu(){
        productService = ProductService.getProductService();
        scanner = new Scanner(System.in);
    }
    public void menu() throws IOException {
        int choice;
        do {
            System.out.println("---Product MENU---");
            System.out.println("1.Add product");
            System.out.println("2.Remove product ");
            System.out.println("3.Fix product");
            System.out.println("4.Display productlist");
            System.out.println("0.Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 -> addProduct();
                case 2 -> removeById();
                case 3 -> fixById();
                case 4 -> displayProduct();
                case 0 -> System.out.println("EXIT");
                default -> System.out.println("Invalid selection!!! Please choose again!");
            }
        }while (choice != 0);
    }

    private void displayProduct() {
        while (productService.checkProductList()){
            productService.display() ;
            break;
        }

    }

    private void fixById() throws IOException {
        while (productService.checkProductList()){
            System.out.println("Fix product by ID");
            System.out.println("Enter productID you wanna fix");
            String id = scanner.nextLine();
            while(!productService.checkId(id)){
                System.out.println("ID is not exist, please re-enter another id : ");
                id = scanner.nextLine();
            }
            System.out.println("Enter new name: ");
            String name = scanner.nextLine();
            System.out.println("Enter new price: ");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();


            productService.fixProduct(id,name,price,quantity);
            System.out.println("Fix successfully!!! ");
            break;
        }


    }

    private void removeById() throws IOException {
        while (productService.checkProductList()){
            System.out.println("Remove product");
            System.out.println("Enter productID you want to delete : ");
            String id = scanner.nextLine();
            while (!productService.checkId(id)){
                System.out.println("ID is not exist, please re-enter another id : ");
                id = scanner.nextLine();
            }
            productService.remove(id);
            System.out.println("Remove successfully!!");
            break;
        }
    }

    private void addProduct() throws IOException {
        System.out.println("Add Product");
        System.out.println("Enter number product you wanna add :");
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter product " + (i+1) );
            System.out.println("Enter id : ");
            String id = scanner.nextLine();
            while (productService.checkId(id)){
                System.out.println("ID existed, please re-enter another id : ");
                id = scanner.nextLine();
            }
            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            System.out.println("Enter price: ");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();


            Product product = new Product(id,name,price,quantity);
            productService.add(product);
        }
        productService.saveFile();

    }
}
