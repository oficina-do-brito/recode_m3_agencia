package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import models.Endereco;

public class EnderecoDAO extends PadraoDao implements IGenericDAO<Endereco>{
	
	public EnderecoDAO() {
		super();
	}

	@Override
	public Integer save(Endereco obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"INSERT INTO Endereco (CEP, estado, cidade, bairro, rua, numero) VALUES (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getCEP());
			super.pst.setString(2, obj.getEstado());
			super.pst.setString(3, obj.getCidade());
			super.pst.setString(4, obj.getBairro());
			super.pst.setString(5, obj.getRua());
			super.pst.setInt(6, obj.getNumero());

			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
				System.out.printf("Inserido %d Endereco no banco, ...  %n %n", linhasAlteradas);
				super.rs = super.pst.getGeneratedKeys();
				int id = 0;
				while (super.rs.next()) {
					id = super.rs.getInt(1);
				}
				return id;
			} else
				System.out.println("nenhuma Endereco foi criado");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeStatement(pst);
			Db.closeResultSet(super.rs);
		}
		return 0;
		
	}

	@Override
	public void update(Endereco obj) {
		
	}

	@Override
	public void delete(Endereco obj) {
		
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public Endereco findById(Integer id) {
		return null;
	}

	@Override
	public ArrayList<Endereco> findAll() {
		return null;
	}

}
