package org.dbflute.erflute.editor.view.action.option.notation.level;

import org.dbflute.erflute.editor.RealModelEditor;
import org.dbflute.erflute.editor.model.settings.Settings;

public class ChangeNotationLevelToExcludeTypeAction extends AbstractChangeNotationLevelAction {

    public static final String ID = ChangeNotationLevelToExcludeTypeAction.class.getName();

    public ChangeNotationLevelToExcludeTypeAction(RealModelEditor editor) {
        super(ID, editor);
    }

    @Override
    protected int getLevel() {
        return Settings.NOTATION_LEVLE_EXCLUDE_TYPE;
    }

}
