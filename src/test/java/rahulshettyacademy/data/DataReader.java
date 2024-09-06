package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDatatoMap() throws IOException {
		
		// read json to string
		String JsonContent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// String to Hashmap Jackson databind

		ObjectMapper mapper = new ObjectMapper();

		/**
		 * 
		 * 
		 * Multiple Hashmaps will be created based on the datasets in json and it will
		 * be returned as List
		 * 
		 * 
		 * 
		 **/

		List<HashMap<String, String>> data = mapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

}
