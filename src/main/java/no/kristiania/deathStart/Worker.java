package no.kristiania.deathStart;


import java.util.Objects;

public class Worker {
    private String name;
    private String email;
    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Worker{ name='" + name + '\'' +
                ", email=" + email + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Worker worker = (Worker) object;
        return Objects.equals(name, worker.name) &&
                Objects.equals(email, worker.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
