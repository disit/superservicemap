/* SuperServiceMap.
   Copyright (C) 2015 DISIT Lab http://www.disit.org - University of Florence
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.
   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>. */

package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.glassfish.jersey.client.ClientConfig;
import servicemap.MySQLManager;
import servicemap.ServiceMap;

/**
 *
 * @author disit
 */
public class PhotoUploadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String serviceUri = null;
        String uid = null;
        String requestFrom = null;
        File file = null;
        
        List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for(FileItem item : multiparts){
            if(item.isFormField()){ 
                if("serviceUri".equals(item.getFieldName())){ 
                    serviceUri = (String)item.getString(); 
                }
                if("uid".equals(item.getFieldName())){ 
                    uid = (String)item.getString(); 
                }
                if("requestFrom".equals(item.getFieldName())){ 
                    requestFrom = (String)item.getString(); 
                }
            } 
            else {
                if("file".equals(item.getFieldName())) {
                    String mimeType = item.getContentType();
                    String filename = item.getName();
                    if(mimeType == null) {
                        if(filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".jpeg"))
                            mimeType = "image/jpeg";
                        else if(filename.toLowerCase().endsWith(".png"))
                            mimeType = "image/png";
                    }
                    if(!"image/jpeg".equals(mimeType) && !"image/png".equals(mimeType)) {
                      response.sendError(400, "supported only image/jpeg and image/png files");
                      return;
                    }
                    String ext="";
                    if("image/jpeg".equals(mimeType)) {
                      ext=".jpg";
                    }
                    else if("image/png".equals(mimeType)) {
                      ext=".png";
                    }
                    file = File.createTempFile("file-", ext);
                    InputStream filecontent = item.getInputStream();
                    Files.copy(filecontent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    filecontent.close();
                }

        
            }
        }
        
        if(serviceUri == null) {
            serviceUri = request.getParameter("serviceUri");
        }
        if(uid == null) {
            uid = request.getParameter("uid");
        }
        if(requestFrom == null) {
            requestFrom = request.getParameter("requestFrom");
        }
        
        MySQLManager store = new MySQLManager();
        String smid = store.getSMIdFromServiceUriCache(serviceUri);
        if(smid == null) {
            List<ServiceMap> sMs = store.getAll();
            for (int i = 0; i < sMs.size(); i++) {
                final String PSMQUERY = store.getUrlPrefixFromSMid(sMs.get(i).getId()) + "/api/v1/?ssm=yes&serviceUri=" + URLEncoder.encode(serviceUri,"UTF-8");
                ClientConfig config = new ClientConfig();
                Client client = ClientBuilder.newClient(config);
                WebTarget targetServiceMap = client.target(UriBuilder.fromUri(PSMQUERY).build());
                Response r = targetServiceMap.request().get();
                if(r.getStatus() == 200) {                            
                    store.insertCache(serviceUri, sMs.get(i).getId());
                    forwardTo(store.getUrlPrefixFromSMid(sMs.get(i).getId()), uid, serviceUri, requestFrom, file, response);
                    return;
                }
            }
        }
        else {
            forwardTo(store.getUrlPrefixFromSMid(smid), uid, serviceUri, requestFrom, file, response);
        }

    }
    
    private void forwardTo(String servletUrl, String uid, String serviceUri, String requestFrom, File file, HttpServletResponse response) throws Exception {
        String charset = "UTF-8";
        String requestURL = servletUrl+"/api/v1/photo";
 
        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);
            multipart.addHeaderField("Access-Control-Allow-Origin", "*");
            if(serviceUri != null) multipart.addFormField("serviceUri", serviceUri);
            if(uid != null) multipart.addFormField("uid", uid);   
            if(requestFrom != null) multipart.addFormField("requestFrom", requestFrom);   
            if(file != null) multipart.addFilePart("file", file);
            List<String> serviceMapResponse = multipart.finish();    
            response.setStatus(multipart.getStatus());
            for(String responseHeaderKey: multipart.getHttpConn().getHeaderFields().keySet()) {
                response.setHeader(responseHeaderKey, multipart.getHttpConn().getHeaderField(responseHeaderKey));
            }
            for (String line : serviceMapResponse) {
                response.getOutputStream().println(line);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PhotoUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
