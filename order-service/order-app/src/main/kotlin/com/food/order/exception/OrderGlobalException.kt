package com.food.order.exception

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
@Slf4j
class OrderGlobalException {

    private val log = LoggerFactory.getLogger(OrderGlobalException::class.java)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [OrderDomainException::class])
    @ResponseBody
    fun handleOrderDomainException(e: OrderDomainException): ErrorDTO {
        log.error("Error occurred: {}", e.message)
        return ErrorDTO(HttpStatus.BAD_REQUEST.reasonPhrase, e.message)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [OrderNotFoundException::class])
    @ResponseBody
    fun handleOrderDomainException(e: OrderNotFoundException): ErrorDTO {
        log.error("Error occurred: {}", e.message)
        return ErrorDTO(HttpStatus.NOT_FOUND.reasonPhrase, e.message)
    }
}

