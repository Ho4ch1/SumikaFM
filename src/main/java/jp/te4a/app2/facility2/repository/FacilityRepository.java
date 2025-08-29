package jp.te4a.app2.facility2.repository;

import java.util.List;

/*import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.BeanUtils;*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.te4a.app2.facility2.bean.FacilityBean;

//    RepositoryはDB寄りとしてFacilityBeanでデータを扱う
//    Serviceは画面寄りとしてFacilityFormでデータを扱う

@Repository
public interface FacilityRepository extends JpaRepository<FacilityBean,Integer> {

    @Query("SELECT X FROM FacilityBean X ORDER BY X.product")
    List<FacilityBean> findAllOrderByTitle();
}
/*public class FacilityRepository {

    private final ConcurrentMap<Integer,FacilityBean> facilityMap = new ConcurrentHashMap<>();
    //課題８追加
    //id発行
    private int FACILITY_ID = 1;
    public int getFacilityId() {
        return FACILITY_ID++;
    }

    //DBの時はメソッドをSQLの実行に変える
    
    //作成
    public FacilityBean save(FacilityBean facilityBean) {
        return facilityMap.put(facilityBean.getId(),facilityBean);
    }

    //更新
    public FacilityBean update(FacilityBean updateFacilityBean) {
        FacilityBean facilityBean = facilityMap.get(updateFacilityBean.getId());
        BeanUtils.copyProperties(updateFacilityBean,facilityBean);
        return facilityBean;
    }

    //削除
    public void delete(Integer facilityId) {
        facilityMap.remove(facilityId);
    }

    //全件取得
    public List<FacilityBean> findAll() {
        return new ArrayList<>(facilityMap.values());
    }

    //１件取得
    public FacilityBean findOne(Integer id) {
        return facilityMap.get(id);
    }

} 

*/