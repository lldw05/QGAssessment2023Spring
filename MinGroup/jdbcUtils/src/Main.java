import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author LLDW
 * @date 2023年3月22日17:37:13
 */
public class Main {
    @Test
    public void test(){
        JdbcUtils ju = JdbcUtils.getInstance();
        Object[] data = {};
        ArrayList<Object> list = ju.execQueryList("select * from account",null);
        int cnt = ju.update("delete from account where id = ?",1);
        System.out.println(cnt);
        System.out.println(list);
    }
}