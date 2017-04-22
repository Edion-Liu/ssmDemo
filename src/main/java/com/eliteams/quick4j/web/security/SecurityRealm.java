package com.eliteams.quick4j.web.security;

import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import com.eliteams.quick4j.web.model.Permission;
import com.eliteams.quick4j.web.model.Role;
import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.service.PermissionService;
import com.eliteams.quick4j.web.service.RoleService;
import com.eliteams.quick4j.web.service.UserService;

/**
 * 用户身份验证,授权 Realm 组件
 * 
 * @author StarZou
 * @since 2014年6月11日 上午11:35:28
 **/
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());
        //为当前用户设置角色和权限
        final User user = userService.selectByUsername(username);
        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getId());
        for (Role role : roleInfos) {


            System.out.println("为"+user.getUsername()+",添加角色："+role.getRoleSign());
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
            //这个就是页面中<shiro:hasRole>标签的name的值
            authorizationInfo.addRole(role.getRoleSign());

            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
            for (Permission permission : permissions) {

                //添加权限
                //这个就是页面中<shiro:hasPermission> 标签的name的值
                System.out.println("为"+user.getUsername()+"用户添加,"+permission.getPermissionName()+"权限,"+permission.getPermissionSign());
                authorizationInfo.addStringPermission(permission.getPermissionSign());
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        // 通过数据库进行验证
        final User authentication = userService.authentication(new User(username, password));
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }

}
