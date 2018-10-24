package com.shopware.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class GenericException extends Exception {
	private static final long serialVersionUID = -310275319563653373L;
	private String status;
	private int statusCode;
	private String message;
	private Object Obj;

}
