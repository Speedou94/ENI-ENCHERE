package fr.eni.encheres.groupe_2.dal;

import java.util.List;

public interface DAO<T>{

    void addNew(T object);
    void delete(int id);
    T selectById(int id);
    void update(T object);
    List<T> selectALL();
}
