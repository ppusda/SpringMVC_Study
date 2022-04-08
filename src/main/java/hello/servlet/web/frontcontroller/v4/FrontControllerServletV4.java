package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    } // 생성자에서 각 URI에 따른 컨트롤러를 설정해준다.

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI(); // 요청 URI를 얻어옴

        ControllerV4 controller = controllerMap.get(requestURI); // URI에 따른 컨트롤러를 얻어옴
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request); // 파라미터 정보가 매핑되어있는 Map을 만든다.
        Map<String, Object> model = new HashMap<>(); // 모델 정보가 들어있는 Map
        String viewName = controller.process(paramMap, model); // 얻어온 컨트롤러에 파라미터 정보와 모델 정보를 넣는다.
        // Form
        //  - return 값으로 어떤 view(JSP)가야하는지를 반환해준다.
        //  - new-form
        // Save
        // - paramMap을 통해 파라미터 정보를 가져오고, Member 저장소에 저장한 후 model에 담는다.
        // - return 값으로 어떤 view(JSP)가야하는지를 반환해준다.
        // - save-result
        // List
        // - 저장소에 있는 멤버 값들을 모두 가져온 후 model에 담는다.
        // - return 값으로 어떤 view(JSP)가야하는지를 반환해준다.
        // - members

        MyView view = viewResolver(viewName); // view의 path를 정확히 설정한다.

        view.render(model, request, response); // 모델 정보(파라미터를 모델에 저장하는 것들)을 MyView에 보내 렌더링을 할 수 있게 한다.
        // 실제로 안에 들어가서 JSP에서 값을 사용할 수 있도록 우리가 model에 넣었던 값들을 꺼내 request.setAttribute를 통해 값을 설정해주게된다.
        // 그리고 dispatcher 로 다른 서블릿 혹은 JSP로 이동하기 위해 위에서 정확히 설정 viewpath를 넣어준다.
        // 그 경로로 이동하면서 요청 파라미터들과 응답 객체를 가져간다.
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

/**
 * 이전 버전과 다른 점은 모델을 파라미터로 넘기고,
 * 뷰의 논리 이름을 반환한다는 작은 아이디어를 적용했을 뿐인데 더욱 실용적으로 사용할 수 있게 되었다.
 *
 * 여기까지 배운 후 뒤에 내용들을 살펴보면
 * V1은 요청을 모두 front에서 받을 수 있게 설정하였으며 forward()까지 controller가 담당했었던 걸 볼 수 있다.
 * V2는 컨트롤러 안에서 setAttiribute 까지 하여 MyView를 통해서는 forward() 기능을 분리하여 각 Controller의 부담을 덜 수 있었다.
 * V3는 모델을 통해 파라미터 처리 또한 MyView에게 떠넘기고 viewPath를 front에서 설정하면서 각 Controller의 부담을 덜 수 있었다.
 * 지금은 그 모델을 더 쉽게 받아오고 처리 할 수 있게 파라미터로 넘기고 받으며 return 값으로는 viewName을 설정해 해당 view(=JSP)로 이동할 수 있게 설정하였다. == 실용성 증가
 *
 * 확실히 가면 갈수록 기능 분배가 더 확실히 되는 느낌이 들며,
 * 어떤면에서는 복잡하지만 되게 간단한 프로그램이 만들어지고 있다.
 */