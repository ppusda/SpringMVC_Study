package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    //@RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

    /**
     *
     * @RequestParam
     * 파라미터를 해당 어노테이션을 통해서 얻어올 수 있다..!
     * 이를 통해 한결 더 깔끔해진 모델을 반환 할 수 있게 되었다.
     *
     *
     * 지금까지는 method를 신경쓰지 않았지만, 이를 꼭 지정해주는게 좋다.
     * ex_) 단순 조회는 GET, 내용 저장 및 변경은 POST 와 같이 지정해두는 것이 좋다.
     *
     * @RequestMapping의 Method속성을 사용해도 되지만,
     * @GetMapping 혹은 @PostMapping을 사용하는 것도 더 깔끔하다.
     *
     */
}