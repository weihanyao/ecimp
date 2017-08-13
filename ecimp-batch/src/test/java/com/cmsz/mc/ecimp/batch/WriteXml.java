package com.cmsz.mc.ecimp.batch.test_4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import com.cmsz.mc.ecimp.batch.test_3.Ledger;

public class WriteXml {

	/**
	 * 填充Xml模板导出
	 * @author ChenShilin
	 * @date 2017-04-19
	 * @param xmlFilePath XML模板path
	 * @param xmlFileName 导出的xml文件名称
	 * @param mapData 填充的数据
	 */
	public static void FillXmlByMapData(String xmlFilePath,String xmlFileName,Map<String,String> mapData ,HttpServletResponse response){
		try {
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new File(xmlFilePath));
			//得到xml文档根节点元素
			Element root = doc.getRootElement();
			//从根节点开始遍历所有节点
			GetNodes(root,mapData);
			// 创建输出格式(OutputFormat对象)
	        OutputFormat format = OutputFormat.createPrettyPrint();
	        ///设置输出文件的编码,设置成当前服务器的编码
	        format.setEncoding(System.getProperty("file.encoding"));
	       // FileOutputStream fileOutputStream = new FileOutputStream(xmlFilePath)
			XMLWriter writer = new XMLWriter(new FileWriter(new File(xmlFileName)), format);
			writer.write(doc);
			writer.close();
			//OutPutTableByOutputStream(response, xmlFileName, xmlFilePath);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	
	public static void  test01(String xmlFilePath,String xmlFileName,List<? extends Ledger> ledgers) throws DocumentException, IOException{
		
		// /ecimp-batch/md.xml  /ecimp-batch/src/main/resources/com.batch/md.xml
		//String xmlFilePath = "src/main/resources/com.batch/md2.xml";//模板路径
		System.out.println("开始写>>>>>>>>>>>>>>>>>>>>>>>>>");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File(xmlFilePath));//获取模板
		
		//得到xml文档根节点元素
		Element rootmd1 = doc.getRootElement();//原始模板根节点
		Element rootmd2 = (Element) rootmd1.elements().get(0);//数据部分模板
		Element root1 = null;
		Element root2 = null;
		root1 = (Element) rootmd1.clone();//要写出的根节点
		root1.attribute("name").setText("xxx");//设置表名
		List<Element> elements = root1.elements();//获取跟节点中的字节点然后删除
		for (int i = 0; i < elements.size(); i++) {
			root1.remove(elements.get(0));
		}
		//遍历数据集合	
		Map<String, String> map = new HashMap<>();
		String name = null;
		String value = null;
		Attribute attribute = null;
		for (Ledger ledger : ledgers) {
			map.put("ID",ledger.getId()+"");
			map.put("RCV_DT",ledger.getReceiptDate().toString());
			map.put("MBR_NM",ledger.getMemberName());
			map.put("CHK_NBR",ledger.getCheckNumber());
			map.put("CHK_DT",ledger.getCheckDate().toString());
			map.put("PYMT_TYP",ledger.getPaymentType());
			map.put("DPST_AMT",ledger.getDepositAmount()+"");
			map.put("PYMT_AMT",ledger.getPaymentAmount()+"");
			map.put("COMMENTS",ledger.getComments());
			root2 = (Element) rootmd2.clone();
			List<Element> elements2 = root2.elements();
			for (Element element : elements2) {
				attribute = element.attribute("name");
				name = attribute.getText();
				if (map.containsKey(name)) {
					element.setText(map.get(name));
					//attribute.setText(map.get(name));
				}else {
					element.setText("");
				}
			}
			root1.add(root2);
		}
		Document doc2 = DocumentHelper.createDocument();//要写出的文本
		doc2.add(root1);
		FileOutputStream file = new FileOutputStream(xmlFileName);
		XMLWriter writer = new XMLWriter(file);
		writer.write(doc2);
		writer.close();
		file.close();
	}
	
	
	
	public static void  wxml(String xmlFilePath,String xmlFileName,List<? extends Map<String, String>> ledgers) throws DocumentException, IOException{
		
		// /ecimp-batch/md.xml  /ecimp-batch/src/main/resources/com.batch/md.xml
		//String xmlFilePath = "src/main/resources/com.batch/md2.xml";//模板路径
		System.out.println("开始写>>>>>>>>>>>>>>>>>>>>>>>>>");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File(xmlFilePath));//获取模板
		
		//得到xml文档根节点元素
		Element rootmd1 = doc.getRootElement();//原始模板根节点
		Element rootmd2 = (Element) rootmd1.elements().get(0);//数据部分模板
		Element root1 = null;
		Element root2 = null;
		root1 = (Element) rootmd1.clone();//要写出的根节点
		root1.attribute("name").setText("xxx");//设置表名
		root1.clearContent();//获取跟节点中的字节点然后删除

		//遍历数据集合	
		String name = null;
		String value = null;
		Attribute attribute = null;
		for (Map<String, String> map : ledgers) {
			root2 = (Element) rootmd2.clone();
			List<Element> elements2 = root2.elements();
			for (Element element : elements2) {
				attribute = element.attribute("name");
				name = attribute.getText();
				if (map.containsKey(name)) {
					element.setText(map.get(name));
					//attribute.setText(map.get(name));
				}else {
					element.setText("");
				}
			}
			root1.add(root2);
		}
		Document doc2 = DocumentHelper.createDocument();//要写出的文本
		doc2.add(root1);
		FileOutputStream file = new FileOutputStream(xmlFileName);
		XMLWriter writer = new XMLWriter(file);
		writer.write(doc2);
		writer.close();
		file.close();
	}
	
	
	
	
	@Test
	public void t() throws DocumentException{
		//String xmlFilePath = "src/main/resources/com.batch/md2.xml";//模板路径
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/main/resources/com.batch/md2.xml"));
		Element rElement = document.getRootElement();
		
		System.out.println(rElement.asXML());
	}
	
	
	/**
	 * 从指定节点开始,递归遍历所有子节点 ,
	 * 并且根据节点名称填充相应的数据
	 * @date 2017-04-19
	 * @author chenShiilin
	 * @param node  遍历的节点
	 * @param mapData 需要填充的数据map
	 */
	private static void GetNodes(Element node , Map<String,String> mapData){  
	   //当前节点的名称、文本内容和属性  
	   if(mapData.containsKey(node.getName())){
		   String value = mapData.get(node.getName());
		   if(value != null && value !=""){
			   node.setText(mapData.get(node.getName()));
			   System.out.println("开始写xml>>>>>>>>>>>>>>>>>>>>>>");
		   }else{
			   node.setText("");
		   }
	   }
	   
	   //递归遍历当前节点所有的子节点  
	   List<Element> listElement=node.elements();//所有一级子节点的list  
	   
	   for(Element e:listElement){//遍历所有一级子节点  
	       GetNodes(e,mapData);//递归  
	   }  
	}  
	
	/**
	 * 文件下载
	 * Action outputTableByOutputStream
	 * @author ChenShilin
	 * @date 2017-04-19
	 * @param response
	 * @param fileName  文件名称
	 * @param xmlFilePath  XML模板path
	 * @throws Exception
	 */
	private static void OutPutTableByOutputStream(HttpServletResponse response,String fileName,String xmlFilePath) throws Exception
	{
		String contentType = "application/xml";//定义导出文件的格式的字符串
        response.setCharacterEncoding("UTF-8");
        String recommendedName = new String(fileName.getBytes("GB2312"), "ISO8859-1");//设置文件名称的编码格式
        response.setContentType(contentType);//设置导出文件格式 
        response.setHeader("Content-Disposition", "attachment; filename="+recommendedName);
        FileInputStream inputStream = new FileInputStream(new File(xmlFilePath));  
        //3.通过response获取ServletOutputStream对象(out)  
        ServletOutputStream out = response.getOutputStream();  
        byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer))>0){
			out.write(buffer,0,len);
		}
        inputStream.close();  
        out.close();  
        out.flush();
	}
}
