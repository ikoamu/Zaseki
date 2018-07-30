package com.zaseki;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ZasekiControllerTests {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private MemberRepository repository;

  private ObjectMapper mapper = new ObjectMapper();

  @Test
  public void memberにGETリクエストすると200OKとMemberのリストが返される() throws Exception {
    List<Member> memberList = new ArrayList<Member>();
    Member member = new Member(1, "name", "yomigana", "division", "floor", "extensionNumber");
    memberList.add(member);

    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(get("/member")).andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].id").value(member.getId()))
        .andExpect(jsonPath("$[0].name").value(member.getName()))
        .andExpect(jsonPath("$[0].yomigana").value(member.getYomigana()))
        .andExpect(jsonPath("$[0].division").value(member.getDivision()))
        .andExpect(jsonPath("$[0].floor").value(member.getFloor()))
        .andExpect(jsonPath("$[0].extensionNumber").value(member.getExtensionNumber()));
    
    verify(repository).findAll();
  }

  @Test
  public void memberにパラメータyomiganaをつけてGETリクエストすると該当するMemberのリストと200OKが返される() throws Exception {
    List<Member> memberList = new ArrayList<Member>();
    Member member1 = new Member(1, "name1", "yomigana1", "division1", "floor1", "extensionNumber1"); //検索で該当しないメンバ
    Member member2 = new Member(2, "name2", "yomigana2", "division2", "floor2", "extensionNumber2"); //該当するメンバ
    memberList.add(member1);
    memberList.add(member2);
    
    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(get("/member").param("yomigana", "yomigana2")).andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$",hasSize(1)))
        .andExpect(jsonPath("$[0].id").value(member2.getId()))
        .andExpect(jsonPath("$[0].name").value(member2.getName()))
        .andExpect(jsonPath("$[0].yomigana").value(member2.getYomigana()))
        .andExpect(jsonPath("$[0].division").value(member2.getDivision()))
        .andExpect(jsonPath("$[0].floor").value(member2.getFloor()))
        .andExpect(jsonPath("$[0].extensionNumber").value(member2.getExtensionNumber()));
    
    verify(repository).findAll();
  }

  @Test
  public void memberにパラメータdivをつけてGETリクエストすると該当するMemberのリストと200OKが返される() throws Exception {
    List<Member> memberList = new ArrayList<Member>();
    Member member1 = new Member(1, "name1", "yomigana1", "ETEC", "floor1", "extensionNumber1"); //検索で該当しないメンバ
    Member member2 = new Member(2, "name2", "yomigana2", "ITS", "floor2", "extensionNumber2"); //該当するメンバ
    memberList.add(member1);
    memberList.add(member2);
    
    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(get("/member").param("div", "its")).andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$",hasSize(1)))
        .andExpect(jsonPath("$[0].id").value(member2.getId()))
        .andExpect(jsonPath("$[0].name").value(member2.getName()))
        .andExpect(jsonPath("$[0].yomigana").value(member2.getYomigana()))
        .andExpect(jsonPath("$[0].division").value(member2.getDivision()))
        .andExpect(jsonPath("$[0].floor").value(member2.getFloor()))
        .andExpect(jsonPath("$[0].extensionNumber").value(member2.getExtensionNumber()));
    
    verify(repository).findAll();
  }

  @Test
  public void memberにyomiganaとdivをつけてGETリクエストすると該当するMemberのリストと200OKが返される() throws Exception {
    List<Member> memberList = new ArrayList<Member>();
    Member member1 = new Member(1, "name1", "yomigana1", "ETEC", "floor1", "extensionNumber1"); //検索で該当しないメンバ
    Member member2 = new Member(2, "name2", "yomigana1", "ITS", "floor2", "extensionNumber2"); //該当するメンバ
    Member member3 = new Member(1, "name1", "yomigana2", "ITS", "floor3", "extensionNumber3"); //該当しないメンバ
    memberList.add(member1);
    memberList.add(member2);
    memberList.add(member3);
    
    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(get("/member").param("yomigana", "yomigana1").param("div", "its"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$",hasSize(1)))
        .andExpect(jsonPath("$[0].id").value(member2.getId()))
        .andExpect(jsonPath("$[0].name").value(member2.getName()))
        .andExpect(jsonPath("$[0].yomigana").value(member2.getYomigana()))
        .andExpect(jsonPath("$[0].division").value(member2.getDivision()))
        .andExpect(jsonPath("$[0].floor").value(member2.getFloor()))
        .andExpect(jsonPath("$[0].extensionNumber").value(member2.getExtensionNumber()));
    
    verify(repository).findAll();
  }

  @Test
  public void memberにidをつけてDELETEリクエストすると200OKが返される() throws Exception {
    doNothing().when(repository).deleteById(1);
    mvc.perform(delete("/member").param("id", "1"))
        .andExpect(status().isOk());
    
    verify(repository, times(1)).deleteById(1);
  }
  
  @Test
  public void POSTリクエストすると200OKが返される() throws Exception {   
    Member member = new Member(1, "name", "yomigana", "division", "floor", "extensionNumber");
    
    when(repository.save(member)).thenReturn(member);
    mvc.perform(post("/member").contentType(MediaType.APPLICATION_JSON)
       .content(mapper.writeValueAsString(member)))
       .andExpect(status().isOk());

    verify(repository).save(member);
  }
}
