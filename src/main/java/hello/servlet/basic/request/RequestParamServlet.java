package hello.servlet.basic.request;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    //요청에 대한 파라미터 정보들 조회
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - Start");

        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "= "+ req.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] - End");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = req.getParameter("username");
        // 파라미터 값이 여러개일 때 getParameter() 를 사용하면 가장 첫번째 값만을 받아온다.
        String age = req.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = req.getParameterValues("username");
        // username에 해당하는 파라미터 값들을 모두 읽어온다.
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        resp.getWriter().write("ok");
    }
    // req.getParameter()는 GET 방식이든 POST 방식이든 쿼리 파라미터를 가져올 수 있다.
    // 받아올 때의 형식이 같기 때문에 같은 메서드를 이용해도 괜찮다!

    // 참고로 HTML form 데이터도 메시지 바디로 조회가 가능한데,
    // 편리한 파라미터 조회 기능이 있으므로 이를 이용하도록 하자.
}
