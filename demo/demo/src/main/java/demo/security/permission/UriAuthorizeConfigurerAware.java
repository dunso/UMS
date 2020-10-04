package demo.security.permission;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import top.dcenter.ums.security.core.api.config.HttpSecurityAware;
import top.dcenter.ums.security.core.bean.UriHttpMethodTuple;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpMethod.GET;
import static top.dcenter.ums.security.core.bean.UriHttpMethodTuple.tuple;

/**
 * spring session 相关配置
 * @author zyw
 * @version V1.0  Created by 2020/5/28 14:06
 */
@Configuration
public class UriAuthorizeConfigurerAware implements HttpSecurityAware {

    @Override
    public void postConfigure(HttpSecurity http) {
        // dto nothing
    }

    @Override
    public void preConfigure(HttpSecurity http) {
        // dto nothing
    }

    @Override
    public Map<String, Map<UriHttpMethodTuple, Set<String>>> getAuthorizeRequestMap() {

         // 也可以在 application.yml 中配置, 不用硬编码
         // security:
         //   client:
         //     # 不需要认证的 uri(可以带 HttpMethod 后缀; 用:隔开), 例如: /user/** 或 /user/**:post, 默认为 空 Set.
         //     permit-urls:
         //       - /**/*.html:GET
         //       - /testSign:GET
         //       - /testSignOfLastSevenDays/**:GET

        final Map<UriHttpMethodTuple, Set<String>> permitAllMap = new HashMap<>(16);
        // 放行要测试 permission 的链接, 以免干扰 permission 测试.
        permitAllMap.put(tuple(GET, "/test/permission/**"), null);
        permitAllMap.put(tuple(GET, "/test/deny/**"), null);
        permitAllMap.put(tuple(GET, "/test/pass/**"), null);

        Map<String, Map<UriHttpMethodTuple, Set<String>>> resultMap = new HashMap<>(1);

        resultMap.put(HttpSecurityAware.PERMIT_ALL, permitAllMap);

        return resultMap;
    }

}
