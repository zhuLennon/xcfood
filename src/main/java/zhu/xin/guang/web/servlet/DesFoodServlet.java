package zhu.xin.guang.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import zhu.xin.guang.domain.Desfood;
import zhu.xin.guang.service.FoodService;
import zhu.xin.guang.service.impl.FoodServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//美食详细
@WebServlet("/desFoodServlet")
public class DesFoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String fidStr = request.getParameter("fid");

        //处理接收的参数
        int fid = Integer.parseInt(fidStr);

        FoodService service = new FoodServiceImpl();
        Desfood desfood = service.desFood(fid);

        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(desfood);

        //将json数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
