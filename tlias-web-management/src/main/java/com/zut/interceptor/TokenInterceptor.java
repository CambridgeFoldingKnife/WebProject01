package com.zut.interceptor;

import com.zut.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
令牌校验拦截器
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         String url = request.getRequestURL().toString();

         if (url.contains("/login")){
             log.info("当前登录请求，直接放行");
             return true;
         }
         String jwt = request.getHeader("token");
         if (!StringUtils.hasLength(jwt)){
             log.info("令牌不存在，返回401");
             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
              return false;
         }
         try {
             JwtUtils.parseJWT(jwt);
             log.info("令牌解析成功，验证通过");
             return true;
         } catch (Exception e) {
             log.error("令牌解析失败: {}", e.getMessage());
             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
             response.getWriter().write("Invalid or expired token");
             return false;
         }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
