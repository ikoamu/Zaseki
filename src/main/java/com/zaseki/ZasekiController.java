package com.zaseki;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

  @RequestMapping(value = "/member", method = RequestMethod.GET)
  public List<Member> member(@RequestParam(defaultValue = "all") String furigana,
      @RequestParam(defaultValue = "all") String div) {

    List<Member> qualifiedMemberList = makeQualifiedMemberList(furigana, div);

    return qualifiedMemberList;
  }

  private List<Member> makeMemberList() {
    return repository.findAll();
  }

  private List<Member> makeQualifiedMemberList(String furigana, String div) {
    List<Member> memberList = makeMemberList();
    List<Member> qualifiedMemberList = new ArrayList<Member>();

    for (Member member : memberList) {
      if (isValidMember(member, furigana, div)) {
        qualifiedMemberList.add(member);
      }
    }

    return qualifiedMemberList;
  }

  private boolean isValidMember(Member member, String yomigana, String div) {
    if (yomigana.equals("all") || yomigana.equals(member.getFurigana())) {
      if (div.equals("all") || Division.from(div).toString().equals(member.getDivision())) {
        return true;
      }
    }

    return false;
  }
}
