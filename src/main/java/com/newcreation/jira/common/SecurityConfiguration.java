package com.newcreation.jira.common;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final static String AUTHENTICATE_ENDPOINT = "/api/authentication";

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private NewCreationProperties newCreationProperties;

    @Bean
    public AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler(){
        return new AjaxLogoutSuccessHandler();
    }
//
//
//    @Autowired
//    private LoginAttemptService loginAttemptService;

    @Autowired
    private CorsFilter corsFilter;

//    @Autowired
//    private FileCheckFilter fileTypeCheckFilter;

    @Autowired
    private SecurityProblemSupport problemSupport;

    private AuthenticationProvider ap;

    @PostConstruct
    public void init() {
        try {

                authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());

        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    //@Bean
//    public RememberMeServices rememberMeServices() {
//
//        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(newCreationProperties.getSecurity().getRememberMe().getKey(), userService);
//
//// See http://stackoverflow.com/questions/25565809/implementing-a-remember-me-for-spring-social
////rememberMeServices.setAlwaysRemember(true);
//
//        return rememberMeServices;
//
//    }


//    @Bean
//    public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
//        return new AjaxAuthenticationSuccessHandler(loginAttemptService);
//    }
//
//    @Bean
//    public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
//        return new AjaxAuthenticationFailureHandler(loginAttemptService);
//    }
//
//    @Bean
//    public AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler() {
//        return new AjaxLogoutSuccessHandler();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/api/**/*.{js,html,png,jpeg,jpg}")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/swagger-ui/index.html")
                .antMatchers("/api/gallery/image/*")
                .antMatchers("/test/**")
//.antMatchers("api/excel/product")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .maximumSessions(-1)
                .sessionRegistry(sessionRegistry())
                .and()
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .requireCsrfProtectionMatcher(new CsrfSecurityRequestMatcher())
                .and()
                .addFilterBefore(corsFilter, CsrfFilter.class)
//                .addFilterAfter(fileTypeCheckFilter, CsrfFilter.class)
                .addFilterAfter(new SameSiteStrictCookie(), BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
//Removing this as of now as this is creating a problem with SSO
/*.and()
.rememberMe()
.rememberMeServices(rememberMeServices)
.rememberMeParameter("remember-me")
.key(oxaneProperties.getSecurity().getRememberMe().getKey())*/
                .and()
//                .httpBasic()
                .formLogin()
                .loginProcessingUrl(AUTHENTICATE_ENDPOINT)
//                .successHandler(ajaxAuthenticationSuccessHandler())
//                .failureHandler(ajaxAuthenticationFailureHandler())
                .permitAll()
                .and()

                .addFilter(customUsernamePasswordAuthenticationFilter())
//                .addFilterBefore(captchaExceptionHandlerFilter(), CustomUsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(ajaxLogoutSuccessHandler())
                .permitAll()
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()//.anyRequest().permitAll()
                .antMatchers("/api/register", "/api/activate", "/api/authenticate", "/api/account/reset-password/init", "/api/account/reset-password/finish",
                        "/api/account/signup-init", "/api/account/signup-init", "/api/account/signup-finish", "/api/profile-info", "/api/getAuthentication",
                        "/api/authenticateWithToken", "/api/getMonitoringInfo", "/api/excel/product")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/application-module/config").permitAll()
                .antMatchers("/api/**").authenticated()
/* .antMatchers("/websocket/tracker").hasAuthority(Constant.ADMIN)
.antMatchers("/websocket/**").permitAll() */
                .antMatchers("/management/health").permitAll()
                .antMatchers("/management/**").hasAnyAuthority(Constant.ADMIN, Constant.SYS_ADMIN)
                .antMatchers("/v2/api-docs/**").permitAll()
                .antMatchers("/swagger-resources/configuration/ui").permitAll()
                .antMatchers("/swagger-ui/index.html").hasAnyAuthority(Constant.ADMIN, Constant.SYS_ADMIN);

    }

    @Bean
    public UsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter()
            throws Exception {
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
        customUsernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(AUTHENTICATE_ENDPOINT, "POST"));
        customUsernamePasswordAuthenticationFilter.setAuthenticationManager(super.authenticationManager());
//        customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
//        customUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
        return customUsernamePasswordAuthenticationFilter;
    }


    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

}