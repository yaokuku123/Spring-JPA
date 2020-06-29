package com.yqj.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Jpa工具类
public class JpaUtils {

    private static EntityManagerFactory factory;

    /*
        由于EntityManagerFactory是线程安全的对象并且创建的开销较大，
        故可以在每个工程中只存在一个对象，并且在初期运行时创建，提升性能
     */
    static {
        factory  = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
