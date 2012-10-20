package net.sf.minuteProject.loader.presentation.node; //schema dictionary

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.presentation.PresentationHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

public class Dictionary extends AbstractConfiguration{

   private String _name;
   private List<Flow> _flows;
   private List<Page> _pages;
   private List<Window> _windows;
   private List<Component> _components;
   private List<Block> _blocks;
   private List<Listblock> _listBlocks;
   private List<Valuelist> _valueLists;

   public Dictionary() {
   }

   public String getTechnicalPackage(Template template) {
      return template.getTechnicalPackage();
   }
   
   public String getName() {
      return _name;
   }
	
   public void setName (String _name) {
      this._name = _name;
   }
   
   
   public List<Flow> getFlows() {
      if (_flows == null){
         _flows = new ArrayList<Flow>();
      }
      return _flows;
   }
   
   public Flow[] getFlowsArray() {
      return (Flow[])getFlows().toArray(new Flow[getFlows().size()]);
   }
      
   public void setFlows (List<Flow> _flows) {
      this._flows = _flows;
   }
 
   public void setFlow (Flow _flow) {
      addFlow(_flow);
   }

   public void addFlow (Flow _flow) {
      getFlows().add(_flow);
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

   public List<Block> getBlocks() {
      if (_blocks == null){
         _blocks = new ArrayList<Block>();
      }
      return _blocks;
   }
   
   public Block[] getBlocksArray() {
      return (Block[])getBlocks().toArray(new Block[getBlocks().size()]);
   }
      
   public void setBlocks (List<Block> _blocks) {
      this._blocks = _blocks;
   }
 
   public void setBlock (Block _block) {
      addBlock(_block);
   }

   public void addBlock (Block _block) {
      getBlocks().add(_block);
   }

   public List<Listblock> getListblocks() {
      if (_listBlocks == null){
         _listBlocks = new ArrayList<Listblock>();
      }
      return _listBlocks;
   }
   
   public Listblock[] getListblocksArray() {
      return (Listblock[])getListblocks().toArray(new Listblock[getListblocks().size()]);
   }
      
   public void setListblocks (List<Listblock> _listBlocks) {
      this._listBlocks = _listBlocks;
   }
 
   public void setListblock (Listblock _listBlock) {
      addListblock(_listBlock);
   }

   public void addListblock (Listblock _listBlock) {
      getListblocks().add(_listBlock);
   }

   public List<Valuelist> getValuelists() {
      if (_valueLists == null){
         _valueLists = new ArrayList<Valuelist>();
      }
      return _valueLists;
   }
   
   public Valuelist[] getValuelistsArray() {
      return (Valuelist[])getValuelists().toArray(new Valuelist[getValuelists().size()]);
   }
      
   public void setValuelists (List<Valuelist> _valueLists) {
      this._valueLists = _valueLists;
   }
 
   public void setValuelist (Valuelist _valueList) {
      addValuelist(_valueList);
   }

   public void addValuelist (Valuelist _valueList) {
      getValuelists().add(_valueList);
   }


}

