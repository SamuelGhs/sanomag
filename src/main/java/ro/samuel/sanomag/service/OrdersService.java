package ro.samuel.sanomag.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import ro.samuel.sanomag.model.InputOrder;
import ro.samuel.sanomag.model.InputProduct;
import ro.samuel.sanomag.model.OutputProduct;
import ro.samuel.sanomag.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.samuel.sanomag.util.ConfigLoader.getProperty;
import static ro.samuel.sanomag.util.FileUtil.getDestinationFile;
import static ro.samuel.sanomag.util.FileUtil.getFile;
import static ro.samuel.sanomag.util.FileUtil.readFile;
import static ro.samuel.sanomag.util.FileUtil.writeJson;

public class OrdersService {
    private static String SEPARATOR = File.separator;

    public void processInputFile() throws IOException {
        InputOrder inputOrder = getInputOrder();
        List<InputProduct> inputProductList = inputOrder.getInputProductList();
        Map<String, List<OutputProduct>> map = new HashMap<>();
        for (InputProduct inputProduct : inputProductList) {
            System.out.println(inputProduct.getSupplier());
            if (map.containsKey(inputProduct.getSupplier())) {
                List<OutputProduct> outputProducts = map.get(inputProduct.getSupplier());
                OutputProduct outputProduct = new OutputProduct();
                outputProduct.setId(inputProduct.getId());
                outputProduct.setDescription(inputProduct.getDescription());
                outputProducts.add(outputProduct);
                map.put(inputProduct.getSupplier(), outputProducts);
            } else {
                List<OutputProduct> outputProducts = new ArrayList<>();
                OutputProduct outputProduct = new OutputProduct();
                outputProduct.setId(inputProduct.getId());
                outputProduct.setDescription(inputProduct.getDescription());
                outputProducts.add(outputProduct);
                map.put(inputProduct.getSupplier(), outputProducts);
            }
        }
        for (Map.Entry<String, List<OutputProduct>> entry : map.entrySet()) {
            File file = getDestinationFile(entry.getKey());
            Gson gson = new Gson();
            String jsonString = gson.toJson(entry.getValue());
            writeJson(file, jsonString);
        }
        String filename = "InputOrder.json";
        String inputPath = getProperty("orders.input.path");
        File file = getFile(inputPath + SEPARATOR + filename);
        FileUtil.moveFileToArhive(file);
    }

    private InputOrder getInputOrder() throws IOException {
        String newFile = readFile(getProperty("orders.input.path") + SEPARATOR + "InputOrder.json");
        ObjectMapper objectMapper = new ObjectMapper();
        InputOrder inputOrder = null;
        try {
            inputOrder = objectMapper.readValue(newFile, InputOrder.class);
        } catch (JsonProcessingException exception) {
            System.out.println(exception.getMessage());
            String filename = "InputOrder.json";
            String inputPath = getProperty("orders.input.path");
            File file = getFile(inputPath + SEPARATOR + filename);
            FileUtil.moveFileToError(file);
        }
        return inputOrder;
    }
}
