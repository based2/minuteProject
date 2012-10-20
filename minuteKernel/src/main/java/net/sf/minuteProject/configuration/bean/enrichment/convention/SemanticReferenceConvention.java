package net.sf.minuteProject.configuration.bean.enrichment.convention;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.enrichment.SemanticReference;
import net.sf.minuteProject.configuration.bean.enrichment.path.SqlPath;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.ColumnUtils;
import net.sf.minuteProject.utils.TableUtils;
import net.sf.minuteProject.utils.parser.ParserUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;

public class SemanticReferenceConvention extends ModelConvention {

	private static final int DEFAULT_MAX_FIELD = 7;
	private Logger logger = Logger.getLogger(SemanticReference.class);
	private String entityPattern, patternType;
	private String fieldPattern, fieldPatternType;
	private int maxNumberOfFields, maxColumn=0;
	private boolean toExtend = true, toOverride=false;
	private String pack, contentType;
	private boolean forceDefaultSemanticReference, lookUpInParentForMatch;

	@Override
	public void apply(BusinessModel model) {
		if (isValid()) {
			if (model.getBusinessPackage()!=null) {
				for (Table table : model.getBusinessPackage().getEntities()) {
					apply (table);
				}
			}
		} else
			logger.error("SemanticReferenceConvention not valid");
	}

	private boolean isValid() {
		return (hasEntityPattern() || hasPackage() || hasContentType() || matchAllEntities()) && hasFieldPattern();
	}
	
	private boolean matchAllEntities() {
		return !hasEntityPattern() && !hasPackage() && !hasContentType();
	}

	private boolean isValid(Table table) {
		return (hasEntityPattern(table) || hasPackage(table) || hasContentType(table) || matchAllEntities()) && !table.isManyToMany();
	}

	private void apply(Table table) {
		if (isValid (table))
			applySemanticReference(table);
	}
	
	private void applySemanticReference(Table table) {
		if (!toOverride && !toExtend && TableUtils.hasSemanticReference(table))
			return;
		int maxColumn = getMaxColumns();
		int cpt=0;
		SemanticReference semanticReference = (toOverride)?new SemanticReference(): table.getSemanticReference();
		if (semanticReference==null)
			semanticReference=new SemanticReference();
		cpt = semanticReference.getSqlPaths().size();
		List<String> columnNames = ColumnUtils.getColumnNames(table);
		for (String pattern : getFieldPatterns()) {
			for (Column column : table.getColumns()) {
				cpt = setSemanticReferenceForColumn(maxColumn, cpt,
						semanticReference, columnNames, pattern, column.getName());
			}
			if (lookUpInParentForMatch) {
				for (Table t : TableUtils.getParents(table)) {
					for (Column column : table.getColumns()) {
						cpt = setSemanticReferenceForColumn(maxColumn, cpt,
								semanticReference, columnNames, pattern, t.getName()+"."+column.getName());
					}
				}
			}
		}
		if (forceDefaultSemanticReference) {
			if (semanticReference.getSqlPaths().size()==0) {
				for (Column column : table.getPrimaryKeyColumns()) {
					semanticReference.addSqlPath(getSqlPath(column.getName()));
				}
				for (Column column : table.getAttributes()) {
					if (!column.isPrimaryKey()) {
						semanticReference.addSqlPath(getSqlPath(column.getName()));
						break;
					}
				}
			}
		}
		table.setSemanticReference(semanticReference);
	}

	private int setSemanticReferenceForColumn(int maxColumn, int cpt,
			SemanticReference semanticReference, List<String> columnNames,
			String pattern, String path) {
		if (columnNames.contains(path) && cpt < maxColumn) {
			if (net.sf.minuteProject.utils.StringUtils.checkExpression(path, fieldPatternType, pattern)) {
				semanticReference.addSqlPath(getSqlPath(path));
				columnNames.remove(path);
				cpt++;
			}
		}
		return cpt;
	}

	private int getMaxColumns() {
		if (maxColumn==0)
			maxColumn = (maxNumberOfFields>0)?maxNumberOfFields:DEFAULT_MAX_FIELD;
		return maxColumn;
	}

	private List<String> getFieldPatterns() {
		return ParserUtils.getList(fieldPattern);
	}
	
//	private SemanticReference getSemanticReference(String path) {
//		SemanticReference semanticReference = new SemanticReference();
//		semanticReference.addSqlPath(getSqlPath(path));
//		return semanticReference;
//	}
//
//	private SqlPath getSqlPath(Column column) {
//		return getSqlPath(column.getName());
//	}
	
	private SqlPath getSqlPath(String st) {
		SqlPath sqlPath = new SqlPath();
		sqlPath.setPath(st);
		return sqlPath;
	}
	
//	private SqlPath getSqlPath(Table table, Column column) {
//		SqlPath sqlPath = new SqlPath();
//		sqlPath.setPath(table.getName()+"."+column.getName());
//		return sqlPath;
//	}

	private boolean hasContentType(Table table) {
		if (table.getContentType()!=null && table.getContentType().equals(contentType))
			return true;
		return false;
	}

	private boolean hasEntityPattern() {
		return ! (StringUtils.isEmpty(entityPattern) && StringUtils.isEmpty(patternType));
	}
	
	private boolean hasEntityPattern(Table table) {
		if (hasEntityPattern()) {
			return net.sf.minuteProject.utils.StringUtils.checkExpression(table.getName(), patternType, entityPattern);
		}
		return false;
	}
	
	private boolean hasPackage(Table table) {
		if (hasPackage()) {
			return pack.toLowerCase().equals(table.getPackage().getName().toLowerCase());
		}
		return false;
	}
	
	private boolean hasFieldPattern() {
		return ! (StringUtils.isEmpty(fieldPattern) && StringUtils.isEmpty(fieldPatternType));
	}
	
	private boolean hasPackage() {
		return ! StringUtils.isEmpty(pack);
	}
	
	private boolean hasContentType() {
		return ! StringUtils.isEmpty(contentType);
	}

	public String getEntityPattern() {
		return entityPattern;
	}

	public void setEntityPattern(String entityPattern) {
		this.entityPattern = entityPattern;
	}

	public String getPatternType() {
		return patternType;
	}

	public void setPatternType(String patternType) {
		this.patternType = patternType;
	}

	public String getFieldPattern() {
		return fieldPattern;
	}

	public void setFieldPattern(String fieldPattern) {
		this.fieldPattern = fieldPattern;
	}

	public String getFieldPatternType() {
		return fieldPatternType;
	}

	public void setFieldPatternType(String fieldPatternType) {
		this.fieldPatternType = fieldPatternType;
	}

	public int getMaxNumberOfFields() {
		return maxNumberOfFields;
	}

	public void setMaxNumberOfFields(int maxNumberOfFields) {
		this.maxNumberOfFields = maxNumberOfFields;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public boolean isLookUpInParentForMatch() {
		return lookUpInParentForMatch;
	}

	public void setLookUpInParentForMatch(boolean lookUpInParentForMatch) {
		this.lookUpInParentForMatch = lookUpInParentForMatch;
	}

	public boolean isForceDefaultSemanticReference() {
		return forceDefaultSemanticReference;
	}

	public void setForceDefaultSemanticReference(
			boolean forceDefaultSemanticReference) {
		this.forceDefaultSemanticReference = forceDefaultSemanticReference;
	}
	
}
