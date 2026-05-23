package services;
import java.util.List;

public interface IGeneric<T> {
    int create(T t);
    int update(T t);
    int delete(int id);
    List<T> getAll();
    T get(int id);
}

