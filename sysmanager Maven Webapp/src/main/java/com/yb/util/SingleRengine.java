package com.yb.util;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;
 
/**
 * Created on 2017/9/29
 * Author: youxingyang.
 */
public class SingleRengine {
    private static Rengine re;
    private SingleRengine() {
    }
    public static Rengine getRegineInstance() {
        if (re == null) {
            try {	
            	//REngine engineForClass = REngine.engineForClass("org.rosuda.REngine.JRI.JRIEngine", new String[] { "--no-save" }, null, false);
				re= new Rengine(new String[] { "--no-save" }, false, new TextConsole());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
        }
        return re;
    }
 
    private static class TextConsole implements RMainLoopCallbacks {
        @Override
        public void rWriteConsole(Rengine re, String text, int oType) {
            System.out.print(text);
        }
        @Override
        public void rBusy(Rengine re, int which) {
            System.out.println("rBusy(" + which + ")");
        }
        @Override
        public String rReadConsole(Rengine re, String prompt, int addToHistory) {
            System.out.print(prompt);
            try {
                BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
                String s = br.readLine();
                return (s == null || s.length() == 0) ? s : s +"\n";
            }catch (Exception e) {
                System.out.println("jriReadConsole exception: " + e.getMessage());
            }
            return null;
        }
        @Override
        public void rShowMessage(Rengine re, String message) {
            System.out.println("rShowMessage \"" + message + "\"");
        }
        @Override
        public void rFlushConsole(Rengine re) {
        }
        @Override
        public void rLoadHistory(Rengine re, String filename) {
        }
        @Override
        public void rSaveHistory(Rengine re, String filename) {
        }
 
        @SuppressWarnings("deprecation")
        @Override
        public String rChooseFile(Rengine re, int newFile) {
            FileDialog fd =new FileDialog(new Frame(), (newFile == 0) ?"Select a file" :"Select a new file",
                    (newFile ==0) ? FileDialog.LOAD : FileDialog.SAVE);
            fd.show();
            String res =null;
            if (fd.getDirectory() != null)
                res = fd.getDirectory();
            if (fd.getFile() != null)
                res = (res ==null) ? fd.getFile() : (res + fd.getFile());
            return res;
        }
    }
}
