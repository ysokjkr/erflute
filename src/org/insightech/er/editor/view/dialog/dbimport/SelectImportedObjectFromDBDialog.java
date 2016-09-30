package org.insightech.er.editor.view.dialog.dbimport;

import org.dbflute.erflute.core.exception.InputException;
import org.dbflute.erflute.core.widgets.CompositeFactory;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.insightech.er.editor.model.ERDiagram;
import org.insightech.er.editor.model.dbimport.DBObjectSet;

public class SelectImportedObjectFromDBDialog extends AbstractSelectImportedObjectDialog {

    public SelectImportedObjectFromDBDialog(Shell parentShell, ERDiagram diagram, DBObjectSet allObjectSet) {
        super(parentShell, diagram, allObjectSet);
    }

    @Override
    protected void initializeOptionGroup(Group group) {
        this.useCommentAsLogicalNameButton = CompositeFactory.createCheckbox(this, group, "label.use.comment.as.logical.name");
        super.initializeOptionGroup(group);
    }

    @Override
    protected void perfomeOK() throws InputException {
        super.perfomeOK();

        this.resultUseCommentAsLogicalName = this.useCommentAsLogicalNameButton.getSelection();
    }

}
