package net.sf.minuteProject.loader.presentation.node; //schema dictionary

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.presentation.PresentationHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

public class Block extends AbstractConfiguration{

   private Dictionary _dictionary;
   private String _name;
   private String _id;
   private Boolean _isForm;
   private List<String> _componentRefs;
   private List <Component> _refcomponents;
   private List<Component> _components;

   public Block() {
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
   
   
   public Boolean getIsform() {
      return _isForm;
   }
	
   public void setIsform (Boolean _isForm) {
      this._isForm = _isForm;
   }
   
   
   public List<String> getComponentrefs() {
      if (_componentRefs == null){
         _componentRefs = new ArrayList<String>();
      }
      return _componentRefs;
   }
   
   public String[] getComponentrefsArray() {
      return (String[])getComponentrefs().toArray(new String[getComponentrefs().size()]);
   }
      
   public void setComponentrefs (List<String> _componentRefs) {
      this._componentRefs = _componentRefs;
   }
 
   public void setComponentref (String _componentRef) {
      addComponentref(_componentRef);
   }

   public void addComponentref (String _componentRef) {
      getComponentrefs().add(_componentRef);
   }

   public List<Component> getComponentByRefs() {
      if (_refcomponents==null) {
         _refcomponents = new ArrayList<Component>();
         List <Component> components = getDictionary().getComponents();
         for (String componentRef : getComponentrefs()) {
            for (Component component : components) {
               if (component.getId().equals(componentRef))
                  _refcomponents.add(component);
            }
         }
      }
      return _refcomponents;
   }
   
   public Component[] getComponentByRefsArray() {
      List<Component> list = getComponentByRefs();
      return (Component[])list.toArray(new Component[list.size()]);
   }
   
   public List<Component> getComponents() {
      if (_components == null){
         _components = new ArrayList<Component>();
      }
      return _components;
   }
   
   public Component[] getComponentsArray() {
      return (Component[])getComponents().toArray(new Component[getComponents().size()]);
   }
      
   public void setComponents (List<Component> _components) {
      this._components = _components;
   }
 
   public void setComponent (Component _component) {
      addComponent(_component);
   }

   public void addComponent (Component _component) {
      getComponents().add(_component);
   }


}

