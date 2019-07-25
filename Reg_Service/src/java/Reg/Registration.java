/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reg;

import com.commondb.Common_DB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SuBasH
 */
@WebService(serviceName = "Registration")
public class Registration {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "Register")
  public String Register(@WebParam(name = "user_name") String user_name, @WebParam(name = "password") String password, @WebParam(name = "email") String email, @WebParam(name = "phone_number") String phone_number) {
    try{

     
      
     Class.forName("com.mysql.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","password");
     PreparedStatement ps=con.prepareStatement("Insert into user(username,password,emailid,phone) Values(?,?,?,?)");
    ps.setString(1, user_name);
    ps.setString(2, password);
    ps.setString(3, email);
    ps.setString(4, phone_number);
    ps.executeUpdate();
        }catch(Exception e){
        e.printStackTrace();
        }
        
        
        
        return "success";
        
    } 
   @WebMethod(operationName = "booked")
  public String booked(@WebParam(name = "qrvalue") String qrvalue, @WebParam(name = "username") String username, @WebParam(name = "busno") String busno, @WebParam(name = "from") String from, @WebParam(name = "to") String to, @WebParam(name = "time") String time , @WebParam(name = "cost") String cost) {
   
            long millis=System.currentTimeMillis();  
            java.sql.Date cdate=new java.sql.Date(millis);  
            System.out.println(cdate); 
//            String parts[]=dandt.split(" ");
//            String cdate=parts[0];
//            String ctime=parts[1];
      
      try{
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","password");
        PreparedStatement ps=con.prepareStatement("Insert into booklist(conductorid,username,busnumber,fromst,tost,time,amount,currentdate) Values('"+qrvalue+"','"+username+"','"+busno+"','"+from+"','"+to+"','"+time+"','"+cost+"','"+cdate+"')");
        ps.executeUpdate();
        }
        catch(ClassNotFoundException | SQLException e)
        {
          System.out.println(e);
        }
        
        
        
        return "success";
        
    } 
  
  
  
  @WebMethod(operationName = "BankDetails")
  public String BankDetails(@WebParam(name = "name") String name, @WebParam(name = "accountno") String accountno, @WebParam(name = "cvv") String cvv, @WebParam(name = "expirydate") String expirydate) {
    try{

     
      
     Class.forName("com.mysql.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","password");
     PreparedStatement ps=con.prepareStatement("Insert into bankdetails(name,accountno,cvv,expirydate) Values('"+name+"','"+accountno+"','"+cvv+"','"+expirydate+"')");
    ps.executeUpdate();
        }catch(Exception e){
        e.printStackTrace();
        }
        
        
        
        return "success";
        
    } 
   @WebMethod(operationName = "Login")
  public String Login(@WebParam(name = "user_name") String user_name, @WebParam(name = "password") String password) {
    try{

     
      
     Class.forName("com.mysql.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","password");
      Statement st=con.createStatement();
     ResultSet rs=st.executeQuery("Select * from user where username='"+user_name+"' and password='"+password+"' ");
     if(rs.next())
     {
         return "success";
         
     }
     else{
    return "server error";
     }
     
    
    }
        catch(Exception e){
        e.printStackTrace();
        }
        return "internal server error";
       
  }

@WebMethod(operationName = "TicketAmount")
     public String TicketAmount (@WebParam(name = "from")  String from,@WebParam(name = "to") String to) {
StringBuilder sb = new StringBuilder();
//        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//        Calendar calobj = Calendar.getInstance();
//        String dateandtime=df.format(calobj.getTime());
//        System.out.println(dateandtime);
//        String[] timeparts=dateandtime.split(" ");
//        String date1=timeparts[0];
//        String time1=timeparts[1];
//        System.out.println("Time: "+time1);
   try{
           Common_DB cd=new Common_DB();
           ResultSet rs=Common_DB.LoginCheck("register", "ticket", "fromst", "tost", from, to);
       
//       String query="select * from ticket where fromst='"+from+"' and tost='"+to+"'";
//       Class.forName("com.mysql.jdbc.Driver");
//       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","password");
//       Statement st=con.createStatement();
//       ResultSet rs=st.executeQuery(query);
       while(rs.next()){
          // return "success";
//           String btime=rs.getString("time1");
//           System.out.println("Bus time: "+btime);
//           if(Integer.parseInt(btime) >= Integer.parseInt(time1))
//           {
//               System.out.println(btime);
//           }
           sb.append("Amount: ").append(rs.getString("amount"));
           sb.append("\n"); 
//           sb.append("Time: ").append(rs.getString(rs.getString("time1")));
//           sb.append("#");
       }
       
    }
       catch(Exception ex){
         Logger.getLogger(Registration.class.getName()).log(Level.SEVERE,null,ex);
         return "server temporarily not avilable";
       }
    System.out.println(sb.toString());
        return sb.toString();
     }

     

 @WebMethod(operationName = "getDeviceList1")
     public String getDeviceList1 (@WebParam(name = "from")  String from,@WebParam(name = "to") String to) {
   
     StringBuilder sb = new StringBuilder();
   try{
           Common_DB cd=new Common_DB();
           ResultSet rs=Common_DB.LoginCheck("register", "ticket", "fromst", "tost",from, to);
       while(rs.next())
       {    
          String time=rs.getString("time1");
              String bus=rs.getString("busnumber");
                
              sb.append("Time: ").append(time);
              sb.append("\n"); 
              sb.append("buses: ").append(bus);
              sb.append("@"); 
       }
     
               
    }
       catch(Exception ex){
         Logger.getLogger(Registration.class.getName()).log(Level.SEVERE,null,ex);
         return "server temporarily not avilable";
       }
     System.out.println(sb.toString());
        return sb.toString();
        
     }
     
    @WebMethod(operationName = "Buslist")
    public String Buslist(@WebParam(name = "timee") String timee, @WebParam(name = "bus") String bus) {
        System.out.println(timee);
        String timing=timee.replace("Time: ", "").trim();
        String busnum=bus.replace("buses: ", "").trim();
        System.out.println(bus);
         try {
           Common_DB cd=new Common_DB();
            ResultSet rs=Common_DB.LoginCheck("register", "ticket","busnumber","time1", busnum, timing);
            if(rs.next()) {
                String amount=rs.getString("amount");
                System.out.println(amount);
            return amount;
            }
            else {
                return "Invallid";
            }
        } catch (Exception ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            return "server temporarily not available";
        }       
    }
     
}
 
            
    
    