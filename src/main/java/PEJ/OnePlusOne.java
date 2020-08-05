package PEJ;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="OnePlusOne_table")
public class OnePlusOne {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String prdId;
    private String prdAttrCd;

    @PostPersist
    public void onPostPersist(){
        SetOnePlused setOnePlused = new SetOnePlused();
        BeanUtils.copyProperties(this, setOnePlused);
        setOnePlused.publishAfterCommit();


    }

    @PreUpdate
    public void onPreUpdate(){

        System.out.println("one_onPreUpdate_1");

        CancelOneplused cancelOneplused = new CancelOneplused();
        BeanUtils.copyProperties(this, cancelOneplused);
        cancelOneplused.setPrdAttrCd("CANCELLED");
        cancelOneplused.publishAfterCommit();

        System.out.println("one_onPreUpdate_2");

        PEJ.external.Product product = new PEJ.external.Product();
        product.setPrdId(cancelOneplused.getPrdId());
        product.setPrdAttrCd("CANCELLED");

        OnePlusOneApplication.applicationContext.getBean(PEJ.external.ProductService.class)
            .changePrdAttrCd(product);

        System.out.println("one_onPreUpdate_3");

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }
    public String getPrdAttrCd() {
        return prdAttrCd;
    }

    public void setPrdAttrCd(String prdAttrCd) {
        this.prdAttrCd = prdAttrCd;
    }




}
