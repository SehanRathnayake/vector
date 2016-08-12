package com.springapp.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by Sehan Rathnayake on 16/08/09.
 */

@EnableAsync
@Configuration
@ComponentScan("com.springapp.mvc")
public class config {
}
