package kabuda.kabuda.com.kabuda.bean;

import java.util.List;

/**
 * Created by YuanGang on 2018/6/1.
 */

public class ShowBean {

    private List<ModuleBean> module;
    private List<BannerBean> banner;

    public List<ModuleBean> getModule() {
        return module;
    }

    public void setModule(List<ModuleBean> module) {
        this.module = module;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class ModuleBean {
        /**
         * module_detail : 兼职工作列表
         * id : 4
         * module_name : 找工作
         * module_url : /module/getJob
         */

        private String module_detail;
        private int id;
        private String module_name;
        private String module_url;

        public String getModule_detail() {
            return module_detail;
        }

        public void setModule_detail(String module_detail) {
            this.module_detail = module_detail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getModule_name() {
            return module_name;
        }

        public void setModule_name(String module_name) {
            this.module_name = module_name;
        }

        public String getModule_url() {
            return module_url;
        }

        public void setModule_url(String module_url) {
            this.module_url = module_url;
        }
    }

    public static class BannerBean {
        /**
         * banner_url : https://dollmachine.oss-cn-hangzhou.aliyuncs.com/283f8870-9e03-4268-bc05-4150c50f78eb.jpg
         * id : 1
         */

        private String banner_url;
        private int id;

        public String getBanner_url() {
            return banner_url;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
