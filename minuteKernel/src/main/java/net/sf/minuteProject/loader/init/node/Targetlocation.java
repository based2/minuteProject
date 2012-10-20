package net.sf.minuteProject.loader.init.node;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Targetlocation {

   private String _name;
   private String _id;
   private String _refname;
   private String _dir;
   private String _filename;

   public Targetlocation() {
   }

   public String getName() {
      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   public String getId() {
      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   public String getRefname() {
      return _refname;
   }
	
   public void setRefname (String _refname) {
      this._refname = _refname;
   }
   
   public String getDir() {
      return _dir;
   }
	
   public void setDir (String _dir) {
      this._dir = _dir;
   }
   
   public String getFilename() {
      return _filename;
   }
	
   public void setFilename (String _filename) {
      this._filename = _filename;
   }
   

}

