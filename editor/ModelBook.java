package editor;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTabbedPane;

import model.Model;

public class ModelBook extends JTabbedPane {
	Editor editor;
	
	public ModelBook(Editor _editor) {
		super();
		editor = _editor;
	}

	private ModelBookPage getModelBookPage(Model m) {
		for (int i = 0; i < getComponentCount(); i++) {
			ModelBookPage page = (ModelBookPage)getComponent(i);
			if (m.equals(page.getModel()))
				return page;
		}
			
		return null;
	}
	
	private void removeOrphanBookPages() {
		Vector orphans = new Vector();
		for (int i = 0; i < getComponentCount(); i++) {
			ModelBookPage page = (ModelBookPage)getComponent(i);
			Model model = page.getModel();
			if (!editor.getSelectedModels().contains(model))
				orphans.add(page);
		}
		
		Iterator i = orphans.iterator();
		while (i.hasNext()) {
			remove((ModelBookPage)i.next());
		}
	}

	public void updateUI() {
		Vector missingModels = new Vector();
		
		if (editor != null) {
			Iterator i = editor.getSelectedModels().iterator();
			while (i.hasNext()) {
				Model m = (Model)i.next();
				ModelBookPage page = getModelBookPage(m);
				if (page != null) {
					page.updateUI();
				}
				else	
					missingModels.add(m);
			}
			
			removeOrphanBookPages();

			i = missingModels.iterator();
			while (i.hasNext()) {
				Model m = (Model)i.next();
				add(m.getName(), getPropertiesPage(m));
			}
		}

		super.updateUI();
	}

	/**
	 * This method initializes jPane	
	 * 	
	 * @return javax.swing.JPane	
	 */
	private ModelBookPage getPropertiesPage(Model m) {
		return new ModelBookPage(m);
	}
}
