package net.sf.minuteProject.plugin.openxava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.enrichment.Action;
import net.sf.minuteProject.configuration.bean.enrichment.SemanticReference;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.plugin.format.I18nUtils;
import net.sf.minuteProject.utils.ColumnUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.ReferenceUtils;
import net.sf.minuteProject.utils.TableUtils;
import net.sf.minuteProject.utils.code.RestrictedCodeUtils;
import net.sf.minuteProject.utils.enrichment.SemanticReferenceUtils;
import net.sf.minuteProject.utils.java.JavaUtils;

public class OpenXavaUtils {

	public static String getHelpDirectory () {
		return "xava/help/";
	}
	
	public static String getHelpFileExtension () {
		return "jsp";
	}
	
	public static List<String> getTabAll (Table table) {
		List<String> list = new ArrayList<String>();
		list.addAll(getTabDefaultProperties(table));
		list.addAll(getParentSemanticReference(table));
		return list;
	}
	public static List<String> getTabSimple (Table table) {
		List<String> list = new ArrayList<String>();
		list.addAll(getTabDefaultProperties(table));
		return list;
	}
	public static List<String> getTabWithRef (Table table) {
		return getTabAll(table);
	}
	
	public static boolean hasTabAll (Table table) {
		return (getTabAll(table).isEmpty())?false:true;
	}
	public static boolean hasTabSimple (Table table) {
		return (getTabSimple(table).isEmpty())?false:true;
	}
	public static boolean hasTabWithRef (Table table) {
		return (getTabWithRef(table).isEmpty())?false:true;
	}
	
	private static List<String> getTabDefaultProperties (Table table) {
		List<String> list = new ArrayList<String>();
		for (Column column : table.getPrimaryKeyColumns()) {
			if (ColumnUtils.isNaturalPk(column)) {
				list.add(JavaUtils.getJavaVariableNaming(FormatUtils.getJavaNameVariable(column.getAlias())));
			}
		}
		for (Column column : table.getAttributes()) {
			if (!column.isLob())
				list.add(JavaUtils.getJavaVariableNaming(FormatUtils.getJavaNameVariable(column.getAlias())));
		}
		return list;
	}
		
	public static List<String> getParentSemanticReference (Table table) {
		return getParentSemanticReference(table, null, true);
	}
	
	public static List<String> getParentSemanticReferenceEntityVariable (Table table) {
		return getParentSemanticReference(table, null, false);
	}
	
	public static List<String> getParentSemanticReference (Table table, Reference removeReference, boolean addChunk) {
		List<String> list = new ArrayList<String>();
		for (Reference reference : table.getParents()) {
			if (!ReferenceUtils.isSimilarReference(reference, removeReference)) {
				Table parent = reference.getForeignTable();
				if (TableUtils.hasSemanticReference(parent)) {
					SemanticReference sr = reference.getForeignTable().getSemanticReference();
					for (String chunk : sr.getSemanticReferenceBeanPath()) {
						String c = FormatUtils.getJavaNameVariable(reference.getLocalColumn().getAlias());
						if (addChunk) c = c+"."+convertChunkToAlias(chunk, parent);
						list.add(c);
						//break; // only the first is added
					}
				}
			}
		}
		return list;
	}
	
	private static String convertChunkToAlias(String chunk, Table table) {
		Column column = ColumnUtils.getColumn(table, chunk);
		return FormatUtils.getJavaNameVariable(column.getAlias());
	}

	public static boolean hasDescriptionList (Table table) {
		if (TableUtils.hasSemanticReference(table) && 
			(TableUtils.isReferenceDataContentType(table) || TableUtils.isPseudoStaticDataContentType(table)))
			return true;
		return false;
	}
	
	public static String getPackageName(Package pack) {
		return I18nUtils.getPackageName(pack);
	}
	
	public static List<String> getListProperties (Reference reference) {
		boolean hasSemRef = false;
		List<String> list = new ArrayList<String>();
		Table linkTable = reference.getForeignTable();
		Table childTable = reference.getLocalTable();
		List<String> relativeChildSR = getParentChildRelativeSemanticReference(reference, linkTable);
		if (SemanticReferenceUtils.hasSemanticReference(childTable)) {
			list.addAll(getSemanticRefProperties(childTable));
			hasSemRef = true;
		}
		if (!relativeChildSR.isEmpty()) {
			if (!hasSemRef)
				list.addAll(getTabDefaultProperties(linkTable));//todo change by reference instead of default
			list.addAll(relativeChildSR);
		}
		return list;
	}

	private static List<String> getSemanticRefProperties(Table childTable) {
		List<String> list = new ArrayList<String>();
		SemanticReference sr = childTable.getSemanticReference();
		for (String chunk : sr.getSemanticReferenceBeanPath()) {
			list.add(convertChunkToAlias(chunk, childTable));
		}
		return null;
	}

	private static List<String> getParentChildRelativeSemanticReference(
			Reference reference, Table linkTable) {
		return getParentSemanticReference (linkTable, reference, true);
	}

	public static String getColumnDescription(Column column) {
		return I18nUtils.getColumnDescription(column); 
	}
	
	public static String getActionClassName (Action action) {
		if (action!=null)
			return RestrictedCodeUtils.convertToValidJava(action.getName());
		return "action_must_be_set";
	}
	
	public static String getControllerName (Table table) {
		return FormatUtils.getJavaName(table.getAlias())+"Controller";
	}
	
	public static String getModuleUrlRelativeViaParameter (String application, String module) {
		return "home.jsp?application="+application+"&module="+module;
	}
	
	public static String getModuleUrlAbsoluteViaSlash (String application, String module) {
		return "/"+application+"/MenuModules/"+module;
	}	
	
	public static String getModuleUrl (String application, String module) {
		return getModuleUrlRelativeViaParameter(application, module);
//		return getModuleUrlAbsoluteViaSlash(application, module);
	}	
	
	public static String getStereotype (Column column) {
		if (ColumnUtils.hasStereotype(column))
			return getStereotype (column.getStereotype().getStereotype());
		return null;
	}

//   MONEY, DINERO
//   PHOTO, IMAGEN, FOTO, IMAGE
//   MEMO, TEXT_AREA, TEXTO_GRANDE
//   LABEL, ETIQUETA
//   BOLD_LABEL, ETIQUETA_NEGRITA
//   TIME, HORA
//   DATETIME, FECHAHORA
//   IMAGES_GALLERY, GALERIA_IMAGENES (setup instructions)
//   ZEROS_FILLED, RELLENADO_CON_CEROS
//   HTML_TEXT, TEXTO_HTML (text with editable format)
//   IMAGE_LABEL, ETIQUETA_IMAGEN (image depending on property content)
//   EMAIL
//   TELEPHONE, TELEFONO
//   WEBURL
//   IP
//   ISBN
//   CREDIT_CARD, TARJETA_CREDITO
//   EMAIL_LIST, LISTA_EMAIL
//   DOCUMENT_LIBRARY, LIBRERIA_DOCUMENTOS (new in v4m6, it only works inside Liferay)
//   PASSWORD, CONTRASENA (new in v4.1)
	private static String getStereotype(String stereotype) {
		String s = stereotype.toLowerCase();
		if (s.equals("url"))
			return "WEBURL";
		if (s.equals("currency"))
			return "MONEY";
		if (s.equals("image"))
			return "IMAGE";
		if (s.equals("text-area"))
			return "TEXT_AREA";
		if (s.equals("text-html"))
			return "HTML_TEXT";
		if (s.equals("email"))
			return "EMAIL";
		if (s.equals("phone"))
			return "TELEPHONE";
		if (s.equals("password"))
			return "PASSWORD";
		return null;
	}

	
	
	/**
	 * @param table
	 * @return
	 */
	public static String getMainView (Table table) {
		// get main view
		// link semantic reference (content type) in short version
		// self semantic reference
		// matrix
		// mandatory param should appear
		// overflow in tab
		// text-html in tab
		return "";
	}

}
