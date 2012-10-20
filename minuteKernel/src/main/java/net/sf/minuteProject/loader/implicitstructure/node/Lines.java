package net.sf.minuteProject.loader.implicitstructure.node; //schema base-structure

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.implicitstructure.ImplicitstructureHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationLoader;
import net.sf.minuteProject.configuration.bean.Template;

public class Lines extends AbstractConfigurationLoader{

    private String _packageName;
   private BaseStructure _baseStructure;
   private String _name;
   private String _id;
   private List<Line> _lines;

   public Lines() {
   }

   public String getTechnicalPackage(Template template) {
      return getPackageName();
   }

   public String getPackageName() {
      if (_packageName == null)
         _packageName = new String();
      return _packageName;
   }

   public void setPackageName(String _packageName) {
      this._packageName = _packageName;
   }
   
   public BaseStructure getBaseStructure() {
      return ImplicitstructureHolder.getBaseStructure(); 
   }
	
   public String getName() {
	  if (_name == null)
	     _name = new String();
	      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   
   public String getId() {
	  if (_id == null)
	     _id = new String();
	      return _id;
   }
	
   public void setId (String _id) {
      this._id = _id;
   }
   
   
   public List<Line> getLines() {
      if (_lines == null){
         _lines = new ArrayList<Line>();
      }
      return _lines;
   }
   
   public Line[] getLinesArray() {
      return (Line[])getLines().toArray(new Line[getLines().size()]);
   }
      
   public void setLines (List<Line> _lines) {
      this._lines = _lines;
   }
 
   public void setLine (Line _line) {
      addLine(_line);
   }

   public void addLine (Line _line) {
      getLines().add(_line);
   }
   
   public Line getFirstLineFromLineByName (String name) {
      if (name==null)
         return null;
      for (Line _line : getLines()) {
         if (_line.getName().equals(name))
            return _line;
      }
      return null;
   } 


}

