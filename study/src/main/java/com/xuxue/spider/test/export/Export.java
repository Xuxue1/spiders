package com.xuxue.spider.test.export;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by HanHan on 2016/6/29.
 */
public class Export {


    public static void main(String[] args)throws Exception{




        Connection conn=getConnection();
        Statement statement=null;
        ResultSet set=null;

        PrintWriter p=new PrintWriter("E:\\tt.txt");

       try{
           HashMap<String,Iteam> data=new HashMap<>();

           String sql="select v_studentID,v_studentname," +
                   "v_studepartment,v_stuclass,v_stuNameLogo,v_coursename," +
                   "v_weekday,v_isabsence,d_date from log_manager where v_stuNameLogo='吕效红_五_03_04_北图301'";
           statement=conn.createStatement();
           set=statement.executeQuery(sql);
           while(set.next()){
               Student s=new Student();
               s.setV_studentID(set.getString("v_studentID"));
               s.setV_coursename(set.getString("V_coursename"));
               s.setV_studentname(set.getString("v_studentname"));
               s.setV_studepartment(set.getString(("v_studepartment")));
               s.setV_stuclass(set.getString("v_stuclass"));
               s.setV_stuNameLogo(set.getString("v_stuNameLogo"));
               s.setV_coursename(set.getString("v_coursename"));
               s.setV_weekday(set.getString("v_weekday"));
               s.setV_isabsence(set.getString("v_isabsence"));
               s.setDate(set.getDate("d_date"));
               Iteam i=data.get(s.getV_studentID());
               //p.println(s);
               if(i==null) i=new Iteam();
               i.add(s);
               data.put(s.getV_studentID(),i);
           }
           System.out.println("*****************************************");
           Set<Map.Entry<String,Iteam>> s=data.entrySet();
           System.out.println(s.size());
           for(Map.Entry<String,Iteam> m:s){
               p.println(m.getValue());
           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {

           set.close();
           statement.close();
           System.out.println("OK");
           conn.close();
           p.flush();
           p.close();
       }

    }

    public static Connection getConnection()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://192.168.4.6:3306/hsukqxt","root","lbmysql");

    }


    public static class Iteam{



       ArrayList<Student> students=new ArrayList<Student>();



        private double chuqin=0;

        private double noChuqin=0;

        private double other=0;

        public Iteam(){

        }

        public int getChuqin(){
            return (int)chuqin;
        }

        public int getNoCHuqin(){
            return (int)noChuqin;
        }

        public double getRate(){
            return chuqin/(chuqin+noChuqin);
        }

        public void add(Student stu){
            students.add(stu);

            if(stu.getV_isabsence().equals("出勤")){
                chuqin+=1;
            }else if(stu.getV_isabsence().equals("缺勤")){
                noChuqin+=1;
            }else{
                other+=1;
            }
        }

        public String toString(){
            StringBuilder b=new StringBuilder();
            System.out.println(students.size());
            Collections.sort(students);
            b.append(students.get(0).getV_studentID()+" "+students.get(0).getV_studentname()
                    +" "+students.get(0).getV_studepartment()+" "+students.get(0).getV_stuclass()
                    +" "+students.get(0).getV_stuNameLogo()+" "+students.get(0).getV_coursename());
            b.append(" "+this.getChuqin()+" "+getNoCHuqin()+" "+getRate()+" ");
            for(Student stu:students){

                b.append(stu.getV_isabsence()+" ");

            }
            return b.toString();
        }

    }

    public static class Student implements  Comparable<Student>{

        public String toString(){
            return v_studentID+" "+v_studentname+" "+v_studepartment+" "+v_stuclass
                    +" "+v_stuNameLogo+" "+v_coursename+" "+v_weekday+" "+v_isabsence;
        }

        private String v_studentID;

        private String v_studentname;

        private String v_studepartment;

        private String v_stuclass;

        private String v_stuNameLogo;

        private String v_coursename;

        private String v_weekday;

        private String v_isabsence;

        private Date date;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof Student)) return false;
            Student s=(Student)o;
            if(s.getV_studentID().equals(this.getV_studentID())) return true;
            return false;
        }

        @Override
        public int hashCode(){
            return this.getV_studentID().hashCode();
        }


        public String getV_studentID() {
            return v_studentID;
        }

        public void setV_studentID(String v_studentID) {
            this.v_studentID = v_studentID;
        }

        public String getV_studentname() {
            return v_studentname;
        }

        public void setV_studentname(String v_studentname) {
            this.v_studentname = v_studentname;
        }

        public String getV_studepartment() {
            return v_studepartment;
        }

        public void setV_studepartment(String v_studepartment) {
            this.v_studepartment = v_studepartment;
        }

        public String getV_stuclass() {
            return v_stuclass;
        }

        public void setV_stuclass(String v_stuclass) {
            this.v_stuclass = v_stuclass;
        }

        public String getV_stuNameLogo() {
            return v_stuNameLogo;
        }

        public void setV_stuNameLogo(String v_stuNameLogo) {
            this.v_stuNameLogo = v_stuNameLogo;
        }

        public String getV_coursename() {
            return v_coursename;
        }

        public void setV_coursename(String v_coursename) {
            this.v_coursename = v_coursename;
        }

        public String getV_weekday() {
            return v_weekday;
        }

        public void setV_weekday(String v_weekday) {
            this.v_weekday = v_weekday;
        }

        public String getV_isabsence() {
            return v_isabsence;
        }

        public void setV_isabsence(String v_isabsence) {
            this.v_isabsence = v_isabsence;
        }

        @Override
        public int compareTo(Student o) {
            int a=(int)(this.getDate().getTime()-o.getDate().getTime());
            return a;
        }
    }

}
