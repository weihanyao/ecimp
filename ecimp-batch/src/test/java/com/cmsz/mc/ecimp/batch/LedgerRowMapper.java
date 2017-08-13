package com.cmsz.mc.ecimp.batch.test_3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.cmsz.mc.ecimp.batch.test_4.GetMd;
import com.sun.org.apache.regexp.internal.recompile;

/** 
 * ledger行的映射类 
 * @author Administrator 
 * 
 */  
@Component("ledgerRowMapper")
public class LedgerRowMapper implements RowMapper<Map<String,String>>{

	@Override
	public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("执行了>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>LedgerRowMapper_" );
		/*Ledger ledger = new Ledger();
		ledger.setId(rs.getInt("id"));  
        ledger.setReceiptDate(rs.getDate("rcv_dt"));  
        ledger.setMemberName(rs.getString("mbr_nm"));  
        ledger.setCheckNumber(rs.getString("chk_nbr"));  
        ledger.setCheckDate(rs.getDate("chk_dt"));  
        ledger.setPaymentType(rs.getString("pymt_typ"));  
        ledger.setDepositAmount(rs.getDouble("dpst_amt"));  
        ledger.setPaymentAmount(rs.getDouble("pymt_amt"));  
        ledger.setComments(rs.getString("comments")); */
        List<String> list = new ArrayList<>();//模板中的属性字段集合
        Map<String, String> map = new HashMap<>();//数据载体
        try {
			GetMd.md(list);//获取模板中所有的name字段属性
		} catch (DocumentException e) {		
			e.printStackTrace();
		}
        String value = null;
       for (int i = 2; i < list.size(); i++) {//将结果集中的值按照字段名封装到map中
    	   value = list.get(i);
    	   System.out.println(value);
			if (!map.containsKey(value)) {
				map.put(value, rs.getObject(value).toString());
			}
	}
        	
		
		return map;
	}
	
}
