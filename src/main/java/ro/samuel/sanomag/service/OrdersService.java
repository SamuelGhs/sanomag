package ro.samuel.sanomag.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import ro.samuel.sanomag.model.InputOrder;
import ro.samuel.sanomag.model.InputProduct;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static ro.samuel.sanomag.util.ConfigLoader.getProperty;
import static ro.samuel.sanomag.util.FileUtil.getDestinationFile;
import static ro.samuel.sanomag.util.FileUtil.readFile;
import static ro.samuel.sanomag.util.FileUtil.writeJson;

public class OrdersService {
    private static String SEPARATOR = File.separator;

    public void processInputFile() throws IOException {
        InputOrder inputOrder = getInputOrder();
        List<InputProduct> inputProductList = inputOrder.getInputProductList();
        for (InputProduct inputProduct : inputProductList) {
            System.out.println(inputProduct.getSupplier());
            File file = getDestinationFile(inputProduct.getSupplier());
            Gson gson = new Gson();
            String jsonString = gson.toJson(inputProduct);
            writeJson(file, jsonString);
        }
    }

    private InputOrder getInputOrder() throws IOException {
        String newFile = readFile(getProperty("orders.input.path") + SEPARATOR + "InputOrder.json");
        ObjectMapper objectMapper = new ObjectMapper();
        InputOrder inputOrder = objectMapper.readValue(newFile, InputOrder.class);
        return inputOrder;
    }
}
