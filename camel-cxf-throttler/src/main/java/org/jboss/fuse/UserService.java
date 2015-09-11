package org.jboss.fuse;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.camel.Header;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

	private static final Logger log = LoggerFactory
			.getLogger(UserService.class);

	public Response processBody(@Header("userId") Integer userId)
			throws JsonGenerationException, JsonMappingException, IOException, InterruptedException {
		log.info("Received userId : " + userId);
				
		return convertToJaxRs(userId);

	}

	private Response convertToJaxRs(Integer userId) {

		String jsonUser = "";

		User user = new User();
		user.setName("Hooman");
		user.setUserId(userId);

		ObjectMapper mapper = new ObjectMapper();

		try {
			jsonUser = mapper.writeValueAsString(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(Status.OK).entity(jsonUser).build();
	}

}
