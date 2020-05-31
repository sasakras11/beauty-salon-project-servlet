package com.salon.dao;

import java.util.Collections;
import java.util.List;

public interface CrudPageableDao<E> extends CrudDao<E> {

   default List<E> findAll(int page, int itemsPerPage){
       return Collections.emptyList();
   }

    

}