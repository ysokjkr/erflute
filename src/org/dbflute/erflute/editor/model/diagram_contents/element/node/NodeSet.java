package org.dbflute.erflute.editor.model.diagram_contents.element.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dbflute.erflute.editor.model.AbstractModel;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.ermodel.VGroup;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.image.InsertedImage;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.image.InsertedImageSet;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.note.Note;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.note.NoteSet;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.table.ERTable;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.table.TableSet;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.table.TableView;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.view.ERView;
import org.dbflute.erflute.editor.model.diagram_contents.element.node.view.ViewSet;

public class NodeSet extends AbstractModel implements Iterable<NodeElement> {

    private static final long serialVersionUID = -120487815554383179L;

    public static final String PROPERTY_CHANGE_CONTENTS = "contents";

    private final NoteSet noteSet;

    private final TableSet tableSet;

    //	private VGroupSet groupSet;

    //	private ERModelSet ermodelSet;

    private final ViewSet viewSet;

    private final List<NodeElement> nodeElementList;

    private final InsertedImageSet insertedImageSet;

    public NodeSet() {
        this.tableSet = new TableSet();
        //		this.groupSet = new VGroupSet();
        //		this.ermodelSet = new ERModelSet();
        this.viewSet = new ViewSet();
        this.noteSet = new NoteSet();
        this.insertedImageSet = new InsertedImageSet();

        this.nodeElementList = new ArrayList<NodeElement>();
    }

    public void addNodeElement(NodeElement nodeElement) {
        if (nodeElement instanceof ERTable) {
            this.tableSet.add((ERTable) nodeElement);

        } else if (nodeElement instanceof ERView) {
            this.viewSet.add((ERView) nodeElement);

        } else if (nodeElement instanceof Note) {
            this.noteSet.add((Note) nodeElement);

        } else if (nodeElement instanceof InsertedImage) {
            this.insertedImageSet.add((InsertedImage) nodeElement);

        } else if (nodeElement instanceof VGroup) {
            // do nothing
            //			this.groupSet.add((VGroup) nodeElement);
            //		} else if (nodeElement instanceof ERModel) {
            //			this.ermodelSet.add((ERModel) nodeElement);

        } else {
            System.out.println("not support " + nodeElement);
            //			throw new RuntimeException("not support " + nodeElement);
        }

        this.nodeElementList.add(nodeElement);

        this.firePropertyChange(PROPERTY_CHANGE_CONTENTS, null, null);
    }

    public void remove(NodeElement nodeElement) {
        //		if (nodeElement instanceof ERVirtualTable) {
        //			this.tableSet.remove((ERVirtualTable) nodeElement);
        //			
        //		} else
        if (nodeElement instanceof ERTable) {
            this.tableSet.remove((ERTable) nodeElement);

        } else if (nodeElement instanceof ERView) {
            this.viewSet.remove((ERView) nodeElement);

        } else if (nodeElement instanceof Note) {
            this.noteSet.remove((Note) nodeElement);

        } else if (nodeElement instanceof InsertedImage) {
            this.insertedImageSet.remove((InsertedImage) nodeElement);

            //		} else if (nodeElement instanceof VGroup) {
            // do nothing
            //			this.groupSet.remove((VGroup) nodeElement);

            //		} else if (nodeElement instanceof ERModel) {
            //			this.ermodelSet.remove((ERModel) nodeElement);

        } else {
            throw new RuntimeException("not support " + nodeElement);
        }

        this.nodeElementList.remove(nodeElement);

        this.firePropertyChange(PROPERTY_CHANGE_CONTENTS, null, null);
    }

    public boolean contains(NodeElement nodeElement) {
        return this.nodeElementList.contains(nodeElement);
    }

    public void clear() {
        this.tableSet.getList().clear();
        this.viewSet.getList().clear();
        this.noteSet.getList().clear();
        this.insertedImageSet.getList().clear();

        this.nodeElementList.clear();
    }

    public boolean isEmpty() {
        return this.nodeElementList.isEmpty();
    }

    public List<NodeElement> getNodeElementList() {
        return this.nodeElementList;
    }

    public List<TableView> getTableViewList() {
        final List<TableView> nodeElementList = new ArrayList<TableView>();

        nodeElementList.addAll(this.tableSet.getList());
        nodeElementList.addAll(this.viewSet.getList());

        return nodeElementList;
    }

    @Override
    public Iterator<NodeElement> iterator() {
        return this.getNodeElementList().iterator();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return getClass().getSimpleName() + ":{" + tableSet + "}";
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public ViewSet getViewSet() {
        return viewSet;
    }

    public NoteSet getNoteSet() {
        return noteSet;
    }

    public TableSet getTableSet() {
        return tableSet;
    }

    public InsertedImageSet getInsertedImageSet() {
        return insertedImageSet;
    }

    //	public ERModelSet getErmodelSet() {
    //		return ermodelSet;
    //	}

}