package DAO.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class PadraoDao {
    protected Connection c1 = null;
	protected Statement st = null;
	protected PreparedStatement pst = null;
	protected ResultSet rs = null;

    public PadraoDao() {}
}