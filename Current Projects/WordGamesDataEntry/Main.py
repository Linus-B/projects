# Import required packages

import pytesseract
import cv2
import os
# Mention the installed location of Tesseract-OCR in your system

pytesseract.pytesseract.tesseract_cmd = '/usr/local/Cellar/tesseract/4.1.3/bin/tesseract'
inPath = "4test/"
outPath = "4testOutput/"
# Read image from which text needs to be extracted
for image_path in os.listdir(inPath):
    img = cv2.imread(os.path.join(inPath, image_path))
    print(os.path.join(inPath, image_path))
    # Preprocessing the image starts
    
    # Convert the image to gray scale
    try:
        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    except:
        print("Could not find file")  
        continue  
    # Performing OTSU threshold

    ret, thresh1 = cv2.threshold(gray, 240, 255, cv2.THRESH_OTSU | cv2.THRESH_BINARY_INV)
    
    # Specify structure shape and kernel size. 
    # Kernel size increases or decreases the area 
    # of the rectangle to be detected.
    # A smaller value like (10, 10) will detect 
    # each word instead of a sentence.

    rect_kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (8, 8))
    
    # Applying dilation on the threshold image

    dilation = cv2.dilate(thresh1, rect_kernel, iterations = 2)
    
    # Finding contours

    contours, hierarchy = cv2.findContours(dilation, cv2.RETR_EXTERNAL, 

                                                    cv2.CHAIN_APPROX_NONE)
    
    # Creating a copy of image

    im2 = img.copy()
    
    # A text file is created and flushed
    output_file_name = image_path + ".txt"
    outputPath = os.path.join(outPath, output_file_name)
    file = open(outputPath, "w+")

    file.write("")

    file.close()
    # Looping through the identified contours
    # Then rectangular part is cropped and passed on
    # to pytesseract for extracting text from it
    # Extracted text is then written into the text file

    for cnt in contours:

        x, y, w, h = cv2.boundingRect(cnt)

        

        # Drawing a rectangle on copied image

        rect = cv2.rectangle(im2, (x, y), (x + w, y + h), (0, 255, 0), 2)

        

        # Cropping the text block for giving input to OCR

        cropped = im2[y:y + h, x:x + w]

        

        # Open the file in append mode

        file = open(outputPath, "a")

        

        # Apply OCR on the cropped image
        custom_config = r'--oem 3 --psm 6 outputbase digits'
        text = pytesseract.image_to_string(cropped, config=custom_config)

        

        # Appending the text into file

        file.write(text)

        file.write("\n")

        

        # Close the file

        file.close