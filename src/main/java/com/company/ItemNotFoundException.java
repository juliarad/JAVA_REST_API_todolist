package com.company;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="There is no such item")
public class ItemNotFoundException extends RuntimeException {
}
