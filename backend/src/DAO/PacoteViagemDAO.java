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
                    "INSERT INTO PacoteViagem (titulo,valor_desconto,preco_total,possui_hospedagem,status,meio_transporte,imagem,prazo_cancelamento,data_viagem,id_origem_destino,id_hospedagem,id_carrinho_compra) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
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
                System.out.printf("Inserido %d PacoteViagem no banco, ... %n %n", linhasAlteradas);
                super.rs = super.pst.getGeneratedKeys();
                int id = 0;
                while (super.rs.next()) {
                    id = super.rs.getInt(1);
                }
                return id;
            } else {
                System.out.println("Nenhuma PacoteViagem foi criado");
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
    public void update(PacoteViagem obj) {
        try {
            super.c1 = Db.getConnection();
            // prazo_cancelamento,data_viagem,id_origem_destino,
            super.pst = c1.prepareStatement(
                    "UPDATE PacoteViagem SET  PacoteViagem.titulo=?, PacoteViagem.valor_desconto=?, PacoteViagem.possui_hospedagem=?, PacoteViagem.status=? PacoteViagem.meio_transporte=? PacoteViagem.imagem=? PacoteViagem.prazo_cancelamento"
                            + "PacoteViagemid_origem_destino=? WHERE PacoteViagem.id = ?");

            super.pst.setString(1, obj.getTitulo());
            super.pst.setInt(2, obj.getValorDesconto());
            super.pst.setString(3, obj.getPossuiHospedagem());
            super.pst.setString(4, obj.getStatus());
            super.pst.setString(5, obj.getMeioTransporte());
            super.pst.setString(6, obj.getImagem());
            super.pst.setDate(7, new java.sql.Date(obj.getPrazoCancelamento().getTime()));
            super.pst.setInt(8, obj.getIdOrigemDestino());

            int linhasAlteradas = super.pst.executeUpdate();
            System.out.printf("PacoteViagem alterado com sucesso %d linhas afetadas", linhasAlteradas);

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
            super.pst = c1.prepareStatement("DELETE FROM PacoteViagem WHERE PacoteViagem.id =?");
            pst.setInt(1, obj.getId());

            int linhasAlteradas = super.pst.executeUpdate();
            System.out.printf("PacoteViagem deletado com sucesso %d linhas afetadas", linhasAlteradas);

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
            super.pst = c1.prepareStatement("DELETE FROM PacoteViagem WHERE PacoteViagem.id =?");
            super.pst.setInt(1, id);

            int linhasAlteradas = super.pst.executeUpdate();
            System.out.printf("PacoteViagem deletado com sucesso %d linhas afetadas", linhasAlteradas);

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
            super.st = c1.createStatement();
            super.rs = super.st.executeQuery("SELECT FROM PacoteViagem WHERE PacoteViagem.id =?");
            super.rs.first();
            p = new PacoteViagem(
                    super.rs.getInt("id"),
                    super.rs.getString("titulo"),
                    super.rs.getInt("valor_desconto"),
                    super.rs.getDouble("preco_total"),
                    super.rs.getString("possui_hospedagem"),
                    super.rs.getString("status"),
                    super.rs.getString("meio_transporte"),
                    super.rs.getString("imagem"),
                    super.rs.getDate("prazo_cancelamento"),
                    super.rs.getDate("data_viagem"),
                    super.rs.getInt("id_origem_destino"),
                    super.rs.getInt("id_hospedagem"),
                    super.rs.getInt("id_carrinho_compra"));
            return p;

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            Db.closeStatement(super.st);
        }
    }

    @Override
    public ArrayList<PacoteViagem> findAll() {
        ArrayList<PacoteViagem> PacoteViagems = new ArrayList<PacoteViagem>();
        try {
            super.c1 = Db.getConnection();
            super.st = c1.createStatement();
            super.rs = super.st.executeQuery("SELECT * FROM PacoteViagem");
            while (super.rs.next()) {
                // status,meio_transporte,imagem,prazo_cancelamento,data_viagem,id_origem_destino,id_hospedagem,id_carrinho_compra
                PacoteViagem u = new PacoteViagem(rs.getInt("id"), rs.getString("titulo"), rs.getInt("valor_desconto"),
                        super.rs.getDouble("preco_total"),
                        super.rs.getString("possui_hospedagem"), super.rs.getString("status"),
                        super.rs.getString("meio_transporte"),
                        super.rs.getString("imagem"),
                        super.rs.getDate("prazo_cancelamento"),
                        super.rs.getDate("data_viagem"),
                        super.rs.getInt("id_origem_destino"),
                        super.rs.getInt("id_hospedagem"),
                        super.rs.getInt("id_carrinho_compra"));
                PacoteViagems.add(u);
            }
            return PacoteViagems;

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            Db.closeStatement(super.st);
        }
    }

}