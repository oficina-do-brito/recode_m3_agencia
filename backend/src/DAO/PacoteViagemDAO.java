package DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.utils.IGenericDAO;
import DAO.utils.PadraoDao;
import db.Db;
import db.DbIntegrityException;
import models.PacoteViagem;

public class PacoteViagemDAO extends PadraoDao implements IGenericDAO<PacoteViagem> {
	public PacoteViagemDAO() {
		super();
	}

	@Override
	public Integer save(PacoteViagem obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(                                                                       
					"INSERT INTO PacoteViagem (titulo,valorDesconto,precoTotal,possuiHospedagem,status,meioTransporte,imagem,prazoCancelamento,dataViagem,fkOrigem,fkCarrinho) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			super.pst.setString(1, obj.getTitulo());
			super.pst.setInt(2, obj.getValorDesconto());
			super.pst.setDouble(3, obj.getPrecoTotal());
			super.pst.setString(4, obj.getPossuiHospedagem());
			super.pst.setString(5, obj.getStatus());
			super.pst.setString(6, obj.getMeioTransporte());
			super.pst.setString(7, obj.getImagem());
			super.pst.setDate(8, new java.sql.Date(obj.getPrazoCancelamento().getTime()));
			super.pst.setDate(9, new java.sql.Date(obj.getDataViagem().getTime()));
			super.pst.setInt(10, obj.getIdOrigemDestino());
			super.pst.setInt(11, obj.getIdHospedagem());
			super.pst.setInt(11, obj.getIdCarrinhoCompra());

			int linhasAlteradas = super.pst.executeUpdate();
			if (linhasAlteradas > 0) {
				int id = 0;
				super.rs = super.pst.getGeneratedKeys();
				while (super.rs.next()) {
					id = super.rs.getInt(1);
				}
				return id;
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
	public void update(PacoteViagem obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement(
					"UPDATE PacoteViagem SET  PacoteViagem.titulo=?, PacoteViagem.valorDesconto=?, PacoteViagem.possuiHospedagem=?, PacoteViagem.status=? PacoteViagem.meioTransporte=? PacoteViagem.imagem=? PacoteViagem.prazoCancelamento"
							+ "fkOrigem=? WHERE PacoteViagem.idPacote = ?");
			super.pst.setString(1, obj.getTitulo());
			super.pst.setInt(2, obj.getValorDesconto());
			super.pst.setString(3, obj.getPossuiHospedagem());
			super.pst.setString(4, obj.getStatus());
			super.pst.setString(5, obj.getMeioTransporte());
			super.pst.setString(6, obj.getImagem());
			super.pst.setDate(7, new java.sql.Date(obj.getPrazoCancelamento().getTime()));
			super.pst.setInt(8, obj.getIdOrigemDestino());
			super.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closePreparedStatement(super.pst);
		}
	}
	
	@Override
	public void delete(PacoteViagem obj) {
		try {
			super.c1 = Db.getConnection();
			super.pst = c1.prepareStatement("DELETE FROM PacoteViagem WHERE PacoteViagem.idPacote =?");
			pst.setInt(1, obj.getId());
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
			super.pst = c1.prepareStatement("DELETE FROM PacoteViagem WHERE PacoteViagem.idPacote =?");
			super.pst.setInt(1, id);

			super.pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closeStatement(super.pst);
		}
	}

	@Override
	public PacoteViagem findById(Integer id) {
		PacoteViagem p = null;
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement("SELECT * FROM PacoteViagem WHERE PacoteViagem.idPacote =?");
			super.rs = super.pst.executeQuery();
			while(super.rs.next()){
			p = new PacoteViagem(super.rs.getInt("idPacote"), super.rs.getString("titulo"), super.rs.getInt("valorDesconto"),
					super.rs.getDouble("precoTotal"), super.rs.getString("possuiHospedagem"),
					super.rs.getString("status"), super.rs.getString("meioTransporte"), super.rs.getString("imagem"),
					super.rs.getDate("prazoCancelamento"), super.rs.getDate("dataViagem"),
					super.rs.getInt("fkOrigem"), super.rs.getInt("fkHospedagem"),
					super.rs.getInt("fkCarrinho"));
			}
			return p;
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
	}

	@Override
	public ArrayList<PacoteViagem> findAll() {
		ArrayList<PacoteViagem> pacoteViagems = new ArrayList<PacoteViagem>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery("SELECT * FROM PacoteViagem");
			while (super.rs.next()) {
				PacoteViagem u = new PacoteViagem(rs.getInt("idPacote"), rs.getString("titulo"), rs.getInt("valorDesconto"),
						super.rs.getDouble("precoTotal"), super.rs.getString("possuiHospedagem"),
						super.rs.getString("status"), super.rs.getString("meioTransporte"),
						super.rs.getString("imagem"), super.rs.getDate("prazoCancelamento"),
						super.rs.getDate("dataViagem"), super.rs.getInt("fkOrigem"),
						super.rs.getInt("fkHospedagem"), super.rs.getInt("fkCarrinho"));
				pacoteViagems.add(u);
			}
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
		return pacoteViagems;
	}
}