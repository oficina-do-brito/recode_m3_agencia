package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.Usuario;

public class UsuarioDAO extends PadraoDao implements IGenericDAO<Usuario> {
	public UsuarioDAO() {
		super();
	}

	@Override
	public Integer save(Usuario obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"INSERT INTO Usuario (nome,email,password,telefone,imagem,data_login,tipo_usuario,id_endereco) VALUES (?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getNome());
			super.pst.setString(2, obj.getEmail());
			super.pst.setString(3, obj.getPassword());
			super.pst.setString(4, obj.getTelefone());
			super.pst.setString(5, obj.getImagem());
			super.pst.setDate(6, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			super.pst.setInt(7, obj.getTipoUsuario());
			super.pst.setInt(8, obj.getIdEndereco());
			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
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
	public void update(Usuario obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement(
					"UPDATE Usuario SET  Usuario.nome=?, Usuario.password=?, Usuario.imagem=? WHERE Usuario.id = ?");
			super.pst.setString(1, obj.getNome());
			super.pst.setString(2, obj.getPassword());
			super.pst.setString(3, obj.getImagem());
			super.pst.setInt(4, obj.getId());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}

	}

	@Override
	public void delete(Usuario obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("DELETE FROM Usuario WHERE Usuario.id =?");
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
			super.pst = super.c1.prepareStatement("DELETE FROM Usuario WHERE Usuario.id =?");
			pst.setInt(1, id);
			super.pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}
	}

	@Override
	public Usuario findById(Integer id) {
		Usuario u = null;
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM Usuario WHERE Usuario.id =?");
			super.rs.first();
			u = new Usuario(super.rs.getInt("id"), super.rs.getString("nome"), super.rs.getString("email"),
					super.rs.getString("password"), super.rs.getString("telefone"), super.rs.getString("imagem"),super.rs.getDate("data_login"),
					super.rs.getInt("tipo_usuario"), super.rs.getInt("id_endereco"));
			return u;
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
			Db.closeResultSet(super.rs);
		}
	}

	@Override
	public ArrayList<Usuario> findAll() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT id,nome,email,password,telefone,imagem,data_login,tipo_usuario,id_endereco FROM Usuario");
			while (super.rs.next()) {
				Usuario u = new Usuario(super.rs.getInt("id"), super.rs.getString("nome"), super.rs.getString("email"),
				super.rs.getString("password"), super.rs.getString("telefone"), super.rs.getString("imagem"),super.rs.getDate("data_login"),
				super.rs.getInt("tipo_usuario"), super.rs.getInt("id_endereco"));
				usuarios.add(u);
			}
			return usuarios;
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
			Db.closeResultSet(super.rs);
		}
	}
}
