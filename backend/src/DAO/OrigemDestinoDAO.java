package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.OrigemDestino;

public class OrigemDestinoDAO extends PadraoDao implements IGenericDAO<OrigemDestino>{

    public OrigemDestinoDAO() {
    	super();
    }
	
    @Override
	public Integer save(OrigemDestino obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"INSERT INTO OrigemDestino (nome,imagem,descricao,tipo,fkEndereco) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getNome());
			super.pst.setString(2, obj.getImagem());
			super.pst.setString(3, obj.getDescricao());
			super.pst.setInt(4, obj.getTipo());
			super.pst.setNull(5,0); //super.pst.setInt(5, obj.getIdEndereco());
			int l = super.pst.executeUpdate();
			if (l > 0) {
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
	public void update(OrigemDestino obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement(
					"UPDATE OrigemDestino SET  OrigemDestino.nome=?, OrigemDestino.imagem=?, OrigemDestino.descricao=? OrigemDestino.tipo=? WHERE OrigemDestino.idOrigem = ?");
			super.pst.setString(1, obj.getNome());
			super.pst.setString(2, obj.getDescricao());
			super.pst.setInt(3, obj.getTipo());
			super.pst.setInt(4, obj.getId());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}
	}

	@Override
	public void delete(OrigemDestino obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("DELETE FROM OrigemDestino WHERE OrigemDestino.idOrigem =?");
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
			super.pst = super.c1.prepareStatement("DELETE FROM OrigemDestino WHERE OrigemDestino.idOrigem =?");
			pst.setInt(1, id);
			super.pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}
	}
	//idOrigem ,nome ,imagem ,descricao ,tipo INTEGER,fkEndereco
	@Override
	public OrigemDestino findById(Integer id) {
		OrigemDestino u = null;
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement("SELECT * FROM OrigemDestino WHERE OrigemDestino.idOrigem =?");
			super.rs = super.pst.executeQuery();
			super.rs.first();
			u = new OrigemDestino(super.rs.getInt("idOrigem"), super.rs.getString("nome"), super.rs.getString("imagem"),super.rs.getString("descricao"), super.rs.getInt("tipo"), super.rs.getInt("fkEndereco"));
			return u;
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
	}

	@Override
	public ArrayList<OrigemDestino> findAll() {
		ArrayList<OrigemDestino> OrigemDestinos = new ArrayList<OrigemDestino>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM OrigemDestino");
			while (super.rs.next()) {
				OrigemDestino u = new OrigemDestino(super.rs.getInt("idOrigem"), super.rs.getString("nome"), super.rs.getString("imagem"),super.rs.getString("descricao"), super.rs.getInt("tipo"), super.rs.getInt("fkEndereco"));
				OrigemDestinos.add(u);
			}
			return OrigemDestinos;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
			Db.closeResultSet(super.rs);
		}
	}
    
}