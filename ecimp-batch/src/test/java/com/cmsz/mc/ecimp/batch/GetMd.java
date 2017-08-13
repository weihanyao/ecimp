package com.cmsz.mc.ecimp.batch.test_4;

import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//获取xml配置模板中的各个节点name属性名称list

public class GetMd {
	public static void md(List<String> list) throws DocumentException{
		SAXReader reader = new SAXReader();
		Document doc = reader.read("src/main/resources/com.batch/md2.xml");
		Element root = doc.getRootElement();
		
		GetNodes(root, list);
		
	}
	private static void GetNodes(Element node , List<String> list){  
		   
			String name = node.attribute("name").getText();
		   if(!list.contains(name) && name != "" && name != null){
			   list.add(name);
			  
		   }
		   //递归遍历当前节点所有的子节点  
		   
		   List<Element> listElement=node.elements();//所有一级子节点的list  
		   
		   for(Element e:listElement){//遍历所有一级子节点  
		       GetNodes(e,list);//递归  
		   }  
		}  
}
