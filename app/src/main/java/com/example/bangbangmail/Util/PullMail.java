package com.example.bangbangmail.Util;

import android.util.Log;

import com.example.bangbangmail.Model.Mail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class PullMail {
    private static final String TAG = "PullMail";
    static ArrayList<Mail> mailList = new ArrayList<>();
    static String server = "120.77.168.57";// POP3服务器地址
    private Socket socket = null;

    private boolean debug = true;

    public static synchronized ArrayList<Mail>  pullMail() throws UnknownHostException, IOException {
        mailList.clear();

        String user = FishMailApplication.getMail();// 用户名
        String password = FishMailApplication.getPwd();// 密码

        Log.d(TAG, "pullMail: 用户名和密码" + user + "-" + password);

        PullMail pop3Client = new PullMail(server, 110);

        pop3Client.recieveMail(user, password);

        Log.d(TAG, "pullMail: " + mailList.size());
//        System.out.println(mailList.size());

        return mailList;
    }

    public static synchronized boolean  DeleteMail(int mailNo) throws UnknownHostException, IOException {

        String user = FishMailApplication.getMail();// 用户名
        String password = FishMailApplication.getPwd();// 密码

        Log.d(TAG, "pullMail: 用户名和密码" + user + "-" + password);

        PullMail pop3Client = new PullMail(server, 110);

        return pop3Client.deleteMail(user, password,mailNo);
    }


//    public static void main(String[] args) throws  IOException{
//        ArrayList<Mail> mailList = PullMail.pullMail();
//        System.out.println(mailList.size());
//    }


    /* 构造函数 */
    public PullMail(String server, int port) throws UnknownHostException, IOException {
        try {

            socket = new Socket(server, port);// 在新建socket的时候就已经与服务器建立了连接

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            System.out.println("建立连接！");
        }
    }


    //删除邮件
    public boolean deleteMail(String user, String password, int mailNumber) {

        int mailcount = 0;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            user(user, in, out);// 输入用户名

            System.out.println("user 命令执行完毕！");

            pass(password, in, out);// 输入密码

            System.out.println("pass 命令执行完毕！");

            dele(mailNumber,in,out);

            quit(in, out);

            System.out.println("quit 命令执行完毕！");

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
        return true;
    }

    // 接收邮件程序
    public boolean recieveMail(String user, String password) {

        int mailcount = 0;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            user(user, in, out);// 输入用户名

            System.out.println("user 命令执行完毕！");

            pass(password, in, out);// 输入密码

            System.out.println("pass 命令执行完毕！");

            mailcount = stat(in, out);

            System.out.println("stat 命令执行完毕！");

            list(in, out);

            System.out.println("list 命令执行完毕！");

            for (int i = mailcount; i > 0; i--) {
                retr(i, in, out);
            }
            System.out.println(mailList.size());
            System.out.println("retr 命令执行完毕！");

            quit(in, out);

            System.out.println("quit 命令执行完毕！");

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
        return true;
    }

    // 得到服务器返回的一行命令
    public String getReturn(BufferedReader in) {

        String line = "";

        try {
            line = in.readLine();

            if (debug) {

                System.out.println("服务器返回状态:" + line);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return line;
    }

    // 从返回的命令中得到第一个字段,也就是服务器的返回状态码(+OK或者-ERR)
    public String getResult(String line) {

        StringTokenizer st = new StringTokenizer(line, " ");

        return st.nextToken();
    }

    // 发送命令
    private String sendServer(String str, BufferedReader in, BufferedWriter out) throws IOException {

        out.write(str);// 发送命令

        out.newLine();// 发送空行

        out.flush();// 清空缓冲区

        if (debug) {

            System.out.println("已发送命令:" + str);
        }
        return getReturn(in);
    }

    // user命令

    public void user(String user, BufferedReader in, BufferedWriter out) throws IOException {

        String result = null;

        result = getResult(getReturn(in));// 先检测连接服务器是否已经成功

        if (!"+OK".equals(result)) {

            throw new IOException("连接服务器失败!");
        }

        result = getResult(sendServer("user " + user, in, out));// 发送user命令

        if (!"+OK".equals(result)) {

            throw new IOException("用户名错误!");
        }
    }

    // pass命令
    public void pass(String password, BufferedReader in, BufferedWriter out) throws IOException {

        String result = null;

        result = getResult(sendServer("pass " + password, in, out));

        if (!"+OK".equals(result)) {

            throw new IOException("密码错误!");
        }
    }

    // stat命令

    public int stat(BufferedReader in, BufferedWriter out) throws IOException {

        String result = null;

        String line = null;

        int mailNum = 0;

        line = sendServer("stat", in, out);

        StringTokenizer st = new StringTokenizer(line, " ");

        result = st.nextToken();

        if (st.hasMoreTokens())

            mailNum = Integer.parseInt(st.nextToken());

        else {

            mailNum = 0;

        }

        if (!"+OK".equals(result)) {

            throw new IOException("查看邮箱状态出错!");
        }

        System.out.println("共有邮件" + mailNum + "封");
        return mailNum;
    }

    // 无参数list命令
    public void list(BufferedReader in, BufferedWriter out) throws IOException {

        String message = "";

        String line = null;

        line = sendServer("list", in, out);

        while (!".".equalsIgnoreCase(line)) {

            message = message + line + "\n";

            line = in.readLine().toString();
        }
        System.out.println(message);
    }

    // 带参数list命令
    public void dele(int mailNumber, BufferedReader in, BufferedWriter out) throws IOException {

        String result = null;

        result = getResult(sendServer("dele " + mailNumber, in, out));

        if (!"+OK".equals(result)) {

            throw new IOException("dele 错误!");
        }
    }

    // 得到邮件详细信息

    public String getMessagedetail(BufferedReader in) throws UnsupportedEncodingException {

        String message = "";
        String from = "";
        String to = "";
        String subject = "";
        String content = "";
        String messageID = "";
        String date1 = "";
        String line = null;
        String argu = null;
        String comm;
        int spaceIndex;
        int lastIndex;
        try {
            // String
            // 另外一种写法
            while (true) {
                line = in.readLine().toString();
                argu = line;
                if (line.equals("")) {
                    message = message + line + "\n";
                    while (!(line = in.readLine().toString()).equalsIgnoreCase(".")) {
                        line = new String(Base64.decode(line));
                        content = content + line + "\n";
                        message = message + line + "\n";
                    }
                    System.out.println(content);
                    break;
                } else {
                    spaceIndex = argu.indexOf(":");
                    if (spaceIndex > 0) {
                        comm = argu.substring(0, spaceIndex);
                        argu = argu.substring(spaceIndex + 1);
                        if (comm.equalsIgnoreCase("Subject")) {
                            argu = argu.substring(1);
                            argu = new String(Base64.decode(argu));
                            subject = argu;
                            System.out.println(subject);
                            line = "Subject: " + argu;
                        } else if (comm.equalsIgnoreCase("From")) {
                            argu = argu.substring(1);
                            from = argu;
                            System.out.println(from);
                        } else if (comm.equalsIgnoreCase("To")) {
                            argu = argu.substring(1);
                            to = argu;
                            System.out.println(to);
                        } else if (comm.equalsIgnoreCase("Message-ID")) {
                            argu = argu.substring(1);
                            messageID = argu;
                        } else if (comm.equalsIgnoreCase("Date")) {
                            argu = argu.substring(1);
                            date1 = argu;
                            date1 = date1.substring(0, date1.indexOf("+") - 1);
                            SimpleDateFormat sdf1 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.ENGLISH);

                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            date1 = sdf2.format(sdf1.parse(date1));
                            System.out.println(date1);
                        }
                    }
                }
                message = message + line + "\n";
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        Mail mail = new Mail();
        mail.setMailID(messageID);
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(content);
        mail.setDate(date1);
        mailList.add(mail);
        return message;

    }

    // retr命令
    public void retr(int mailNum, BufferedReader in, BufferedWriter out) throws IOException, InterruptedException {

        String result = null;

        result = getResult(sendServer("data " + mailNum, in, out));

        if (!"+OK".equals(result)) {

            throw new IOException("接收邮件出错!");
        }

        System.out.println("第" + mailNum + "封");
        System.out.println(getMessagedetail(in));
        // Thread.sleep(1000);
    }

    // 退出
    public void quit(BufferedReader in, BufferedWriter out) throws IOException {

        String result;

        result = getResult(sendServer("QUIT", in, out));

        if (!"+OK".equals(result)) {

            throw new IOException("未能正确退出");
        }
    }

}
