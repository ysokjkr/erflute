package org.dbflute.erflute.editor.view;

import java.math.BigDecimal;

import org.dbflute.erflute.Activator;
import org.dbflute.erflute.core.DisplayMessages;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.ermodel.ERVirtualDiagram;
import org.dbflute.erflute.editor.model.settings.CategorySetting;
import org.dbflute.erflute.editor.model.settings.Settings;
import org.dbflute.erflute.editor.view.action.category.ChangeFreeLayoutAction;
import org.dbflute.erflute.editor.view.action.category.ChangeShowReferredTablesAction;
import org.dbflute.erflute.editor.view.action.dbexport.ExportToDDLAction;
import org.dbflute.erflute.editor.view.action.dbexport.ExportToImageAction;
import org.dbflute.erflute.editor.view.action.dbimport.ImportFromDBAction;
import org.dbflute.erflute.editor.view.action.ermodel.ERDiagramQuickOutlineAction;
import org.dbflute.erflute.editor.view.action.ermodel.VirtualDiagramAddAction;
import org.dbflute.erflute.editor.view.action.line.DefaultLineAction;
import org.dbflute.erflute.editor.view.action.line.ResizeModelAction;
import org.dbflute.erflute.editor.view.action.line.RightAngleLineAction;
import org.dbflute.erflute.editor.view.action.option.OptionSettingAction;
import org.dbflute.erflute.editor.view.action.option.notation.ChangeCapitalAction;
import org.dbflute.erflute.editor.view.action.option.notation.ChangeNotationExpandGroupAction;
import org.dbflute.erflute.editor.view.action.option.notation.ChangeStampAction;
import org.dbflute.erflute.editor.view.action.option.notation.ChangeTitleFontSizeAction;
import org.dbflute.erflute.editor.view.action.option.notation.design.ChangeDesignToFrameAction;
import org.dbflute.erflute.editor.view.action.option.notation.design.ChangeDesignToFunnyAction;
import org.dbflute.erflute.editor.view.action.option.notation.design.ChangeDesignToSimpleAction;
import org.dbflute.erflute.editor.view.action.option.notation.level.ChangeNotationLevelToColumnAction;
import org.dbflute.erflute.editor.view.action.option.notation.level.ChangeNotationLevelToDetailAction;
import org.dbflute.erflute.editor.view.action.option.notation.level.ChangeNotationLevelToExcludeTypeAction;
import org.dbflute.erflute.editor.view.action.option.notation.level.ChangeNotationLevelToNameAndKeyAction;
import org.dbflute.erflute.editor.view.action.option.notation.level.ChangeNotationLevelToOnlyKeyAction;
import org.dbflute.erflute.editor.view.action.option.notation.level.ChangeNotationLevelToOnlyTitleAction;
import org.dbflute.erflute.editor.view.action.option.notation.system.ChangeToIDEF1XNotationAction;
import org.dbflute.erflute.editor.view.action.option.notation.system.ChangeToIENotationAction;
import org.dbflute.erflute.editor.view.action.option.notation.type.ChangeViewToBothAction;
import org.dbflute.erflute.editor.view.action.option.notation.type.ChangeViewToLogicalAction;
import org.dbflute.erflute.editor.view.action.option.notation.type.ChangeViewToPhysicalAction;
import org.dbflute.erflute.editor.view.action.printer.PageSettingAction;
import org.dbflute.erflute.editor.view.action.search.SearchAction;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @author ermaster
 * @author jflute
 */
public class ERVirtualDiagramPopupMenuManager extends MenuManager {

    private final ActionRegistry actionRegistry;
    @SuppressWarnings("unused")
    private final ERVirtualDiagram virtualDiagram;

    public ERVirtualDiagramPopupMenuManager(ActionRegistry actionRegistry, final ERVirtualDiagram virtualDiagram) {
        this.actionRegistry = actionRegistry;
        this.virtualDiagram = virtualDiagram;
        Activator.debug(this, "constructor()", "...Preparing pop-up menu: actionRegistry=" + actionRegistry + ", virtualDiagram="
                + virtualDiagram.getName());

        final ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();

        final IAction changeViewToPhysicalAction = getAction(ChangeViewToPhysicalAction.ID);
        final IAction changeViewToLogicalAction = getAction(ChangeViewToLogicalAction.ID);
        final IAction changeViewToBothAction = getAction(ChangeViewToBothAction.ID);

        final IAction changeToIENotationAction = getAction(ChangeToIENotationAction.ID);
        final IAction changeToIDEF1XNotationAction = getAction(ChangeToIDEF1XNotationAction.ID);

        final IAction changeNotationLevelToOnlyTitleAction = getAction(ChangeNotationLevelToOnlyTitleAction.ID);
        final IAction changeNotationLevelToOnlyKeyAction = getAction(ChangeNotationLevelToOnlyKeyAction.ID);
        final IAction changeNotationLevelToColumnAction = getAction(ChangeNotationLevelToColumnAction.ID);
        final IAction changeNotationLevelToDetailAction = getAction(ChangeNotationLevelToDetailAction.ID);
        final IAction changeNotationLevelToExcludeTypeAction = getAction(ChangeNotationLevelToExcludeTypeAction.ID);
        final IAction changeNotationLevelToNameAndKeyAction = getAction(ChangeNotationLevelToNameAndKeyAction.ID);

        final IAction changeNotationExpandGroupAction = getAction(ChangeNotationExpandGroupAction.ID);

        final IAction changeDesignToFunnyAction = getAction(ChangeDesignToFunnyAction.ID);
        final IAction changeDesignToFrameAction = getAction(ChangeDesignToFrameAction.ID);
        final IAction changeDesignToSimpleAction = getAction(ChangeDesignToSimpleAction.ID);

        final IAction changeCapitalAction = getAction(ChangeCapitalAction.ID);
        final IAction changeTitleFontSizeAction = getAction(ChangeTitleFontSizeAction.ID);
        final IAction changeStampAction = getAction(ChangeStampAction.ID);

        final IAction changeFreeLayoutAction = getAction(ChangeFreeLayoutAction.ID);
        final IAction changeShowReferredTablesAction = getAction(ChangeShowReferredTablesAction.ID);

        final IAction undoAction = getAction(ActionFactory.UNDO);
        undoAction.setActionDefinitionId("org.eclipse.ui.edit.undo");

        final IAction redoAction = getAction(ActionFactory.REDO);
        redoAction.setActionDefinitionId("org.eclipse.ui.edit.redo");

        this.add(undoAction);
        this.add(redoAction);

        final IAction copyAction = getAction(ActionFactory.COPY);
        copyAction.setActionDefinitionId("org.eclipse.ui.edit.copy");
        this.add(copyAction);

        final IAction pasteAction = getAction(ActionFactory.PASTE);
        pasteAction.setActionDefinitionId("org.eclipse.ui.edit.paste");
        this.add(pasteAction);

        this.add(getAction(ActionFactory.DELETE));
        this.add(getAction(ActionFactory.SELECT_ALL));

        this.add(new Separator());

        this.add(getAction(ResizeModelAction.ID));
        this.add(getAction(RightAngleLineAction.ID));
        this.add(getAction(DefaultLineAction.ID));

        this.add(new Separator());

        this.add(getAction(SearchAction.ID));
        this.add(getAction(ERDiagramQuickOutlineAction.ID));

        this.add(new Separator());

        final MenuManager displayMenu = new MenuManager(DisplayMessages.getMessage("label.display"));

        final MenuManager viewModeMenu = new MenuManager(DisplayMessages.getMessage("label.view.mode"));
        viewModeMenu.add(changeViewToPhysicalAction);
        viewModeMenu.add(changeViewToLogicalAction);
        viewModeMenu.add(changeViewToBothAction);

        displayMenu.add(viewModeMenu);

        final MenuManager notationMenu = new MenuManager(DisplayMessages.getMessage("label.notation"));
        notationMenu.add(changeToIENotationAction);
        notationMenu.add(changeToIDEF1XNotationAction);

        displayMenu.add(notationMenu);

        final MenuManager notationLevelMenu = new MenuManager(DisplayMessages.getMessage("label.notation.level"));
        notationLevelMenu.add(changeNotationLevelToOnlyTitleAction);
        notationLevelMenu.add(changeNotationLevelToOnlyKeyAction);
        notationLevelMenu.add(changeNotationLevelToColumnAction);
        notationLevelMenu.add(changeNotationLevelToNameAndKeyAction);
        notationLevelMenu.add(changeNotationLevelToExcludeTypeAction);
        notationLevelMenu.add(changeNotationLevelToDetailAction);
        notationLevelMenu.add(new Separator());
        notationLevelMenu.add(changeNotationExpandGroupAction);
        displayMenu.add(notationLevelMenu);

        final MenuManager designMenu = new MenuManager(DisplayMessages.getMessage("label.design"));
        designMenu.add(changeDesignToFunnyAction);
        designMenu.add(changeDesignToFrameAction);
        designMenu.add(changeDesignToSimpleAction);
        displayMenu.add(designMenu);
        displayMenu.add(changeCapitalAction);
        displayMenu.add(changeTitleFontSizeAction);
        displayMenu.add(changeStampAction);
        this.add(displayMenu);

        this.add(new Separator());

        add(prepareImportMenu(sharedImages));
        add(prepareExportMenu(sharedImages));

        add(getAction(VirtualDiagramAddAction.ID));
        this.add(new Separator());

        // #deleted category
        //final MenuManager categoryMenu = new MenuManager(DisplayMessages.getMessage("label.category"));
        //categoryMenu.add(this.getAction(CategoryManageAction.ID));
        //categoryMenu.add(changeShowReferredTablesAction);
        //this.add(categoryMenu);

        // #thinking unused for now, needed? by jflute
        //final MenuManager vgroupMenu = new MenuManager("Table Group");
        //vgroupMenu.add(this.getAction(WalkerGroupManageAction.ID));
        //this.add(vgroupMenu);

        // #thinking unused for now, needed? by jflute
        //this.add(this.getAction(PlaceTableAction.ID));

        this.add(getAction(PageSettingAction.ID));
        add(getAction(OptionSettingAction.ID));

        this.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(IMenuManager manager) {
                undoAction.setText(DisplayMessages.getMessage("action.title.undo"));
                redoAction.setText(DisplayMessages.getMessage("action.title.redo"));

                final Settings settings = virtualDiagram.getDiagram().getDiagramContents().getSettings();

                changeViewToPhysicalAction.setChecked(false);
                changeViewToLogicalAction.setChecked(false);
                changeViewToBothAction.setChecked(false);

                if (settings.getViewMode() == Settings.VIEW_MODE_PHYSICAL) {
                    changeViewToPhysicalAction.setChecked(true);
                } else if (settings.getViewMode() == Settings.VIEW_MODE_LOGICAL) {
                    changeViewToLogicalAction.setChecked(true);
                } else {
                    changeViewToBothAction.setChecked(true);
                }

                changeToIENotationAction.setChecked(false);
                changeToIDEF1XNotationAction.setChecked(false);

                if (Settings.NOTATION_IDEF1X.equals(settings.getNotation())) {
                    changeToIDEF1XNotationAction.setChecked(true);
                } else {
                    changeToIENotationAction.setChecked(true);
                }

                changeNotationLevelToOnlyTitleAction.setChecked(false);
                changeNotationLevelToOnlyKeyAction.setChecked(false);
                changeNotationLevelToColumnAction.setChecked(false);
                changeNotationLevelToNameAndKeyAction.setChecked(false);
                changeNotationLevelToExcludeTypeAction.setChecked(false);
                changeNotationLevelToDetailAction.setChecked(false);

                if (settings.getNotationLevel() == Settings.NOTATION_LEVLE_TITLE) {
                    changeNotationLevelToOnlyTitleAction.setChecked(true);

                } else if (settings.getNotationLevel() == Settings.NOTATION_LEVLE_COLUMN) {
                    changeNotationLevelToColumnAction.setChecked(true);

                } else if (settings.getNotationLevel() == Settings.NOTATION_LEVLE_KEY) {
                    changeNotationLevelToOnlyKeyAction.setChecked(true);

                } else if (settings.getNotationLevel() == Settings.NOTATION_LEVLE_NAME_AND_KEY) {
                    changeNotationLevelToNameAndKeyAction.setChecked(true);

                } else if (settings.getNotationLevel() == Settings.NOTATION_LEVLE_EXCLUDE_TYPE) {
                    changeNotationLevelToExcludeTypeAction.setChecked(true);

                } else {
                    changeNotationLevelToDetailAction.setChecked(true);
                }

                if (settings.isNotationExpandGroup()) {
                    changeNotationExpandGroupAction.setChecked(true);
                }

                changeDesignToFunnyAction.setChecked(false);
                changeDesignToFrameAction.setChecked(false);
                changeDesignToSimpleAction.setChecked(false);

                if (settings.getTableStyle().equals(ChangeDesignToFrameAction.TYPE)) {
                    changeDesignToFrameAction.setChecked(true);

                } else if (settings.getTableStyle().equals(ChangeDesignToSimpleAction.TYPE)) {
                    changeDesignToSimpleAction.setChecked(true);

                } else {
                    changeDesignToFunnyAction.setChecked(true);
                }

                if (settings.isCapital()) {
                    changeCapitalAction.setChecked(true);
                }
                if (new BigDecimal("1.5").equals(settings.getTitleFontEm())) {
                    changeTitleFontSizeAction.setChecked(true);
                }

                if (settings.getModelProperties().isDisplay()) {
                    changeStampAction.setChecked(true);
                }

                final CategorySetting categorySettings = settings.getCategorySetting();
                if (categorySettings.isFreeLayout()) {
                    changeFreeLayoutAction.setChecked(true);
                }
                if (categorySettings.isShowReferredTables()) {
                    changeShowReferredTablesAction.setChecked(true);
                }
            }

        });
    }

    // ===================================================================================
    //                                                                              Import
    //                                                                              ======
    private MenuManager prepareImportMenu(final ISharedImages sharedImages) {
        final MenuManager importMenu = new MenuManager("Import", sharedImages.getImageDescriptor("IMG_ETOOL_IMPORT_WIZ"), "Import");
        importMenu.add(this.getAction(ImportFromDBAction.ID));

        // #deleted only from DB
        //importMenu.add(getAction(ImportFromFileAction.ID));
        return importMenu;
    }

    // ===================================================================================
    //                                                                              Export
    //                                                                              ======
    protected MenuManager prepareExportMenu(ISharedImages sharedImages) {
        final MenuManager exportMenu =
                new MenuManager(DisplayMessages.getMessage("action.title.export"), sharedImages.getImageDescriptor("IMG_ETOOL_EXPORT_WIZ"),
                        "Export");

        exportMenu.add(this.getAction(ExportToDDLAction.ID));
        exportMenu.add(this.getAction(ExportToImageAction.ID));

        // #deleted
        //exportMenu.add(this.getAction(ExportToExcelAction.ID));
        //exportMenu.add(this.getAction(ExportToHtmlAction.ID));
        //exportMenu.add(this.getAction(ExportToDictionaryAction.ID));
        //exportMenu.add(this.getAction(ExportToTranslationDictionaryAction.ID));
        //exportMenu.add(this.getAction(ExportToTestDataAction.ID));
        //exportMenu.add(this.getAction(ExportToJavaAction.ID));

        exportMenu.add(new GroupMarker("export"));

        return exportMenu;
    }

    // ===================================================================================
    //                                                                              Action
    //                                                                              ======
    private IAction getAction(ActionFactory actionFactory) {
        return this.actionRegistry.getAction(actionFactory.getId());
    }

    private IAction getAction(String id) {
        return this.actionRegistry.getAction(id);
    }
}
