import com.lldw.www.dao.BrandDaoImpl;
import com.lldw.www.po.Brand;
import org.junit.Test;

import java.util.List;

/**
 * @author
 * @date
 */
public class TestBrandDaoImpl {
    BrandDaoImpl brandDao = new BrandDaoImpl();
    @Test
    public void test1(){
        int i = brandDao.selectTotalCount();
        System.out.println(i);
    }
}
