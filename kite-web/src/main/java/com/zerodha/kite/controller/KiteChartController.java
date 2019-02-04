package com.zerodha.kite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zerodha.kite.service.KiteChartService;

@RestController
public class KiteChartController {

	@Autowired
	private KiteChartService kiteChartService;

	@RequestMapping(value = { "/chart" }, method = { RequestMethod.GET })
	public String chart() {
		kiteChartService.chart();
		return "done";
	}
}
