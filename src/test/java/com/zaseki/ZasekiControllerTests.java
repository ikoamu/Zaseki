package com.zaseki;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ZasekiControllerTests {
  @Autowired
  private MockMvc mvc;

  private List<com.zaseki.Member> memberList;
  
  @MockBean
  private MemberRepository repository;

  @Test
  public void memberにGETリクエストすると200OKが返される() throws Exception {
    mvc.perform(get("/member")).andExpect(status().isOk());
  }

  @Test
  public void memberにパラメータfuriganaをつけてGETリクエストすると200OKが返される() throws Exception {
    mvc.perform(get("/member").param("furigana", "ふりがな")).andExpect(status().isOk());
  }

  @Test
  public void memberにパラメータdivをつけてGETリクエストすると200OKが返される() throws Exception {
    mvc.perform(get("/member").param("div", "division")).andExpect(status().isOk());
  }

  @Test
  public void memberにfuriganaとdivをつけてGETリクエストすると200OKが返される() throws Exception {
    mvc.perform(get("/member").param("furigana", "ふりがな").param("div", "division")).andExpect(status().isOk());
  }
  
  @Test
  public void 正しく検索できているのか() throws Exception {
    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(get("/member").param("furigana", "ふりがな").param("div", "division")).andExpect(status().isOk());
  }
}
