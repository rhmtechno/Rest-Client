package com.exam.client.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.client.model.User;
import com.exam.client.service.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RestClientController {
	@Autowired
	private RestClient resClient;
	private String fr;

	@GetMapping("/")
	public String home() {
		return "This is home";
	}

	@GetMapping("/all")
	public void GetAll() {
		User[] res = resClient.CallGetAllUserAPI();
		List<User> ulist = new ArrayList<>();
		for (User ob : res) {
			// User u = new User(ob.getId(), ob.getName(), ob.getEmail());
			User u = new User();
			u.setClientId(ob.getId());
			u.setName(ob.getName());
			u.setEmail(ob.getEmail());
			ulist.add(u);
		}
		resClient.saveAllUserResponse2(ulist);
		System.out.println(res);
		// resClient.saveAllUserResponse();

	}

	//////////////////////////////////////////////////
	@GetMapping("/eorange")
	public String eOrange() {
		fr = "";
		String url = "https://eorange.shop/product/18320/bajaj-pulsar-150-cc-twin-disc?exclusive_offer=1";
		String result = resClient.FindStockStatus(url);
		resClient.doWrite("C:\\Users\\rakib\\Desktop\\hard\\out2.txt", result);
		if (result.equals("<i class=\"fa fa-times-circle\"></i>\r\n"
				+ "											<span>Out Of Stock</span>\r\n"
				+ "										</p>")) {
			System.out.println("Out Stock");
		}
		if (result.equals("<i class=\"far fa-check-circle\"></i>\r\n"
				+ "											<span>In Stock</span>\r\n"
				+ "										</p>")) {
			System.out.println("In Stock");
		}else {
			System.out.println("not Match");
		}
		return result;

	}

	@RequestMapping(value = "/all2", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	public ResponseEntity<List<User>> GetAll2() {
		System.out.println("uuuuuuuuuuuuuuuu");
		User[] res = resClient.CallGetAllUserAPI();
		List<User> ulist = new ArrayList<>();
		for (User ob : res) {
			// User u = new User(ob.getId(), ob.getName(), ob.getEmail());
			User u = new User();
			u.setId(ob.getId());
			u.setName(ob.getName());
			u.setEmail(ob.getEmail());
			ulist.add(u);
		}
		return new ResponseEntity<>(ulist, HttpStatus.OK);

	}

	@GetMapping("/gson")
	public ResponseEntity<String> gSonTest() throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> res = resClient.CallGetAllUserAPIJson();
		String JsonString = res.getBody();
		ObjectMapper mapper = new ObjectMapper();
		List<User> map = mapper.readValue(JsonString, new TypeReference<List<User>>() {
		});
		System.out.println(res.getStatusCodeValue());
		resClient.saveAllUserResponse2(map);
		for (User u : map) {
			System.out.println(u.getName());
			System.out.println(u.getEmail());
		}
		return res;
	}

	@GetMapping("/user/{id}")
	public User GetById(@PathVariable(value = "id") int id) {

		return resClient.callGetUserByIdApi(id);
	}

}
