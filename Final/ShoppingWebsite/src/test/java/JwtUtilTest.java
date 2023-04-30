import com.auth0.jwt.interfaces.DecodedJWT;
import com.lldw.www.utils.JwtUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date
 */
public class JwtUtilTest {
    @Test
    public void t() {
        Map<String, Object> m = new HashMap<>();
        m.put("name", "lldw");
        m.put("id", 1);
        String token = JwtUtil.getToken(m);
        System.out.println(token);
        DecodedJWT verify = JwtUtil.decode(token);
        String name = verify.getClaim("name").asString();
        String name1 = JwtUtil.getObjectFromToken( String.class,token, "name");
        Integer id = JwtUtil.getObjectFromToken(Integer.class,token,  "id");
        System.out.println(name1);
        System.out.println(id);
    }
}
