package org.dbflute.erflute.editor.view.dialog.dbexport;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbflute.erflute.Activator;
import org.dbflute.erflute.core.DisplayMessages;
import org.dbflute.erflute.core.exception.InputException;
import org.dbflute.erflute.db.DBManager;
import org.dbflute.erflute.db.DBManagerFactory;
import org.dbflute.erflute.editor.model.ERDiagram;
import org.dbflute.erflute.editor.model.dbexport.db.PreTableExportManager;
import org.dbflute.erflute.editor.model.settings.Environment;
import org.dbflute.erflute.editor.model.settings.Settings;
import org.dbflute.erflute.editor.view.dialog.dbsetting.AbstractDBSettingDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ExportDBSettingDialog extends AbstractDBSettingDialog {

    private Combo environmentCombo;

    private String ddl;

    public ExportDBSettingDialog(Shell parentShell, ERDiagram diagram) {
        super(parentShell, diagram);
        this.dbSetting = this.diagram.getDbSetting();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeBody(Composite group) {
        GridData labelLayoutData = new GridData();
        // labelLayoutData.widthHint = 130;

        // DB
        Label label = new Label(group, SWT.NONE);
        label.setLayoutData(labelLayoutData);
        label.setText(DisplayMessages.getMessage("label.tablespace.environment"));
        label.setEnabled(true);

        this.environmentCombo = new Combo(group, SWT.BORDER | SWT.READ_ONLY);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = 200;
        this.environmentCombo.setLayoutData(data);
        this.environmentCombo.setVisibleItemCount(20);
        this.environmentCombo.setEnabled(true);

        super.initializeBody(group);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialize(Composite parent) {
        super.initialize(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String doValidate() {
        if (this.settingAddButton != null) {
            this.settingAddButton.setEnabled(false);
        }

        if (isBlank(this.environmentCombo)) {
            return "error.tablespace.environment.empty";
        }

        if (!this.diagram.getDatabase().equals(this.getDBSName())) {
            return "error.database.not.correct";
        }

        return super.doValidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performOK() throws InputException {
        this.setCurrentSetting();

        String db = this.getDBSName();
        DBManager manager = DBManagerFactory.getDBManager(db);

        Connection con = null;

        try {
            this.diagram.setDbSetting(this.dbSetting);

            con = this.dbSetting.connect();

            int index = this.environmentCombo.getSelectionIndex();
            Environment environment = this.diagram.getDiagramContents().getSettings().getEnvironmentSetting().getEnvironments().get(index);

            PreTableExportManager exportToDBManager = manager.getPreTableExportManager();
            exportToDBManager.init(con, dbSetting, diagram, environment);

            exportToDBManager.run();

            Exception e = exportToDBManager.getException();
            if (e != null) {
                Activator.error(e);
                String message = e.getMessage();
                String errorSql = exportToDBManager.getErrorSql();

                if (errorSql != null) {
                    message += "\r\n\r\n" + errorSql;
                }
                ErrorDialog errorDialog = new ErrorDialog(this.getShell(), message);
                errorDialog.open();

                throw new InputException("error.jdbc.version");
            }

            this.ddl = exportToDBManager.getDdl();

        } catch (InputException e) {
            throw e;

        } catch (Exception e) {
            Activator.error(e);
            Throwable cause = e.getCause();

            if (cause instanceof UnknownHostException) {
                throw new InputException("error.server.not.found");
            }

            Activator.showExceptionDialog(e);
            throw new InputException("error.database.not.found");

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    Activator.showExceptionDialog(e);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTitle() {
        return "dialog.title.export.db";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setData() {
        super.setData();

        Settings settings = this.diagram.getDiagramContents().getSettings();

        for (Environment environment : settings.getEnvironmentSetting().getEnvironments()) {
            this.environmentCombo.add(environment.getName());
        }
        this.environmentCombo.select(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isOnlyCurrentDatabase() {
        return true;
    }

    /**
     * ddl ���擾���܂�.
     * 
     * @return ddl
     */
    public String getDdl() {
        return ddl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addListener() {
        super.addListener();

        this.environmentCombo.addSelectionListener(new SelectionAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                validate();
            }
        });
    }

}
