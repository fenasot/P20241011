package com.systex.lottery.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.systex.lottery.exception.InputParameterException;
import com.systex.lottery.service.LotteryService;

@Controller
@RequestMapping("/lottery")
public class LotteryController {

	@GetMapping
	public String getLottery() {
		return "lottery/lottery";
	}
	
	@PostMapping
	public String postlottery(@RequestParam String numbers, @RequestParam(defaultValue = "2") String times, Model model) {
		model.addAttribute("errorMsg", "");
		model.addAttribute("numbers", numbers);
		model.addAttribute("times", times);
		
		try {
			// 輸入限制數字	
			LotteryService a = new LotteryService(numbers);
			// 輸入次數
			ArrayList<int[]> result = a.writeBingo(times);
			
			// 回傳結果
			model.addAttribute("result", result);
			model.addAttribute("excludeNums", a.getStrExcludeNum());
		} catch (InputParameterException e) {
			model.addAttribute("errorMsg", e);
			return "lottery/lottery";
		}
		
		return "lottery/lotteryResult";
	}
}
