/*
 * MIT License
 * Copyright (c) 2020-2029 YongWu zheng (dcenter.top and gitee.com/pcore and github.com/ZeroOrInfinity)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package demo.security.permission.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import top.dcenter.ums.security.core.api.premission.service.AbstractUriAuthorizeService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * request 的 uri 访问权限控制服务.<br>
 * @author YongWu zheng
 * @version V1.0  Created by 2020/9/8 21:54
 */
@Component
@Slf4j
public class DemoUriAuthorizeServiceImpl extends AbstractUriAuthorizeService {

    @Override
    @NonNull
    public Map<String, Map<String, Set<String>>> getRolesAuthorities() {

        // 生产环境: 从数据源获取 RolesAuthorities

        // 示例代码
        Map<String, Map<String, Set<String>>> rolesAuthorities = new HashMap<>(2);
        Map<String, Set<String>> uriAuthority = new HashMap<>(1);
        Set<String> permissions = new HashSet<>();
        permissions.add("add");

        uriAuthority.put("/test/permission/*", permissions);
        uriAuthority.put("/test/pass/*", permissions);

        rolesAuthorities.put("ROLE_USER", uriAuthority);
        rolesAuthorities.put("ROLE_ANONYMOUS", uriAuthority);
        return rolesAuthorities;
    }

    @Override
    public void updateAuthoritiesOfAllRoles() {
        // do nothing, 需要时实现此逻辑
        throw new RuntimeException("未实现 更新角色(roleId)所拥有的 resourceIds 资源信息缓存的接口逻辑");
    }

    @Override
    protected void updateAuthoritiesOfAllTenant() {
        // do nothing, 需要时实现此逻辑
        throw new RuntimeException("未实现基于 多租户 的权限控制的更新或缓存所有角色的权限服务");
    }

    @Override
    protected void updateAuthoritiesOfAllScopes() {
        // do nothing, 需要时实现此逻辑
        throw new RuntimeException("未实现基于 SCOPE 的权限控制的更新或缓存所有角色的权限服务");
    }

    @Override
    protected void updateAllGroupsOfAllTenant() {
        throw new RuntimeException("未实现基于 角色组(Group) 更新或缓存所有角色组的权限服务");
    }

}