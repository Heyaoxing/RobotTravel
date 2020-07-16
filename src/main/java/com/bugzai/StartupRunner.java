package com.bugzai;

import com.bugzai.strategy.RobotCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Title: StartupRunner.java
 * @Package com.bugzai
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/16 14:04
 * @Version V1.0
 */
@Slf4j
@Component
public class StartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("启动初始化");
        RobotCache.getInstance().init();
    }
}
