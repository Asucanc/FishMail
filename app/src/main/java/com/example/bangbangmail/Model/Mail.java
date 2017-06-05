package com.example.bangbangmail.Model;

/**
 * Created by Kenshin on 2017/5/20.
 */

public class Mail {
    //邮件ID
    private String mailID;
    //发送方
    private String from;
    //接收方
    private String to;
    //发送时间
    private String date;
    //主题
    private String subject;
    //附件
    private String attahment;
    //是否已读，0未读，1已读
    private boolean flagread;
    //处于哪个文件
    private String folder;
    //抄送
    private String wcc;
    //密送
    private String bcc;
    //内容正文
    private String text;


    public Mail(String mailID, String from, String to, String date, String subject,
                String attahment, boolean flagread, String folder, String wcc,
                String bcc, String text) {
        this.mailID = mailID;
        this.from = from;
        this.to = to;
        this.date = date;
        this.subject = subject;
        this.attahment = attahment;
        this.flagread = flagread;
        this.folder = folder;
        this.wcc = wcc;
        this.bcc = bcc;
        this.text = text;
    }



    public Mail() {
    }

    public Mail(String mailid, String from, String to, String date, String subject, String content) {
        this.mailID = mailid;
        this.from = from;
        this.to = to;
        this.date = date;
        this.subject = subject;
        this.text = content;
    }

    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAttahment(String attahment) {
        this.attahment = attahment;
    }

    public void setFlagread(boolean flagread) {
        this.flagread = flagread;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setWcc(String wcc) {
        this.wcc = wcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMailID() {
        return mailID;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getAttahment() {
        return attahment;
    }

    public boolean isFlagread() {
        return flagread;
    }

    public String getFolder() {
        return folder;
    }

    public String getWcc() {
        return wcc;
    }

    public String getBcc() {
        return bcc;
    }

    public String getText() {
        return text;
    }
}
