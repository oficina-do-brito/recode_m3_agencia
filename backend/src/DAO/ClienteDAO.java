package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.Cliente;

public class ClienteDAO extends PadraoDao implements IGenericDAO<Cliente> {

	public ClienteDAO() {
		super();
	}

	@Override
	public Integer save(Cliente obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"INSERT INTO Cliente (RG,CPF,numero_viagens,cartao_credito,id_usuario) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getRG());
			super.pst.setString(2, obj.getCPF());
			super.pst.setInt(3, obj.getNumeroViagens());
			super.pst.setString(4, obj.getcartaoCredito());
			super.pst.setInt(5, obj.getidUsuario());

			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
				super.rs = super.pst.getGeneratedKeys();
				int id = 0;
				while (super.rs.next()) {
					id = super.rs.getInt(1);
				}
				return id;
			} else {
				System.out.println("nenhum cliente foi criado...");
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
	public void update(Cliente obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement(
					"UPDATE Cliente SET  Cliente.numero_viagens=?, Cliente.cartao_credito=? WHERE Cliente.id = ?");
			super.pst.setInt(1, obj.getNumeroViagens());
			super.pst.setString(2, obj.getcartaoCredito());
			super.pst.setInt(3, obj.getId());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(pst);
		}
	}

	@Override
	public void delete(Cliente obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement("DELETE FROM Cliente WHERE Cliente.id =?");
			super.pst.setInt(1, obj.getId());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(pst);
		}
	}

	@Override
	public void deleteById(Integer id) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement("DELETE FROM Cliente WHERE Cliente.id =?");
			pst.setInt(1, id);
			super.pst.executeUpdate();

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}
	}

	@Override
	public Cliente findById(Integer id) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"SELECT * FROM Usuario JOIN Cliente on Usuario.id = Cliente.id_usuario JOIN Endereco on Usuario.id_endereco = Endereco.id WHERE Cliente.id=?",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setInt(1, id);
			super.rs = pst.executeQuery();
			super.rs.first();
			Cliente c = new Cliente(rs.getInt("id"), rs.getString("RG"), rs.getString("CPF"),
					rs.getInt("numero_viagens"), rs.getString("cartao_credito"), rs.getInt("id_usuario"));
			c.setNome(super.rs.getString("nome"));
			c.setEmail(super.rs.getString("email"));
			c.setPassword(super.rs.getString("password"));
			c.setTelefone(super.rs.getString("telefone"));
			c.setImagem(super.rs.getString("imagem"));
			c.setTipoUsuario(super.rs.getInt("tipo_usuario"));
			c.setIdEndereco(super.rs.getInt("id_endereco"));
			c.setEndereco(super.rs.getInt("id"), super.rs.getString("CEP"), super.rs.getString("estado"),
					super.rs.getString("cidade"), super.rs.getString("bairro"), super.rs.getString("rua"),
					super.rs.getInt("numero"));
			return c;
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
	}

	@Override
	public ArrayList<Cliente> findAll() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM Usuario JOIN Cliente on Usuario.id = Cliente.id_usuario JOIN Endereco on Usuario.id_endereco = Endereco.id");
			while (super.rs.next()) {
				Cliente c = new Cliente(rs.getInt("id"), rs.getString("RG"), rs.getString("CPF"),
						rs.getInt("numero_viagens"), rs.getString("cartao_credito"), rs.getInt("id_usuario"));
				c.setNome(super.rs.getString("nome"));
				c.setEmail(super.rs.getString("email"));
				c.setPassword(super.rs.getString("password"));
				c.setTelefone(super.rs.getString("telefone"));
				c.setImagem(super.rs.getString("imagem"));
				c.setTipoUsuario(super.rs.getInt("tipo_usuario"));
				c.setIdEndereco(super.rs.getInt("id_endereco"));
				c.setEndereco(super.rs.getInt("id"), super.rs.getString("CEP"), super.rs.getString("estado"),
						super.rs.getString("cidade"), super.rs.getString("bairro"), super.rs.getString("rua"),
						super.rs.getInt("numero"));
				clientes.add(c);
			}
			return clientes;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
			Db.closeResultSet(super.rs);
		}
	}

}
