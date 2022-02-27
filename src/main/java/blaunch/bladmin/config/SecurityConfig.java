package blaunch.bladmin.config;

import blaunch.bladmin.jwt.JwtAccessDeniedHandler;
import blaunch.bladmin.jwt.JwtAuthenticationEntryPoint;
import blaunch.bladmin.jwt.JwtFilter;
import blaunch.bladmin.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)//메소드 단위로 @PreAuthorize 검증 어노테이션을 사용하기 위해 추가
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()//Token 방식을 사용하므로 csrf 설정을 disable 합니다.

                .exceptionHandling()//예외처리를 위해 만들었던 코드를 지정해줍니다.
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()//데이터 확인을 위해 사용하고 있는 h2-console을 위한 설정을 추가해줍니다.
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()//세션을 사용하지 않기 때문에 세션 설정을 STATELESS로 지정해줍니다.
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()//HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정
                .antMatchers("/api/v1/signup").permitAll()//해당 path로 들어오는 요청은 인증없이 접근을 허용
                .antMatchers("/api/v1/authenticate").permitAll()
                .anyRequest().hasRole("ADMIN")//나머지 요청은 모두 인증인가

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }

    public static class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

        private TokenProvider tokenProvider;

        public JwtSecurityConfig(TokenProvider tokenProvider) {
            this.tokenProvider = tokenProvider;
        }

        @Override
        public void configure(HttpSecurity http) {
            JwtFilter customFilter = new JwtFilter(tokenProvider);
            http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }
}
