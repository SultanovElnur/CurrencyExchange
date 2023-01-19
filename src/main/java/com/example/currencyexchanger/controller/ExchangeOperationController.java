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
    public Map<String, Object> getAllOperations(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

        return exchangeOperationService.getAllOperations(page, size);
    }

    @GetMapping("/client-name/{name}")
    @ApiOperation(value = "This method is used to get by name all exchanged currencies")
    public Map<String, Object> getAllOperationsByName(@PathVariable String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

        return exchangeOperationService.getAllOperationsByName(name, page, size);
    }

    @GetMapping("/amount/{amount}")
    @ApiOperation(value = "This method is used to get by amount all exchanged currencies")
    public Map<String, Object> getAllOperationsByAmount(@PathVariable String amount, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

        return exchangeOperationService.getAllOperationsByAmount(amount, page, size);
    }

    @GetMapping("/date/{date}")
    @ApiOperation(value = "This method is used to get by date all exchanged currencies")
    public Map<String, Object> getAllOperationsByDate(@PathVariable String date, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

        return exchangeOperationService.getAllOperationsByDate(date, page, size);
    }
}
