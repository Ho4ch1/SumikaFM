package jp.te4a.app2.facility2.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.app2.facility2.bean.FacilityBean;
import jp.te4a.app2.facility2.form.FacilityForm;
import jp.te4a.app2.facility2.repository.FacilityRepository;

@Service
public class FacilityService {
    
    //課題６参照
    @Autowired
    FacilityRepository facilityRepository;

    /*            課題８で削除 */
    /*
    //保存用メソッド
    public FacilityBean save(FacilityBean facilityBean) {
        return facilityRepository.save(facilityBean);
    }

    //全件取得用メソッド
    public List<FacilityBean> findAll() {
        return facilityRepository.findAll();
    }
    */

    
    //追加処理　データはFacilityFormで扱い、Repoを使う時はFacilityBeanに入れて渡す
    public FacilityForm create(FacilityForm facilityForm) {
        //facilityForm.setId(facilityRepository.getFacilityId());
        FacilityBean facilityBean = new FacilityBean();
        BeanUtils.copyProperties(facilityForm, facilityBean);
        facilityRepository.save(facilityBean);
        return facilityForm;
    }

    public FacilityForm save(FacilityForm facilityForm) {
        FacilityBean facilityBean = new FacilityBean();
        BeanUtils.copyProperties(facilityForm, facilityBean);
        facilityRepository.save(facilityBean);
        return facilityForm;
       }
       

    //更新処理
    public FacilityForm update(FacilityForm facilityForm) {
        FacilityBean facilityBean = new FacilityBean();
        BeanUtils.copyProperties(facilityForm, facilityBean);
        facilityRepository.save(facilityBean);
        return facilityForm;
    }

    //削除処理
    public void delete(Integer id) { 
        facilityRepository.deleteById(id); 
    }

    //全件取得処理
    public List<FacilityForm> findAll() {
        List<FacilityBean> beanList = facilityRepository.findAll();
        List<FacilityForm> formList = new ArrayList<>();
        for (FacilityBean facilityBean : beanList) {
            FacilityForm facilityForm = new FacilityForm();
            BeanUtils.copyProperties(facilityBean, facilityForm);
            facilityForm.setDepreciation(isDepreciated(facilityBean));
            formList.add(facilityForm);
        }
        return formList;
    }

    //１件取得処理
    public FacilityForm findOne(Integer id) {
        Optional<FacilityBean> opt = facilityRepository.findById(id);
        FacilityBean facility = opt.orElseThrow();
        FacilityForm facilityForm = new FacilityForm();
        BeanUtils.copyProperties(facility, facilityForm);
        facilityForm.setDepreciation(isDepreciated(facility));
        return facilityForm;
    }

     public List<FacilityBean> search(Integer id, String name, String manufacturer, Integer serviceLife, String location) {
        List<FacilityBean> result = facilityRepository.findAll(); // 全件取得からフィルター
    
        return result.stream()
            .filter(bean -> id == null || bean.getId().equals(id))
            .filter(bean -> name == null || bean.getProduct().contains(name))
            .filter(bean -> manufacturer == null || bean.getManufacturer().contains(manufacturer))
            .filter(bean -> serviceLife == null || bean.getServiceLife().equals(serviceLife))
            .filter(bean -> location == null || bean.getLocation().contains(location))
            .toList();
    }

    public List<Integer> getAllIds() {
        return facilityRepository.findAllIds();
    }

    public List<String> getAllProducts() {
        return facilityRepository.findAllProducts();
    }

    public List<String> getAllManufacturers() {
        return facilityRepository.findAllManufacturers();
    }

    public List<String> getAllLocations() {
        return facilityRepository.findAllLocations();
    }

    public List<Integer> getAllServiceLifes() {
        return facilityRepository.findAllServiceLifes();
    }

    public boolean isDepreciated(FacilityBean facility) {
        if (facility.getPurchaseDate() == null || facility.getServiceLife() == null) {
            return true;
        }
    
        LocalDate purchase = LocalDate.parse(
            facility.getPurchaseDate(),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );
        LocalDate expiry = purchase.plusYears(facility.getServiceLife());
    
        return !LocalDate.now().isAfter(expiry);
    }
    
    

}
