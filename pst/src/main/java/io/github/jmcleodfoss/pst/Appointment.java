package io.github.jmcleodfoss.pst;

/**	The Appointment class holds information about a calendar entry.
*	@see	io.github.jmcleodfoss.pst.Contact
*	@see	io.github.jmcleodfoss.pst.JournalEntry
*	@see	io.github.jmcleodfoss.pst.Message
*	@see	io.github.jmcleodfoss.pst.StickyNote
*	@see	io.github.jmcleodfoss.pst.Task
*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/f6ab1613-aefe-447d-a49c-18217230b148">MX-OXPROPS: Exchange Server Protocols Master Property List</a>
*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxcmsg/7fd7ec40-deec-4c06-9493-1bc06b349682">MS-OXCMSG: Message and Attachment Object Protocol Specification</a>
*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/office_file_formats/ms-pst/1042af37-aaa4-4edc-bffd-90a1ede24188">MS-PST Section 2.4.5: Message Objects</a>
*/
public class Appointment extends MessageObject
{
	/**	The property ID under which to look up the AppointmentStartWhole property. */
	private static int AppointmentStartWholeLID = PropertyLIDs.UNKNOWN;

	/**	The property ID under which to look up the AppointmentEndWhole property. */
	private static int AppointmentEndWholeLID = PropertyLIDs.UNKNOWN;

	/**	The property ID under which to look up the AppointmentDuration property. */
	private static int AppointmentDurationLID = PropertyLIDs.UNKNOWN;

	/**	The property ID under which to look up the RecurrencePattern property. */
	private static int RecurrencePatternLID = PropertyLIDs.UNKNOWN;

	/**	The property ID under which to look up the Recurring property. */
	private static int RecurringLID = PropertyLIDs.UNKNOWN;

	/**	The property ID under which to look up the RecurrenceType property. */
	private static int RecurrenceTypeLID = PropertyLIDs.UNKNOWN;

	/**	The property ID under which to look up the ReminderDelta property. */
	private static int ReminderDeltaLID = PropertyLIDs.UNKNOWN;

	/**	The start time of the appointment in UTC. */
	public final java.util.Date startTime;

	/**	The end time of the appointment in UTC. */
	public final java.util.Date endTime;

	/**	The duration of the appointment. */
	public final int duration;

	/**	The recurrence pattern of the appointment. */
	public final String recurrencePattern;

	/**	Is this a recurring appointment? */
	public final boolean fRecurring;

	/**	The recurrence type. */
	public final int recurrenceType;

	/**	How long before the appointment time to provide a reminder. */
	public final int reminderDelta;

	/**	Create a message object for the given row in the folder contents table.
	*	@param	contentsTable	The containing folder's contents table.
	*	@param	row	The row of the contents table from which to create the appointment.
	*	@param	nbt		The PST file's node B-Tree.
	*	@param	pstFile		The PST file's header, input stream, etc.
	*	@throws	NotHeapNodeException			A node which is not a heap node was found while reading the appointment.
	*	@throws	UnknownClientSignatureException		An unknown client signature was found while reading the appointment.
	*	@throws	UnparseablePropertyContextException	A bad / corrupt property context was found while reading the appointment.
	*	@throws	UnparseableTableContextException	A bad / corrupt table context was found while reading the appointment.
	*	@throws	java.io.IOException			An I/O error was encountered while reading the appointment.
	*/
	Appointment(final TableContext contentsTable, final int row, final NodeMap nbt, final PSTFile pstFile)
	throws
		NotHeapNodeException,
		UnknownClientSignatureException,
		UnparseablePropertyContextException,
		UnparseableTableContextException,
		java.io.IOException
	{
		super(contentsTable, row, nbt, pstFile);

		startTime = (java.util.Date)contentsTable.get(row, AppointmentStartWholeLID);
		endTime = (java.util.Date)contentsTable.get(row, AppointmentEndWholeLID);

		Object o = contentsTable.get(row, AppointmentDurationLID);
		duration = o == null ? 0 : (Integer)o;

		recurrencePattern = (String)contentsTable.get(row, RecurrencePatternLID);

		o = contentsTable.get(row, RecurringLID);
		fRecurring = o == null ? false : (Boolean)o;

		o = contentsTable.get(row, RecurrenceTypeLID);
		recurrenceType = o == null ? 0 : (Integer)o;

		o = contentsTable.get(row, ReminderDeltaLID);
		reminderDelta = o == null ? 0 : (Integer)o;
	}

	/**	Save named property IDs for IDs of interest.
	*	@param	namedProperties	The list of named properties.
	*	@param	fUnicode	Whether the current PST file is a Unicode file or not.
	*/
	static void initConstants(NameToIDMap namedProperties, boolean fUnicode)
	{
		AppointmentStartWholeLID = namedProperties.id(PropertyLIDs.AppointmentStartWhole, DataType.TIME);
		AppointmentEndWholeLID = namedProperties.id(PropertyLIDs.AppointmentEndWhole, DataType.TIME);
		AppointmentDurationLID = namedProperties.id(PropertyLIDs.AppointmentDuration, DataType.INTEGER_32);
		RecurrencePatternLID = namedProperties.id(PropertyLIDs.RecurrencePattern, fUnicode ? DataType.STRING : DataType.STRING_8);
		RecurringLID = namedProperties.id(PropertyLIDs.Recurring, DataType.BOOLEAN);
		RecurrenceTypeLID = namedProperties.id(PropertyLIDs.RecurrenceType, DataType.INTEGER_32);
		ReminderDeltaLID = namedProperties.id(PropertyLIDs.ReminderDelta, DataType.INTEGER_32);
	}

	/**	Create a String representation of an appointment (used primarily for testing)
	*	@return	A String giving some information about this appointment.
	*/
	@Override
	public String toString()
	{
		return String.format("%s, %s, %d", subject, startTime, duration);
	}

	/**	Test the Appointment class by iterating through the appointments
	* 	@param args	The files to test
	*/
	public static void main(final String[] args)
	{
		test("io.github.jmcleodfoss.pst.Appointment", args);
	}
}
