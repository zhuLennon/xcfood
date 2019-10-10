package zhu.xin.guang.web.servlet;

import zhu.xin.guang.domain.Desfood;
import zhu.xin.guang.service.FoodService;
import zhu.xin.guang.service.impl.FoodServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCountServlet")
public class AddCountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String fidStr = request.getParameter("fid");

        //处理接收的参数
        int fid = Integer.parseInt(fidStr);

        FoodService service = new FoodServiceImpl();
        service.addCount(fid);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
