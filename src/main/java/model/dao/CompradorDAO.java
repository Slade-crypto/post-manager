package model.dao;

import java.util.List;

import model.Comprador;
import model.ModelException;


public interface CompradorDAO {
	boolean save(Comprador comprador) throws ModelException ;
	boolean update(Comprador comprador) throws ModelException;
	boolean delete(Comprador comprador) throws ModelException;
	List<Comprador> listAll() throws ModelException;
	Comprador findById(int id) throws ModelException;
}
