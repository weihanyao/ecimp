package com.cmsz.mc.ecimp.batch.test_3;

import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.io.OutputFormat;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cmsz.mc.ecimp.batch.test_4.WriteXml;



/** 
 * ledger写入数据 
 *  
 * @author Fu Wei 
 *  
 */  
@Component("ledgerWriter")
public class LedgerWriter implements ItemWriter<Map<String, String>>{
	@Autowired
	private LedgerDaoImpl ledgerDao;
	
	public LedgerDaoImpl getLedgerDao() {
		return ledgerDao;
	}

	public void setLedgerDao(LedgerDaoImpl ledgerDao) {
		this.ledgerDao = ledgerDao;
	}

	/** 
     * 写入数据 
     *  
     * @param ledgers 
     */  
	@Override
	public void write(List<?extends Map<String, String>> ledgers) throws Exception {
		System.out.println("执行了>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>LedgerWriter");
		//JSON json = null;
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		//String jsonName = "E:/" + ledgers.get(0).get("ID") + sFormat.format(new Date()) + ".json";
		//FileWriter fileWriter = new FileWriter(jsonName);
		
		/*for (Map<String, String> : ledgers) {
			System.out.println(ledger);
			JSON.writeJSONString(fileWriter, ledger);
		}*/
		
		/*for (Map<String, String> map : ledgers) {
			map.put("ID",ledger.getId()+"");
			map.put("RCV_DT",ledger.getReceiptDate().toString());
			map.put("MBR_NM",ledger.getMemberName());
			map.put("CHK_NBR",ledger.getCheckNumber());
			map.put("CHK_DT",ledger.getCheckDate().toString());
			map.put("PYMT_TYP",ledger.getPaymentType());
			map.put("DPST_AMT",ledger.getDepositAmount()+"");
			map.put("PYMT_AMT",ledger.getPaymentAmount()+"");
			map.put("COMMENTS",ledger.getComments());
		}
		*/
		String xmlName = "E:/" + ledgers.get(0).get("ID") + sFormat.format(new Date()) + ".xml";//输出的文件名
		WriteXml.wxml("src/main/resources/com.batch/md2.xml", xmlName, ledgers);//写文件
	}

	

}
