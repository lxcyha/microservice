package per.cyh.user.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import per.cyh.thrift.user.dto.UserInfoDTO;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public abstract class LoginFilter implements Filter {

    private static Cache<String, UserInfoDTO> cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(3, TimeUnit.SECONDS).build();

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = httpServletRequest.getParameter("token");

        if (StringUtils.isBlank(token)) {
            Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies!= null){
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        token = cookie.getValue();
                    }
                }
            }

        }

        UserInfoDTO userInfoDTO = null;

        if (StringUtils.isNotBlank(token)) {

            userInfoDTO = cache.getIfPresent("token");
            if (userInfoDTO == null) {
                userInfoDTO = requestUserInfo(token);
                cache.put(token, userInfoDTO);
            }

            if (userInfoDTO == null) {
                httpServletResponse.sendRedirect("http://127.0.0.1:8082/user/login");
                return;
            }

            login(httpServletRequest, httpServletResponse, userInfoDTO);

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
        httpServletResponse.sendRedirect("http://127.0.0.1:8082/user/login");
        return;

    }

    protected abstract void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, UserInfoDTO userInfoDTO);

    private UserInfoDTO requestUserInfo(String token) {
        String url = "http://127.0.0.1:8082/authentication";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("token", token);
        InputStream inputStream = null;
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("request user info failed! statusLine: " + httpResponse.getStatusLine());
            }

            inputStream = httpResponse.getEntity().getContent();
            byte[] temp = new byte[1024];
            int len;
            StringBuilder stringBuilder = new StringBuilder();
            while ((len = inputStream.read(temp)) > 0) {
                stringBuilder.append(new String(temp, 0, len));
            }
            UserInfoDTO userInfoDTO = new ObjectMapper().readValue(stringBuilder.toString(), UserInfoDTO.class);
            return userInfoDTO;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void destroy() {

    }
}
