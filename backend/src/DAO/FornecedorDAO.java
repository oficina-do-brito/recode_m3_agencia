package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.Fornecedor;

public class FornecedorDAO extends PadraoDao implements IGenericDAO<Fornecedor> {
	public FornecedorDAO() {
		super();
	}

	@Override
	public Integer save(Fornecedor obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"INSERT INTO Fornecedor (CNPJ ,tipo_servico ,id_usuario) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getCNPJ());
			super.pst.setInt(2, obj.getTipoServico());
			super.pst.setInt(3, obj.getIdUsuario());

			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
				System.out.printf("Inserido %d Fornecedor no banco, ... %n %n", linhasAlteradas);
				super.rs = super.pst.getGeneratedKeys();
				int id = 0;
				while (super.rs.next()) {
					id = super.rs.getInt(1);
				}
				return id;
			} else {
				System.out.println("nenhuma Fornecedor foi criado");
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
	public void update(Fornecedor obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement("UPDATE Fornecedor SET  Fornecedor.tipo_servico=? WHERE Fornecedor.id = ?");
			super.pst.setInt(1, obj.getTipoServico());
			super.pst.setInt(4, obj.getId());

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("Fornecedor alterado com sucesso %d linhas afetadas", linhasAlteradas);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}

	}

	@Override
	public void delete(Fornecedor obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("DELETE FROM Fornecedor WHERE Fornecedor.id =?");
			super.pst.setInt(1, obj.getId());

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("Fornecedor deletado com sucesso %d linhas afetadas", linhasAlteradas);

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
			super.pst = super.c1.prepareStatement("DELETE FROM Fornecedor WHERE Fornecedor.id =?");
			pst.setInt(1, id);

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("Fornecedor deletado com sucesso %d linhas afetadas", linhasAlteradas);

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}

	}

	@Override
	public Fornecedor findById(Integer id) {
		Fornecedor f = null;
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"SELECT * FROM Usuario JOIN Fornecedor on Usuario.id = Fornecedor.id_usuario JOIN Endereco on Usuario.id_endereco = Endereco.id WHERE Fornecedor.id=?",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setInt(1, id);
			super.rs = pst.executeQuery();
			super.rs.first();
			f = new Fornecedor(rs.getInt("id"), rs.getString("CNPJ"), rs.getInt("tipoServico"),
					rs.getInt("id_usuario"));

			f.setNome(super.rs.getString("nome"));
			f.setEmail(super.rs.getString("email"));
			f.setPassword(super.rs.getString("password"));
			f.setTelefone(super.rs.getString("telefone"));
			f.setImagem(super.rs.getString("imagem"));
			f.setTipoUsuario(super.rs.getInt("tipo_usuario"));
			f.setIdEndereco(super.rs.getInt("id_endereco"));

			f.setEndereco(super.rs.getInt("id"), super.rs.getString("CEP"), super.rs.getString("estado"),
					super.rs.getString("cidade"), super.rs.getString("bairro"), super.rs.getString("rua"),
					super.rs.getInt("numero"));
			return f;
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
	}

	@Override
	public ArrayList<Fornecedor> findAll() {
		ArrayList<Fornecedor> Fornecedors = new ArrayList<Fornecedor>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery(
					"SELECT Fornecedor.id,nome,email,password,telefone,imagem,data_login,tipo_usuario,id_endereco,CNPJ,tipo_servico,id_usuario FROM Fornecedor INNER JOIN Usuario on Fornecedor.id_usuario =  Usuario.id");
			while (super.rs.next()) {
				Fornecedor u = new Fornecedor(super.rs.getInt("id"), super.rs.getString("nome"),
						super.rs.getString("email"), super.rs.getString("password"), super.rs.getString("telefone"),
						super.rs.getString("imagem"),super.rs.getDate("data_login"), super.rs.getInt("tipo_usuario"), super.rs.getInt("id_endereco"),
						super.rs.getString("CNPJ"), super.rs.getInt("tipo_servico"), super.rs.getInt("id_usuario"));
				Fornecedors.add(u);
			}
			return Fornecedors;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
	}

}