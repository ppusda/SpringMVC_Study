package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); // 컨트롤러에서 뷰로 이돌할 때 사용, 경로 설정
        dispatcher.forward(request, response); // 다른 JSP나 서블릿으로 이동할 수 있는 기능, 위에서 설정한 경로로 이동
        // .forward()는 리다이렉트가 아니다.
        // 리다이렉트는 웹 브라우저(실제 클라이언트)에 응답이 나갔다가 오기에 클라이언트가 인지할 수 있는 반면, 포워드는 서버 내부의 통신이기에 클라이언트가 인지하지 못한다.

    }
}
