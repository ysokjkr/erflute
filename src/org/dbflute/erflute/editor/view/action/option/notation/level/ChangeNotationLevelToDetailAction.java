package org.dbflute.erflute.editor.view.action.option.notation.level;

import org.dbflute.erflute.editor.MainDiagramEditor;
import org.dbflute.erflute.editor.model.settings.Settings;

public class ChangeNotationLevelToDetailAction extends AbstractChangeNotationLevelAction {

    public static final String ID = ChangeNotationLevelToDetailAction.class.getName();

    public ChangeNotationLevelToDetailAction(MainDiagramEditor editor) {
        super(ID, editor);
    }

    @Override
    protected int getLevel() {
        return Settings.NOTATION_LEVLE_DETAIL;
    }

}
