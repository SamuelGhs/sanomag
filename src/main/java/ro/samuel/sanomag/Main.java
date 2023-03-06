package ro.samuel.sanomag;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.samuel.sanomag.model.InputOrder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String newFile = Util.readFile("src/main/resources/InputOrder.json");
        System.out.println(newFile);
        ObjectMapper objectMapper = new ObjectMapper();
        InputOrder newObj = objectMapper.readValue(newFile, InputOrder.class);
        System.out.println(newObj);
    }
}
