package com.feng.util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
public class MybatisUtils {
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();
    private static SqlSessionFactory sqlSessionFactory = null;
    static{
        //创建SqlSessionFactory
        InputStream is = null;
        try{
            is = Resources.getResourceAsStream("Mybatis.xml");
        }catch (IOException e){
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }
    //获取SqlSession
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = threadLocal.get();
        if(sqlSession == null){
            sqlSession = sqlSessionFactory.openSession(true);
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    //关闭SqlSession
//    public static void closeSqlSession(){
//        SqlSession sqlSession = threadLocal.get();
//        if(sqlSession != null){
//            sqlSession.commit();
//            sqlSession.close();
//            threadLocal.set(null);
//        }
//    }
}