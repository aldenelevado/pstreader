#!/bin/bash

# Go through each PST file in the directory $input_dir, processing them by running various pst and xml class main functions on thim.
#
# Run as:
# 	extras/test.sh
#
# jar files to be tested are $lib_dir
# results are placed in $results_dir

# Locations used
declare input_dir=test-pst-files
declare results_dir=test-output
declare lib_dir=build/lib

# Output file for tracking / timing
declare stats=$results_dir/stats.txt

# Jar files
declare pst_jar=$lib_dir/pst.jar
declare xml_jar=$lib_dir/xml.jar
declare util_jar=$lib_dir/util.jar

CheckPrerequisite() {
	which "$1" > /dev/null
	if [[ $? != 0 ]]
	then
		echo "Missing prerequisite $1"
		return 0
	fi
	return 1
}

GetTestDirectory() {
	declare temp=$(basename "$1")
	echo "$results_dir/${temp%.pst}"
}

TestModule() {
	declare cp=$1
	shift
	declare class=$1
	shift

	declare output=$(GetTestDirectory "$1")/${class#io/github/jmcleodfoss/*/*}.out
	echo "
"`date +%H:%M:%S`": starting $class test" >> $stats
	java $optionsi -cp "$cp" $class "$@" > "$output"
	echo `date +%H:%M:%S`": done $class test" >> $stats
}

TestPSTFile() {
	declare pst="$1"
	declare output_dir=$(GetTestDirectory "$pst")
	if [ ! -d "$output_dir" ]; then
		mkdir "$output_dir"
	fi
	echo "Testing $pst; output directory $output_dir" >> $stats

	# These were necessary on the Mac but don't seem to be on the Win laptop.
	#options="-Xmx2g -Xms2g"
	options=""

	TestModule $pst_jar io/github/jmcleodfoss/pst/BlockBTree "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/BTreeOnHeap "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/Folder "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/GUID "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/Header "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/HeapOnNode "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/NameToIDMap "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/NodeBTree "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/PropertyContext "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/SimpleBlock "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/SubnodeBTree "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/TableContext "$pst"
	TestModule $pst_jar io/github/jmcleodfoss/pst/XBlock "$pst"

	TestModule "$xml_jar;$util_jar" io/github/jmcleodfoss/xml/PSTIPFFolderTypeToXML "$pst" contact
	if [ $xsltproc_found ]; then
		mv "$output_dir/PSTIPFFolderTypeToXML.out" "$output_dir/PSTIPFFolderTypeToXML.xml"
		xsltproc xslt/pst_contacts_to_html.xml "$output_dir/PSTIPFFolderTypeToXML.xml" > "$output_dir/contacts.html"
	fi

	TestModule "$xml_jar;$util_jar" io/github/jmcleodfoss/xml/PSTToXML "$pst"
	if [ $xsltproc_found ]; then
		mv "$output_dir/PSTToXML.out" "$output_dir/PSTToXML.xml"
		xsltproc xslt/psttohtml.xml "$output_dir/PSTToXML.xml" > "$output_dir/pst.html"
	fi

	options=""
}

CheckPrerequisite xsltproc
declare xsltproc_found=$?

rm -rf $results_dir
if [ ! -d $results_dir ]; then
	mkdir $results_dir
fi

echo "Starting tests at " `date +%H:%M:%S` > $stats

for pst in "$input_dir"/*.pst; do
	TestPSTFile "$pst"
done

echo "Ending tests at " `date +%H:%M:%S` >> $stats

pwd
declare result=`grep -e java\.lang\..*Exception -e [a-n]Exception $results_dir/*/*.{out,xml}`
if [[ $? -gt 0 ]]; then
	echo "Errors found\n$result"
fi