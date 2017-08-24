package freemark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.pojo.Person;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GenHtml {
	@Test
	public void GetH() throws IOException, TemplateException{
		//1 创建个configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		//2 设置模板文件所在的路径的目录
		configuration.setDirectoryForTemplateLoading(new File("E:/TEST/freemarker/test_01"));
		//3 设置模板文件字符集
		configuration.setDefaultEncoding("GBK");
		//4 首先创建模板文件,再加载模板文件 
		Template template = configuration.getTemplate("md.xml");
		//5 创建模板文件需要
		List<Person> list = new ArrayList<Person>();
		list.add(new Person(1000, "哈哈"));
		list.add(new Person(2000, "嘿嘿"));
		list.add(new Person(3000, "呵呵"));
		Map<String, Person> map = new HashMap<String, Person>();
		map.put("m1", new Person(1101, "嘻嘻"));
		map.put("m2", new Person(1102, "哼哼"));
		map.put("m3", new Person(1103, "恩恩"));
		
		Map<Object,Object> model = new HashMap<Object,Object>();
		model.put("person",new Person(10000, "哈哈"));
		model.put("list",list);
		model.put("map", map);
		model.put("date", new Date());
		model.put("test", null);
		model.put("name", "hello");
		//6 创建一个fileWriter对象,指定生成的静态文件的文件路径及文件名
		//拼接前缀和后缀
		FileWriter writer = new FileWriter(new File("E:\\TEST\\freemarker\\test_01" + "\\result3.doc"));
		//7 调用模板对象的process方法,执行输出文件
		template.process(model, writer);
		writer.close();
		
	}
	
	
}
