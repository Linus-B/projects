;; Linus Bendel-Stenzel 

;; prints numbers between those indexes in a list
(defun print-between (lst start end count)
    ;;post creation note - can get rid of Count by comparing a return value of recursion + 1
    (if (null lst)
        0
            (if (and (> count start) (< count end))
            (and (princ (car lst)) (print-between (cdr lst) start end (+ count 1)))
            (print-between (cdr lst) start end (+ count 1))
            )
    )
)

;; prints a row given the list and row number
(defun print-row (lst row)
    (print-between lst (-(*(- row 1) 3) 1) (* row 3) 0)
    (fresh-line)
)


;; prints a given 3 row board. Could adjust this for an n size board by making it recursive. 
(defun print-board (lst)
    (fresh-line)
    (print-row lst 1)
    (print-row lst 2)
    (print-row lst 3)
    (format T " ")
    lst

)

;; Changes a 1 to 0 and 0 to 1
(defun toggle (light)
    (if (= light 1)
    0
    1)
)

;; toggles a light at certain index
;; if index is out of bounds, then ignores it. 
(defun toggle-light (lst index)
    (if (or (< index 0) (> index 8)) lst 
        (if (= index 0)
        (cons (toggle (car lst)) (cdr lst))
        (cons (car lst) (toggle-light (cdr lst) (- index 1)))))
)

;; toggles a whole list of values
(defun toggle-multiple (lst index-lst)
    (if (null index-lst)
    lst
    (toggle-multiple (toggle-light lst (car index-lst)) (cdr index-lst)))
)

;; Checks if all lights are out
(defun are-lights-out (lst)
    (if (null lst)
        t
        (if (= (car lst) 1)
        nil
        (are-lights-out (cdr lst)))
    )
)

;; creates a random board of length len
(defun random-board (len)
    (if (= len 0)
    nil
    (cons (random 2) (random-board (- len 1))))
)

;; Creates a board
;; if the board is blank, then re-generate it
(defun generate-board ()
    (let ((board (random-board 9)))
        (if (are-lights-out board)
        (generate-board)
        board
    ))
)
;; given a central number, creates a list of all the numbers around it that should be toggled
(defun select-group (center-number)
    (cond
        ((= (mod center-number 3) 1)(list 
            (- center-number 4) (- center-number 1) center-number (+ center-number 2))
        )
        ((= (mod center-number 3) 2)(list 
            (- center-number 4) (- center-number 1) center-number (+ center-number 2) (- center-number 2))
        )
        ((= (mod center-number 3) 0)(list 
            (- center-number 4) (- center-number 1) (+ center-number 2) (- center-number 2))
        )
    )
)

;; Prompts user for a number
;; asks again if number is outside the bounds 1-9
(defun get-input (prompt)
  (format T prompt)
  (let ( (usernum (read)))
    (if (or (< usernum 1) (> usernum 9))
       (get-input "Number must be 1-9. Enter again: ")
       usernum
    )
  )
)

;; Descibes a turn of the game. Checks if won, then asks for move,
;; then toggles the lights and starts the next turn. 
(defun one-turn (board message count)
    (if (are-lights-out board)
    (format T "You won the game!")
    (and (print-board board)
    (let ( 
          (move (get-input message))
         )
        (one-turn (toggle-multiple board (select-group move))
                "Please enter a position (1-9)"
                (+ count 1)))
    ))
)

;; Given a list of indexes, goes through and activates them if the light above it is on
(defun chase-rows (board index-lst)
(if (null index-lst)
    board
    (chase-rows 
        (if (= (nth (- (car index-lst) 3) board) 1)
            (toggle-multiple (print-board board) (select-group (+ 1 (car index-lst))))
            board)
    (cdr index-lst))
)
)

;; Calls the chase rows with the rows from top to bottom. 
(defun chase (board)
  (chase-rows board '(3 4 5 6 7 8))
)

;; With a failsafe in case it's infinite. Chases, hits one more if not solved, and call another turn. 
(defun ai-turn (board count)
(if (> count 80) (format T "Big loopy")
    (let ((post-chase (chase (print-board board))))
    (if (are-lights-out post-chase)
       (format T "Computer wins!")
           (cond
            ((equal 0 (nth 6 post-chase))(ai-turn (toggle-multiple post-chase (select-group 3)) (+ count 1)))
            ((equal 0 (nth 8 post-chase))(ai-turn (toggle-multiple post-chase (select-group 1)) (+ count 1)))
            ((equal 0 (nth 7 post-chase))(ai-turn (toggle-multiple post-chase (select-group 2)) (+ count 1)))
            (t (ai-turn (toggle-multiple post-chase (select-group 2)) (+ count 1)))
            )
    )
)))

;; Starts the number guessing game.
(defun start-game ()
   (format T "Which light would you like to press (1-9)")
   (one-turn (generate-board)
              "Please enter a position (1-9)"
              1 ;; the count
   )
)

;; Starts the ai with a random baord
(defun start-game-ai ()
  (ai-turn (generate-board) 1))

;; function to test everything
(defun test-function ()
    (cond 
        ((not(equal 0 (toggle 1)))(princ "Toggle doesn't work"))
        ((not(equal '(0 0 1 0 0 0 0 0 0) (toggle-light '(0 0 0 0 0 0 0 0 0) 2)))(princ "Toggle Light doesn't work"))
        ((not(equal '(0 1 0 1 1 0 0 0 0) (toggle-multiple '(1 0 0 0 0 0 0 0 0) '(0 1 3 4))))(princ "Toggle Multiple doesn't work"))
        ((not(equal t (are-lights-out '(0 0 0 0 0 0 0 0 0))))(princ "lights out True doesn't work"))
        ((not(equal nil (are-lights-out '(0 0 0 0 0 1 0 0 0))))(princ "Lights out false doesn't work"))
        ((not(equal '(2 4 5 8) (select-group 6)))(princ "Select group doesn't work"))
        (t (princ "All tests are passed"))
    )
)
;; tests all of them above
;;(test-function)

;;(start-game)