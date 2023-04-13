;; Linus Bendel-Stenzel

;; inserting (letter 1) into the end of list
;; Called when we found a new letter
(defun insert (in lst)
    (if (null lst)
    (list (cons in (cons '1 nil)))
    (cons (car lst) (insert in (cdr lst))))
)

;; checks which dictionary pair is less
(defun lessThan (li1 li2)
    (< (car (cdr li1)) (car (cdr li2)))
)

;; inserts one dictionary pair into list in sorted order
(defun insert_in_order (in lst)
    (if (null lst)
    (cons in nil)
        (if (lessThan in (car lst))
        (cons in lst)
        (cons (car lst) (insert_in_order in (cdr lst)))))
)

;; Inserts each pair of list into output in sorter order
(defun my_sort (lst)
    (if (null lst)
    nil
    (insert_in_order (car lst) (my_sort (cdr lst))))
)

;; Check if the letter already is in the list
(defun ifExists (in lst)
    (if (null lst)
    nil
        (if (eql in (car (car lst)))
        T
        (ifExists in (cdr lst))
        )
    )
)

;; Given a list (a, 3), increase the number by 1
(defun increaseByOne (lst)
    (cons (car lst) (cons (+ (car (cdr lst)) 1) nil))
)

;; if it's known that in is in the list, then increment it by one. 
(defun increment (in lst)
    (if (eql in (car (car lst)))
    (cons (increaseByOne (car lst)) (cdr lst))
    (cons (car lst) (increment in (cdr lst)))
    )
)

;; Record number of occurences, and output as dictionary pair. 
(defun occurences-helper (lst out)
    (if (null lst)
    out
        (if (ifExists (car lst) out)
        (occurences-helper (cdr lst) (increment (car lst) out))
        (occurences-helper (cdr lst) (insert (car lst) out)))
    )
)

;; Find number of occurences of each, and then sort it
(defun occurences (lst)
    (my_sort (occurences-helper lst '()))
)


(princ (occurences '(a b a b v c x z a s a a d d d d f d d d f)))