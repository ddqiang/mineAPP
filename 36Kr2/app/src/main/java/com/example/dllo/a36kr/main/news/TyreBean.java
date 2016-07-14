package com.example.dllo.a36kr.main.news;

import java.util.List;

/**
 * Created by dllo on 16/6/22.
 *轮播图数据
 */
public class TyreBean {

    /**
     * code : 0
     * data : {"pics":[{"action":"web","imgUrl":"https://krplus-pic.b0.upaiyun.com/201606/20/9a41f2330c52378a67d37b4f51c4a03b.png","location":"http://h5.eqxiu.com/s/YdEL32kA","title":"极速融资7"},{"action":"web","imgUrl":"https://krplus-pic.b0.upaiyun.com/201606/15/4b6eeb01c229b08406e8419ae56c8e59.jpg","location":"https://z.36kr.com/promotion/anniversary?ktm_source=36kr&ktm_campaign=yizhounian&ktm_medium=h5&ktm_term=appbanner","title":"股权投资一周年"},{"action":"web","imgUrl":"https://krplus-pic.b0.upaiyun.com/201606/08/791222c4f0053bb9b8e7bbdd9f801cb9.jpg","location":"https://huodong.36kr.com/h5/star/rank.html?ktm_source=appbanner","title":"创业星生代排行"},{"action":"web","imgUrl":"https://krplus-pic.b0.upaiyun.com/201606/15/588eb20724f7a5ca341bd5756ec91729.jpg","location":"http://chuang.36kr.com/huodong#/activityApply/details/353","title":"广州路演"}]}
     * msg : 操作成功！
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * action : web
         * imgUrl : https://krplus-pic.b0.upaiyun.com/201606/20/9a41f2330c52378a67d37b4f51c4a03b.png
         * location : http://h5.eqxiu.com/s/YdEL32kA
         * title : 极速融资7
         */

        private List<PicsBean> pics;

        public List<PicsBean> getPics() {
            return pics;
        }

        public void setPics(List<PicsBean> pics) {
            this.pics = pics;
        }

        public static class PicsBean {
            private String action;
            private String imgUrl;
            private String location;
            private String title;

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}

