package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@Component + @RequestMapping
//@RequestMapping + @Bean 등록으로 해도 됨
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}

/**
 * @Controller
 * 컴포넌트 스캔의 대상이 되며(스프링 빈으로 등록됨) 스프링에서 어노테이션 기반 컨트롤러로 인식한다.
 * 거의 사용되는 방식, 매우 깔끔함 ㅎㅎ
 *
 * @RequestMapping
 * 요청 정보를 매핑하며 해당하는 URL이 호출되면 이 메서드가 호출된다.
 *
 * ModelAndView 모델과 뷰 정보를 담아서 반환하면된다.
 */