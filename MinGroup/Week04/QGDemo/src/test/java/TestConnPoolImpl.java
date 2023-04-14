import com.lldw.www.utils.JdbcUtils;
import org.junit.Test;

/**
 * @author
 * @date
 */
public class TestConnPoolImpl {
    @Test
    public void Test1(){
        JdbcUtils ju = JdbcUtils.getInstance();
        System.out.println(ju.execQueryList("select * from tb_user",null));
        //System.out.println("AAA");
    }
}
