package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.Administrador;

public class AdministradorDAO extends PadraoDao implements IGenericDAO<Administrador>{

    public AdministradorDAO() {
    	super();
    }

    @Override
	public Integer save(Administrador obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"INSERT INTO Administrador (numero_viagem_revisadas, id_usuario) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setInt(1, obj.getNumeroViagemRevisadas());
			super.pst.setInt(2, obj.getIdUsuario());

			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
				System.out.printf("Inserido %d Administrador no banco, ... %n %n", linhasAlteradas);
				super.rs = super.pst.getGeneratedKeys();
				int id = 0;
				while (super.rs.next()) {
					id = super.rs.getInt(1);
				}
				return id;
			} else {
				System.out.println("nenhuma Administrador foi criado");
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
	public void update(Administrador obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement(
					"UPDATE Administrador SET  Administrador.numero_viagem_revisadas=? WHERE Administrador.id = ?");
			super.pst.setInt(1, obj.getNumeroViagemRevisadas());

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("Administrador alterado com sucesso %d linhas afetadas", linhasAlteradas);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}

	}

	@Override
	public void delete(Administrador obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("DELETE FROM Administrador WHERE Administrador.id =?");
			super.pst.setInt(1, obj.getId());

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("Administrador deletado com sucesso %d linhas afetadas", linhasAlteradas);

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
			super.pst = super.c1.prepareStatement("DELETE FROM Administrador WHERE Administrador.id =?");
			pst.setInt(1, id);

			int linhasAlteradas = super.pst.executeUpdate();
			System.out.printf("Administrador deletado com sucesso %d linhas afetadas", linhasAlteradas);

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}

	}
	
	@Override
	public Administrador findById(Integer id) {
		Administrador u = null;
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT FROM Administrador WHERE Administrador.id =?");
			super.rs.first();
			u = new Administrador(super.rs.getInt("id"), super.rs.getInt("numero_viagem_revisadas"), super.rs.getInt("id_usuario"));
			return u;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
	}

	@Override
	public ArrayList<Administrador> findAll() {
		ArrayList<Administrador> Administradors = new ArrayList<Administrador>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM Usuario INNER JOIN Administrador on Usuario.id = Administrador.id_usuario");

			while (super.rs.next()) {
				Administrador u = new Administrador(super.rs.getString("nome"),
				super.rs.getString("email"),
				super.rs.getString("password"),
				super.rs.getString("telefone"),
				super.rs.getString("imagem"),
				super.rs.getInt("tipoUsuario"),
				super.rs.getInt("idEndereco"),
				super.rs.getInt("numero_viagem_revisadas"),
				super.rs.getInt("id_usuario"));
				Administradors.add(u);
			}
			return Administradors;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
	}
    
}