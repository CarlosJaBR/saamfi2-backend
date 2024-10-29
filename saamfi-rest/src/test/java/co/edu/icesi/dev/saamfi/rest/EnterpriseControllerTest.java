package co.edu.icesi.dev.saamfi.rest;

//package co.edu.icesi.dev.uccareapp.support.rest;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import co.edu.icesi.dev.uccareapp.support.rest.implementations.EnterpriseRestController;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(EnterpriseRestController.class)
//@ContextConfiguration(classes = {RestApplicationTests.class})
//public class EnterpriseControllerTest {
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//
//	@Test
//	public void getAllEnterprise() throws Exception {
//		mockMvc.perform( MockMvcRequestBuilders
//			      .get("/medvedapi/enterprises")
//			      .accept(MediaType.APPLICATION_JSON))
//			      .andDo(print())
//			      .andExpect(status().isOk())
//			      .andExpect(MockMvcResultMatchers.jsonPath("").exists());
//	}
//	
//	
//	public void testA() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders
//				.get("/medvedapi/enterprises/")
//				.accept(MediaType.APPLICATION_JSON))
//	      .andDo(print())
//	      .andExpect(status().isOk());
//
//	}
//}
