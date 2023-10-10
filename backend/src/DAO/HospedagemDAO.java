package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.Hospedagem;

public class HospedagemDAO extends PadraoDao implements IGenericDAO<Hospedagem> {
	public HospedagemDAO() {
		super();
	}

	@Override
	public Integer save(Hospedagem obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"INSERT INTO Hospedagem (nome,imagem,diaria,preco,id_origem_destino,id_fornecedor) VALUES (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getNome());
			super.pst.setString(2, obj.getImagem());
			super.pst.setInt(3, obj.getDiaria());
			super.pst.setDouble(4, obj.getPreco());
			super.pst.setInt(5, obj.getIdOrigemDestino());
			super.pst.setInt(6, obj.getIdFornecedor());

			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
				System.out.printf("Inserido %d Hospedagem no banco, ... %n %n", linhasAlteradas);
				super.rs = super.pst.getGeneratedKeys();
				int id = 0;
				while (super.rs.next()) {
					id = super.rs.getInt(1);
				}
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
		return 0;
	}

	@Override
	public void update(Hospedagem obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement(
					"UPDATE Hospedagem SET  Hospedagem.nome=?, Hospedagem.imagem=?, Hospedagem.preco=? WHERE Hospedagem.id = ?");
			super.pst.setString(1, obj.getNome());
			super.pst.setString(2, obj.getImagem());
			super.pst.setDouble(3, obj.getPreco());
			super.pst.setInt(4, obj.getId());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}

	}

	@Override
	public void delete(Hospedagem obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("DELETE FROM Hospedagem WHERE Hospedagem.id =?");
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
			super.pst = super.c1.prepareStatement("DELETE FROM Hospedagem WHERE Hospedagem.id =?");
			pst.setInt(1, id);
			super.pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}

	}

	@Override
	public Hospedagem findById(Integer id) {
		Hospedagem u = null;
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT FROM Hospedagem WHERE Hospedagem.id =?");
			super.rs.first();
			u = new Hospedagem(super.rs.getInt("id"), super.rs.getString("nome"), super.rs.getString("imagem"),
					super.rs.getInt("diaria"), super.rs.getDouble("preco"), super.rs.getInt("id_origem_destino"),
					super.rs.getInt("id_fornecedor"));
			return u;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
	}

	@Override
	public ArrayList<Hospedagem> findAll() {
		ArrayList<Hospedagem> Hospedagems = new ArrayList<Hospedagem>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM Hospedagem");
			while (super.rs.next()) {
				Hospedagem u = new Hospedagem(super.rs.getInt("id"), super.rs.getString("nome"),
						super.rs.getString("imagem"),
						super.rs.getInt("diaria"), super.rs.getDouble("preco"), super.rs.getInt("id_origem_destino"),
						super.rs.getInt("id_fornecedor"));
				Hospedagems.add(u);
			}
			return Hospedagems;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
	}

}