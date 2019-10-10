package zhu.xin.guang.service;

import zhu.xin.guang.domain.Desfood;
import zhu.xin.guang.domain.Food;
import zhu.xin.guang.domain.PageBean;

/**
 * 美食Service
 */
public interface FoodService {

    /**
     * 分页查询美食列表
     * @param currentPage
     * @param pageSize
     * @param fname
     * @return
     */
    public PageBean<Food> pageQuery (int currentPage, int pageSize, String fname);

    /**
     * 分页查询点赞排序美食
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Food> lovePageQuery (int currentPage, int pageSize);

    /**
     * 查询美食详细信息
     * @param fid
     * @return
     */
    Desfood desFood(int fid);

    /**
     * 增加点赞次数
     */
    void addCount(int fid);

}
