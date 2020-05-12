package tn.esprit.spring.control;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.primefaces.model.UploadedFile;
 
@ManagedBean(name = "uploadImage")
@SessionScoped
@Join(path= "/pages/forum/forum.xhtml", to = "/pages/forum/forum.jsf")
public class UploadImage implements Serializable{
	private static final long serialVersionUID = 1L;
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
    public void upload() {
        System.out.println("sssss");
        	if (file !=(null)) {
        		try {
                System.out.println(file.getFileName());
                InputStream fin2 = file.getInputstream();
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pihamza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
                PreparedStatement pre = con.prepareStatement("insert into upload_image (image_name,image) values(?,?)");
                pre.setString(1, file.getFileName().toString());
                pre.setBinaryStream(2, fin2, file.getSize());
                pre.executeUpdate();
                System.out.println("Inserting Successfully!");
                pre.close();
                FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
 
            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        }
        else{
        FacesMessage msg = new FacesMessage("Please select image!!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
