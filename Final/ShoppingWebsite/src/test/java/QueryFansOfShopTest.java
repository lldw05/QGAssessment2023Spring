import com.lldw.www.po.Shop;
import com.lldw.www.service.Impl.UserFollowShopServiceImpl;
import com.lldw.www.service.UserFollowShopService;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author lldw
 * @date 2023-4-23 14:26:39
 */
public class QueryFansOfShopTest {
    @Test
    public void te(){
        Shop shop = new Shop();
        shop.setShopId(2);
//        Shop shop =null;
        UserFollowShopServiceImpl userFollowShopService = new UserFollowShopServiceImpl();
        ArrayList<Integer> list = userFollowShopService.queryFansOfShop(shop);
//        System.out.println(list.get(0));
        Integer integer = list.get(0);
        System.out.println(integer);
        System.out.println(userFollowShopService.queryFansOfShop(shop));
    }
}
