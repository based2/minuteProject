package net.sf.minuteProject.loader.presentation.node; //schema dictionary

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.presentation.PresentationHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

public class Flow extends AbstractConfiguration{

   private Dictionary _dictionary;
   private String _name;
   private String _id;
   private List<String> _pageRefs;
   private List <Page> _refpages;
   private List<Page> _pages;

   public Flow() {
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
   
   
   public List<String> getPagerefs() {
      if (_pageRefs == null){
         _pageRefs = new ArrayList<String>();
      }
      return _pageRefs;
   }
   
   public String[] getPagerefsArray() {
      return (String[])getPagerefs().toArray(new String[getPagerefs().size()]);
   }
      
   public void setPagerefs (List<String> _pageRefs) {
      this._pageRefs = _pageRefs;
   }
 
   public void setPageref (String _pageRef) {
      addPageref(_pageRef);
   }

   public void addPageref (String _pageRef) {
      getPagerefs().add(_pageRef);
   }

   public List<Page> getPageByRefs() {
      if (_refpages==null) {
         _refpages = new ArrayList<Page>();
         List <Page> pages = getDictionary().getPages();
         for (String pageRef : getPagerefs()) {
            for (Page page : pages) {
               if (page.getId().equals(pageRef))
                  _refpages.add(page);
            }
         }
      }
      return _refpages;
   }
   
   public Page[] getPageByRefsArray() {
      List<Page> list = getPageByRefs();
      return (Page[])list.toArray(new Page[list.size()]);
   }
   
   public List<Page> getPages() {
      if (_pages == null){
         _pages = new ArrayList<Page>();
      }
      return _pages;
   }
   
   public Page[] getPagesArray() {
      return (Page[])getPages().toArray(new Page[getPages().size()]);
   }
      
   public void setPages (List<Page> _pages) {
      this._pages = _pages;
   }
 
   public void setPage (Page _page) {
      addPage(_page);
   }

   public void addPage (Page _page) {
      getPages().add(_page);
   }


}

