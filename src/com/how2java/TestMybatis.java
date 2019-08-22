package com.how2java;
 
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
 
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
 
import com.how2java.pojo.Category;
 
public class TestMybatis {
 
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
 
        listAll(session);

        session.commit();
        session.close();
 
    }

	private static void update(SqlSession session) {
		Category c= session.selectOne("getCategory",3);
        c.setName("�޸��˵�Category���Q");
        session.update("updateCategory",c);
         
        listAll(session);
	}

	private static void get(SqlSession session) {
		Category c= session.selectOne("getCategory",3);
        
        System.out.println(c.getName());
	}

	private static void delete(SqlSession session) {
		Category c = new Category();
        c.setId(6);
        session.delete("deleteCategory",c);
        listAll(session);
	}

	private static void add(SqlSession session) {
		Category c = new Category();
        c.setName("�����ӵ�Category");
        session.insert("addCategory",c);
         
        listAll(session);
	}
 
    private static void listAll(SqlSession session) {
        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
}