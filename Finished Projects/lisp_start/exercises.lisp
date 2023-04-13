;; functions written by Linus Bendel-Stenzel

;; #1 (lastEl lst)
;; Gets last element in list
(defun lastEl (lst)
    ;; return element if null, else calls on cdr of list. 
    (if (null (cdr lst))
    (car lst)
    (lastEl (cdr lst)))
)

;; #2 (rev lst)
;; Add to end of list
;; helper function used in reversal of list.
(defun add-to-end (li el)
    (if (null li)
    (cons el nil)
    (cons (car li) (add-to-end (cdr li) el)))
)
;; reverse things in list
(defun rev (lst)
    (if (null (cdr lst))
    lst
    (add-to-end (rev (cdr lst)) (car lst)))
)

;; #3
;; Replaces var biggest if the next element is larger
(defun greatest-helper (li biggest)
    (if (null li)
    biggest
        (if (> (car li) biggest)
        (greatest-helper (cdr li) (car li))
        (greatest-helper (cdr li) biggest))
    )
)
;; Calls the recursive function assuming first is largest
(defun greatest (li)
    (greatest-helper (cdr li) (car li))   
)

;; #4
;; if element is 0, then don't cons anything
(defun rem-zeroes (li)
    (if (null li)
    nil
        (if (= (car li) 0)
        (rem-zeroes (cdr li))
        (cons (car li) (rem-zeroes (cdr li)))
        )
    )
)

;; #5
;; Okay so this code doesn't run. But I think it's close to the right answer.
;; I'm using x as a placeholder to mean that it isn't a leaf node. Then at each leaf node I cons the letter with nil. 
(cons (cons (cons 'a 'b) (cons 'c 'd)) (cons (cons 'e 'f) (cons 'g 'h)))
;; (((A . B) C . D) (E . F) G . H)