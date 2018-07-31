package com.zaseki;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZasekiController {

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  DivisionRepository divisionRepository;

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

  @PostMapping(value = "member", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void postMember(@RequestBody Member member) {
    memberRepository.save(member);
  }

  @DeleteMapping(value = "member")
  public void deleteMember(@RequestParam Integer id) {
    memberRepository.deleteById(id);
  }

  @PutMapping(value = "member", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateMember(@RequestParam Integer id, @RequestBody Member member) {
    member.setId(id);
    memberRepository.save(member);
  }

  private List<Member> findAllMembers() {
    return memberRepository.findAll();
  }

  private List<Division> findAllDivisions() {
    return divisionRepository.findAll();
  }

  private List<Member> findQualifiedMembers(String furigana, String div) {
    List<Division> divisionList = findAllDivisions();

    return findAllMembers().stream()
        .filter(m -> furigana == null || m.furiganaIs(furigana))
        .filter(m -> div == null || m.divisionIs(div, divisionList))
        .collect(Collectors.toList());
  }
}
