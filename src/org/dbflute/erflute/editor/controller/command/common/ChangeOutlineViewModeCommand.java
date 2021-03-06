package org.dbflute.erflute.editor.controller.command.common;

import org.dbflute.erflute.editor.controller.command.AbstractCommand;
import org.dbflute.erflute.editor.model.ERDiagram;
import org.dbflute.erflute.editor.model.settings.Settings;

public class ChangeOutlineViewModeCommand extends AbstractCommand {

    private ERDiagram diagram;

    private int oldViewMode;

    private int newViewMode;

    private Settings settings;

    public ChangeOutlineViewModeCommand(ERDiagram diagram, int viewMode) {
        this.diagram = diagram;
        this.settings = this.diagram.getDiagramContents().getSettings();
        this.newViewMode = viewMode;
        this.oldViewMode = this.settings.getViewMode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        this.settings.setOutlineViewMode(this.newViewMode);
        this.diagram.changeAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doUndo() {
        this.settings.setOutlineViewMode(this.oldViewMode);
        this.diagram.changeAll();
    }
}
