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
					"INSERT INTO Cliente (RG,CPF,numeroViagens,cartaoCredito,fkUsuario) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getRG());
			super.pst.setString(2, obj.getCPF());
			super.pst.setInt(3, obj.getNumeroViagens());
			super.pst.setString(4, obj.getCartaoCredito());
			super.pst.setInt(5, obj.getIdUsuario());

			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
				int id = 0;
				super.rs = super.pst.getGeneratedKeys();
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
			Db.closePreparedStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
		return 0;
	}

	@Override
	public void update(Cliente obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement(
					"UPDATE Cliente SET  Cliente.numeroViagens=?, Cliente.cartaoCredito=? WHERE Cliente.idCliente = ?");
			super.pst.setInt(1, obj.getNumeroViagens());
			super.pst.setString(2, obj.getCartaoCredito());
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
			super.pst = c1.prepareStatement("DELETE FROM Cliente WHERE Cliente.idCliente =?");
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
			super.pst = c1.prepareStatement("DELETE FROM Cliente WHERE Cliente.idCliente =?");
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
		Cliente c = new Cliente();

		String sql = "SELECT * FROM Usuario INNER JOIN Cliente ON Usuario.idUsuario=Cliente.fkUsuario INNER JOIN Endereco ON Usuario.fkEndereco = Endereco.idEndereco WHERE Cliente.idCliente=?";
		//idCliente,nome,email,password,telefone,imagem,tipoUsuario,dataLogin,fkEndereco,RG,CPF,numeroViagens,cartaoCredito,fkUsuario,CEP,estado,cidade,bairro,rua,numero"
		
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(sql);
			super.pst.setInt(1, id);
			
			super.rs = super.pst.executeQuery();
			
			while(super.rs.next()) {
				c.setId(super.rs.getInt("idCliente"));
				c.setRG(rs.getString("RG"));
				c.setCPF(rs.getString("CPF"));
				c.setNumeroViagens(rs.getInt("numeroViagens"));
				c.setcartaoCredito(rs.getString("cartaoCredito"));
				c.setidUsuario( rs.getInt("fkUsuario"));
				
				c.setNome(super.rs.getString("nome"));
				c.setEmail(super.rs.getString("email"));
				c.setPassword(super.rs.getString("password"));
				c.setTelefone(super.rs.getString("telefone"));
				c.setImagem(super.rs.getString("imagem"));
				c.setTipoUsuario(super.rs.getInt("tipoUsuario"));
				c.setIdEndereco(super.rs.getInt("fkEndereco"));
				c.setEndereco(super.rs.getInt("fkEndereco"), super.rs.getString("CEP"), super.rs.getString("estado"),
						super.rs.getString("cidade"), super.rs.getString("bairro"), super.rs.getString("rua"),
						super.rs.getInt("numero"));
			}
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
		return c;
	}

	@Override
	public ArrayList<Cliente> findAll() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery(
					"SELECT * FROM Usuario INNER JOIN Cliente on Usuario.idUsuario = Cliente.fkUsuario INNER JOIN Endereco on Usuario.fkEndereco = Endereco.idEndereco");
			while (super.rs.next()) {
				Cliente c = new Cliente(rs.getInt("idCliente"), rs.getString("RG"), rs.getString("CPF"),
						rs.getInt("numeroViagens"), rs.getString("cartaoCredito"), rs.getInt("fkUsuario"));
				c.setNome(super.rs.getString("nome"));
				c.setEmail(super.rs.getString("email"));
				c.setPassword(super.rs.getString("password"));
				c.setTelefone(super.rs.getString("telefone"));
				c.setImagem(super.rs.getString("imagem"));
				c.setTipoUsuario(super.rs.getInt("tipoUsuario"));
				c.setIdEndereco(super.rs.getInt("fkEndereco"));
				c.setEndereco(super.rs.getInt("idCliente"), super.rs.getString("CEP"), super.rs.getString("estado"),
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
