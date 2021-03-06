package io.github.jmcleodfoss.pstExtractor;

import java.util.ArrayList;
import java.util.List;

/**	The FolderBean object contains information about a single folder.
*	Note that this is not a "full" bean, in that it does not have any setters; its contents are set by other classes within the same package.
*	Note also that this does not take into account the potential tree structure of a folder hierarchy, i.e. it only shows the email messages in a folder, not the sub-folders.
*	@param	<B>	The bean type for the folder contents
*	@see	AppointmentBean
*	@see	ContactBean
*	@see	JournalEntryBean
*	@see	StickyNoteBean
*	@see	TaskBean
*	@see	PSTBean#appointments
*	@see	PSTBean#contacts
*	@see	PSTBean#journalEntries
*	@see	PSTBean#stickyNotes
*	@see	PSTBean#tasks
*/
public class FolderBean<B>
{
	/**	The folder name. */
	String name;

	/**	The folder contents. */
	List<B> contents;

	/**	Create a FolderBean. */
	public FolderBean()
	{
		name = null;
		contents = new ArrayList<B>();
	}

	/**	Retrieve the name of the folder.
	*	@return	The name of the folder.
	*/
	public String getName()
	{
		return name;
	}

	/**	Retrieve the contents of the folder.
	*	@return	The folder contents.
	*/
	public List<B> getContents()
	{
		return contents;
	}
}
