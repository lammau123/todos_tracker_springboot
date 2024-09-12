package org.trackers.todos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import net.minidev.json.JSONArray;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodosApplicationTests {
	@LocalServerPort
	private int port;
	
	@Value("classpath:schedules.json")
    private org.springframework.core.io.Resource resourceFile;
	
	@Autowired
	private TestRestTemplate restTemplate;

	private Map<Number, String> loadTestData() throws Exception {
		String jsonContent = new String(Files.readAllBytes(Paths.get(resourceFile.getURI())));
        // Parse the JSON content using JsonPath
        DocumentContext documentContext = JsonPath.parse(jsonContent);
        // Read the JSON array as a List of Maps
        JSONArray jsonArray = documentContext.read("$");
        Map<Number, String> map = new HashMap<>();
        for (Object obj: jsonArray) {
        	ReadContext readContext = JsonPath.parse(obj);
        	Number id = readContext.read("$.id");
        	String jsonString = readContext.jsonString();
        	map.put(id, jsonString);
        }
        return map;
	}
	
	@Test
	void todosShouldReturnListOfTodos() throws Exception {
		ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/todos/1", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		DocumentContext documentContext = JsonPath.parse(response.getBody());
		JSONArray jsonArray = documentContext.read("$");
		assertThat(jsonArray.size()).isEqualTo(4);    
		var map = loadTestData();
		for (Object obj : jsonArray) {
			ReadContext readContext = JsonPath.parse(obj);
			var id = readContext.read("$.id");
			var expectJsonString = map.get(id);
			assertThat(readContext.jsonString()).isEqualTo(expectJsonString);
		}
	}
}
