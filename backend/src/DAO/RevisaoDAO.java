package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.Revisao;

public class RevisaoDAO extends PadraoDao implements IGenericDAO<Revisao> {

	public RevisaoDAO() {
		super();
	}

	@Override
	public Integer save(Revisao obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1
					.prepareStatement("INSERT INTO  revisa (idAdministrador, idPacote_viagem) values(?,?)");
			super.pst.setInt(1, obj.getIdAdministrador());
			super.pst.setInt(2, obj.getIdPacoteViagem());
			super.pst.executeUpdate();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}
		return null;

	}

	@Override
	public void update(Revisao obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1
					.prepareStatement("UPDATE revisa revisa.idAdministrador=? WHERE revisa.idAdministrador=?");
			super.pst.setInt(1, obj.getIdAdministrador());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}
	}

	@Override
	public void delete(Revisao obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"DELETE FROM revisa WHERE revisa.idAdministrador AND revisa.idPacote_viagem =?");
			super.pst.setInt(1, obj.getIdAdministrador());
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
			super.pst = c1.prepareStatement("DELETE FROM revisa WHERE revisa.idAdministrador");
			super.pst.setInt(1, id);
			super.pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}
	}

	@Override
	public Revisao findById(Integer id) {
		Revisao rv = null;
		try {
			super.c1 = Db.getConnection();
			super.st = super.c1.createStatement();
			super.rs = super.st.executeQuery("SELECT FROM revisa WHERE revisa.idAdministrador =?");
			rs.first();
			rv = new Revisao(super.rs.getInt("idAdministrador"),super.rs.getInt("idPacote_viagem"));
			return rv;
			
		}catch(SQLException e){
			throw new DbIntegrityException(e.getMessage());
		}finally {}
	}

	@Override
	public ArrayList<Revisao> findAll() {
		ArrayList<Revisao> listRv = new ArrayList<Revisao>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM revisa");
			while (super.rs.next()) {
				Revisao r = new Revisao(super.rs.getInt("idAdministrador"),super.rs.getInt("idPacote_viagem"));
				listRv.add(r);
			}
			return listRv;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
		
	}

}