package com.demo.springJMS;

import java.io.Serializable;

public class MessageInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int batchCode;
    private int clientMemCode;
    private String clientCode = "";
    private String classCode = "";

    public MessageInfo(){}
    public MessageInfo(int batchCode) {
        this.batchCode = batchCode;
    }
    
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public int getClientMemCode() {
		return clientMemCode;
	}
	public void setClientMemCode(int clientMemCode) {
		this.clientMemCode = clientMemCode;
	}
	public int getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(int batchCode) {
		this.batchCode = batchCode;
	}
	public String toString() {
        return "Message: batchCode(" + batchCode + ")" + "clientMemCode(" + clientMemCode + ")";
    }

}
