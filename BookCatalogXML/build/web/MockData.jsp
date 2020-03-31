<%-- 
    Document   : MockData.jsp
    Created on : Jan 7, 2020, 11:14:58 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <link rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mock file Data</title>
        <script type="text/javascript"> 
            
function generateAuthorDetails(number){
    if(number==='')
    { alert("Enter a valid number");
        return false;
    }
    document.getElementById("submit").style.display = "none";
    document.getElementById("authorDetails").style.display = "none";
    var newDIV='';
    var bookNumbers='';
var bookDetails = document.getElementById('bookDetails');
for(var i=1;i<=number;i++)
{            newDIV=document.createElement("div");
             newDIV.setAttribute('id','newDIV');
             bookDetails.append(newDIV);
             
             var label= document.createElement("label");
             label.setAttribute('class','styleLabel');
             var labelText = document.createTextNode('Author Name '+i);
            label.appendChild(labelText);
            var input = document.createElement("input");
            input.setAttribute('type','text');
             input.setAttribute('class','inputStyle');
            input.setAttribute('name','authorName' +i);
            input.setAttribute('id','authorName' +i);
            newDIV.appendChild(label);
            newDIV.appendChild(input);
             
            newDIV.appendChild(document.createElement('br'));
            var label= document.createElement("label");
            label.setAttribute('class','styleLabel2');
             var labelText = document.createTextNode('Number of Books for Author '+i);
             label.appendChild(labelText);
            var input = document.createElement("input");
             input.setAttribute('class','inputStyle');
             input.setAttribute('id','noOfBooks' +i);
            input.setAttribute('name','noOfBooks' +i);
            input.setAttribute('type','text');
            input.setAttribute('id','noOfBooks' +i);
            newDIV.appendChild(label);
            newDIV.appendChild(input);               
}
var Next=document.createElement("input");
Next.setAttribute("type","button");
//Next.setAttribute("onclick","generateTextBox(" +number+")");
Next.setAttribute("value","Next");
Next.setAttribute("id","Next");
Next.classList.add("buttonStyle");
Next.setAttribute("onclick","generateTextBox("+number+")");
bookDetails.appendChild(Next);

  }  
 function generateTextBox(bookNumbers){
     var details=document.getElementById("details");
      document.getElementById("bookDetails").style.display = "none";
      document.getElementById("Next").style.display = "none";
    document.getElementById("submit").style.display = "none";
    document.getElementById("bookDetails").style.display = "none";
    var bookValues=[];
    for(var k=1;k<=bookNumbers;k++){
    bookValues[k-1]=document.getElementById('noOfBooks'+k).value;
}
for(var j=1;j<=bookNumbers;j++){
      for(var i=1;i<=bookValues[j-1];i++)
{          
    var bookDIV=document.createElement("div");
   
           details.append(bookDIV);
            
         var label= document.createElement("label");
        label.setAttribute('class','styleLabel3');
          var labelText = document.createTextNode('Author ' +j+ ' Book '+i+' Name');
           label.appendChild(labelText);
           
           var input = document.createElement("input");
           input.setAttribute('type','text');
           input.setAttribute('class','inputStyle');
         input.setAttribute('name','a'+j+'bName' +i);
         input.setAttribute('id','a'+j+'bName' +i);
           bookDIV.appendChild(label);
            bookDIV.appendChild(input);
            bookDIV.appendChild(document.createElement('br'));
            
            
            var label= document.createElement("label");
            label.setAttribute('class','styleLabel4');
          var labelText = document.createTextNode('Author ' +j+ ' Book '+i+' Genre');
           label.appendChild(labelText);
           var input = document.createElement("input");
           input.setAttribute('class','inputStyle');
           input.setAttribute('type','text');
         input.setAttribute('name','a'+j+'bGenre' +i);
         input.setAttribute('id','a'+j+'bGenre' +i);
           bookDIV.appendChild(label);
            bookDIV.appendChild(input);
       bookDIV.appendChild(document.createElement('br'));
       
       
            var label= document.createElement("label");
            label.setAttribute('class','styleLabel5'); 
          var labelText = document.createTextNode('Author ' +j+ ' Book '+i+' Price');
           label.appendChild(labelText);
           var input = document.createElement("input");
           input.setAttribute('class','inputStyle');
           input.setAttribute('type','text');
         input.setAttribute('name','a'+j+'bPrice' +i);
         input.setAttribute('id','a'+j+'bPrice' +i);
           bookDIV.appendChild(label);
            bookDIV.appendChild(input);
            

}}
var MockFile=document.createElement("input");
MockFile.setAttribute("type","Submit");
MockFile.setAttribute("value","Place XML File");
MockFile.classList.add("buttonStyle1");
details.appendChild(MockFile);
}

</script>
    </head>
    <body>
        <form name="mockData"  action="mockFileDataServlet" method="get">
        <h1><center>Mock File Data</center></h1>
        <div class="homeDivStyle">
        <div id="authorDetails" >Number of Authors&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="inputStyle" name="noOfAuthors" id="noOfAuthors"/></div>
            <input type="button" class="buttonStyle" onclick="generateAuthorDetails(document.getElementById('noOfAuthors').value);" value="Next" id="submit"/>
            <div id="bookDetails"></div>
            <div id="details"></div>
            </table></div>
        </form>
    </body>
</html>
