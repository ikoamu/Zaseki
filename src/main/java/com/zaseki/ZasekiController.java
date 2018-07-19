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
  public List<Member> member(@RequestParam(name = "furigana") String furigana,
      @RequestParam(name = "div", defaultValue = "all") String div) {

    List<Member> qualifiedMemberList = makeQualifiedMemberList(furigana, div);

    return qualifiedMemberList;
  }

  private List<Member> makeMemberList() {
    List<Member> memberList = new ArrayList<Member>();
    Iterable<Member> list = repository.findAll();

    for (Member zaseki : list) {
      memberList.add(zaseki);
    }

    return memberList;
  }

  private List<Member> makeQualifiedMemberList(String furigana, String keyWord) {
    List<Member> memberList = makeMemberList();
    List<Member> qualifiedMemberList = new ArrayList<Member>();

    if (keyWord.equals("all")) {
      for (Member member : memberList) {
        if (member.getFurigana().equals(furigana)) {
          qualifiedMemberList.add(member);
        }
      }
    } else {
      for (Member member : memberList) {
        if (member.getFurigana().equals(furigana) && member.getDivision().equals(Division.from(keyWord).toString())) {
          qualifiedMemberList.add(member);
        }
      }
    }

    return qualifiedMemberList;
  }
}
