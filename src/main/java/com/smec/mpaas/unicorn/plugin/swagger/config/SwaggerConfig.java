package com.smec.mpaas.unicorn.plugin.swagger.config;

import com.smec.mpaas.unicorn.comm.exception.MPaasBusinessException;
import com.smec.mpaas.unicorn.comm.property.SecurityProperty;
import com.smec.mpaas.unicorn.plugin.swagger.property.SwaggerProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableSwagger2
@EnableConfigurationProperties({ SwaggerProperty.class})
@ConditionalOnProperty(name = "mpaas.unicorn.swagger.enable",havingValue = "true")
public class SwaggerConfig {
    @Resource
    private SecurityProperty securityProperty;
    @Resource
    private SwaggerProperty swaggerProperty;

    @Bean
    public void openPluginSwagger(){
        System.out.println("\n ---------  ^__^   Open Unicorn Plugin Swagger \n");
    }

    @Bean
    public Docket productApi(){
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(MPaasBusinessException.STATUS_CODE).message("自定义业务异常").responseModel(new ModelRef("ErrorResponse")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(401).message("没有访问权限").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());
        Docket docket= new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
        if(securityProperty.isOpen()){
            docket=docket.securitySchemes(apiKeyList());
        }
        return docket;
    }

    /**
     * title、description、version 可以通过配置文件注入
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(swaggerProperty.getTitle())
                .description(swaggerProperty.getDescription())
                .version(swaggerProperty.getVersion())

                .build();
    }

    /**
     * header 可以通过配置文件注入
     * @return
     */
    private List<ApiKey> apiKeyList(){
        return Arrays.asList(
                new ApiKey("token",securityProperty.getHeaderName(),"header")
        );
    }


}
