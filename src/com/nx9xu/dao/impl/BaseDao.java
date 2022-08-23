package com.nx9xu.dao.impl;

import com.nx9xu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    // 使用DBUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 用来执行 Insert/Update/Delete 语句
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(connection);
        }
    }

    /**
     * 查询返回一个 javaBean 的 sql 语句
     * @param type 返回对象类型
     * @param sql 执行的 sql 语句
     * @param args sql 的参数值
     * @param <T> 返回类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(connection);
        }
    }

    /**
     * 查询返回多个 javaBean 的 sql 语句
     * @param type 返回对象类型
     * @param sql 执行的 sql 语句
     * @param args sql 的参数值
     * @param <T> 返回类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(connection);
        }
    }

    /**
     * 执行返回一行一列的 sql 语句
     * @param type 返回对象类型
     * @param sql 执行的 sql 语句
     * @param args sql 的参数值
     * @param <T> 返回类型的泛型
     * @return
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(connection);
        }
    }

}
