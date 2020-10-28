package io.github.jmcleodfoss.explorer;

import javax.swing.JSplitPane;
import javax.swing.tree.TreeModel;

/**	This class is a specialization of BTreeWithData for block B-tree display. */
@SuppressWarnings("serial")
class BlockBTreeDisplay extends BTreeWithData
{
	/** The current block's contents */
	private BlockDescriptionDisplay blockDescriptionDisplay;

	/**	Construct a BTreeWithData object with the appropriate orientation and contents for the block B-tree display. */
	BlockBTreeDisplay()
	{
		super(JSplitPane.HORIZONTAL_SPLIT);
		blockDescriptionDisplay = new BlockDescriptionDisplay(tree);
		setDataView(blockDescriptionDisplay);
	}

	/**	Get the tree model for the block B-tree. */
	TreeModel treeModel()
	{
		return (TreeModel)pstExplorer.pst().blockBTree;
	}

	/**	Clear the tree model. */
	void reset()
	{
		super.reset();
		blockDescriptionDisplay.reset();
	}
}
