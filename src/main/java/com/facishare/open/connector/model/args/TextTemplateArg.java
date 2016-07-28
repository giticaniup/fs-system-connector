package com.facishare.open.connector.model.args;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.List;

/**
 * 复合消息类型参数
 * Created by zhongcy on 2016/7/4.
 */
public class TextTemplateArg extends BaseArg {
    private static final long serialVersionUID = -3268555221507512409L;

    private List<String> toUser;

    private String msgType;

    private TextTemplate textTemplate;

    public List<String> getToUser() {
        return toUser;
    }

    public void setToUser(List<String> toUser) {
        this.toUser = toUser;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public TextTemplate getTextTemplate() {
        return textTemplate;
    }

    public void setTextTemplate(TextTemplate textTemplate) {
        this.textTemplate = textTemplate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("toUser", toUser)
                .add("msgType", msgType)
                .add("textTemplate", textTemplate)
                .toString();
    }

    public class TextTemplate {
        /**
         * 标题部分
         */
        private ContentWarp title;

        /**
         * first 部分
         */
        private ContentWarp first;

        /**
         * remark 部分
         */
        private ContentWarp remark;

        /**
         * keyword 部分最多5个
         */
        private List<LabelWarp> infos;

        /**
         * 按钮部分
         */
        private ButtonWarp button;

        /**
         * 媒体数据部分，最多5个
         */
        private List<MediaWarp> mediaList;

        public ContentWarp getTitle() {
            return title;
        }

        public void setTitle(ContentWarp title) {
            this.title = title;
        }

        public ContentWarp getFirst() {
            return first;
        }

        public void setFirst(ContentWarp first) {
            this.first = first;
        }

        public ContentWarp getRemark() {
            return remark;
        }

        public void setRemark(ContentWarp remark) {
            this.remark = remark;
        }

        public List<LabelWarp> getInfos() {
            return infos;
        }

        public void setInfos(List<LabelWarp> infos) {
            this.infos = infos;
        }

        public ButtonWarp getButton() {
            return button;
        }

        public void setButton(ButtonWarp button) {
            this.button = button;
        }

        public List<MediaWarp> getMediaList() {
            return mediaList;
        }

        public void setMediaList(List<MediaWarp> mediaList) {
            this.mediaList = mediaList;
        }
    }

    public class ContentWarp implements Serializable {

        private static final long serialVersionUID = -4346675802440424119L;

        private String content;

        private String color;

        private String time;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public class LabelWarp implements Serializable {

        private static final long serialVersionUID = -7338845634577837626L;

        private String label;

        private String color;

        private String value;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public class ButtonWarp implements Serializable {

        private static final long serialVersionUID = -5595157211188196349L;

        private String title;

        private String color;

        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    private class MediaWarp implements Serializable {

        private static final long serialVersionUID = 8455534673238058952L;

        private String type;

        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
