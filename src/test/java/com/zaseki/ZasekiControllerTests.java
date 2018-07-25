package com.zaseki;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZasekiControllerTests {
  private MockMvc mvc;

  @InjectMocks
  private ZasekiController zasekiController;

  @Mock
  private MemberRepository repository;

  @Before
  public void setUp() {
    mvc = MockMvcBuilders.standaloneSetup(this.zasekiController).build();
  }

  @Test
  public void test() throws Exception {
    mvc.perform(get("/member")).andExpect(status().isOk());
  }
}
