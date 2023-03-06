package ro.samuel.sanomag.model;

import java.util.List;
import java.util.Objects;

public class InputOrder {
    private int id;
    private List<InputProduct> inputProductList;

    public InputOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<InputProduct> getInputProductList() {
        return inputProductList;
    }

    public void setInputProductList(List<InputProduct> inputProductList) {
        this.inputProductList = inputProductList;
    }

    @Override
    public String toString() {
        return "InputOrder{" +
                "id=" + id +
                ", inputProductList=" + inputProductList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputOrder that = (InputOrder) o;

        if (id != that.id) return false;
        return Objects.equals(inputProductList, that.inputProductList);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (inputProductList != null ? inputProductList.hashCode() : 0);
        return result;
    }
}
