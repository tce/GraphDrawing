package gui.actions.main.frame;

import gui.main.frame.MainFrame;
import gui.model.GraphModel;
import gui.view.GraphView;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class LoadAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private XStream xstream;

	public LoadAction(){
		putValue(NAME, "Load");
		putValue(SHORT_DESCRIPTION, "Load graph");
		xstream = new XStream(new StaxDriver());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fileChooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			    "Graph files", "graph");
		fileChooser.setFileFilter(filter);
		
		File f;
		if (fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
			f = fileChooser.getSelectedFile();
		}
		else
			return;
		
		GraphModel model = (GraphModel) xstream.fromXML(f);
		GraphView view = new GraphView(model);
		System.out.println(model.getGraph().getVertices());
		MainFrame.getInstance().addDiagram(view);
		
	}

}
