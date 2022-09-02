package com.example.trainingsystembackend.config;

import com.example.trainingsystembackend.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(value = {"com.example.trainingsystembackend.service","com.example.trainingsystembackend.config"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;



    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(jwtUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/authenticate").permitAll()

                .antMatchers(HttpMethod.POST,"api/v1/saveTrainer",
                        "api/v1/saveCourse","api/v1/saveTrainee","api/v1/saveBatch","api/v1/saveTopic").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"api/v1/listTrainer","api/v1/listTrainee","api/v1/listCourse",
                        "api/v1/listBatch","api/v1/listTopic").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"api/v1/postAssignment").hasAnyAuthority("TRAINER")
                        .antMatchers(HttpMethod.GET,"api/v1/listTrainersTopic",
                                "api/v1/getAssignmentAnswer/{userId}").hasAnyAuthority("TRAINER")
                .antMatchers(HttpMethod.PUT,"api/v1/updateTrainer/{userId}",
                        "api/v1/provideMarks/{evaluationId}").hasAnyAuthority("TRAINER")
                .antMatchers(HttpMethod.PUT,"api/v1/updateTrainee/{userId}").hasAnyAuthority("TRAINEE")
                .antMatchers(HttpMethod.GET,"api/v1/getAssignmentforTrainee/{userId}",
                        "api/v1/getYourmarks/{userId}").hasAnyAuthority("TRAINEE")
               .antMatchers(HttpMethod.POST,"api/v1/submitAssignment").hasAnyAuthority("TRAINEE")
                .antMatchers(HttpMethod.GET,"api/v1/getUserinfo/{userId}").hasAnyAuthority("ADMIN","TRAINER","TRAINEE").
                // all other requests need to be authenticated
                        anyRequest().authenticated().and().

                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}