package service;
import coffeeshop.Order;
import coffeeshop.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderService {
    private static List<Order> orders;
    private static OrderService orderSercive = new OrderService();
    Set<Product> products ;
    ProductService productService ;

    private OrderService()  {
        products = ProductService.getProductSet();
        productService = ProductService.getProductService();
        orderSercive = OrderService.getOrderSercive();
        orders = new ArrayList<>();
        readFile();
    }
    public static List<Order> getOrders() {
        return orders;
    }
    public static OrderService getOrderSercive()  {
        return orderSercive;
    }

    public void add(Order order){
        orders.add(order);
    }

    public boolean checkProduct(String name, int quantity){
        for (Product product: products
             ) {
            if (product.getName().equals(name) && product.getQuantity() >= quantity){
                return true;
            }
        }
        return false;
    }

    public boolean checkID(String id) {
        for (Order order: orders) {
            if (order.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void calculatorProductAdd(String product_name, int order_product_quantyti) throws IOException {
        for (Product product: products
             ) {
            if (product.getName().equals(product_name)){
                int setQuantity = product.getQuantity() - order_product_quantyti;
                product.setQuantity(setQuantity);
                productService.saveFile();
            }
        }
    }

    public void calculatorProductRemove(Order order) throws IOException {

            for (Product product : products
            ){
                if (order.getProduct_name().equals(product.getName())){
                    int setQuantity = product.getQuantity()+order.getOrder_product_quantity();
                    product.setQuantity(setQuantity);
                    productService.saveFile();
                    break;
                }
            }
        }
    public Order remove(String id)  {
        for (Order order: orders
             ) {
            if (order.getId().equals(id)){
                orders.remove(order);
                saveFile();
                return order;
            }
        }
        return null;
    }

    public int getSum(){
        int sum = 0;
        for (Order order : orders
        ){
          sum += order.getSum();
        }
        return sum;
    }


    public static void readFile()  {
        orders.clear();
        FileReader fileReader = null;
        BufferedReader reader = null;
        try{
            fileReader = new FileReader("coffeShop\\src\\files\\orderfiles.txt");
            reader = new BufferedReader(fileReader);
            String line  ;
            while ((line = reader.readLine()) != null){
                Order order = handleLine(line);
                orders.add(order);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                reader.close();
                fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static Order handleLine(String line){
        String[] strings = line.split(",");
        return new Order(strings[0].trim(),strings[1].trim(),strings[2].trim(), Integer.parseInt(strings[3].trim()),
                Integer.parseInt(strings[4].trim()));
    }
    public boolean checkOrderList(){
        if(orders.size() > 0){
            return true;
        }
        System.out.println("List empty!!!");
        return false;
    }

   public void display(){
       System.out.println(orders);
   }

    public void saveFile( )  {
        FileWriter fw = null;
        BufferedWriter bufferedWriter = null;
        try{
            String path = "coffeShop\\src\\files\\orderfiles.txt";
             fw = new FileWriter(path);
             bufferedWriter = new BufferedWriter(fw);
            for (Order order: orders
            ) {
                    bufferedWriter.write(order.toFile());
                    bufferedWriter.newLine();

            }

        }catch (IOException e){
            System.out.println(e);
        }finally {
            try{
                bufferedWriter.close();
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
