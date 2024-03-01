package com.example.springmvc.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.springmvc.message.ErrorMessage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage  handleMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpServletRequest httpServletRequest) {
		ErrorMessage apiErrorResponse = new ErrorMessage(HttpStatus.NOT_FOUND.value(), "Api not found" + ex.getMessage());
		return apiErrorResponse;
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage  handlNotSupported(NoHandlerFoundException ex,
			HttpServletRequest httpServletRequest) {
		ErrorMessage apiErrorResponse = new ErrorMessage(HttpStatus.NOT_FOUND.value(), "Api not found" + ex.getMessage());
		return apiErrorResponse;
	}


//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	public ErrorMessage handleAllException(Exception ex, WebRequest request) {
//		// quá trình kiểm soat lỗi diễn ra ở đây
//		return new ErrorMessage(ex.hashCode(), ex.getMessage());
//	}

	/**
	 * IndexOutOfBoundsException sẽ được xử lý riêng tại đây
	 */
	@ExceptionHandler(IndexOutOfBoundsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage TodoException(Exception ex, WebRequest request) {
		return new ErrorMessage(10100, "Đối tượng không tồn tại");
	}

}
