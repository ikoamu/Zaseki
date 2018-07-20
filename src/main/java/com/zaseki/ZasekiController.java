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
  public List<Member> member(@RequestParam(required = false) String furigana,
      @RequestParam(required = false) String div) {

    List<Member> qualifiedMemberList = makeQualifiedMemberList(furigana, div);

    return qualifiedMemberList;
  }

  private List<Member> makeMemberList() {
    return repository.findAll();
  }

  private List<Member> makeQualifiedMemberList(String furigana, String div) {
    return makeMemberList().stream().filter(m -> checkFurigana(m, furigana)).filter(m -> checkDivision(m, div))
        .collect(Collectors.toList());
  }

  private boolean checkDivision(Member member, String div) {
    return div == null || Division.from(div).name().equals(member.getDivision());
  }

  private boolean checkFurigana(Member member, String furigana) {
    return (furigana == null || furigana.equals(member.getFurigana()));
  }
}
