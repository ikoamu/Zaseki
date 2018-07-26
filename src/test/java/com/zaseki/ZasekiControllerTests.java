package com.zaseki;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
  
  @MockBean
  private MemberRepository repository;
  
  @Before
  public void setup() {
    List<Member> memberList = new ArrayList<Member>();
    Member member = new Member(1,"name","furigana","division","floor","extensionNumber");
    memberList.add(member);
  }
  
  @Test
  public void memberにGETリクエストすると200OKとMemberのリストが返される() throws Exception {
    List<Member> memberList = new ArrayList<Member>();
    Member member = new Member(1,"name","furigana","division","floor","extensionNumber");
    memberList.add(member);
    
    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(get("/member"))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$").isArray())
    .andExpect(jsonPath("$[0].id").value(member.getId()))
    .andExpect(jsonPath("$[0].name").value(member.getName()))
    .andExpect(jsonPath("$[0].furigana").value(member.getFurigana()))
    .andExpect(jsonPath("$[0].division").value(member.getDivision()))
    .andExpect(jsonPath("$[0].floor").value(member.getFloor()))
    .andExpect(jsonPath("$[0].extensionNumber").value(member.getExtensionNumber()));    
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
}
