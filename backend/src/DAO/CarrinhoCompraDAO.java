package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.CarrinhoCompra;

public class CarrinhoCompraDAO extends PadraoDao implements IGenericDAO<CarrinhoCompra> {

	public CarrinhoCompraDAO() {
		super();
	}

	@Override
	public Integer save(CarrinhoCompra obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(
					"INSERT INTO CarrinhoCompra (valorTotal ,formaPagamento ,quantItems ,idCliente) VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setDouble(1, obj.getValorTotal());
			super.pst.setInt(2, obj.getFormaPagamento());
			super.pst.setInt(3, obj.getQuantItems());
			super.pst.setInt(4, obj.getIdCliente());

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
			Db.closeStatement(pst);
			Db.closeResultSet(super.rs);
		}
		return 0;
	}

	@Override
	public void update(CarrinhoCompra obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement(
					"UPDATE CarrinhoCompra SET  CarrinhoCompra.formaPagamento=? WHERE CarrinhoCompra.id = ?");
			super.pst.setInt(1, obj.getFormaPagamento());
			super.pst.setInt(2, obj.getId());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}

	}

	@Override
	public void delete(CarrinhoCompra obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("DELETE FROM CarrinhoCompra WHERE CarrinhoCompra.id =?");
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
			super.pst = super.c1.prepareStatement("DELETE FROM CarrinhoCompra WHERE CarrinhoCompra.id =?");
			pst.setInt(1, id);
			super.pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}

	}
	//id, valorTotal ,formaPagamento ,quantItems ,idCliente)
	@Override
	public CarrinhoCompra findById(Integer id) {
		CarrinhoCompra u = null;
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT FROM CarrinhoCompra WHERE CarrinhoCompra.id =?");
			super.rs.first();
			u = new CarrinhoCompra(super.rs.getInt("id"), super.rs.getDouble("valorTotal"), super.rs.getInt("formaPagamento"),
					super.rs.getInt("quantItems"), super.rs.getInt("idCliente"));
			return u;
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
	}

	@Override
	public ArrayList<CarrinhoCompra> findAll() {
		ArrayList<CarrinhoCompra> usuarios = new ArrayList<CarrinhoCompra>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM CarrinhoCompra");
			while (super.rs.next()) {
				CarrinhoCompra u = new CarrinhoCompra(super.rs.getInt("id"), super.rs.getDouble("valorTotal"), super.rs.getInt("formaPagamento"),
					super.rs.getInt("quantItems"), super.rs.getInt("idCliente"));
				usuarios.add(u);
			}
			return usuarios;

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.st);
		}
	}

}