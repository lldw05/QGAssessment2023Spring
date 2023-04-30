import com.lldw.www.dao.Impl.GoodsDaoImpl;
import com.lldw.www.dao.Impl.UserDaoImpl;
import com.lldw.www.po.Goods;
import com.lldw.www.po.User;
import com.lldw.www.utils.OthersUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date
 */
public class BeanToMapTest {
    @Test
    public void t(){
        GoodsDaoImpl goodsDao = new GoodsDaoImpl();
        Goods goods =new Goods();
        goods.setGoodsId(1);
        Goods g = goodsDao.selectGoodsByGoodsId(goods);
        Map<String,Object> map = OthersUtil.beantoMap(g);
        System.out.println(map);
    }
}
