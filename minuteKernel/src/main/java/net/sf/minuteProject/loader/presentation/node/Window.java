package net.sf.minuteProject.loader.presentation.node; //schema dictionary

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.presentation.PresentationHolder;
import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

public class Window extends AbstractConfiguration{

   private Dictionary _dictionary;
   private String _name;
   private String _id;
   private Boolean _isForm;
   private List<String> _blockRefs;
   private List <Block> _refblocks;
   private List<Block> _blocks;
   private List<String> _listBlockRefs;
   private List <Listblock> _reflistBlocks;
   private List<Listblock> _listblocks;

   public Window() {
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
   
   
   public List<String> getBlockrefs() {
      if (_blockRefs == null){
         _blockRefs = new ArrayList<String>();
      }
      return _blockRefs;
   }
   
   public String[] getBlockrefsArray() {
      return (String[])getBlockrefs().toArray(new String[getBlockrefs().size()]);
   }
      
   public void setBlockrefs (List<String> _blockRefs) {
      this._blockRefs = _blockRefs;
   }
 
   public void setBlockref (String _blockRef) {
      addBlockref(_blockRef);
   }

   public void addBlockref (String _blockRef) {
      getBlockrefs().add(_blockRef);
   }

   public List<Block> getBlockByRefs() {
      if (_refblocks==null) {
         _refblocks = new ArrayList<Block>();
         List <Block> blocks = getDictionary().getBlocks();
         for (String blockRef : getBlockrefs()) {
            for (Block block : blocks) {
               if (block.getId().equals(blockRef))
                  _refblocks.add(block);
            }
         }
      }
      return _refblocks;
   }
   
   public Block[] getBlockByRefsArray() {
      List<Block> list = getBlockByRefs();
      return (Block[])list.toArray(new Block[list.size()]);
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

   public List<String> getListblockrefs() {
      if (_listBlockRefs == null){
         _listBlockRefs = new ArrayList<String>();
      }
      return _listBlockRefs;
   }
   
   public String[] getListblockrefsArray() {
      return (String[])getListblockrefs().toArray(new String[getListblockrefs().size()]);
   }
      
   public void setListblockrefs (List<String> _listBlockRefs) {
      this._listBlockRefs = _listBlockRefs;
   }
 
   public void setListblockref (String _listBlockRef) {
      addListblockref(_listBlockRef);
   }

   public void addListblockref (String _listBlockRef) {
      getListblockrefs().add(_listBlockRef);
   }

   public List<Listblock> getListblockByRefs() {
      if (_reflistBlocks==null) {
         _reflistBlocks = new ArrayList<Listblock>();
         List <Listblock> listBlocks = getDictionary().getListblocks();
         for (String listBlockRef : getListblockrefs()) {
            for (Listblock listBlock : listBlocks) {
               if (listBlock.getId().equals(listBlockRef))
                  _reflistBlocks.add(listBlock);
            }
         }
      }
      return _reflistBlocks;
   }
   
   public Listblock[] getListblockByRefsArray() {
      List<Listblock> list = getListblockByRefs();
      return (Listblock[])list.toArray(new Listblock[list.size()]);
   }
   
   public List<Listblock> getListblocks() {
      if (_listblocks == null){
         _listblocks = new ArrayList<Listblock>();
      }
      return _listblocks;
   }
   
   public Listblock[] getListblocksArray() {
      return (Listblock[])getListblocks().toArray(new Listblock[getListblocks().size()]);
   }
      
   public void setListblocks (List<Listblock> _listblocks) {
      this._listblocks = _listblocks;
   }
 
   public void setListblock (Listblock _listblock) {
      addListblock(_listblock);
   }

   public void addListblock (Listblock _listblock) {
      getListblocks().add(_listblock);
   }


}

