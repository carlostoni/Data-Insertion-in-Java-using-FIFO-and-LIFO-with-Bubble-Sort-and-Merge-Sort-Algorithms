package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.DadosInpe;

public class DadosInpeDAO implements IDadosInpeDAO {
	
	   private static final Logger LOGGER = Logger.getLogger(DadosInpeDAO.class.getName());

	private static final String SQL_INSERT= // insere um novo dado 
			"insert into DadosInpe (ano, mes, km) values (?,?,?)";
	
	private static final String SQL_UPDATE= // altera um dado 
			 "update DadosInpe set ano = ?, mes = ?, km = ? where id = ?";
			
	
	private static final String SQL_REMOVE= // delete um dado 
			"delete from DadosInpe where id = ?";
	
	private static final String SQL_FIND_ALL= // seleciona dados do banco 
			"select * from DadosInpe";



	public int salvar(DadosInpe dadosinpe) { // metodo para salvar novo dado no banco
		Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
		try {
			
			pstm = conn.prepareStatement(SQL_INSERT);
			pstm.setString(1, dadosinpe.getAno());
			pstm.setString(2, dadosinpe.getMes());
			pstm.setString(3, dadosinpe.getKm());

			result = pstm.executeUpdate();

			
		}catch(SQLException e){
			if(conn !=null) {
				 LOGGER.log(Level.SEVERE, "Um erro ocorreu ao tentar salvar o registro.", e);
			}
		} finally {
            DBConnection.close(conn, pstm, null);

		}
		return result;
	}


	public int update(DadosInpe dadosinpe) { // metodo para alterar um dado no banco
		 Connection conn = DBConnection.getConnection();
	        PreparedStatement pstm = null;
	        int result = 0;
		try {
			pstm = conn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, dadosinpe.getAno());
			pstm.setString(2, dadosinpe.getMes());
			pstm.setString(3, dadosinpe.getKm());
			pstm.setLong(4, dadosinpe.getId());

			result = pstm.executeUpdate();
			
		  } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Um erro ocorreu ao tentar editar o registro.", e);
	        } finally {
	            DBConnection.close(conn, pstm, null);
	        }
	        return result;
	
		
	}


	public int remover(Long id) { // metodo para deletar um  dado no banco
		 Connection conn = DBConnection.getConnection();
	        PreparedStatement pstm = null;
	        int result = 0;
		try {
			
			
			pstm = conn.prepareStatement(SQL_REMOVE);

			pstm.setLong(1, id);

			result = pstm.executeUpdate();

			
		}catch(SQLException e){
			if(conn !=null) {
				 LOGGER.log(Level.SEVERE, "Um erro ocorreu ao tentar salvar o registro.", e);
			}
		} finally {
            DBConnection.close(conn, pstm, null);
		}
		return result;
	
	}



	public List<DadosInpe> findAll() { // metodo para selecionar dados no banco
		 Connection conn = DBConnection.getConnection();
	        PreparedStatement pstm = null;
		List<DadosInpe> dadosinpee = new ArrayList<DadosInpe>();
		 ResultSet rs = null;
		try {
			
			pstm = conn.prepareStatement(SQL_FIND_ALL);

			 rs = pstm.executeQuery();
			while(rs.next()) {
				DadosInpe dadosinpe = new DadosInpe();
				dadosinpe.setId(rs.getLong("id"));
				dadosinpe.setAno(rs.getString("ano"));
				dadosinpe.setMes(rs.getString("mes"));
				dadosinpe.setKm(rs.getString("km"));
				
				dadosinpee.add(dadosinpe);
						
			}
						
		}catch(SQLException e){
			 LOGGER.log(Level.SEVERE, "Um erro ocorreu ao tentar salvar o registro.", e);
		
		} finally {
            DBConnection.close(conn, pstm, rs);
		}
		return dadosinpee;
			
	
			
	}


	
	
	

	
	

}
