package com.nexoventas.api.auth;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwt; private final UserDetailsService users;
    public JwtAuthenticationFilter(JwtService jwt, UserDetailsService users) { this.jwt=jwt; this.users=users; }
    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header=request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header==null || !header.startsWith("Bearer ")) { chain.doFilter(request,response); return; }
        try { String token=header.substring(7); String username=jwt.username(token);
            if (SecurityContextHolder.getContext().getAuthentication()==null) { UserDetails user=users.loadUserByUsername(username); if(jwt.valid(token,user)) { var auth=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities()); auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); SecurityContextHolder.getContext().setAuthentication(auth); } }
        } catch (RuntimeException ignored) { }
        chain.doFilter(request,response);
    }
}
