package ro.samuel.sanomag.model;

import java.util.Objects;

public class InputProduct {
    private int id;
    private String description;
    private String supplier;
    private Integer price;

    public InputProduct() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ImputProduct{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", supplier='" + supplier + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputProduct that = (InputProduct) o;
        return id == that.id && Objects.equals(description, that.description) && Objects.equals(supplier, that.supplier) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, supplier, price);
    }
}
