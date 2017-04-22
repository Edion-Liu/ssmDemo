package com.eliteams.quick4j.web.authorization.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.eliteams.quick4j.core.Config.Constants;
import com.eliteams.quick4j.core.util.CUSTOM_UTIL;
import com.eliteams.quick4j.web.authorization.annotation.Authorization;
import com.eliteams.quick4j.web.authorization.tokenManager.TokenManager;
import com.eliteams.quick4j.web.authorization.tokenModel.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.druid.support.json.JSONUtils.toJSONString;

/**
 * 自定义拦截器，判断此次请求是否有权限
 * @see
 * @author ScienJus
 * @date 2015/7/30.
 */

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private TokenManager manager;

    /**
     * preHandle在业务处理器处理请求之前被调用，
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //关闭token 验证
        return true;



        //如果不是映射到方法直接通过
   /*     if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String authorization = request.getParameter(Constants.AUTHORIZATION);
       // String authorization = request.getHeader(Constants.AUTHORIZATION);
        //验证token
        TokenModel model = manager.getToken(authorization);
        if (manager.checkToken(model)) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
            return true;
        }
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // 未通过验证，返回提示json
            JSONObject  responseJSONObject=new JSONObject();
            responseJSONObject.put(CUSTOM_UTIL.STATUS_KEY,CUSTOM_UTIL.STATUS_NG);
            responseJSONObject.put(CUSTOM_UTIL.ERROR_MSG_KEY,CUSTOM_UTIL.TOKEN_ERROR);
            String json =toJSONString(responseJSONObject);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(json);


            return false;
        }
        return true;
*/
    }

    /**
     *  postHandle在业务处理器处理请求执行完成后,生成视图之前执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      //  super.postHandle(request, response, handler, modelAndView);
    }


    /**
     * afterCompletion在DispatcherServlet完全处理完请求后被调用,可用于清理资源等 。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
      //  super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      //  super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
