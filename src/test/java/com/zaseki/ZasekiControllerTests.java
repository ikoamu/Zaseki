package com.zaseki;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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

  @MockBean
  private MemberRepository repository;

  @Test
  public void memberにGETリクエストすると200OKとMemberのリストが返される() throws Exception {
    List<Member> memberList = new ArrayList<Member>();
    Member member = new Member(1, "name", "furigana", "division", "floor", "extensionNumber");
    memberList.add(member);

    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(get("/member")).andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].id").value(member.getId()))
        .andExpect(jsonPath("$[0].name").value(member.getName()))
        .andExpect(jsonPath("$[0].furigana").value(member.getFurigana()))
        .andExpect(jsonPath("$[0].division").value(member.getDivision()))
        .andExpect(jsonPath("$[0].floor").value(member.getFloor()))
        .andExpect(jsonPath("$[0].extensionNumber").value(member.getExtensionNumber()));
  }

  @Test
  public void memberにパラメータfuriganaをつけてGETリクエストすると該当するMemberのリストと200OKが返される() throws Exception {
    List<Member> memberList = new ArrayList<Member>();
    Member member1 = new Member(1, "name1", "furigana1", "division1", "floor1", "extensionNumber1"); //検索で該当しないメンバ
    Member member2 = new Member(2, "name2", "furigana2", "division2", "floor2", "extensionNumber2"); //該当するメンバ
    memberList.add(member1);
    memberList.add(member2);
    
    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(get("/member").param("furigana", "furigana2")).andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$",hasSize(1)))
        .andExpect(jsonPath("$[0].id").value(member2.getId()))
        .andExpect(jsonPath("$[0].name").value(member2.getName()))
        .andExpect(jsonPath("$[0].furigana").value(member2.getFurigana()))
        .andExpect(jsonPath("$[0].division").value(member2.getDivision()))
        .andExpect(jsonPath("$[0].floor").value(member2.getFloor()))
        .andExpect(jsonPath("$[0].extensionNumber").value(member2.getExtensionNumber()));
  }

  @Test
  public void memberにパラメータdivをつけてGETリクエストすると該当するMemberのリストと200OKが返される() throws Exception {
    List<Member> memberList = new ArrayList<Member>();
    Member member1 = new Member(1, "name1", "furigana1", "ETEC", "floor1", "extensionNumber1"); //検索で該当しないメンバ
    Member member2 = new Member(2, "name2", "furigana2", "ITS", "floor2", "extensionNumber2"); //該当するメンバ
    memberList.add(member1);
    memberList.add(member2);
    
    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(get("/member").param("div", "its")).andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$",hasSize(1)))
        .andExpect(jsonPath("$[0].id").value(member2.getId()))
        .andExpect(jsonPath("$[0].name").value(member2.getName()))
        .andExpect(jsonPath("$[0].furigana").value(member2.getFurigana()))
        .andExpect(jsonPath("$[0].division").value(member2.getDivision()))
        .andExpect(jsonPath("$[0].floor").value(member2.getFloor()))
        .andExpect(jsonPath("$[0].extensionNumber").value(member2.getExtensionNumber()));
  }

  @Test
  public void memberにfuriganaとdivをつけてGETリクエストすると該当するMemberのリストと200OKが返される() throws Exception {
    List<Member> memberList = new ArrayList<Member>();
    Member member1 = new Member(1, "name1", "furigana1", "ETEC", "floor1", "extensionNumber1"); //検索で該当しないメンバ
    Member member2 = new Member(2, "name2", "furigana1", "ITS", "floor2", "extensionNumber2"); //該当するメンバ
    Member member3 = new Member(1, "name1", "furigana2", "ITS", "floor3", "extensionNumber3"); //該当しないメンバ
    memberList.add(member1);
    memberList.add(member2);
    memberList.add(member3);
    
    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(get("/member").param("furigana", "furigana1").param("div", "its"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$",hasSize(1)))
        .andExpect(jsonPath("$[0].id").value(member2.getId()))
        .andExpect(jsonPath("$[0].name").value(member2.getName()))
        .andExpect(jsonPath("$[0].furigana").value(member2.getFurigana()))
        .andExpect(jsonPath("$[0].division").value(member2.getDivision()))
        .andExpect(jsonPath("$[0].floor").value(member2.getFloor()))
        .andExpect(jsonPath("$[0].extensionNumber").value(member2.getExtensionNumber()));
  }

  @Test
  public void memberにidをつけてDELETEリクエストすると200OKが返される() throws Exception {
    List<Member> memberList = new ArrayList<Member>();
    Member member = new Member(1, "name", "furigana", "division", "floor", "extensionNumber");
    memberList.add(member);

    when(repository.findAll()).thenReturn(memberList);
    mvc.perform(delete("/member").param("id", "1"))
        .andExpect(status().isOk());
  }
}
