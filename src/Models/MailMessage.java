/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Mijael
 */
public class MailMessage {

    static MailMessage format(String message) {
        String[] m=message.split(" ");
        int index=Integer.valueOf(m[0]);
        int id=Integer.valueOf(m[1]);
        return new MailMessage(index, id);
    }
    private int index;
    private int id;
    private byte status;

    public MailMessage(int index, int id) {
        this.index = index;
        this.id = id;
        status=0;
    }

    public MailMessage() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Index: "+index+" Id: "+id;
    }
    
}
