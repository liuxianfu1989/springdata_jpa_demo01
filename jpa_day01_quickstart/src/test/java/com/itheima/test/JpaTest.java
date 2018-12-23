package com.itheima.test;

import com.itheima.domain.Customer;
import com.itheima.utils.JPAUtils;
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

    /**
     * 保存一个实体
     */
    @Test
    public void testAdd(){
        //定义对象
        Customer c = new Customer();
        c.setCustName("传智专修学院");
        c.setCustLevel("VIP客户");
        c.setCustIndustry("IT教育");
        c.setCustAddress("江苏省宿迁市");
        c.setCustSource("网络");
        c.setCustPhone("12345678");
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            //获取实体管理对象
            em = JPAUtils.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            //开启事务
            tx.begin();
            //执行操作
            em.persist(c);
            //提交事务
            tx.commit();
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            //释放资源
            em.close();
        }
    }

    @Test
    public void testMerge(){
        //定义对象
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            //获取实体管理对象
            em = JPAUtils.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            //开启事务
            tx.begin();
            //执行操作
            Customer c1 = em.find(Customer.class, 1L);
            //直接修改(需要写全所有属性值，没赋值的属性默认为null，相当于替换)所以先查询出对象再修改某一属性
            c1.setCustName("江苏传智学院");
            em.merge(c1);//修改
            //提交事务
            tx.commit();
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            //释放资源
            em.close();
        }
    }

    /**
     * 删除
     */
    @Test
    public void testRemove(){
        //定义对象
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            //获取实体管理对象
            em = JPAUtils.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            //开启事务
            tx.begin();
            //执行操作
            Customer c1 = em.find(Customer.class, 1L);//先查询出对象再删除
            em.remove(c1);
            //提交事务
            tx.commit();
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            //释放资源
            em.close();
        }
    }
}
