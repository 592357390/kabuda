package kabuda.kabuda.com.kabuda.bean;

import java.io.Serializable;

/**
 * Created by YuanGang on 2018/6/1.
 */

public class JobBean implements Serializable {

    /**
     * job_address :
     * wage_jz : 100元/天
     * job_requirement : 1、在顾客需要的时候要及时的引导顾客消费，介绍产品，以便顾客更好的消费；2、认真听取顾客信息，并将其记录，及时反馈给公司；3、维持展台的清洁
     * job_name : 促销员
     * recruit_num : 若干
     * job_descr : 责任心强、吃苦耐劳者优先
     * id : 137
     * job_contact :
     */

    private String job_address;
    private String wage_jz;
    private String job_requirement;
    private String job_name;
    private String recruit_num;
    private String job_descr;
    private int id;
    private String job_contact;

    public String getJob_address() {
        return job_address;
    }

    public void setJob_address(String job_address) {
        this.job_address = job_address;
    }

    public String getWage_jz() {
        return wage_jz;
    }

    public void setWage_jz(String wage_jz) {
        this.wage_jz = wage_jz;
    }

    public String getJob_requirement() {
        return job_requirement;
    }

    public void setJob_requirement(String job_requirement) {
        this.job_requirement = job_requirement;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getRecruit_num() {
        return recruit_num;
    }

    public void setRecruit_num(String recruit_num) {
        this.recruit_num = recruit_num;
    }

    public String getJob_descr() {
        return job_descr;
    }

    public void setJob_descr(String job_descr) {
        this.job_descr = job_descr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob_contact() {
        return job_contact;
    }

    public void setJob_contact(String job_contact) {
        this.job_contact = job_contact;
    }
}
