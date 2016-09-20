package pack1;
import java.io.*;

public class Firma
{
   public Firma()
   {
       //this.age = 20;
       //this.firma = "OboKrav";
       //this.dolgnost = "программист";
   }
   public final String BLUE = "#227AFF";

   private String firma = null;
   private String dolgnost = null;
   private String chart = "";
   private String education = "";
   private String extra = "";
   private String sex = null;
   private int salary ;
   private int age = 0;
   private String []dolgnosti = {"программист","дизайнер","инженер"};
   private String []firms = {"kture","google","OboKrav"};
   private boolean isOk = false;

   public String testDolgnost()
   {
       for (int i = 0; i < dolgnosti.length; i++)
       {
           if(dolgnosti[i].equals(dolgnost))
               return "#227AFF";
       }
       return "#f00";
   }
   public String testFirms()
   {
       for (int i = 0; i < firms.length; i++)
       {
           if(firms[i].equals(firma))
               return "#227AFF";
       }
       return "#f00";
   }
   public String testAge()
   {
       if(age > 18 && age < 55)
               return "#227AFF";
       return "#f00";
   }
   public String saveFile(String f)
   {
       try
       {
           if(testAge().equals("#227AFF") && testFirms().equals("#227AFF") && testDolgnost().equals("#227AFF") )
           {
              DataOutputStream myfile = new DataOutputStream( new FileOutputStream(f));
              myfile.writeUTF("Фирма: " + this.firma + "\r\n");
              myfile.writeUTF("Должность: " + this.dolgnost + "\n");
              myfile.writeUTF("Оклад: " + this.salary + "\n");
              myfile.writeUTF("График работы: " + this.chart + "\n");
              myfile.writeUTF("Возраст: " + this.age + "\n");
              myfile.writeUTF("Пол: " + this.sex + "\n");
              myfile.writeUTF("Образование: " + this.education + "\n");
              myfile.writeUTF("Дополнительно: " + this.extra + "\n");
              isOk = true;
              return "Файл успешно сохранен";
           }
           else { isOk = false; throw new Exception("Не заполнено (или не корректно заполено) обязательное поле!");}
       }
       catch(Exception e)
       {
        return "Файл не сохранен - " + e.getMessage();
       }
   }

   public String getFirma() {
       return this.firma;
   }
   public void setFirma(String firma) {
       this.firma = firma;
   }
   public String getDolgnost() {
       return this.dolgnost;
   }
   public void setDolgnost(String dolgnost) {
       this.dolgnost = dolgnost;
   }
   public String getChart() {
       return this.chart;
   }
   public void setChart(String chart) {
       this.chart = chart;
   }
   public String getEducation() {
       return this.education;
   }
   public void setEducation(String education) {
       this.education = education;
   }
   public String getExtra() {
       return this.extra;
   }
   public void setExtra(String extra) {
       this.extra = extra;
   }
   public String getSex() {
       return this.sex;
   }
   public void setSex(String sex) {
       this.sex = sex;
   }
   public int getSalary() {
       return this.salary;
   }
   public void setSalary(int  salary) {
       this.salary = salary;
   }
   public int getAge() {
       return this.age;
   }
   public void setAge(int  age) {
       this.age = age;
   }
   //public void setIsOK(boolean ok){ this.isOk = ok;}
   public boolean getIsOk(){
     return isOk;
   }
}
