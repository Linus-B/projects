/*
 * myBookcase.js
 * List of books
 */

// The default book if no others exist.
var defaultBook = {
        "title": "The Great Gatsby",
        "author":"F. Scott Fitzgerald",
        "CRDate":"1920",
        "pages":208,
        "url":"https://ia801401.us.archive.org/view_archive.php?archive=/32/items/l_covers_0008/l_covers_0008_43.tar&file=0008432047-L.jpg"
    };

$(document).ready(function() {
    console.log("Page ready");
    pageLoadedMain();
});

/*
 * Once the DOM has loaded, load in the food from the array,
 * add click listeners to each food name.
 */
function pageLoadedMain() {
    loadBookNames();
    addBookNameListeners();
    addButtonListeners();
}




/*
 * Creates an unordered list of food names from the foods array,
 * and places it in the navigation innerHTML.
 */
function loadBookNames() {
    console.log("Length: " + localStorage.length);
    // If local storage is empty, then add the great gatsby. 
    if (!localStorage.length){
        localStorage.setItem(defaultBook.title, JSON.stringify(defaultBook));
    }
    var listing = "<ul>\n";
    for( var i = 0; i < localStorage.length; i++) {
        var book = localStorage.getItem(localStorage.key(i));
        var bookObject = JSON.parse(book);
        listing += "<li>" + bookObject.title + "</li>\n";
    }
    listing += "</ul>\n";

    $("nav").html(listing);
}

/*
 * Adds a click event listener to each food name in navigation, calling
 * the onSelect method.
 */ 
function addBookNameListeners() {
    $("li").each(function() {
        console.log( "found the li node " + $(this));
        $(this).click(onSelect);
        //aNameNode.addEventListener( "click", onSelect);
    });
}

/**
 * Adds a click function to the edit and add button. 
 */
function addButtonListeners() {
    $("#editbutton").click(onEdit);
    $("#addbutton").click(onAdd);
}

/*
 * Fills in the information in the main section given the selected food.
 */
function onSelect() {
    console.log("on Select is run")
    $("#editbutton").attr("disabled", false);
    
    // Locate the food item in the array corresponding to the selected name.
    var bookName = this.innerHTML;
    console.log("Name: " + bookName);
    targetBook = null;
    for( var index = 0; index < localStorage.length; index++) {
        if(bookName == localStorage.key(index)) {
            targetBook = JSON.parse(localStorage.getItem(localStorage.key(index)));
        }
    }    
    // Now that we've found the food, put its details in main
    $("#title").val(targetBook.title);
    $("#author").val(targetBook.author)
    $("#CRDate").val(targetBook.CRDate);
    $("#pages").val(targetBook.pages);
    $("#coverPicture").attr("src", targetBook.url);
        
}


function onEdit() {
    console.log("onEdit called");
    if( this.innerHTML == "Edit") {
        // Editing
        this.innerHTML = "Save";

        // Change to editable
        $("#author").attr("readOnly", false);
        $("#CRDate").attr("readOnly", false);
        $("#pages").attr("readOnly", false);

        // Disable the add button
        $("#addbutton").attr("disabled", true);
    }
    else {
        // Saving
        this.innerHTML = "Edit";

        var bookTitle = $("#title").val();
        $("#author").attr("readOnly", true);
        $("#CRDate").attr("readOnly", true);
        $("#pages").attr("readOnly", true);

        // Save the data to the array
        targetBook = null;
        for( var index = 0; index < localStorage.length; index++) {
            if( bookTitle == localStorage.key(index)) {
                targetBook = JSON.parse(localStorage.getItem(localStorage.key(index)));
            }
        }
        targetBook.author = $("#author").val();
        targetBook.CRDate = $("#CRDate").val();
        targetBook.pages = $("#pages").val();
        localStorage.setItem(targetBook.title, JSON.stringify(targetBook));
        
        // Enable the add button
        $("#addbutton").attr("disabled", false);
    }
}

// Loads the API request
function responseReceived(data){
    console.log(data);
    // Finds the url of the cover. 
    var url = "https://covers.openlibrary.org/b/ID/" + data.docs[0].cover_i + "-L.jpg"
    console.log(url);
    $("coverPicture").attr("src", url);
    let title = $("#title").val();
    targetBook = null;
    for( var index = 0; index < localStorage.length; index++) {
        if( title == localStorage.key(index)) {
            targetBook = JSON.parse(localStorage.getItem(localStorage.key(index)));
        }
    }
    targetBook.url = url;
    localStorage.setItem(title, JSON.stringify(targetBook));

}

function getURL(bookName){
    var url = "https://openlibrary.org/search.json?";
    $.get(url, {q: bookName}, responseReceived, "json");
}


function onAdd() {
    console.log("Called onAdd");
    if( this.innerHTML == "Add") {
        // Enter add mode
        this.innerHTML = "Save";
        var $titleNode = $("#title");
        $titleNode.val("");
        $titleNode.attr("readOnly", false);

        var $authorNode = $("#author");
        $authorNode.val("");
        $authorNode.attr("readOnly", false);

        var $CRDateNode = $("#CRDate");
        $CRDateNode.val("");
        $CRDateNode.attr("readOnly", false);

        var $pagesNode = $("#pages");
        $pagesNode.val("");
        $pagesNode.attr("readOnly", false);

        // Disable the edit button
        $("editbutton").attr("disabled", true);
    }
    else {
        // Leave add mode, save book info 
        this.innerHTML = "Add";
        var someBook = {};
        var $titleNode = $("#title");
        someBook.title = $titleNode.val();
        $titleNode.attr("readOnly", true);

        var $authorNode = $("#author");
        someBook.author = $authorNode.val();
        $authorNode.attr("readOnly", true);

        var $CRDateNode = $("#CRDate");
        someBook.CRDate = $CRDateNode.val();
        $CRDateNode.attr("readOnly", true);

        var $pagesNode = $("#pages");
        someBook.pages = $pagesNode.val();
        $pagesNode.attr("readOnly", true);

        // Finds the url and puts in storage. 
        localStorage.setItem(someBook.title, JSON.stringify(someBook));
        getURL(someBook.title);

        localStorage.setItem(someBook.title, JSON.stringify(someBook));
        
        // put the new food into the array, and recreate list
        loadBookNames();
        addBookNameListeners();
        
        // Enable the edit button
        $("editbutton").attr("disabled", false);
    }
}














