package zhu.xin.guang.dao;

import zhu.xin.guang.domain.Desfood;
import zhu.xin.guang.domain.Food;

import java.util.List;

public interface FoodDao {

    /**
     * 查询美食总个数
     * @param fname
     * @return
     */
    public int findTotalCount (String fname);

    /**
     * 查询美食集合
     * @param start
     * @param pageSize
     * @param fname
     * @return
     */
    public List<Food> findByPage (int start, int pageSize, String fname);

    /**
     * 查询点赞排行美食集合
     * @param start
     * @param pageSize
     * @return
     */
    public List<Food> loveFindByPage (int start, int pageSize);

    /**
     * 根据美食id查询详细信息
     * @param fid
     * @return
     */
    Desfood findDesfoodById(int fid);

    /**
     * 根据Id查询美食的信息
     * @param fid
     * @return
     */
    Food findFoodById(int fid);

    /**
     * 增加点赞次数
     */
    void addCount(int fid);
}
