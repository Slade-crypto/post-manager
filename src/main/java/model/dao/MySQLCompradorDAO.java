package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Comprador;
import model.ModelException;


public class MySQLCompradorDAO implements CompradorDAO {

	@Override
	public boolean save(Comprador comprador) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlInsert = "INSERT INTO `facebook`.`compradores` (`name`, `address`, `email`, `user_id`) VALUES (?, ?, ?, ?);";
		
		db.prepareStatement(sqlInsert);
		db.setString(1, comprador.getName());
		db.setString(2, comprador.getAddress());
		db.setString(3, comprador.getEmail());
		db.setInt(4, '1');
		  
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Comprador comprador) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE compradores "
				         	+ "SET nome = ?, "
				         	+ "address = ?, "
				         	+ "email = ? "
				         + "WHERE id = ?";
		
		
		db.prepareStatement(sqlUpdate);
		
		db.setString(1, comprador.getName());
		db.setString(2, comprador.getAddress());
		db.setString(3, comprador.getEmail());
		db.setInt(4, comprador.getUser().getId());
		db.setInt(5, comprador.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Comprador comprador) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlDelete = " DELETE FROM compradores "
		                 + " WHERE id = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, comprador.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public List<Comprador> listAll() throws ModelException {
	
		DBHandler db = new DBHandler();
		
		List<Comprador> compradores = new ArrayList<Comprador>();
			
		// Declara um instrução SQL
		String sqlQuery = "SELECT * FROM compradores";
		
		db.createStatement();
	
		db.executeQuery(sqlQuery);

		while (db.next()) {
			Comprador u = createComprador(db);
			compradores.add(u);
		}
		
		return compradores;
	}

	@Override
	public Comprador findById(int id) throws ModelException {
		
		DBHandler db = new DBHandler();
				
		String sql = "SELECT * FROM compradores WHERE id = ?";
		
		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();
		
		Comprador u = null;
		while (db.next()) {
			u = createComprador(db);
			break;
		}
		
		return u;
	}
	
	private Comprador createComprador(DBHandler db) throws ModelException {
		Comprador u = new Comprador(db.getInt("id"));
		u.setName(db.getString("name"));
		u.setAddress(db.getString("address"));
		u.setEmail(db.getString("email"));
		
		return u;
	}

}
