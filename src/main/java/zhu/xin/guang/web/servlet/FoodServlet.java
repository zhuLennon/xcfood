package zhu.xin.guang.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import zhu.xin.guang.domain.Food;
import zhu.xin.guang.domain.PageBean;
import zhu.xin.guang.service.FoodService;
import zhu.xin.guang.service.impl.FoodServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//全部美食
@WebServlet("/foodServlet")
public class FoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String currentPageStr = request.getParameter("currentPage");
        String fname = request.getParameter("fname");

        //处理接收的参数
        int currentPage = 0;  //当前页码，如果不传递，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        int pageSize = 6;   //每页显示条数，默认显示6条记录

        FoodService service = new FoodServiceImpl();
        PageBean<Food> pageBean = service.pageQuery(currentPage, pageSize, fname);

        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pageBean);

        //将json数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
