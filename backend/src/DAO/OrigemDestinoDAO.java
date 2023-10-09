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
					"INSERT INTO OrigemDestino (nome,email,password,telefone,imagem,data_login,tipo_OrigemDestino,id_endereco) VALUES (?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getNome());
			super.pst.setString(2, obj.getImagem());
			super.pst.setString(3, obj.getDescricao());
			super.pst.setInt(4, obj.getTipo());
			super.pst.setInt(5, obj.getIdEndereco());

			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
				System.out.printf("Inserido %d OrigemDestino no banco, ... %n %n", linhasAlteradas);
				super.rs = super.pst.getGeneratedKeys();
				int id = 0;
				while (super.rs.next()) {
					id = super.rs.getInt(1);
				}
				return id;
			} else {
				System.out.println("nenhuma OrigemDestino foi criado");
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
	public void update(OrigemDestino obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement(
					"UPDATE OrigemDestino SET  OrigemDestino.nome=?, OrigemDestino.imagem=?, OrigemDestino.descricao=? OrigemDestino.tipo=? WHERE OrigemDestino.id = ?");
			super.pst.setString(1, obj.getNome());
			super.pst.setString(2, obj.getDescricao());
			super.pst.setInt(3, obj.getTipo());
			super.pst.setInt(4, obj.getId());

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("OrigemDestino alterado com sucesso %d linhas afetadas", linhasAlteradas);

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
			super.pst = super.c1.prepareStatement("DELETE FROM OrigemDestino WHERE OrigemDestino.id =?");
			super.pst.setInt(1, obj.getId());

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("OrigemDestino deletado com sucesso %d linhas afetadas", linhasAlteradas);

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
			super.pst = super.c1.prepareStatement("DELETE FROM OrigemDestino WHERE OrigemDestino.id =?");
			pst.setInt(1, id);

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("OrigemDestino deletado com sucesso %d linhas afetadas", linhasAlteradas);

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}

	}

	@Override
	public OrigemDestino findById(Integer id) {
		OrigemDestino u = null;
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT FROM OrigemDestino WHERE OrigemDestino.id =?");
			super.rs.first();
			//id ,nome,imagem ,descricao ,tipo ,id_endereco

			u = new OrigemDestino(super.rs.getInt("id"), super.rs.getString("nome"), super.rs.getString("imagem"),super.rs.getString("descricao"), super.rs.getInt("tipo"), super.rs.getInt("id_endereco"));
			return u;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
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
				OrigemDestino u = new OrigemDestino(super.rs.getInt("id"), super.rs.getString("nome"), super.rs.getString("imagem"),super.rs.getString("descricao"), super.rs.getInt("tipo"), super.rs.getInt("id_endereco"));
				OrigemDestinos.add(u);
			}
			return OrigemDestinos;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
	}
    
}