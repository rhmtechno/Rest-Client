package com.exam.client.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.exam.client.config.EnvSetup;
import com.exam.client.model.User;
import com.exam.client.repo.UserRepo;


@Service
public class RestClient {
	@Autowired
	private EnvSetup envSetup;
	@Autowired
	private UserRepo repo;

	// private static final String GET_ALL_USERS_API =
	// "http://localhost:8080//findAll";

	static RestTemplate restTemplate = new RestTemplate();

	public User[] CallGetAllUserAPI() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//		ResponseEntity<String> result = restTemplate.exchange(envSetup.getAllgetApi(), HttpMethod.GET, entity,
//				String.class);
//		System.out.println(result);
		// return result;
		ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(envSetup.getAllgetApi(), User[].class);
		User[] objects = responseEntity.getBody();
		System.out.println(objects);
		return objects;
//		 for(User ob:objects) {
//		 System.out.println(ob.getId());
//		 System.out.println(ob.getName());
//		 System.out.println(ob.getEmail());
//		
//	             }

	}

	public User callGetUserByIdApi(int id) {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", id);
		return restTemplate.getForObject(envSetup.getGetUserByIdApi(), User.class, param);

	}

	public void callCreateUserApi(User user) {
		ResponseEntity<User> user2 = restTemplate.postForEntity(envSetup.getCreateUserApi(), user, User.class);
		System.out.println(user2.getBody());
	}

	public void callUpdateUserApi(int id, User user) {
		Map<String, Integer> param = new HashMap<>();
		restTemplate.put(envSetup.getUpdateUserApi(), user, param);
	}

	public void callUpdateUserApi(int id) {
		Map<String, Integer> param = new HashMap<>();
		restTemplate.delete(envSetup.getDeleteUserApi(), param);
	}

	public void saveAllUserResponse(User user) {
		System.out.println("hi");
		repo.save(user);
	}

	public void saveAllUserResponse2(List<User> user) {
		System.out.println("hi");
		repo.saveAll(user);
	}

	public List<User> getAllUserResponse() {
		return repo.findAll();
	}

	public ResponseEntity<String> CallGetAllUserAPIJson() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(envSetup.getAllgetApi(), HttpMethod.GET, entity,
				String.class);
		return result;

	}

	public String FindStockStatus(String url) {
		HttpHeaders headers = new HttpHeaders();
        Charset utf8 = Charset.forName("UTF-8");
        MediaType mediaType = new MediaType("text", "html", utf8);
        headers.setContentType(mediaType);
        headers.set("User-Agent", "mozilla");
        headers.set("Accept","text/html");
       // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
       // headers.set("Accept-Encoding", "gzip, deflate, br"); 
        
        
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String result = responseEntity.toString();
        // doWrite("C:\\Users\\rakib\\Desktop\\hard\\out.txt",result);
		return result;

	}
	
	
	 public void doWrite(String path, String text) {

	        BufferedWriter br = null;
	        try {
	            File file = new File(path);
	            br = new BufferedWriter(new FileWriter(file));
	            br.write(text);
	            br.flush();
	        } catch (IOException ex) {
	        } finally {
	            try {
	                br.close();
	            } catch (IOException ex) {

	            }
	        }

	    }
	


}
