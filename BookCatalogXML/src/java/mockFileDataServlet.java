/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ADMIN
 */
public class mockFileDataServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
        
              String[][] bNames=new String[50][50];
              String[][] bGenres=new String[50][50];
              String[][] bPrice=new String[50][50];
             Integer noOfAuthors=Integer.parseInt(request.getParameter("noOfAuthors"));
                  String authorNames[]=new String[noOfAuthors];
                  Integer noOfBooks[]=new Integer[50];
                  //out.println(noOfAuthors);
              int count=1;
                  
              for(int k=0;k<noOfAuthors;k++){      
               authorNames[k]=request.getParameter("authorName"+count);
               noOfBooks[k]=Integer.parseInt(request.getParameter("noOfBooks"+count));
                  count++;
              }
              
              for(int i=0;i<noOfAuthors;i++){
                  int num1=i+1;
              for(int j=0;j<noOfBooks[i];j++)
              {
                  int num2=j+1;
                  bNames[i][j]=request.getParameter("a"+num1+"bName"+num2);
                  bGenres[i][j]=request.getParameter("a"+num1+"bGenre"+num2);
                  bPrice[i][j]=request.getParameter("a"+num1+"bPrice"+num2);
                 
              }}
              
              Date date = new Date();
             String currentDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

           DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
 
            Element Catalog = document.createElement("Catalog");
 
            document.appendChild(Catalog);
 

            Attr attr = document.createAttribute("xmlns:xsi");
            attr.setValue("http://www.w3.org/2001/XMLSchema-instance");
            Catalog.setAttributeNode(attr);
          for(int i=0;i<noOfAuthors;i++){
            Element Authors = document.createElement("Authors");
            Catalog.appendChild(Authors);
            for(int j=0;j<noOfBooks[i];j++){

            Element Books = document.createElement("Books");
            Authors.appendChild(Books);
            
            Element Name = document.createElement("Name");
            Name.appendChild(document.createTextNode(bNames[i][j]));
            Books.appendChild(Name);

            Element Genre = document.createElement("Genre");
            Genre.appendChild(document.createTextNode(bGenres[i][j]));
            Books.appendChild(Genre);
            
            Element Price = document.createElement("Price");
            Price.appendChild(document.createTextNode(bPrice[i][j]));
            Books.appendChild(Price);
            }

            Element IDType = document.createElement("IDType");
            IDType.appendChild(document.createTextNode("AUTHOR_NAME"));
            Authors.appendChild(IDType);
            
            Element IDValue = document.createElement("IDValue");
            IDValue.appendChild(document.createTextNode(authorNames[i]));
            Authors.appendChild(IDValue);
          }
            String xmlFilePath="G:/ravina/BookCatalogXML-"+date.getTime()+".xml";
            String xmlFilePathSubstring=xmlFilePath.substring(10);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(xmlFilePath);
            transformer.transform(domSource, streamResult);
 
            
        JSch jsch = new JSch();
        Session session = null;
        try {
          session = jsch.getSession("desktop-p9oqobg\\admin", "localhost", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("*****");
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.put(xmlFilePath, "/H:/books/catalog/XML Files/"+xmlFilePathSubstring);
            out.println("<script type=\"text/javascript\">");
   out.println("alert('Successfully Uploaded');");
   out.println("location='index.html';");
   out.println("</script>");
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            System.out.println(e);  
        } catch (SftpException e) {
            System.out.println(e); 
        }
 
        } catch (ParserConfigurationException pce) {
           System.out.println(pce);
        } catch (TransformerException tfe) {
            System.out.println(tfe);
        
        
        
        
        }
 catch(Exception e){
     PrintWriter out = response.getWriter();
 out.println(e);}
              
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
              
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    @Override
    public String getServletInfo() {
        return "Short description";
        
    }// </editor-fold>

}
