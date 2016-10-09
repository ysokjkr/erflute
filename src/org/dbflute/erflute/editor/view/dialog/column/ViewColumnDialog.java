package org.dbflute.erflute.editor.view.dialog.column;

import org.dbflute.erflute.editor.model.diagram_contents.element.node.table.column.NormalColumn;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.view.ERView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * @author modified by jflute (originated in ermaster)
 */
public class ViewColumnDialog extends AbstractColumnDialog {

    public ViewColumnDialog(Shell parentShell, ERView view) {
        super(parentShell, view.getDiagram());
    }

    protected int getStyle(int style) {
        if (this.foreignKey) {
            style |= SWT.READ_ONLY;
        }
        return style;
    }

    @Override
    protected String getTitle() {
        return "dialog.title.column";
    }

    @Override
    protected void initializeComposite(Composite parent) {
        super.initializeComposite(parent);
        if (this.foreignKey) {
            // #for_erflute not use word linkage
            //this.wordCombo.setEnabled(false);
            this.typeCombo.setEnabled(false);
            this.lengthText.setEnabled(false);
            this.decimalText.setEnabled(false);
        }
    }

    // ===================================================================================
    //                                                                          Perform OK
    //                                                                          ==========
    @Override
    protected void performOK() {
        super.performOK();
        this.returnColumn = new NormalColumn(this.returnWord, false, false, false, false, null, null, null, null, null);
    }
}
