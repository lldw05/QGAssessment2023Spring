import main.util.JdbcUtils;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author LLDW
 * @date 2023年3月22日17:37:13
 */
public class Main {
    @Test
    public void test(){
        //JdbcUtils ju = new JdbcUtils();
        JdbcUtils ju = JdbcUtils.getInstance();
        Object[] data = {};
//        ArrayList<Object> list = ju.execQueryList("select * from account where id = ?",new Object[]{1});
//        int cnt = ju.update("insert into account2(name,money) values (?,?)","小七",500);
//        int cnt2 = ju.update("insert into account2 values (?,?,?)",null,"小七",500);
//        int cnt3 = ju.update("insert into account2(money) values (?)",500);
//        System.out.println(cnt);
//        System.out.println(cnt2);
//        System.out.println(cnt3);
        ju.query("select * from account where id = ?",new Object[]{1});
        //System.out.println(list);
    }
}