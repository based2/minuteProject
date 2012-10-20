package net.sf.minuteProject.loader.presentation.node; //schema dictionary

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.presentation.PresentationHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

public class Page extends AbstractConfiguration{

   private Dictionary _dictionary;
   private String _name;
   private String _id;
   private List<String> _windowRefs;
   private List <Window> _refwindows;
   private List<Window> _windows;

   public Page() {
   }

   public String getTechnicalPackage(Template template) {
      return template.getTechnicalPackage();
   }
   
   public Dictionary getDictionary() {
      return PresentationHolder.getDictionary(); 
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
   
   
   public List<String> getWindowrefs() {
      if (_windowRefs == null){
         _windowRefs = new ArrayList<String>();
      }
      return _windowRefs;
   }
   
   public String[] getWindowrefsArray() {
      return (String[])getWindowrefs().toArray(new String[getWindowrefs().size()]);
   }
      
   public void setWindowrefs (List<String> _windowRefs) {
      this._windowRefs = _windowRefs;
   }
 
   public void setWindowref (String _windowRef) {
      addWindowref(_windowRef);
   }

   public void addWindowref (String _windowRef) {
      getWindowrefs().add(_windowRef);
   }

   public List<Window> getWindowByRefs() {
      if (_refwindows==null) {
         _refwindows = new ArrayList<Window>();
         List <Window> windows = getDictionary().getWindows();
         for (String windowRef : getWindowrefs()) {
            for (Window window : windows) {
               if (window.getId().equals(windowRef))
                  _refwindows.add(window);
            }
         }
      }
      return _refwindows;
   }
   
   public Window[] getWindowByRefsArray() {
      List<Window> list = getWindowByRefs();
      return (Window[])list.toArray(new Window[list.size()]);
   }
   
   public List<Window> getWindows() {
      if (_windows == null){
         _windows = new ArrayList<Window>();
      }
      return _windows;
   }
   
   public Window[] getWindowsArray() {
      return (Window[])getWindows().toArray(new Window[getWindows().size()]);
   }
      
   public void setWindows (List<Window> _windows) {
      this._windows = _windows;
   }
 
   public void setWindow (Window _window) {
      addWindow(_window);
   }

   public void addWindow (Window _window) {
      getWindows().add(_window);
   }


}

