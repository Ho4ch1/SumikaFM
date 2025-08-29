package jp.te4a.app2.facility2.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="facilities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityBean {
    @Id
    @GeneratedValue
    private Integer id;             //管理番号
    @Column(nullable=false)         //NOTNULL制約指定
    private String product;         //品名
    private String modelNumber;     //型番
    private String manufacturer;    //メーカー
    private String specification;   //仕様
    private Integer serviceLife;    //耐用年数
    private String purchaseDate;   //購入日
    private Boolean depreciation;   //減価償却
    private Boolean notAvailable;   //使用不可
    private Boolean rentAble;       //貸出可能
    private String location;        //設置場所
    private String expiry;         //使用期限
}
