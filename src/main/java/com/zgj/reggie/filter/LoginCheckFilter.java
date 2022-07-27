package com.zgj.reggie.filter;


import com.alibaba.fastjson.JSON;
import com.zgj.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否登录
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUrl = request.getRequestURI();
        log.info("拦截到请求{}", requestUrl);
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        // 检查是否在放行中的url
        boolean check = check(urls, requestUrl);
        if (check) {
            log.info("本次请求{}不需要处理", requestUrl);
            filterChain.doFilter(request, response);
            return;
        }
        // 检查登录状态
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已登录,id{}", request.getSession().getAttribute("employee"));
            filterChain.doFilter(request, response);
            return;
        }
        // 未登录返回未登录的结果，用输出流的方式向页面返回响应
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        log.info("用户未登录");
        return;

    }

    /**
     * 检查请求url是否需要放行
     *
     * @param urls
     * @param requestUrl
     * @return
     */
    public boolean check(String[] urls, String requestUrl) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestUrl);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
