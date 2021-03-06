package org.dbflute.erflute.editor.controller.editpart.outline.table;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.dbflute.erflute.Activator;
import org.dbflute.erflute.core.ImageKey;
import org.dbflute.erflute.editor.controller.editpart.DeleteableEditPart;
import org.dbflute.erflute.editor.controller.editpart.element.node.ERTableEditPart;
import org.dbflute.erflute.editor.controller.editpart.outline.AbstractOutlineEditPart;
import org.dbflute.erflute.editor.controller.editpolicy.element.node.DiagramWalkerComponentEditPolicy;
import org.dbflute.erflute.editor.model.AbstractModel;
import org.dbflute.erflute.editor.model.ERDiagram;
import org.dbflute.erflute.editor.model.diagram_contents.element.connection.Relationship;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.category.Category;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.table.ERTable;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.table.index.IndexSet;
import org.dbflute.erflute.editor.model.settings.Settings;
import org.dbflute.erflute.editor.view.dialog.table.TableDialog;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;

public class TableOutlineEditPart extends AbstractOutlineEditPart implements DeleteableEditPart {

    private boolean quickMode;

    public TableOutlineEditPart(boolean quickMode) {
        this.quickMode = quickMode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List getModelChildren() {
        List<AbstractModel> children = new ArrayList<AbstractModel>();

        ERTable table = (ERTable) this.getModel();

        Category category = this.getCurrentCategory();

        if (!quickMode) {
            for (Relationship relation : table.getIncomingRelationshipList()) {
                if (category == null || category.contains(relation.getWalkerSource())) {
                    children.add(relation);
                }
            }
            children.addAll(table.getIndexes());
        }

        return children;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ERTable.PROPERTY_CHANGE_PHYSICAL_NAME)) {
            refreshName();

        } else if (evt.getPropertyName().equals(ERTable.PROPERTY_CHANGE_LOGICAL_NAME)) {
            refreshName();

        } else if (evt.getPropertyName().equals(ERTable.PROPERTY_CHANGE_COLUMNS)) {
            refresh();

        } else if (evt.getPropertyName().equals(IndexSet.PROPERTY_CHANGE_INDEXES)) {
            refresh();

        }
    }

    protected void refreshName() {
        ERTable model = (ERTable) this.getModel();

        ERDiagram diagram = (ERDiagram) this.getRoot().getContents().getModel();

        String name = null;

        int viewMode = diagram.getDiagramContents().getSettings().getOutlineViewMode();

        if (viewMode == Settings.VIEW_MODE_PHYSICAL) {
            if (model.getPhysicalName() != null) {
                name = model.getPhysicalName();

            } else {
                name = "";
            }

        } else if (viewMode == Settings.VIEW_MODE_LOGICAL) {
            if (model.getLogicalName() != null) {
                name = model.getLogicalName();

            } else {
                name = "";
            }

        } else {
            if (model.getLogicalName() != null) {
                name = model.getLogicalName();

            } else {
                name = "";
            }

            name += "/";

            if (model.getPhysicalName() != null) {
                name += model.getPhysicalName();

            }
        }

        this.setWidgetText(diagram.filter(name));
        this.setWidgetImage(Activator.getImage(ImageKey.TABLE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void refreshOutlineVisuals() {
        this.refreshName();

        for (Object child : this.getChildren()) {
            EditPart part = (EditPart) child;
            part.refresh();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createEditPolicies() {
        this.installEditPolicy(EditPolicy.COMPONENT_ROLE, new DiagramWalkerComponentEditPolicy());
        // this.installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performRequest(Request request) {
        ERTable table = (ERTable) this.getModel();
        ERDiagram diagram = (ERDiagram) this.getRoot().getContents().getModel();

        if (request.getType().equals(RequestConstants.REQ_OPEN)) {
            ERTable copyTable = table.copyData();

            TableDialog dialog =
                    new TableDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), this.getViewer(), copyTable, diagram
                            .getDiagramContents().getColumnGroupSet());

            if (dialog.open() == IDialogConstants.OK_ID) {
                CompoundCommand command = ERTableEditPart.createChangeTablePropertyCommand(diagram, table, copyTable);

                this.execute(command.unwrap());
            }
        }

        super.performRequest(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DragTracker getDragTracker(Request req) {
        return new SelectEditPartTracker(this);
    }

    public boolean isDeleteable() {
        return true;
    }
}
