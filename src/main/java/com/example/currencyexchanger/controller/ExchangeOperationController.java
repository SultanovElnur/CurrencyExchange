package com.example.currencyexchanger.controller;

import com.example.currencyexchanger.dto.ExchangeDto;
import com.example.currencyexchanger.dto.ExchangeResultDto;
import com.example.currencyexchanger.service.ExchangeOperationService;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExchangeOperationController {

    ExchangeOperationService exchangeOperationService;

    @PostMapping
    @ApiOperation(value = "This method is used to exchange currency")
    public ExchangeResultDto exchange(@Valid @RequestBody ExchangeDto exchangeDto) {
        return exchangeOperationService.exchange(exchangeDto);
    }

    @GetMapping
    @ApiOperation(value = "This method is used to get all exchanged currencies")
    public Map<String, Object> getAllOperations(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String amount,
                                                @RequestParam(required = false) String date,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size) {

        return exchangeOperationService.getAllOperations(name, amount, date,page, size);
    }
}
