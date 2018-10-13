package com.shopware.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/*@ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)    
    public @ResponseBody ApiResponseDTO handleException(HttpServletRequest request,Exception ex) {     
    	int code =ErrorCode.EXCEPTION_OCCURRED.getStatus();
        String errorMessage = "Something went wrong";
        ex.printStackTrace();
        return new ApiResponseDTO(code, failed, errorMessage,null);
    }
    */

}
