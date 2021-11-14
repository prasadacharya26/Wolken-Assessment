function validateField(){
	var name=$('#brandName').val();
	var modelNo=$('#modelNo').val();
	var modelName=$('#modelName').val();
	var type=$('#type').val();
	var ram=$('#ram').val();
	var rom=$('#rom').val();
	var price=$('#price').val();
	var available=$('#availabele').val();
	
	if(name == "" ){
        $('#nameValid').html("Invalid brand name").css("color", "red");
    }
    else if(modelNo == ""){
        $('#modelnoValid').html("Invalid model number.").css("color", "red");
    }
    else if(modelName == ""){
         $('#modelnameValid').html("Invalid model name.").css("color", "red");
    }
    else  if(type != ""){
        $('#brandtypeValid').html("Invalid brand type").css("color", "red");
    }
    else if(ram != ""){
        $('#ramValid').html("Invalid RAM.").css("color", "red");
    }
    else if(rom != "" && rom.match(number)){
        $('#romValid').html("Invalid ROM.").css("color", "red");
    }
    else if(price != "" && price.match(number)){
        $('#priceValid').html("Invalid Price.").css("color", "red");
    }
	if(available != ""){
        $('#availableValid').html('&#10004;').css("color", "green");
    }else{
        $('#availableValid').html("Select your availability.").css("color", "red");
    }
}

function validateName(){
    var name=$('#brandName').val();
    var lowerCaseLetters = /^[a-z]+$/;
    if(name != ""){
        $('#nameValid').html('&#10004;').css("color", "green");
    }else{
        $('#nameValid').html("Invalid brand name").css("color", "red");
    }
}

function validateModelNumber(){
    var modelNo=$('#modelNo').val();
    var number = /^[0-9]+$/;
    if(modelNo != ""){
        $('#modelnoValid').html('&#10004;').css("color", "green");
    }else{
        $('#modelnoValid').html("Invalid model number.").css("color", "red");
    }
}

function validateModelName(){
    var modelName=$('#modelName').val();
    if(modelName != "" ){
        $('#modelnameValid').html('&#10004;').css("color", "green");
    }else{
        $('#modelnameValid').html("Invalid model name.").css("color", "red");
    }
}

function validateBrandType(){
    var type=$('#type').val();
    var lowerCaseLetters = /^[a-z]+$/;
    if(type !=""){
        $('#brandtypeValid').html('&#10004;').css("color", "green");
    }else{
        $('#brandtypeValid').html("Invalid brand type").css("color", "red");
    }
}

function validateRAM(){
    var ram=$('#ram').val();
    var number = /^[0-9]+$/;
    if(ram != "" ){
        $('#ramValid').html('&#10004;').css("color", "green");
    }else{
        $('#ramValid').html("Invalid RAM.").css("color", "red");
    }
}

function validateROM(){
    var rom=$('#rom').val();
    var number = /^[0-9]+$/;
    if(rom != "" ){
        $('#romValid').html('&#10004;').css("color", "green");
    }else{
        $('#romValid').html("Invalid ROM.").css("color", "red");
    }
}

function validatePrice(){
    var price=$('#price').val();
    var number = /^[0-9]+$/;
    if(price != ""){
        $('#priceValid').html('&#10004;').css("color", "green");
    }else{
        $('#priceValid').html("Invalid Price.").css("color", "red");
    }
}