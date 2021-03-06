package org.dbflute.erflute.editor.view.figure.view;

import org.dbflute.erflute.core.ImageKey;
import org.dbflute.erflute.editor.model.settings.Settings;
import org.dbflute.erflute.editor.view.figure.table.TableFigure;

public class ViewFigure extends TableFigure {

    public ViewFigure(Settings settings) {
        super(settings);
    }

    @Override
    public String getImageKey() {
        return ImageKey.VIEW;
    }

}
