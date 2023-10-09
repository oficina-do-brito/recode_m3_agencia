package DAO.utils;

import java.util.ArrayList;

public interface IGenericDAO<T> {
		public Integer save(T obj);
	    public void update(T obj);
	    public void delete( T obj);
	    public void deleteById(Integer id);
	    public T findById(Integer id);
	    public ArrayList<T> findAll();
}
