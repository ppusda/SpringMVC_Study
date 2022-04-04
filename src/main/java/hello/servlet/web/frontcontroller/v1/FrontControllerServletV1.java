package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI(); // 요청이 들어온 URI를 받아옴

        ControllerV1 controller = controllerMap.get(requestURI);
        // 위 HashMap에서 키 값(URI)를 통해서 매핑해둔 값을 가져온다.
        // ControllerV1 controller = new MemberListControllerV1(); 사실상 이것과 같음

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
            // 요청이 비어있다면 404를 통해서 응답을 보내주게 된다.
        }

        controller.process(request, response);
        // 위에서 다형성에 의해 가져와진 클래스를 통해서 process() 메서드를 실행하게되는 것이다.
        // ex_) MemberListControllerV1.process()
    }
}

/**
 * FrontController 구현
 * urlpattern을 보면 알 수 있다시피, /front-controller/v1/* 을 이용한다.
 * v1 하위의 모든 요청을 이 곳에서 받을 수 있게된다.
 *
 * 도입 단계 자체는 생각보다 복잡하다.
 * 한 번에 이해되지는 않았지만 인터페이스를 구현하는 각 컨트롤러를 만든다.
 * 그리고 그 인터페이스를 상속받아 FrontController 또한 만들게된다.
 *
 * 큰 구조 개선 및 리팩터링의 조언
 * 구조적 먼저 개선을 한 후 세밀한 것을 조정하는 것이 좋다.
 */