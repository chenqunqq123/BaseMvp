package mvp.ancely.com.myapplication.bean;

import java.util.List;

public class RecommentInfoBean {



    private StateBean state;
    private List<BodyBean> body;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class StateBean {

        private int errCode;
        private String errMessage;
        private String errInfo;
        private long timestamp;

        public int getErrCode() {
            return errCode;
        }

        public void setErrCode(int errCode) {
            this.errCode = errCode;
        }

        public String getErrMessage() {
            return errMessage;
        }

        public void setErrMessage(String errMessage) {
            this.errMessage = errMessage;
        }

        public String getErrInfo() {
            return errInfo;
        }

        public void setErrInfo(String errInfo) {
            this.errInfo = errInfo;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class BodyBean {

        private String user_telphone;
        private long effective_time;
        private long first_visit_time;
        private long last_visit_time;
        private long recommend_id;

        public long getRecommend_id() {
            return recommend_id;
        }

        public void setRecommend_id(long recommend_id) {
            this.recommend_id = recommend_id;
        }

        public long getFirst_visit_time() {
            return first_visit_time;
        }

        public void setFirst_visit_time(long first_visit_time) {
            this.first_visit_time = first_visit_time;
        }

        public long getLast_visit_time() {
            return last_visit_time;
        }

        public void setLast_visit_time(long last_visit_time) {
            this.last_visit_time = last_visit_time;
        }

        private String status_name;
        private String recommend_telphone;

        public String getRecommend_telphone() {
            return recommend_telphone;
        }

        public void setRecommend_telphone(String recommend_telphone) {
            this.recommend_telphone = recommend_telphone;
        }

        public String getRecommend_nick_name() {
            return recommend_nick_name;
        }

        public void setRecommend_nick_name(String recommend_nick_name) {
            this.recommend_nick_name = recommend_nick_name;
        }

        private String recommend_nick_name;
        private String recommend_real_name;

        public String getRecommend_real_name() {
            return recommend_real_name;
        }

        public void setRecommend_real_name(String recommend_real_name) {
            this.recommend_real_name = recommend_real_name;
        }

        private long user_register_time;
        private String user_nick_name;
        private String consultant_name;
        private long consultant_create_time;
        private String consultant_telphone;
        private int user_id;
        private String user_real_name;
        private String estate_name;
        private int recommend_identity;
        private long recommend_create_time;
        private int server_source;
        private String status;
        private String estate_id;

        public String getUser_telphone() {
            return user_telphone;
        }

        public void setUser_telphone(String user_telphone) {
            this.user_telphone = user_telphone;
        }

        public long getEffective_time() {
            return effective_time;
        }

        public void setEffective_time(long effective_time) {
            this.effective_time = effective_time;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public long getUser_register_time() {
            return user_register_time;
        }

        public void setUser_register_time(long user_register_time) {
            this.user_register_time = user_register_time;
        }

        public String getUser_nick_name() {
            return user_nick_name;
        }

        public void setUser_nick_name(String user_nick_name) {
            this.user_nick_name = user_nick_name;
        }

        public String getConsultant_name() {
            return consultant_name;
        }

        public void setConsultant_name(String consultant_name) {
            this.consultant_name = consultant_name;
        }

        public long getConsultant_create_time() {
            return consultant_create_time;
        }

        public void setConsultant_create_time(long consultant_create_time) {
            this.consultant_create_time = consultant_create_time;
        }

        public String getConsultant_telphone() {
            return consultant_telphone;
        }

        public void setConsultant_telphone(String consultant_telphone) {
            this.consultant_telphone = consultant_telphone;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_real_name() {
            return user_real_name;
        }

        public void setUser_real_name(String user_real_name) {
            this.user_real_name = user_real_name;
        }

        public String getEstate_name() {
            return estate_name;
        }

        public void setEstate_name(String estate_name) {
            this.estate_name = estate_name;
        }

        public int getRecommend_identity() {
            return recommend_identity;
        }

        public void setRecommend_identity(int recommend_identity) {
            this.recommend_identity = recommend_identity;
        }

        public long getRecommend_create_time() {
            return recommend_create_time;
        }

        public void setRecommend_create_time(long recommend_create_time) {
            this.recommend_create_time = recommend_create_time;
        }

        public int getServer_source() {
            return server_source;
        }

        public void setServer_source(int server_source) {
            this.server_source = server_source;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEstate_id() {
            return estate_id;
        }

        public void setEstate_id(String estate_id) {
            this.estate_id = estate_id;
        }
    }
}