package entities;

import java.time.Instant;
import java.time.LocalDateTime;

public class Category extends BaseEntity{
    private String name;
    private boolean state;

    public Category(int id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Category() {
    }

    public Category(int id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, boolean state) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.state = state;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", state=" + state +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
