package no.kristiania.DAO;

import java.util.Objects;

public class Operation {

    private String name;
    private long id;

    public Operation (){}

    public Operation(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation project = (Operation) o;
        return id == project.id &&
                Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectname='" + name + '\'' +
                ", projectid=" + id +
                '}';
    }
}
