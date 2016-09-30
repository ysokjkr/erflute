package org.insightech.er.editor.view.dialog.category;

import org.dbflute.erflute.core.dialog.AbstractDialog;
import org.dbflute.erflute.core.exception.InputException;
import org.dbflute.erflute.core.widgets.CompositeFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.insightech.er.editor.model.diagram_contents.element.node.ermodel.VGroup;

public class VGroupNameChangeDialog extends AbstractDialog {

    private Text categoryNameText = null;

    private VGroup targetCategory;

    private String categoryName;

    public VGroupNameChangeDialog(Shell parentShell, VGroup category) {
        super(parentShell, 2);
        this.targetCategory = category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialize(Composite composite) {
        this.categoryNameText = CompositeFactory.createText(this, composite, "label.vgroup.name", true);
    }

    @Override
    protected String getTitle() {
        return "dialog.title.change.vgroup.name";
    }

    @Override
    protected void perfomeOK() throws InputException {
    }

    @Override
    protected void setData() {
        this.categoryNameText.setText(this.targetCategory.getName());
    }

    @Override
    protected String getErrorMessage() {
        String text = categoryNameText.getText().trim();

        if ("".equals(text)) {
            return "error.category.name.empty";
        }

        this.categoryName = text;

        return null;
    }

    public String getCategoryName() {
        return this.categoryName;
    }
}
