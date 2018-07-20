package com.zaseki;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @GetMapping(value = "member")
  public List<Member> member(@RequestParam(defaultValue = "all") String furigana,
      @RequestParam(defaultValue = "all") String div) {

    List<Member> qualifiedMemberList = makeQualifiedMemberList(furigana, div);

    return qualifiedMemberList;
  }

  private List<Member> makeMemberList() {
    return repository.findAll();
  }

  private List<Member> makeQualifiedMemberList(String furigana, String div) {
    return makeMemberList().stream().filter(m -> isValidMember(m, furigana, div)).collect(Collectors.toList());
  }

  private boolean isValidMember(Member member, String yomigana, String div) {
    return (yomigana.equals("all") || yomigana.equals(member.getFurigana()))
        && (div.equals("all") || Division.from(div).toString().equals(member.getDivision()));
  }
}
