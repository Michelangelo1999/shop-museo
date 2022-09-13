//<!DOCTYPE html>
//<html>
//  <body>
//    <div>
//      <p>Convert this text to PDF.</p>
//    </div>
//    <div id="hidden-element">This will not be printed</div>
//  </body>
//</html>

document.getElementById('pdf').addEventListener("click", doPdf);


function doPdf() {
	const docElement = document.getElementById('ciao');
	
	html2pdf().from(docElement).save();
}

