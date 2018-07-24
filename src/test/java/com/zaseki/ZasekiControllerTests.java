package com.zaseki;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class ZasekiControllerTests {
  @Autowired
  private WebApplicationContext wac;

  @InjectMocks
  private ZasekiController zasekiController;

  @Mock
  private MemberRepository repository;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    repository.findAll();
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void test() throws Exception {
    mockMvc.perform(get("/member").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }
}
