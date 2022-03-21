<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/order.css">
		<script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.0.0.js"></script>	
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">	
		
	
		
		<title>Credit Card</title>
	</head>
	
	
<body>
	
<!-- 	<div id="content"> -->
<!-- 		<form action="OrderStore" method="GET" style="text-align:center"> -->
<!-- 		<div style="height:10%"> -->
<!-- 			<input type="text" name="CardNum" id="CardNum" size="14" class="input" placeholder="Card No:"> -->
<!-- 			<input type="text" name="CardHolder" id="CardHolder" size="3" class="input" placeholder="Holder Name:"> -->
<!-- 			<input type="text" name="IssueDate" id="IssueDate" size="3" class="input" placeholder="Issue Date:">	 -->
<!-- 			<input type="text" name="CCV" id="CCV" size="3" class="input" placeholder="CCV:">	 -->
			
<!-- 		</div> -->
<!-- 	<div><input id="bt1" type="submit" value="Confirm" class="submit"></div> -->
<!-- 		</form> -->
<!-- 	</div> -->
<div id="content">
		<form action="OrderStore" method="GET" style="text-align:center">
			<input type="radio" name="payment" value="Visa" id="visa" />Visa &nbsp;
            <input type="radio" name="payment" value="Master Card" />Master Card &nbsp;
            <input type="radio" name="payment" value="American Express" />American Express &nbsp;
            <input type="radio" name="payment" value="Discover" />Discover <br /><br />
            <label>Card Number:</label>
            <input type="text" name="cardNumber" id="cardNum" size="30" value="" onblur="ValidateCreditCardNumber()" />
        
		</form>
	</div>

	
	<script type="text/javascript">
		
	

	function ValidateCreditCardNumber(){

	    var ccNum = document.getElementById("cardNum").value;
	 
	    var visaRegEx = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
	    var mastercardRegEx = /^(?:5[1-5][0-9]{14})$/;
	    var amexpRegEx = /^(?:3[47][0-9]{13})$/;
	    var discovRegEx = /^(?:6(?:011|5[0-9][0-9])[0-9]{12})$/; 
          
	    var val=$('input:radio[name="payment"]:checked').val();
        if(val==null){
         alert("Please Choose Payment Method!!");
         return false;
         }
        else if(val=="Visa"){
        	        	
        	if (visaRegEx.test(ccNum) === false ){ // Visa validation
     	       alert("Please provide a valid Visa number!");   
     	        }  
     	      else  
     	        {  
     	         alert("Thank You!");  
     	        location.href="/Payment.jsp";
     	        }    	
        }
        else if(val=="Master Card"){
        	
        	if (mastercardRegEx.test(ccNum) === false){ // MasterCard validation
    	        alert("Please provide a valid MasterCard number!");  
    	        }  
    	      else  
    	        {  
    	        alert("Thank You!"); 
    	        location.href="/Payment.jsp";
    	        } 	
        }
        else if(val=="American Express"){
        	if(amexpRegEx.test(ccNum) === false){ // Amex  validation
    	        alert("Not a valid America Express number!");  
    	        }  
    	      else  
    	        {   
    	        alert("Thank You!"); 
    	        location.href="/Payment.jsp";
    	        } 
        	    	
        }
        else if(val=="Discover"){
        	if (discovRegEx.test(ccNum) === false){ // Discover validation
    	        alert("Please provide a valid Discover number!"); 
    	        }  
    	      else  
    	        {  
    	        alert("Thank You!"); 
    	        location.href="/Payment.jsp";
    	        } 
        	     	
        }
         else{
              alert("Payment will be on Cash!");
              location.href="/Payment.jsp";
              
          }

	    

	    

	    

	    


	    }
	    
	    
	  /*  $("#bt1").click(function(){
	        var CardNum=document.getElementById("CardNum").value;
	        var CardHolder=document.getElementById("CardHolder").value;
	        var IssueDate=document.getElementById("IssueDate").value;
	        var CCV=document.getElementById("CCV").value;
	        
	        if(CardNum==null||CardHolder==null){
	         alert("Please key in Card Number");
	         return false;
	         }
	     
	         else{
	        	  alert("Payment done! Thank you!!!");
	        	  
	        	  this.close();
	        	  window.location="Payment.jsp";
	              
	          }
	                    
	     });

		*/
	</script>
	
	
</body>
</html>