package zhu.xin.guang.service.impl;

import zhu.xin.guang.dao.FoodDao;
import zhu.xin.guang.dao.impl.FoodDaoImpl;
import zhu.xin.guang.domain.Desfood;
import zhu.xin.guang.domain.Food;
import zhu.xin.guang.domain.PageBean;
import zhu.xin.guang.service.FoodService;

import java.util.List;

public class FoodServiceImpl implements FoodService {
    private FoodDao foodDao = new FoodDaoImpl();

    //无序查询美食集合
    @Override
    public PageBean<Food> pageQuery(int currentPage, int pageSize, String fname) {
        //创建PageBean对象
        PageBean<Food> pb = new PageBean<>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = foodDao.findTotalCount(fname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;  //开始的记录数
        List<Food> list = foodDao.findByPage(start,pageSize,fname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    //有序查询美食集合
    @Override
    public PageBean<Food> lovePageQuery(int currentPage, int pageSize) {
        //创建PageBean对象
        PageBean<Food> pb = new PageBean<>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = foodDao.findTotalCount(null);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;  //开始的记录数
        List<Food> list = foodDao.loveFindByPage(start,pageSize);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    //美食的详细信息
    @Override
    public Desfood desFood(int fid) {
        Desfood desfood = foodDao.findDesfoodById(fid);
        Food food = foodDao.findFoodById(fid);
        desfood.setFood(food);
        return desfood;
    }

    //增加美食的点赞次数
    @Override
    public void addCount(int fid) {
        foodDao.addCount(fid);
    }
}
