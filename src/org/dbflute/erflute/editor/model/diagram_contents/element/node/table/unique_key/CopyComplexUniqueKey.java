package org.dbflute.erflute.editor.model.diagram_contents.element.node.table.unique_key;

import java.util.ArrayList;
import java.util.List;

import org.dbflute.erflute.editor.model.diagram_contents.element.node.table.column.CopyColumn;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.table.column.ERColumn;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.table.column.NormalColumn;

public class CopyComplexUniqueKey extends ComplexUniqueKey {

    private static final long serialVersionUID = 4099783813887218599L;

    private ComplexUniqueKey originalComplexUniqueKey;

    public CopyComplexUniqueKey(ComplexUniqueKey original, List<ERColumn> copyColumns) {
        super(original.getUniqueKeyName());

        this.originalComplexUniqueKey = original;

        for (NormalColumn originalColumn : original.getColumnList()) {
            for (ERColumn column : copyColumns) {
                if (column instanceof CopyColumn) {
                    CopyColumn copyColumn = (CopyColumn) column;

                    if (copyColumn.getOriginalColumn().equals(originalColumn)) {
                        this.addColumn(copyColumn);
                        break;
                    }
                }
            }
        }
    }

    public ComplexUniqueKey restructure() {
        if (this.originalComplexUniqueKey == null) {
            this.originalComplexUniqueKey = new ComplexUniqueKey(this.getUniqueKeyName());
        }

        List<NormalColumn> normalColumns = new ArrayList<NormalColumn>();

        for (NormalColumn column : this.getColumnList()) {
            CopyColumn copyColumn = (CopyColumn) column;
            column = copyColumn.getOriginalColumn();
            normalColumns.add(column);
        }

        this.originalComplexUniqueKey.setColumnList(normalColumns);
        this.originalComplexUniqueKey.setUniqueKeyName(this.getUniqueKeyName());

        return this.originalComplexUniqueKey;
    }

    public ComplexUniqueKey getOriginal() {
        return this.originalComplexUniqueKey;
    }
}
