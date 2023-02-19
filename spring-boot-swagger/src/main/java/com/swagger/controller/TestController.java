package com.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/4/8 5:33 PM
 */
@RestController
@Api(tags = "测试接口")
public class TestController {

    @Value("${server.port}")
    private String port;

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public String test() {
        return "test" + port;
    }
}
