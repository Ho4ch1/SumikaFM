package jp.te4a.app2.facility2.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/*
    FacilityBeanはDBと連動
    FacilityFormは画面と連動
 */
public class FacilityForm {
    private Integer id;             //管理番号
    @NotBlank
    private String product;         //品名
    @NotBlank
    private String modelNumber;     //型番
    @NotBlank
    private String manufacturer;    //メーカー
    @NotBlank
    private String specification;   //仕様
    @NotNull
    @Min(0)
    private Integer serviceLife;    //耐用年数
    @NotBlank
    private String purchaseDate;   //購入日
    private Boolean depreciation;   //減価償却
    private Boolean notAvailable;   //使用不可
    private Boolean rentAble;       //貸出可能
    @NotBlank
    private String location;        //設置場所
    @NotBlank
    private String expiry;         //使用期限
}
