package com.Tushar.Ecommerce.Multivendor.Controller;

import com.Tushar.Ecommerce.Multivendor.Response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping()
    public ApiResponse HomeControllerHandler(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Welcome to Ecommerce Multivendor ");
        return apiResponse;

    }
}
