package ro.samuel.sanomag.model;

import java.util.Objects;

public class OutputProduct {

    private int id;
    private String description;

    public OutputProduct() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutputProduct that = (OutputProduct) o;

        if (id != that.id) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OutputProduct{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
