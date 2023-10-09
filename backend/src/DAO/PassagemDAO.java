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
			super.pst = super.c1.prepareStatement("INSERT INTO Passagem (titulo,preco,tipo,id_fornecedor,id_pacote_viagem) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getTitulo());
			super.pst.setDouble(2, obj.getPreco());
			super.pst.setInt(3, obj.getTipo());
			super.pst.setInt(4, obj.getIdFornecedor());
			super.pst.setInt(5, obj.getIdPacoteViagem());

			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
				System.out.printf("Inserido %d Passagem no banco, ... %n %n", linhasAlteradas);
				super.rs = super.pst.getGeneratedKeys();
				int id = 0;
				while (super.rs.next()) {
					id = super.rs.getInt(1);
				}
				return id;
			} else {
				System.out.println("Nenhuma Passagem foi criado");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeStatement(pst);
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

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("Passagem alterado com sucesso %d linhas afetadas", linhasAlteradas);

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

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("Passagem deletado com sucesso %d linhas afetadas", linhasAlteradas);

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

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("Passagem deletado com sucesso %d linhas afetadas", linhasAlteradas);

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}

	}

	@Override
	public Passagem findById(Integer id) {
		Passagem p = null;
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT FROM Passagem WHERE Passagem.id =?");
			super.rs.first();
			p = new Passagem(rs.getInt("id"), rs.getString("titulo"), rs.getDouble("preco"),
					rs.getInt("tipo"), rs.getInt("id_fornecedor"), rs.getInt("id_pacote_viagem"));
			return p;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
	}

	@Override
	public ArrayList<Passagem> findAll() {
		ArrayList<Passagem> Passagems = new ArrayList<Passagem>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM Passagem");
			while (super.rs.next()) {
				Passagem u = new Passagem(super.rs.getInt("id"), super.rs.getString("titulo"), super.rs.getDouble("preco"),
					super.rs.getInt("tipo"), super.rs.getInt("id_fornecedor"), super.rs.getInt("id_pacote_viagem"));
				Passagems.add(u);
			}
			return Passagems;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
	}
    
}