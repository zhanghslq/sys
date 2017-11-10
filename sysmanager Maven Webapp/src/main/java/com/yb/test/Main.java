package com.yb.test;

import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.io.FileInputStream;  
import java.io.File;  
import java.net.HttpURLConnection;  
import java.net.URL;  
  



import org.htmlparser.Node;  
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeIterator;  
import org.htmlparser.util.NodeList;
import org.htmlparser.Parser;  
  
/** 
* @author www.baizeju.com 
*/  
public class Main {  
    private static String ENCODE = "UTF-8";  
    private static void message( String szMsg ) {  
        try{ System.out.println(new String(szMsg.getBytes(ENCODE), System.getProperty("file.encoding"))); }    catch(Exception e ){}  
    }  
    public static String openFile( String szFileName ) {  
        try {  
            BufferedReader bis = new BufferedReader(new InputStreamReader(new FileInputStream( new File(szFileName)),    ENCODE) );  
            String szContent="";  
            String szTemp;  
              
            while ( (szTemp = bis.readLine()) != null) {  
                szContent+=szTemp+"\n";  
            }  
            bis.close();  
            return szContent;  
        }  
        catch( Exception e ) {  
            return "";  
        }  
    }  
      
   public static void main(String[] args) {  
        try{  
            Parser parser = new Parser( (HttpURLConnection) (new URL("http://www.bjwater.gov.cn/eportal/ui?pageId=349049&moduleId=c0c024e49b714288977cc55f88779e5c")).openConnection() );  
            //可以过滤
            TagNameFilter tagNameFilter = new TagNameFilter("tr");//根据标签名字来选择
            NodeList extractAllNodesThatMatch = parser.extractAllNodesThatMatch(tagNameFilter);
            if(extractAllNodesThatMatch!=null) {  
                for (int i = 0; i < extractAllNodesThatMatch.size(); i++) {  
                    Node textnode = (Node) extractAllNodesThatMatch.elementAt(i);
                    if(i==17){
                    	message("getText:"+textnode.getText());  
                    	message("=================================================");  
                    	Node firstChild = textnode.getFirstChild();
                    	System.out.println("txt"+firstChild);
                    	Node lastChild = textnode.getLastChild();
                    	System.out.println(lastChild.toHtml());
                    }
                }  
            }       
            /*for (NodeIterator i = parser.elements (); i.hasMoreNodes(); ) {  
                Node node = i.nextNode();  
               // message("getText:"+node.getText());  
               // message("getPlainText:"+node.toPlainTextString());  
                 //message("toHtml:"+node.toHtml());  
                 // message("toHtml(true):"+node.toHtml(true));  
                  //message("toHtml(false):"+node.toHtml(false));
                    message("toString:"+node.toString());
            }   */           
        }  
        catch( Exception e ) {       
            System.out.println( "Exception:"+e );  
        }  
    }  
}  