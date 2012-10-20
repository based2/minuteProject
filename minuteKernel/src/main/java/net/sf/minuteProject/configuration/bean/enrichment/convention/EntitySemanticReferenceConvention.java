package net.sf.minuteProject.configuration.bean.enrichment.convention;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.enrichment.SemanticReference;
import net.sf.minuteProject.configuration.bean.enrichment.path.SqlPath;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.parser.ParserUtils;
//TODO migrate into SemanticReferenceConvention
public class EntitySemanticReferenceConvention extends ModelConvention {

	private String pattern, description, contentType;
	private List<String> patterns;
	@Override
	public void apply(BusinessModel model) {
		if (model.getBusinessPackage()!=null) {
			for (Table table : model.getBusinessPackage().getEntities()) {
				apply (table);
			}
		}
	}

	private void apply(Table table) {
		if (isMatch (table))
			applySemanticReference(table);
	}
	
	private void applySemanticReference(Table table) {
		for (Column column : table.getColumns()) {
			for (String pattern : getPatterns()) {
				if (column.getName().equals(pattern)) {
					table.setSemanticReference(getSemanticReference(column));
					return;
				}
			}
		}
		
	}

	private SemanticReference getSemanticReference(Column column) {
		SemanticReference semanticReference = new SemanticReference();
		semanticReference.addSqlPath(getSqlPath(column));
		return semanticReference;
	}

	private SqlPath getSqlPath(Column column) {
		SqlPath sqlPath = new SqlPath();
		sqlPath.setPath(column.getName());
		return sqlPath;
	}

	private boolean isMatch(Table table) {
		if (table.getContentType()!=null && table.getContentType().equals(contentType))
			return true;
		return false;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public List<String> getPatterns() {
		if (patterns==null) {
			patterns = ParserUtils.getList(pattern);
		}
		return patterns;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
}
