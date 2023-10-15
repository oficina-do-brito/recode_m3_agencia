package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.Endereco;
import models.Hospedagem;

public class EnderecoDAO extends PadraoDao implements IGenericDAO<Endereco>{
	
	public EnderecoDAO() {
		super();
	}

	@Override
	public Integer save(Endereco obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"INSERT INTO Endereco (CEP, estado, cidade, bairro, rua, numero) VALUES (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getCEP());
			super.pst.setString(2, obj.getEstado());
			super.pst.setString(3, obj.getCidade());
			super.pst.setString(4, obj.getBairro());
			super.pst.setString(5, obj.getRua());
			super.pst.setInt(6, obj.getNumero());

			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
				int id = 0;
				super.rs = super.pst.getGeneratedKeys();
				while (super.rs.next()) {
					id = super.rs.getInt(1);
				}
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
		return 0;
	}

	@Override
	public void update(Endereco obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("UPDATE Endereco SET  Endereco.CEP=?, Endereco.estado=?, Endereco.cidade=?,Endereco.bairro , Endereco.rua=?,Endereco.numero=? WHERE Endereco.idEndereco=? ");
			super.pst.setString(1, obj.getCEP());
			super.pst.setString(2, obj.getEstado());
			super.pst.setString(3, obj.getCidade());
			super.pst.setString(4, obj.getBairro());
			super.pst.setString(5, obj.getRua());
			super.pst.setInt(6, obj.getNumero());
			super.pst.setInt(7, obj.getId());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}
	}

	@Override
	public void delete(Endereco obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("DELETE FROM Endereco WHERE Endereco.idEndereco =?");
			super.pst.setInt(1, obj.getId());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(super.pst);
		}
	}

	@Override
	public void deleteById(Integer id) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("DELETE FROM Endereco WHERE Endereco.idEndereco =?");
			super.pst.setInt(1, id);
			super.pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(super.pst);
		}
	}

	@Override
	public Endereco findById(Integer id) {
		Endereco u = null;
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement("SELECT FROM Endereco WHERE Endereco.idEndereco =?");
			super.rs = super.pst.executeQuery();
			super.rs.first();
			u = new Endereco(super.rs.getInt("idEndereco"), super.rs.getString("CEP"), super.rs.getString("estado"),
					super.rs.getString("cidade"),super.rs.getString("bairro"),super.rs.getString("rua"),
					super.rs.getInt("numero"));
			return u;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
	}
	 
	@Override
	public ArrayList<Endereco> findAll() {
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM Endereco");
			while (super.rs.next()) {
				Endereco u = new Endereco(super.rs.getInt("idEndereco"), super.rs.getString("CEP"), super.rs.getString("estado"),
					super.rs.getString("cidade"),super.rs.getString("bairro"),super.rs.getString("rua"),
					super.rs.getInt("numero"));
				enderecos.add(u);
			}
			return enderecos;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
			Db.closeResultSet(super.rs);
		}
	}

}
