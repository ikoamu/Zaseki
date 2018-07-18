package com.zaseki;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZasekiController {

  @Autowired
  MemberRepository repository;

  @RequestMapping("/")
  @ResponseBody
  public List<Member> home() {
    List<Member> memberList = makeMemberList();

    return memberList;
  }

  @RequestMapping("/{furigana}")
  public List<Member> index(@PathVariable String furigana) {
    List<Member> memberList = makeMemberList();
    List<Member> qualifiedMember = new ArrayList<Member>();

    for (Member member : memberList) {
      if (member.getFurigana().equals(furigana)) {
        qualifiedMember.add(member);
      }
    }

    return qualifiedMember;
  }

  @RequestMapping("/{furigana}/{keyWord}")
  public List<Member> index(@PathVariable String furigana, @PathVariable String keyWord) {
    List<Member> memberList = makeMemberList();
    List<Member> qualifiedMember = new ArrayList<Member>();

    for (Member member : memberList) {
      if (member.getFurigana().equals(furigana) && member.getDivision().equals(Division.from(keyWord).toString())) {
        qualifiedMember.add(member);
      }
    }

    return qualifiedMember;
  }

  private List<Member> makeMemberList() {
    List<Member> memberList = new ArrayList<Member>();
    Iterable<Member> list = repository.findAll();

    for (Member zaseki : list) {
      memberList.add(zaseki);
    }

    return memberList;
  }
}
