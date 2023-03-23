package ro.samuel.sanomag.model;

import java.util.List;
import java.util.Objects;

public class OutputOrder {

    private int id;
    private List<OutputProduct> inputProductList;

    public OutputOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OutputProduct> getInputProductList() {
        return inputProductList;
    }

    public void setInputProductList(List<OutputProduct> inputProductList) {
        this.inputProductList = inputProductList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutputOrder that = (OutputOrder) o;

        if (id != that.id) return false;
        return Objects.equals(inputProductList, that.inputProductList);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (inputProductList != null ? inputProductList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OutputOrder{" +
                "id=" + id +
                ", inputProductList=" + inputProductList +
                '}';
    }
}
