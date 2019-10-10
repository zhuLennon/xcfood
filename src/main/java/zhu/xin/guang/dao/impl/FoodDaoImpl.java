package zhu.xin.guang.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import zhu.xin.guang.dao.FoodDao;
import zhu.xin.guang.domain.Desfood;
import zhu.xin.guang.domain.Food;
import zhu.xin.guang.util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //参数中rname和cid不一定都存在，因此要用动态sql
    //查询总数量
    @Override
    public int findTotalCount(String fname) {
        //String sql = "select count(*) from food where fname like ?";
        //定义sql模板
        String sql = "select count(*) from food where 1=1 ";
        //定义StringBuilder来添加sql字符串
        StringBuilder sb = new StringBuilder(sql);
        //定义集合添加条件们，即fanme
        List params = new ArrayList();
        //判断参数是否有值
        if(fname != null && fname.length() > 0 && !"null".equals(fname)){
            sb.append(" and fname like ? ");
            params.add("%"+fname+"%");
        }
        //将StringBuilder转化为String
        sql = sb.toString();

        Integer count = template.queryForObject(sql,Integer.class,params.toArray());
        return count;
    }

    //无序条件查询
    @Override
    public List<Food> findByPage(int start, int pageSize, String fname) {
        //String sql = "select * from food where fname like ? limit ?,?";
        //定义sql模板
        String sql = " select * from food where 1 = 1 ";
        //定义StringBuilder来添加sql字符串
        StringBuilder sb = new StringBuilder(sql);
        //定义集合添加条件们，即fanme
        List params = new ArrayList();

        //判断参数是否有值
        if(fname != null && fname.length() > 0 && !"null".equals(fname)){
            sb.append(" and fname like ? ");
            params.add("%"+fname+"%");
        }

        //添加分页条件，这两个传进来有数据，不用判断
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);

        //将StringBuilder转化为String
        sql = sb.toString();

        List<Food> list = template.query(sql,new BeanPropertyRowMapper<Food>(Food.class),params.toArray());
        return list;
    }

    //排序查询美食
    @Override
    public List<Food> loveFindByPage(int start, int pageSize) {
        String sql = "select * from food order by count DESC limit ? , ? ";
        List<Food> list = template.query(sql,new BeanPropertyRowMapper<Food>(Food.class),start,pageSize);
        return list;
    }

    //根据id查询美食详细信息
    @Override
    public Desfood findDesfoodById(int fid) {
        String sql = "select * from des_food where fid = ? ";
        Desfood desfood = template.queryForObject(sql, new BeanPropertyRowMapper<Desfood>(Desfood.class), fid);
        return desfood;
    }

    //根据id查询美食
    @Override
    public Food findFoodById(int fid) {
        String sql = "select * from food where fid = ? ";
        Food food = template.queryForObject(sql, new BeanPropertyRowMapper<Food>(Food.class), fid);
        return food;
    }

    //增加点赞次数
    @Override
    public void addCount(int fid) {
        String sql = "update food set count = count+1 where fid = ?";
        template.update(sql,fid);
    }
}
