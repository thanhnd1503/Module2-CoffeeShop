package service;

import coffeeshop.Order;
import coffeeshop.Product;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class ProductService {
    private static  ProductService productService= new ProductService();
    private static Set<Product> productSet ;

    private  ProductService(){
        productService = ProductService.getProductService();
        productSet = new LinkedHashSet<>();
        readFile();
    }

    public static Set<Product> getProductSet() {
        return productSet;
    }
    public static ProductService getProductService() {
        return productService;
    }

    public void add(Product product){
        productSet.add(product);
    }

    public boolean checkId(String id){
        for (Product product: productSet
             ) {
            if (product.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public Product remove(String id)  {
        for (Product product: productSet
             ) {
            if (product.getId().equals(id)){
                productSet.remove(product);
                saveFile();
                return product;
            }
        }
        return null;
    }
    public boolean checkProductList(){
        if(productSet.size() > 0){
            return true;
        }
        System.out.println("List empty!!!");
        return false;
    }

    public void fixProduct(String id, String name, int price, int quantity ) throws IOException {
        for (Product product: productSet
             ) {
            if (product.getId().equals(id)){
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                saveFile();
            }

        }
    }

    public void display(){
        System.out.println(productSet);
    }

    public void saveFile( )  {
        FileWriter fw = null;
        BufferedWriter bufferedWriter = null;
        try{
            String path = "coffeShop\\src\\files\\products.txt";
            fw = new FileWriter(path);
            bufferedWriter = new BufferedWriter(fw);
            for (Product product: productSet
            ) {
                    bufferedWriter.write(product.toFile());
                    bufferedWriter.newLine();

            }
        }catch (IOException e){
            System.out.println(e);
        }
        finally {
            try{
                bufferedWriter.close();
                fw.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static Product handleLine(String line){
        String[] strings = line.split(",");
        return new Product(strings[0].trim(),strings[1].trim(),Integer.parseInt(strings[2].trim()), Integer.parseInt(strings[3].trim()));
    }

    public static void readFile()  {
        productSet.clear();
        FileReader fileReader = null;
        BufferedReader reader = null;
        try{
            fileReader = new FileReader("coffeShop\\src\\files\\products.txt");
            reader = new BufferedReader(fileReader);
            String line  ;
            while ((line = reader.readLine()) != null){
                Product product = handleLine(line);
                productSet.add(product);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if (reader != null){
                    reader.close();

                }
                if (fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
