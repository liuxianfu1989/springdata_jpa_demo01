package com.itheima.test;

import com.itheima.domain.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author: LiuXianfu
 * @version: 1.0
 * @date: 2018/12/23
 * @since: 1.0
 */

public class JpaTest {
    @Test
    public void test(){
        /**
         * 创建实体管理类工厂，借助Persistence的静态方法获取
         * 其中传递的参数为持久单元名称，需要jpa配置文件中指定，对应persistence.xml：持久化单元名称
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //1. 创建实体管理类
        EntityManager entityManager = factory.createEntityManager();
        //2. 获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //3. 开启事务
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("传智播客.黑马程序员");
        customer.setCustAddress("中粮商务类");
        customer.setCustLevel("Vip客户");

        //4. 保存操作
        entityManager.persist(customer);
        //5. 提交事务
        transaction.commit();
        //6. 释放资源
        entityManager.clear();
        factory.close();
    }
}
