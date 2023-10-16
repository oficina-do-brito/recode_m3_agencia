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
					"INSERT INTO Fornecedor (CNPJ ,tipoServico ,fkUsuario) VALUES (?,?,?)",
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
			super.pst = c1.prepareStatement(
					"UPDATE Fornecedor SET  Fornecedor.tipoServico=? WHERE Fornecedor.idFornecedor = ?");
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
			super.pst = super.c1.prepareStatement("DELETE FROM Fornecedor WHERE Fornecedor.idFornecedor =?");
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
			super.pst = super.c1.prepareStatement("DELETE FROM Fornecedor WHERE Fornecedor.idFornecedor =?");
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
		Fornecedor f = new Fornecedor();
		//idFornecedor,nome,email,password,telefone,imagem,tipoUsuario,dataLogin,fkEndereco,CNPJ,tipoServico,fkUsuario,CEP,estado,cidade,bairro,rua,numero
		String sql="SELECT  * FROM Fornecedor JOIN Usuario ON  Fornecedor.fkUsuario = Usuario.idUsuario JOIN Endereco ON Endereco.idEndereco = Usuario.fkEndereco WHERE  Usuario.idUsuario=?";
		try {
			super.c1 = Db.getConnection();
			super.pst = super.c1.prepareStatement(sql);
			super.pst.setInt(1,id);
			super.rs = super.pst.executeQuery();
			while (super.rs.next()) {
				 // Usuario id (Usuario) ,nome,email,password,telefone,imagem,dataLogin,tipoUsuario,idEndereco;
				 //fornecedor  id;CNPJ;tipoServico;idUsuario;
				f.setId(super.rs.getInt("idFornecedor"));
				f.setNome(super.rs.getString("nome"));
				f.setEmail(super.rs.getString("email"));
				f.setPassword(super.rs.getString("password"));
				f.setTelefone(super.rs.getString("telefone"));
				f.setImagem(super.rs.getString("imagem"));
				f.setDataLogin(super.rs.getDate("dataLogin"));
				f.setIdEndereco(super.rs.getInt("fkEndereco"));
				f.setTipoUsuario(super.rs.getInt("tipoUsuario"));
				f.setCNPJ(super.rs.getString("CNPJ"));
				f.setTipoServico(super.rs.getInt("tipoServico"));
				
				f.setEndereco(super.rs.getInt("idEndereco"), super.rs.getString("CEP"), super.rs.getString("estado"),
						super.rs.getString("cidade"), super.rs.getString("bairro"), super.rs.getString("rua"),
						super.rs.getInt("numero"));
			}
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			Db.closePreparedStatement(super.pst);
			Db.closeResultSet(super.rs);
		}
		return f;
	}

	@Override
	public ArrayList<Fornecedor> findAll() {
		ArrayList<Fornecedor> Fornecedors = new ArrayList<Fornecedor>();
		try {
			super.c1 = Db.getConnection();
			super.st = c1.createStatement();
			super.rs = super.st.executeQuery(
					"SELECT id_fornecedor,nome,email,password,telefone,imagem,dataLogin,tipoUsuario,fkEndereco,CNPJ,tipoServico,fkUsuario FROM Fornecedor INNER INNER JOIN Usuario on Fornecedor.fkUsuario =  Usuario.idUsuario");
			while (super.rs.next()) {
				Fornecedor u = new Fornecedor(super.rs.getInt("idFornecedor"), super.rs.getString("nome"),
						super.rs.getString("email"), super.rs.getString("password"), super.rs.getString("telefone"),
						super.rs.getString("imagem"), super.rs.getDate("dataLogin"), super.rs.getInt("tipoUsuario"),
						super.rs.getInt("fkEndereco"), super.rs.getString("CNPJ"), super.rs.getInt("tipoServico"),
						super.rs.getInt("fkUsuario"));
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