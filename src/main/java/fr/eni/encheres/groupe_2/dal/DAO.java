package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bll.BuissnessException;

import java.util.List;

public interface DAO<T>{

    void addNew(T object) throws BuissnessException;
    void delete(int id);
    T selectById(int id);
    void update(T object) throws BuissnessException;
    List<T> selectALL();


}
