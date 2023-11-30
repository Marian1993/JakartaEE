package org.eclipse.jakarta.hello.service;

import org.eclipse.jakarta.hello.model.Foto;

import java.util.List;

public interface CrudServiceI<T> {
    default List<Foto> findAll()throws  Exception{

       throw  new Exception("Mètode no implementat");
    }
    default void insert(T t)throws  Exception{
        throw  new Exception("Mètode no implementat");
    }
    default void delete(T t)throws  Exception{

        throw  new Exception("Mètode no implementat");
    }
    default void update(T t)throws  Exception{
        throw  new Exception("Mètode no implementat");
    }
}
