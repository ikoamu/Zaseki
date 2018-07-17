package com.zaseki;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZasekiController {

  @Autowired
  memberRepository repository;

  @RequestMapping("/")
  @ResponseBody
  public ArrayList<Member> home() {
    ArrayList<Member> memberList = makeMemberList();

    return memberList;
  }

  @RequestMapping("/{furigana}")
  public ArrayList<Member> index(@PathVariable String furigana) {
    ArrayList<Member> memberList = makeMemberList();
    ArrayList<Member> qualifiedMember = new ArrayList<Member>();

    for (Member member : memberList) {
      if (member.getFurigana().equals(furigana)) {
        qualifiedMember.add(member);
      }
    }

    return qualifiedMember;
  }

  @RequestMapping("/{furigana}/{keyWord}")
  public ArrayList<Member> index(@PathVariable String furigana, @PathVariable String keyWord) {
    ArrayList<Member> memberList = makeMemberList();
    ArrayList<Member> qualifiedMember = new ArrayList<Member>();

    for (Member member : memberList) {
      if (member.getFurigana().equals(furigana) && member.getDivision().equals(Division.from(keyWord).toString())) {
        qualifiedMember.add(member);
      }
    }

    return qualifiedMember;
  }

  private ArrayList<Member> makeMemberList() {
    ArrayList<Member> memberList = new ArrayList<Member>();
    Iterable<Member> list = repository.findAll();

    for (Member zaseki : list) {
      memberList.add(zaseki);
    }

    return memberList;
  }
}
