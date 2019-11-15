package no.kristiania.DAO;

import java.util.Objects;

public class OperationTrooper {


    private long id;
    private String name;
    private String email;

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public OperationTrooper() {


    }

    public OperationTrooper(String name, String email) {
        this.name = name;
        this.email = email;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationTrooper member = (OperationTrooper) o;
        return id == member.id &&
                Objects.equals(name, member.name) &&
                Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "OperationTrooper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
