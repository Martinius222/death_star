package no.kristiania.DAO;

import java.util.Objects;

public class OperationObjective {
    private String name;
    private String description;
    private long id;

    public OperationObjective (){}

    public OperationObjective(String name, String description, Integer id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationObjective operationObjective = (OperationObjective) o;
        return id == operationObjective.id &&
                Objects.equals(name, operationObjective.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id);
    }

    @Override
    public String toString() {
        return "OperationObjective{" +
                "OperationObjective Name='" + name + '\'' +
                "OperationObjective Description='" + description + '\'' +
                "OperationObjective ID=" + id +
                '}';
    }
}
