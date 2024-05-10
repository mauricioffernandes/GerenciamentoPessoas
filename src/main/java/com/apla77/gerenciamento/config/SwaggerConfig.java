package com.apla77.gerenciamento.config;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;

import java.util.ArrayList;

public class SwaggerConfig {

    private ApiInfo metaInfo( ) {
        ApiInfo apiInfo = new ApiInfo( "TODO List", "GERENCIAMENTO DE PESSOAS list API REST.", "1.0.0",
                "Terms of Service", new Contact( "Mauricio Ferreira Fernandes",
                "https://github.com/mauricioffernandes",
                "verocidade77@gmail.com" ),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html",
                new ArrayList<VendorExtension>( ) );
        return apiInfo;
    }
}
