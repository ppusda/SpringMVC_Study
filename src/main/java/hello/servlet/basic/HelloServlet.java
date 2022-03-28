package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // 서블릿 설정, 이름과 url 패턴이 겹쳐서는 안된다.
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); // 파라미터를 받아올 수 있는 메서드이다. ex_) ?username = GG_BB
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("Hello " + username + "!"); // 응답 메시지 보내기

        // 참고로 응답 시에 Content-Length 와 같은 부가정보는 웹 애플리케이션에서 자동으로 처리해준다!
    }
}
