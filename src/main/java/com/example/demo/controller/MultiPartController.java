package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Document;
import com.example.demo.service.MultiPartService;

@RestController
public class MultiPartController {
	
	@Autowired
	MultiPartService multiPartService;

/*
This Api is for uploading file using multipart
*/
	
	@RequestMapping(value="api/v1/saveFile", method = RequestMethod.POST)
	public Map<String, Object> saveFile(HttpServletRequest request,MultipartFile file){
		Map<String, Object> result = new HashMap<>();
		result = multiPartService.saveFile(file);
		return result;
	}
	
	@RequestMapping(value="api/v1/insertUser", method = RequestMethod.POST)
	public Map<String, Object> insertUser(HttpServletRequest request, @RequestBody Document document){
		Map<String, Object> result = new HashMap<>();
		result = multiPartService.insertUser(document);
		return result;
	}

	@RequestMapping(value="api/v1/fetchUser", method = RequestMethod.GET)
	public Map<String, Object> fetchUser(HttpServletRequest request){
		Map<String, Object> result = new HashMap<>();
		result = multiPartService.fetchUser();
		return result;
	}
	
	@RequestMapping(value="api/v1/dokyc", method = RequestMethod.POST)
	public Map<String, Object> dokyc(HttpServletRequest request, @RequestBody Map<String, Object> body){
		Map<String, Object> result = new HashMap<>();
		result = multiPartService.doKyc(body);
		return result;
	}
}
