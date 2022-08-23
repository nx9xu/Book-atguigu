package com.nx9xu.test;

import com.nx9xu.utils.JDBCUtils;
import org.junit.Test;

public class JDBCUtilsTest {

    @Test
    public void testJDBCUtils() {
        System.out.println(JDBCUtils.getConnection());
    }
}
