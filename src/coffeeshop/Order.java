package coffeeshop;

public class Order {
   private String id;
   private String product_name;
   private String order_date;
   private int order_product_quantity;
   private int product_price;
   private int sum;

    public Order(String id, String product_name, String order_date, int order_product_quantity, int product_price) {
        this.id = id;
        this.product_name = product_name;
        this.order_date = order_date;
        this.order_product_quantity = order_product_quantity;
        this.product_price = product_price;
        this.sum = product_price* order_product_quantity;
    }

    public int getSum() {
        return sum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getOrder_product_quantity() {
        return order_product_quantity;
    }

    public void setOrder_product_quantity(int order_product_quantity) {
        this.order_product_quantity = order_product_quantity;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", order_date='" + order_date + '\'' +
                ", order_product_quantyti=" + order_product_quantity +
                ", product_price=" + product_price +
                ", sum="+ this.sum +
                '}';
    }

    public String toFile() {
        return id + "," + product_name + "," + order_date + "," +order_product_quantity + "," + product_price + "," + sum ;
    }
}
