package ro.samuel.sanomag;

import ro.samuel.sanomag.service.OrdersService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OrdersService ordersService = new OrdersService();
        ordersService.processInputFile();
    }
}
