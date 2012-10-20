package net.sf.minuteProject.configuration.bean.enumeration;

import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.utils.LicenceUtils;

import org.apache.commons.lang.StringUtils;

public enum Extension implements FileComment {

	java {
		public String lineCommentBeginning() {
			return "//";
		}
		public String lineCommentEnding() {
			return "";
		}
		public String textCommentBeginning() {
			return "/**";
		}
		public String textCommentMiddle() {
			return "*";
		}
		public String textCommentEnding() {
			return "*/";
		}
		public boolean licenceAtBeginning() {
			return true;
		}
	},
	groovy {
		public String lineCommentBeginning() {
			return "//";
		}
		public String lineCommentEnding() {
			return "";
		}
		public String textCommentBeginning() {
			return "/**";
		}
		public String textCommentMiddle() {
			return "*";
		}
		public String textCommentEnding() {
			return "*/";
		}
		public boolean licenceAtBeginning() {
			return true;
		}
	},
	xml {
		public String lineCommentBeginning() {
			return "<!--";
		}
		public String lineCommentEnding() {
			return "-->";
		}
		public String textCommentBeginning() {
			return "<!--";
		}
		public String textCommentMiddle() {
			return "";
		}
		public String textCommentEnding() {
			return "-->";
		}
		public boolean licenceAtBeginning() {
			return false;
		}
	},
	xsd {
		public String lineCommentBeginning() {
			return "<!--";
		}
		public String lineCommentEnding() {
			return "-->";
		}
		public String textCommentBeginning() {
			return "<!--";
		}
		public String textCommentMiddle() {
			return "";
		}
		public String textCommentEnding() {
			return "-->";
		}
		public boolean licenceAtBeginning() {
			return false;
		}
	},
	properties {
		public String lineCommentBeginning() {
			return "#";
		}
		public String lineCommentEnding() {
			return "";
		}
		public String textCommentBeginning() {
			return "#";
		}
		public String textCommentMiddle() {
			return "#";
		}
		public String textCommentEnding() {
			return "#";
		}
		public boolean licenceAtBeginning() {
			return true;
		}
	},
	sql {
		public String lineCommentBeginning() {
			return "--";
		}
		public String lineCommentEnding() {
			return "";
		}
		public String textCommentBeginning() {
			return "/*";
		}
		public String textCommentMiddle() {
			return "";
		}
		public String textCommentEnding() {
			return "*/";
		}
		public boolean licenceAtBeginning() {
			return true;
		}
	};

	private static final String CRLF = "\n";
	private String licence;
	
	public String getLicence() {
		if (licence==null) {
			licence = format(LicenceUtils.getLicence());
		}
		return licence;
	}
	
	public String format (String text) {
		if (text==null) return null;
		String[] lines = StringUtils.split(text, CRLF);
		if (lines.length==0) return null;
		if (lines.length==1) return formatLine(lines[0]);
		return formatText(lines);
	}

	private String formatText(String[] lines) {
		StringBuffer sb = new StringBuffer();
		sb.append(textCommentBeginning()+CRLF);
		for (int i = 0; i < lines.length; i++) {
			sb.append("\t"+textCommentMiddle()+lines[i]+CRLF);
		}
		sb.append(textCommentEnding()+CRLF);
		return sb.toString();
	}

	private String formatLine(String string) {
		return lineCommentBeginning()+string+lineCommentEnding();
	}
	
    public static Extension fromValue(String v) {
        for (Extension c : Extension.values()) {
            if (c.toString().equals(v)) {
                return c;
            }
        }
        return null;
    }
    
    //UGLY but for groovy compile...
 	public String lineCommentBeginning() {
 		return "";
 	}
	public String lineCommentEnding() {
		return "";
	}
	public boolean licenceAtBeginning() {
		return false;
	}
}
