package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.Passagem;

public class PassagemDAO extends PadraoDao implements IGenericDAO<Passagem>{

    public PassagemDAO() {
    	super();
    }

    @Override
	public Integer save(Passagem obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("INSERT INTO Passagem (titulo,preco,tipo,idFornecedor,idPacote_viagem) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getTitulo());
			super.pst.setDouble(2, obj.getPreco());
			super.pst.setInt(3, obj.getTipo());
			super.pst.setInt(4, obj.getIdFornecedor());
			super.pst.setInt(5, obj.getIdPacoteViagem());

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
	public void update(Passagem obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement(
					"UPDATE Passagem SET  Passagem.titulo=?, Passagem.preco=?, Passagem.tipo=? WHERE Passagem.id = ?");
			pst.setString(1, obj.getTitulo());
			pst.setDouble(2, obj.getPreco());
			pst.setInt(3, obj.getTipo());
			pst.setInt(4, obj.getId());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}
	}

	@Override
	public void delete(Passagem obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("DELETE FROM Passagem WHERE Passagem.id =?");
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
			super.pst = super.c1.prepareStatement("DELETE FROM Passagem WHERE Passagem.id =?");
			super.pst.setInt(1, id);
			super.pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}
	}

	@Override
	public Passagem findById(Integer id) {
		Passagem p = new Passagem();
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement("SELECT FROM Passagem WHERE Passagem.id =?");
			super.rs = super.pst.executeQuery();
			while(super.rs.next()) {
				p.setId(rs.getInt("idPassagem"));
				p.setTitulo(rs.getString("titulo"));
				p.setPreco(rs.getDouble("preco"));
				p.setTipo(rs.getInt("tipo"));
				p.setIdFornecedor(rs.getInt("idFornecedor"));
				p.setIdPacoteViagem(rs.getInt("idPacote_viagem"));
			}
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
		return p;
	}

	@Override
	public ArrayList<Passagem> findAll() {
		ArrayList<Passagem> Passagems = new ArrayList<Passagem>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM Passagem");
			while (super.rs.next()) {
				Passagem u = new Passagem(super.rs.getInt("idPassagem"), super.rs.getString("titulo"), super.rs.getDouble("preco"),
					super.rs.getInt("tipo"), super.rs.getInt("idFornecedor"), super.rs.getInt("idPacote_viagem"));
				Passagems.add(u);
			}
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
			Db.closeResultSet(super.rs);
		}
		return Passagems;
	}
    
}