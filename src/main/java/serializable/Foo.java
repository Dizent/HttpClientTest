package serializable;

import lombok.Data;
import lombok.Builder;

import java.io.Serializable;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/2/28-15:22
 * @description :
 * @Modify By :
 */
@Data
@Builder
public class Foo implements Serializable {

    private static final String LOGGER = "logger";
    public static final String PUB_STATIC_FINAL = "publicStaticFinal";
    public static String PUB_STATIC;

    public String fa;
    private String fb;
    transient public String ta;
    transient private String tb;
}
