package com.cmsz.mc.ecimp.batch.test_3;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

/** 
 * ledger数据操作类 
 *  
 * @author Fu Wei 
 *  
 */  
@Repository
public class LedgerDaoImpl {
	private static final String SAVE_SQL = "insert into LEDGER_TEMP (ID,RCV_DT, MBR_NM, CHK_NBR, CHK_DT, PYMT_TYP, DPST_AMT, PYMT_AMT, COMMENTS) values(?,?,?,?,?,?,?,?,?)";
	//@Autowired
	private JdbcTemplate jdbcTemplate;
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	public void save (final Ledger item){
		//String SAVE_SQL = "insert into LEDGER_TEMP (ID, RCV_DT, MBR_NM, CHK_NBR, CHK_DT, PYMT_TYP, DPST_AMT, PYMT_AMT, COMMENTS)values (?,?,?,?,?,?,?,?,?)";
		System.out.println("执行了>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>LedgerDaoImpl");
		
		
		jdbcTemplate.update(SAVE_SQL,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
					stmt.setInt(1, item.getId());
					stmt.setDate(2, new java.sql.Date(new Date().getTime())); 
				 
	                stmt.setString(3, item.getMemberName());  
	                stmt.setString(4, item.getCheckNumber());  
	                stmt.setDate(5, new java.sql.Date(new Date().getTime()));  
	                stmt.setString(6, item.getPaymentType());  
	                stmt.setDouble(7, item.getDepositAmount());  
	                stmt.setDouble(8, item.getPaymentAmount());  
	                stmt.setString(9, item.getComments());  
				
			}
		});
	}
}
