package service;

import coffeeshop.CoffeeShop;

import java.io.FileNotFoundException;


public class CoffeeShopService {
    private static  CoffeeShopService coffeeShopService;

    private CoffeeShopService()  {
        coffeeShopService = new CoffeeShopService();
    }

    public static CoffeeShopService getCoffeeShopService() {
        return coffeeShopService;
    }

    private CoffeeShop coffeeShop = new CoffeeShop();
    private StaffService staffService = StaffService.getStaffService();

    private OrderService orderService = OrderService.getOrderSercive();


    public int setWalletShop(){
        int profit = orderService.getSum()- staffService.caclSumSalary();
        coffeeShop.setWallet(profit);
        return coffeeShop.getWallet();
    }
}
