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
    List<Member> memberList = findAllMembers();

    return memberList;
  }

  @GetMapping(value = "member")
  public List<Member> member(@RequestParam(required = false) String furigana,
      @RequestParam(required = false) String div) {

    List<Member> qualifiedMemberList = findQualifiedMembers(furigana, div);

    return qualifiedMemberList;
  }

  private List<Member> findAllMembers() {
    return repository.findAll();
  }

  private List<Member> findQualifiedMembers(String furigana, String div) {
    return findAllMembers().stream()
        .filter(m -> furigana == null || m.furiganaIs(furigana))
        .filter(m -> div == null || m.divisionIs(div))
        .collect(Collectors.toList());
  }
}
