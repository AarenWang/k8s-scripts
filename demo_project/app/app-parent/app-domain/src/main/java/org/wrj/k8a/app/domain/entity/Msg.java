package org.wrj.k8a.app.domain.entity;


import java.sql.Timestamp;

public class Msg {

    private  Integer id;

    private  String msgType;

    private String msgSender;

    private String msgReceiver;

    private  int state;

    private String msgContent;

    private Timestamp readTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }

    public String getMsgReceiver() {
        return msgReceiver;
    }

    public void setMsgReceiver(String msgReceiver) {
        this.msgReceiver = msgReceiver;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Timestamp getReadTime() {
        return readTime;
    }

    public void setReadTime(Timestamp readTime) {
        this.readTime = readTime;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id=" + id +
                ", msgType='" + msgType + '\'' +
                ", msgSender='" + msgSender + '\'' +
                ", msgReceiver='" + msgReceiver + '\'' +
                ", state=" + state +
                ", msgContent='" + msgContent + '\'' +
                ", readTime=" + readTime +
                '}';
    }
}
