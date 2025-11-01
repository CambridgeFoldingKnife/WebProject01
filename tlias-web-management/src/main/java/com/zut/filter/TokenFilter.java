package com.zut.filter;

import com.zut.util.JwtUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
@Slf4j
//@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转成httpservlet 便于获得请求头
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        1.获取请求url。
        String url = request.getRequestURL().toString();
//        2.判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if (url.contains("/login")) {
            log.info("------------->当前登录请求，直接放行");
            filterChain.doFilter(request, response);
            return;
        }
//        3.获取请求头中的令牌（token）。判断是否是
        String  jwt = request.getHeader("token");
//        4.判断令牌是否存在，如果不存在，响应401。
        if(!StringUtils.hasLength(jwt)){
            log.info("------------->令牌不存在，返回401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
            return;
        }
//        5.令牌存在解析token，如果解析失败，响应401
        // 5. 解析token
        try {
            // 解析JWT，验证有效性
            JwtUtils.parseJWT(jwt);
            log.info("------------->令牌解析成功，验证通过");

            // 6. 解析成功，放行请求
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            // 解析失败（过期、签名错误等）
            log.error("------------->令牌解析失败: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("Invalid or expired token");
        } catch (Exception e) {
            // 其他异常
            log.error("------------->令牌验证过程发生异常: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
            response.getWriter().write("Server error during token validation");
        }
    }

    }

