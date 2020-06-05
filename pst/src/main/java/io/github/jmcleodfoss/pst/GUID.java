package io.github.jmcleodfoss.pst;

/** The GUID class holds a PST GUID.
*	@see	"[MS-PST] Outlook Personal Folders (.pst) File Format v20110608, section 2.5"
*	@see	<a href="http://msdn.microsoft.com/en-us/library/ff386696(v=office.12).aspx">Calculated Properties (MSDN)</a>
*	@see	"[MS-OXPROPS] Exchange Server Protocols Master Property List v20101026 section 1.3.2"
*	@see	<a href="http://msdn.microsoft.com/en-us/library/gg318108.aspx">Commonly Used Property Sets (MSDN)</a>
*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
*	@see	<a href="https://github.com/Jmcleodfoss/pstreader/blob/master/pst/src/main/java/io/github/jmcleodfoss/GUID.java">Original version in pstreader</a>
*/
class GUID {

	/** Known GUID names. */
	static private final java.util.HashMap<GUID, String> names = new java.util.HashMap<GUID, String>();

	/** The size of a GUID, in bytes */
	static final int SIZE = 16;

	/** The null GUID (used for unassigned or unknown GUIDs). */
	static final GUID PS_NULL = createGUID(new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00});
	static { names.put(PS_NULL, "uninitialized"); }

	/** The public strings property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PS_PUBLIC_STRINGS = createGUID(new byte[]{0x00, 0x02, 0x03, 0x29, 0x00, 0x00, 0x00, 0x00, (byte)0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x46});
	static { names.put(PS_PUBLIC_STRINGS, "PS_PUBLIC_STRINGS"); }

	/** The common property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_COMMON = createGUID(new byte[]{0x00, 0x06, 0x20, 0x08, 0x00, 0x00, 0x00, 0x00, (byte)0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x46});
	static { names.put(PSETID_COMMON, "PSETID_COMMON"); }

	/** The address property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_ADDRESS = createGUID(new byte[]{0x00, 0x06, 0x20, 0x04, 0x00, 0x00, 0x00, 0x00, (byte)0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x46});
	static { names.put(PSETID_ADDRESS, "PSETID_ADDRESS"); }

	/** The appointment property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_APPOINTMENT = createGUID(new byte[]{0x00, 0x06, 0x20, 0x02, 0x00, 0x00, 0x00, 0x00, (byte)0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x46});
	static { names.put(PSETID_APPOINTMENT, "PSETID_APPOINTMENT"); }

	/** The Calendar Assistent property set GUID */
	static final GUID PSETID_CALENDAR_ASSISTANT = createGUID(new byte[]{0x11, 0x00, 0x0E, 0x07, (byte)0xB5, 0x1B, 0x40, (byte)0xD6, (byte)0xAF, 0x21, (byte)0xCA, (byte)0xA8, (byte)0x5E, (byte)0xDA, (byte)0xB1, (byte)0xD0});
	static { names.put(PSETID_CALENDAR_ASSISTANT, "PSETID_CALENDAR_ASSISTANT"); }

	/**	The meetings property set GUID. Note that the documented value does not match what has been observed in PST files:
	*	Documented:	6ED8DA90-450B-101B-98DA-00AA003F1305
	*	Observed:	6ED8DA90-0B45-101B-98DA-00AA003F1305
	*	The observed value is the one we use; the other is commented out as a reminder
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_MEETING = createGUID(new byte[]{0x6E, (byte)0xD8, (byte)0xDA, (byte)0x90, 0x0B, 0x45, 0x1B, 0x10, (byte)0x98, (byte)0xDA, 0x00, (byte)0xAA, 0x00, 0x3F, 0x13, 0x05});
//	static final GUID PSETID_MEETING = createGUID(new byte[]{0x6E, (byte)0xD8, (byte)0xDA, (byte)0x90, 0x45, 0x0B, 0x1B, 0x10, (byte)0x98, (byte)0xDA, 0x00, (byte)0xAA, 0x00, 0x3F, 0x13, 0x05});
	static { names.put(PSETID_MEETING, "PSETID_MEETING"); }

	/** The journal property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_LOG = createGUID(new byte[]{0x00, 0x06, 0x20, 0x0a, 0x00, 0x00, 0x00, 0x00, (byte)0xc0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x46});
	static { names.put(PSETID_LOG, "PSETID_LOG"); }

	/** The messaging property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_MESSAGING = createGUID(new byte[]{0x41, (byte)0xf2, (byte)0x8f, 0x13, (byte)0x83, (byte)0xf4, 0x41, 0x14, (byte)0xa5, (byte)0x84, (byte)0xee, (byte)0xdb, 0x5a, 0x6b, 0x0b, (byte)0xff});
	static { names.put(PSETID_MESSAGING, "PSETID_MESSAGING"); }

	/** The sticky notes property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_NOTE = createGUID(new byte[]{0x00, 0x06, 0x20, 0x0E, 0x00, 0x00, 0x00, 0x00, (byte)0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x46});
	static { names.put(PSETID_NOTE, "PSETID_NOTE"); }

	/** The task property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_TASK = createGUID(new byte[]{0x00, 0x06, 0x20, 0x03, 0x00, 0x00, 0x00, 0x00, (byte)0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x46});
	static { names.put(PSETID_TASK, "PSETID__TASK"); }

	/** The Unified Messaging property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_UNIFIED_MESSAGING = createGUID(new byte[]{0x44, 0x42, (byte)0x85, (byte)0x8e, (byte)0xa9, (byte)0xe3, 0x4e, (byte)0x80, (byte)0xb9, 0x00, 0x31, (byte)0x7a, 0x21, 0x0c, (byte)0xc1, 0x5b});
	static { names.put(PSETID_UNIFIED_MESSAGING, "PSETID_UNIFIED_MESSAGING"); }

	/** The MAPI property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PS_MAPI = createGUID(new byte[]{0x00, 0x02, 0x03, 0x28, 0x00, 0x00, 0x00, 0x00, (byte)0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x46});
	static { names.put(PS_MAPI, "PS_MAPI"); }

	/** The Air Sync property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_AIRSYNC = createGUID(new byte[]{0x71, 0x03, 0x55, 0x49, 0x07, 0x39, 0x4d, (byte)0xcb, (byte)0x91, 0x63, 0x00, (byte)0xf0, 0x58, 0x0d, (byte)0xbb, (byte)0xdf});
	static { names.put(PSETID_AIRSYNC, "PSETID_AIRSYNC"); }

	/** The Sharing property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_SHARING = createGUID(new byte[]{0x00, 0x06, 0x20, 0x40, 0x00, 0x00, 0x00, 0x00, (byte)0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x46});
	static { names.put(PSETID_SHARING, "PSETID_SHARING"); }

	/** The XML Extracted Entities property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_XML_EXTRACTED_ENTITIES = createGUID(new byte[]{0x23, 0x23, (byte)0x96, 0x08, 0x68, 0x5d, 0x47, 0x32, (byte)0x9c, 0x55, 0x4c, (byte)0x95, (byte)0xcb, 0x4e, (byte)0x8e, 0x33});
	static { names.put(PSETID_XML_EXTRACTED_ENTITIES, "PSETID_XML_EXTRACTED_ENTITIES"); }

	/** The attachment property set GUID
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PSETID_ATTACHMENT = createGUID(new byte[]{(byte)0x96, 0x35, 0x7f, 0x7f, 0x59, (byte)0xe1, 0x47, (byte)0xd0, (byte)0x99, (byte)0xa7, 0x46, 0x51, 0x5c, 0x18, 0x3b, 0x54});
	static { names.put(PSETID_ATTACHMENT, "PSETID_ATTACHMENT"); }

	/** The PostRss property set GUID */
	static final GUID PSETID_POST_RSS = createGUID(new byte[]{0x00, 0x06, 0x20, 0x41, 0x00, 0x00, 0x00, 0x00, (byte)0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x46});
	static { names.put(PSETID_POST_RSS, "PSETID_POST_RSS"); }

	/** The (unknown use) property set GUID */
	static final GUID PS_INTERNAL = createGUID(new byte[]{(byte)0xc1, (byte)0x84, 0x32, (byte)0x81, (byte)0x85, 0x05, (byte)0xd0, 0x11, (byte)0xb2, (byte)0x90, 0x00, (byte)0xaa, 0x00, 0x3c, (byte)0xf6, (byte)0x76});
	// Don't track the name for this until we have something more precise

	/** The ProviderUID for a one-off recipient in a distribution list.
	*	@see	<a href="https://docs.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxprops/cc9d955b-1492-47de-9dce-5bdea80a3323">MS-OXPROPS Section 1.3.2: Commontly Used Property Sets</a>
	*/
	static final GUID PROVIDER_UID_ONE_OFF = createGUID(new byte[]{(byte)0x81, (byte)0x2b, (byte)0x1f, (byte)0xa4, (byte)0xbe, (byte)0xa3, (byte)0x10, (byte)0x19, (byte)0x9d, (byte)0x6e, (byte)0x00, (byte)0xdd, (byte)0x01, (byte)0x0f, (byte)0x54, (byte)0x02});

	/** The actual GUID */
	final byte[] guid;

	/** Create a GUID from the bytes in the given array.
	*	@param	arr	The array of bytes from which to construct the GUID.
	*/
	GUID(byte[] arr)
	{
		this(arr, 0);
	}

	/** Create a GUID from the bytes at the given offset in the given array.read in from the PST file. Note that the first four
	*   bytes form a little-endian 16 bit value, and the remaining bytes form big-endian values.
	*	@param	arr	The array of bytes containing the GUID
	*	@param	offset	The offset to the start of the GUID in arr.
	*/
	GUID(byte[] arr, int offset)
	{
		byte[] guid = new byte[SIZE];
		int iSrc, iDest;
		for (iSrc = offset, iDest = 0; iDest < 4; ++iSrc, ++iDest)
			guid[3-iDest] = arr[iSrc];
		for (; iDest < SIZE; ++iSrc, ++iDest)
			guid[iDest] = arr[iSrc];
		this.guid = guid;
	}

	/** Create a GUID from the given bytes assuming they are in the correct
	*   order (normally, bytes 0-3 are little-endian and the constructor
	*   reverses these, but it is convenient here to show them in the order
	*   they appear to make it easier to check them visually)
	*	@param	arr	The array of bytes containing the GUID
	*	@return	A GUID object consisting of the bytes passed in.
	*/
	private static GUID createGUID(byte[] arr)
	{
		byte[] arr2 = {	arr[ 3], arr[ 2], arr[ 1], arr[ 0],
				arr[ 4], arr[ 5], arr[ 6], arr[ 7],
				arr[ 8], arr[ 9], arr[10], arr[11],
				arr[12], arr[13], arr[14], arr[15] };
		return new GUID(arr2);
	}

	/** Compare two GUIDs.
	*	@param	o	The other GUID to check.
	*	@return	false if the other guid differs from this one, true if it is the same as this one.
	*/
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;

		if (!(o instanceof GUID))
			return false;

		if (o == null)
			return this == null;

		final GUID guid = (GUID)o;
		for (int i = 0; i < this.guid.length; ++i) {
			if (this.guid[i] != guid.guid[i])
				return false;
		}

		return true;
	}

	/** Calculate hashcode.
	*	@return	Hashcode for the guid.
	*/
	@Override
	public int hashCode()
	{
		int hashcode = 0;
		for (int i = 0; i < guid.length; ++i) {
			hashcode += guid[i];
		}

		return hashcode;
	}

	/** Get the name for a GUID
	*	@param	guid	The GUID to get the name for
	*	@return	The GUID name, if known, otherwise the string representation of the GUID
	*	@see #names
	*/
	static public String name(GUID guid)
	{
		if (names.containsKey(guid))
			return names.get(guid);

		return guid.toString();
	}

	/** Obtain a string representation of this GUID.
	*	@return	A string describing the GUID in PST "Canonical" format (00000000-0000-0000-0000-0000000000).
	*/
	@Override
	public String toString()
	{
		final int[] blockOffsets = {4, 6, 8, 10, SIZE};
		String s = new String("");

		int i = 0;
		for (int b = 0; b < blockOffsets.length; ++b) {
			if (b > 0)
				s += "-";

			for (; i < blockOffsets[b]; ++i)
				s += String.format("%02x", guid[i] & 0xff);
		}

		return s;
	}

	/** Test this class by printing out the known GUIDs
	*	@param	args	The command line arguments to the test application (unused).
	*/
	public static void main(String[] args)
	{
		String format = "%25s\t%s\n";

		System.out.printf(format, "Name", "GUID");
		System.out.printf(format, "____", "____");
		System.out.printf(format, "Null", PS_NULL);
		System.out.printf(format, "Public Strings", PS_PUBLIC_STRINGS);
		System.out.printf(format, "Common", PSETID_COMMON);
		System.out.printf(format, "Address", PSETID_ADDRESS);
		System.out.printf(format, "Appointment", PSETID_APPOINTMENT);
		System.out.printf(format, "Calendar Assistant", PSETID_CALENDAR_ASSISTANT);
		System.out.printf(format, "Meeting", PSETID_MEETING);
		System.out.printf(format, "Journal", PSETID_LOG);
		System.out.printf(format, "Messaging", PSETID_MESSAGING);
		System.out.printf(format, "Task", PSETID_TASK);
		System.out.printf(format, "Unified Messaging", PSETID_UNIFIED_MESSAGING);
		System.out.printf(format, "Note", PSETID_NOTE);
		System.out.printf(format, "MAPI", PS_MAPI);
		System.out.printf(format, "Air Sync", PSETID_AIRSYNC);
		System.out.printf(format, "Sharing", PSETID_SHARING);
		System.out.printf(format, "XML Extracted Entities", PSETID_XML_EXTRACTED_ENTITIES);
		System.out.printf(format, "Attachment", PSETID_ATTACHMENT);
		System.out.printf(format, "PostRss", PSETID_POST_RSS);
		System.out.printf(format, "Internal", PS_INTERNAL);
	}
}